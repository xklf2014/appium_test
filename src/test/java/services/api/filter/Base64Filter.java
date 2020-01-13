package services.api.filter;

import io.restassured.builder.ResponseBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.util.Base64;

public class Base64Filter implements Filter {
    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification, FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext) {
        Response responseOrigin = filterContext.next(filterableRequestSpecification,filterableResponseSpecification);
        ResponseBuilder responseBuilder=new ResponseBuilder().clone(responseOrigin);
        String decode=new String(
                Base64.getDecoder().decode(
                        responseOrigin.body().asString().trim()
                )
        );
        responseBuilder.setBody(decode);
        return responseBuilder.build();
    }
}
