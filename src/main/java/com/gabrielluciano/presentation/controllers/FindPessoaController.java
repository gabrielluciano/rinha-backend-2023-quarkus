package com.gabrielluciano.presentation.controllers;

import com.gabrielluciano.core.entities.Pessoa;
import com.gabrielluciano.core.usecases.FindPessoaByUUIDUseCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.UUID;
import java.util.concurrent.CompletionStage;

import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestResponse;

@ApplicationScoped
@Path("/pessoas")
public class FindPessoaController {

    @Inject
    FindPessoaByUUIDUseCase findPessoaByUUIDUseCase;

    @GET
    @Path("/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<RestResponse<Pessoa>> findByUUID(@RestPath UUID uuid) {
        return findPessoaByUUIDUseCase.execute(uuid)
                .thenApply(p -> {
                    if (p == null) return RestResponse.notFound();
                    return RestResponse.status(RestResponse.Status.OK, p);
                });
    }
}
