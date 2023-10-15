package com.gabrielluciano.presentation.controllers;

import com.gabrielluciano.core.usecases.CountPessoasUseCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.concurrent.CompletionStage;

import org.jboss.resteasy.reactive.RestResponse;

@ApplicationScoped
@Path("/contagem-pessoas")
public class CountPessoasController {

    @Inject
    CountPessoasUseCase countPessoasUseCase;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public CompletionStage<RestResponse<Long>> countPessoas() {
        return countPessoasUseCase.execute(null).thenApply(RestResponse::ok);
    }
}
