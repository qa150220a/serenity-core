package net.serenitybdd.rest.module.mockmvc.instantiation

import io.restassured.RestAssured
import io.restassured.specification.FilterableRequestSpecification
import net.serenitybdd.rest.module.mockmvc.decorators.request.RequestSpecificationDecorated
import net.serenitybdd.rest.module.mockmvc.decorators.ResponseSpecificationDecorated
import net.serenitybdd.rest.module.mockmvc.rules.RestConfigurationAction
import net.serenitybdd.rest.module.mockmvc.rules.RestConfigurationRule
import org.junit.Rule
import spock.lang.Specification

import static net.serenitybdd.rest.module.mockmvc.SerenityRest.*


/**
 * User: YamStranger
 * Date: 3/14/16
 * Time: 9:57 AM
 */
class WhenInstantiationRequestWithGivenWord extends Specification {

    @Rule
    def RestConfigurationRule rule = new RestConfigurationRule(new RestConfigurationAction() {
        @Override
        void apply() {
            reset()
        }
    },)

    def "should return wrapped request and response if used given method"() {
        given: "provided implementation of Rest Assurance"
        when: "creating new request"
            def request = given();
            def response = request.response();
        then: "created request and response should be decorated"
            request instanceof RequestSpecificationDecorated
            response instanceof ResponseSpecificationDecorated
    }

    def "should return wrapped request and response if they initialised separately"() {
        given: "initialised Request"
            def request = RestAssured.given()
            request = request.proxy(10)
        when: "creating new request"
            def generated = given(request)
        then: "created response should be decorated"
            generated instanceof RequestSpecificationDecorated
        and: "parameters should be merged"
            ((FilterableRequestSpecification) generated).getProxySpecification().getPort() == 10
    }
}
