package net.serenitybdd.rest.module.mockmvc.stubs;

import io.restassured.function.RestAssuredFunction;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.*;
import org.hamcrest.Matcher;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by john on 23/07/2015.
 */
public class ResponseSpecificationStub implements ResponseSpecification {
    @Override
    public ResponseSpecification content(final Matcher<?> matcher, final Matcher<?>... additionalMatchers) {
        return this;
    }

    @Override
    public ResponseSpecification content(final List<Argument> arguments, final Matcher matcher, final Object... additionalKeyMatcherPairs) {
        return this;
    }

    @Override
    public Response validate(final Response response) {
        return new ResponseStub();
    }

    @Override
    public ResponseSpecification content(final String key, final Matcher<?> matcher, final Object... additionalKeyMatcherPairs) {
        return this;
    }

    @Override
    public ResponseSpecification time(Matcher<Long> matcher) {
        return this;
    }

    @Override
    public ResponseSpecification time(Matcher<Long> matcher, TimeUnit timeUnit) {
        return this;
    }

    @Override
    public ResponseSpecification body(final String key, final List<Argument> arguments, final Matcher matcher, final Object... additionalKeyMatcherPairs) {
        return this;
    }

    @Override
    public ResponseSpecification body(final List<Argument> arguments, final Matcher matcher, final Object... additionalKeyMatcherPairs) {
        return this;
    }

    @Override
    public ResponseSpecification statusCode(final Matcher<? super Integer> expectedStatusCode) {
        return this;
    }

    @Override
    public ResponseSpecification statusCode(final int expectedStatusCode) {
        return this;
    }

    @Override
    public ResponseSpecification statusLine(final Matcher<? super String> expectedStatusLine) {
        return this;
    }

    @Override
    public ResponseSpecification statusLine(final String expectedStatusLine) {
        return this;
    }

    @Override
    public ResponseSpecification headers(final Map<String, ?> expectedHeaders) {
        return this;
    }

    @Override
    public ResponseSpecification headers(final String firstExpectedHeaderName, final Object firstExpectedHeaderValue, final Object... expectedHeaders) {
        return this;
    }

    @Override
    public ResponseSpecification header(final String headerName, final Matcher<?> expectedValueMatcher) {
        return this;
    }

    @Override
    public <T> ResponseSpecification header(String s, RestAssuredFunction<String, T> restAssuredFunction, Matcher<? super T> matcher) {
        return this;
    }

    @Override
    public ResponseSpecification header(final String headerName, final String expectedValue) {
        return this;
    }

    @Override
    public ResponseSpecification cookies(final Map<String, ?> expectedCookies) {
        return this;
    }

    @Override
    public ResponseSpecification cookie(final String cookieName) {
        return this;
    }

    @Override
    public ResponseSpecification cookies(final String firstExpectedCookieName, final Object firstExpectedCookieValue, final Object... expectedCookieNameValuePairs) {
        return this;
    }

    @Override
    public ResponseSpecification cookie(final String cookieName, final Matcher<?> expectedValueMatcher) {
        return this;
    }

    @Override
    public ResponseSpecification cookie(final String cookieName, final Object expectedValue) {
        return this;
    }

    @Override
    public ResponseLogSpecification log() {
        return new ResponseLogSpecificationStub();
    }

    @Override
    public ResponseSpecification rootPath(final String rootPath) {
        return this;
    }

    @Override
    public ResponseSpecification rootPath(final String rootPath, final List<Argument> arguments) {
        return this;
    }

    @Override
    public ResponseSpecification root(final String rootPath, final List<Argument> arguments) {
        return this;
    }

    @Override
    public ResponseSpecification root(final String rootPath) {
        return this;
    }

    @Override
    public ResponseSpecification noRoot() {
        return this;
    }

    @Override
    public ResponseSpecification noRootPath() {
        return this;
    }

    @Override
    public ResponseSpecification appendRoot(final String pathToAppend) {
        return this;
    }

    @Override
    public ResponseSpecification appendRoot(final String pathToAppend, final List<Argument> arguments) {
        return this;
    }

    @Override
    public ResponseSpecification detachRoot(final String pathToDetach) {
        return this;
    }

    @Override
    public ResponseSpecification contentType(final ContentType contentType) {
        return this;
    }

    @Override
    public ResponseSpecification contentType(final String contentType) {
        return this;
    }

    @Override
    public ResponseSpecification contentType(final Matcher<? super String> contentType) {
        return this;
    }

    @Override
    public ResponseSpecification body(final Matcher<?> matcher, final Matcher<?>... additionalMatchers) {
        return this;
    }

    @Override
    public ResponseSpecification body(final String path, final Matcher<?> matcher, final Object... additionalKeyMatcherPairs) {
        return this;
    }

    @Override
    public ResponseSpecification content(final String path, final List<Argument> arguments, final Matcher matcher, final Object... additionalKeyMatcherPairs) {
        return this;
    }

    @Override
    public RequestSender when() {
       return (RequestSender)this;
    }

    @Override
    public RequestSpecification given() {
        return new RequestSpecificationStub();
    }

    @Override
    public ResponseSpecification that() {
        return this;
    }

    @Override
    public RequestSpecification request() {
        return new RequestSpecificationStub();
    }

    @Override
    public ResponseSpecification response() {
        return this;
    }

    @Override
    public ResponseSpecification and() {
        return this;
    }

    @Override
    public RequestSpecification with() {
        return new RequestSpecificationStub();
    }

    @Override
    public ResponseSpecification then() {
        return this;
    }

    @Override
    public ResponseSpecification expect() {
        return this;
    }

    @Override
    public ResponseSpecification spec(final ResponseSpecification responseSpecificationToMerge) {
        return this;
    }

    @Override
    public ResponseSpecification specification(final ResponseSpecification responseSpecificationToMerge) {
        return this;
    }

    @Override
    public ResponseSpecification parser(final String contentType, final Parser parser) {
        return this;
    }

    @Override
    public ResponseSpecification defaultParser(final Parser parser) {
        return this;
    }

}
