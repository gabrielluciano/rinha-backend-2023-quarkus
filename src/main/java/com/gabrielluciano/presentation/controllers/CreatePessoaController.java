package com.gabrielluciano.presentation.controllers;

import com.gabrielluciano.core.entities.Pessoa;
import com.gabrielluciano.core.usecases.CreatePessoaUseCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.concurrent.CompletionStage;

import org.jboss.resteasy.reactive.RestResponse;

@ApplicationScoped
@Path("/pessoas")
public class CreatePessoaController {

    @Inject
    CreatePessoaUseCase createPessoaUseCase;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<RestResponse<Pessoa>> create(@Valid Pessoa pessoa) {
        return createPessoaUseCase.execute(pessoa).thenApply(this::createResponse);
    }

    private RestResponse<Pessoa> createResponse(Pessoa p) {
        return RestResponse.ResponseBuilder.create(RestResponse.Status.CREATED, p)
                .header("Location", "/pessoas/" + p.getId())
                .build();
    }
}
