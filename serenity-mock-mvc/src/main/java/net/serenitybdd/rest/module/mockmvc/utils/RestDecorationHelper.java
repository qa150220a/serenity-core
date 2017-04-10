package net.serenitybdd.rest.module.mockmvc.utils;

import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.internal.ResponseSpecificationImpl;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import net.serenitybdd.rest.module.mockmvc.decorators.ResponseSpecificationDecorated;
import net.serenitybdd.rest.module.mockmvc.decorators.request.RequestSpecificationDecorated;

/**
 * User: YamStranger
 * Date: 4/15/16
 * Time: 8:08 AM
 */
public class RestDecorationHelper {
    public static RequestSpecification decorate(final RequestSpecification specification) {
        if (specification instanceof RequestSpecificationDecorated) {
            return specification;
        } else if (specification instanceof RequestSpecificationImpl) {
            RequestSpecificationDecorated decorated = RestSpecificationFactory.getInstrumentedRequestSpecification((RequestSpecificationImpl) specification);
            return decorated;
        } else {
            throw new IllegalArgumentException("Can not be used custom Request Specification Implementation");
        }
    }

    public static ResponseSpecification decorate(final ResponseSpecification specification) {
        if (specification instanceof ResponseSpecificationDecorated) {
            return specification;
        } else if (specification instanceof ResponseSpecificationImpl) {
            return RestSpecificationFactory.getInstrumentedResponseSpecification((ResponseSpecificationImpl) specification);
        } else {
            throw new IllegalArgumentException("Can not be used custom Response Specification Implementation");
        }
    }
}
