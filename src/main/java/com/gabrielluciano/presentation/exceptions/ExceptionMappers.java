package com.gabrielluciano.presentation.exceptions;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolationException;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import com.gabrielluciano.data.external.jpa.constraints.NotNumber;

import java.lang.annotation.Annotation;
import java.util.concurrent.CompletionException;

@ApplicationScoped
public class ExceptionMappers {

    @ServerExceptionMapper
    public RestResponse<Void> handleCompletionException(CompletionException ex) {
        return RestResponse.status(422);
    }

    @ServerExceptionMapper
    public RestResponse<Void> handleConstraintViolationException(ConstraintViolationException ex) {
        int status = 422;
        for (var violation : ex.getConstraintViolations()) {
            Class<? extends Annotation> type = violation.getConstraintDescriptor().getAnnotation().annotationType();
            if (type.equals(NotNumber.class)) {
                status = 400;
            }
        }
        return RestResponse.status(status);
    }
}
