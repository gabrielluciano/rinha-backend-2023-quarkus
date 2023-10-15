package com.gabrielluciano.core.usecases;

import java.util.concurrent.CompletionStage;

import com.gabrielluciano.core.repositories.PessoaRepository;
import com.gabrielluciano.core.shared.UseCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CountPessoasUseCase implements UseCase<Void, CompletionStage<Long>> {

    @Inject
    PessoaRepository repository;

    @Override
    public CompletionStage<Long> execute(Void i) {
        return repository.count();
    }
}
