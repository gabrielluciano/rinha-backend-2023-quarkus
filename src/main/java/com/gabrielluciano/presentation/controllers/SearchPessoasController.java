package com.gabrielluciano.presentation.controllers;

import com.gabrielluciano.core.entities.Pessoa;
import com.gabrielluciano.core.usecases.SearchPessoasUseCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestQuery;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
@Path("/pessoas")
public class SearchPessoasController {

    @Inject
    SearchPessoasUseCase searchPessoasUseCase;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<RestResponse<List<Pessoa>>> searchByTerm(@RestQuery String t) {
        if (t == null) throw new BadRequestException();
        return searchPessoasUseCase.execute(t).thenApply(RestResponse::ok);
    }
}
