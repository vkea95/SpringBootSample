package com.vkea.sbs.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class WebUtil {

    @Autowired
    RestTemplate restTemplate;

    public <T> T getRequest(URI uri, Class<T> responseType) {
//        ResponseEntity<T> result = restTemplate.getForEntity(uri, responseType);
        T result = restTemplate.getForObject(uri, responseType);
        return result;
    }

    public <T> T postRequest(URI uri, Object postBody, Class<T> responseType) {
//        ResponseEntity<T> result = restTemplate.getForEntity(uri, responseType);
        T result = restTemplate.postForObject(uri, postBody, responseType);
        return result;
    }
//    ref exception handling: http://www.baeldung.com/exception-handling-for-rest-with-spring
//    http://www.baeldung.com/global-error-handler-in-a-spring-rest-api
//restTemplate handling error http://springinpractice.com/2013/10/07/handling-json-error-object-responses-with-springs-resttemplate
}
