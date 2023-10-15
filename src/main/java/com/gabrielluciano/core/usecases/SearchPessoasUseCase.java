package com.gabrielluciano.core.usecases;

import com.gabrielluciano.core.entities.Pessoa;
import com.gabrielluciano.core.repositories.PessoaRepository;
import com.gabrielluciano.core.shared.UseCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class SearchPessoasUseCase implements UseCase<String, CompletionStage<List<Pessoa>>> {

    @Inject
    PessoaRepository repository;

    @Override
    public CompletionStage<List<Pessoa>> execute(String term) {
        return repository.searchByTerm(term);
    }
}
