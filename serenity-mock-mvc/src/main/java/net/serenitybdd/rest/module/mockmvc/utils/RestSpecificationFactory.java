package net.serenitybdd.rest.module.mockmvc.utils;

import io.restassured.filter.Filter;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.internal.ResponseSpecificationImpl;
import io.restassured.internal.filter.SendRequestFilter;
import io.restassured.specification.*;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.serenitybdd.rest.module.mockmvc.SerenityRest;
import net.serenitybdd.rest.module.mockmvc.decorators.ResponseSpecificationDecorated;
import net.serenitybdd.rest.module.mockmvc.decorators.request.RequestSpecificationDecorated;
import net.serenitybdd.rest.module.mockmvc.filters.FieldsRecordingFilter;
import net.serenitybdd.rest.module.mockmvc.filters.UpdatingContextFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static io.restassured.filter.log.LogDetail.*;
import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;


public class RestSpecificationFactory {

    private static final Logger log = LoggerFactory.getLogger(RestSpecificationFactory.class);

    private static Constructor<?> requestSpecificationDecoratedConstructor;

    private static Constructor<?> responseSpecificationDecoratedConstructor;

    static {
        final Class<?>  requestSpecificationDecoratedClass = new ByteBuddy()
                .subclass(RequestSpecificationDecorated.class)
                .method(isDeclaredBy(RequestSpecification.class).or(isDeclaredBy(RequestSenderOptions.class)).or(isDeclaredBy(FilterableRequestSpecification.class)))
                .intercept(MethodDelegation.toField("core"))
                .make()
                .load(SerenityRest.class.getClassLoader())
                .getLoaded();
        final Class<?> responseSpecificationDecoratedClass = new ByteBuddy()
                .subclass(ResponseSpecificationDecorated.class)
                .method(isDeclaredBy(ResponseSpecification.class).or(isDeclaredBy(RequestSenderOptions.class)).or(isDeclaredBy(FilterableResponseSpecification.class)))
                .intercept(MethodDelegation.toField("core"))
                .make()
                .load(SerenityRest.class.getClassLoader())
                .getLoaded();
        try {
            requestSpecificationDecoratedConstructor = requestSpecificationDecoratedClass.getConstructor(RequestSpecificationImpl.class);
        } catch (NoSuchMethodException e) {
            log.error("Cannot found constructor for RequestSpecificationDecorated ",e);
        }

        try {
            responseSpecificationDecoratedConstructor = responseSpecificationDecoratedClass.getConstructor(ResponseSpecificationImpl.class);
        } catch (NoSuchMethodException e) {
            log.error("Cannot found constructor for ResponseSpecificationDecorated ",e);
        }
    }

    public static RequestSpecificationDecorated getInstrumentedRequestSpecification(RequestSpecificationImpl delegate) {
        RequestSpecificationDecorated instrumentedResponse = null;
        try {
            instrumentedResponse = (RequestSpecificationDecorated) requestSpecificationDecoratedConstructor.newInstance((delegate));
            final List<Filter> filters = new LinkedList<>();
            for (final LogDetail logDetail : Arrays.asList(HEADERS, COOKIES, BODY, PARAMS, METHOD, URI)) {
                filters.add(new FieldsRecordingFilter(true, logDetail));
            }
            if (RestExecutionHelper.restCallsAreEnabled()) {
                filters.add(new UpdatingContextFilter(SendRequestFilter.class));
            }
            instrumentedResponse.filters(filters);
        } catch(InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            log.error("Cannot instrument RequestSpecificationImpl ", ex);
        }
        return instrumentedResponse;
    }

    public static ResponseSpecificationDecorated getInstrumentedResponseSpecification(ResponseSpecificationImpl delegate) {
        ResponseSpecificationDecorated instrumentedResponseSpec = null;
        try {
            instrumentedResponseSpec = (ResponseSpecificationDecorated) responseSpecificationDecoratedConstructor.newInstance(delegate);
        } catch(InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            log.error("Cannot instrument ResponseSpecificationDecorated ", ex);
        }
        return instrumentedResponseSpec;
    }
}
