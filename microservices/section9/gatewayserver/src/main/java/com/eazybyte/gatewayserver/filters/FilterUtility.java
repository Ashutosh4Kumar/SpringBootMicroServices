package com.eazybyte.gatewayserver.filters;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Component
public class FilterUtility
{
    public static final String CORRELATION_ID_HEADER = "eazybank-correlation-id";

    public String getCorrelationId(HttpHeaders requestHeaders){

        System.out.println("Correlation Id is {}"+ requestHeaders.get(CORRELATION_ID_HEADER));
        if(requestHeaders.get(CORRELATION_ID_HEADER) != null){
            List<String> correlationIds = requestHeaders.get(CORRELATION_ID_HEADER);
            return correlationIds.stream().findFirst().get();
        }else
            return null;
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange,String value){

        return exchange.mutate().request(exchange.getRequest().mutate().header(CORRELATION_ID_HEADER, value).build()).build();
    }
}
