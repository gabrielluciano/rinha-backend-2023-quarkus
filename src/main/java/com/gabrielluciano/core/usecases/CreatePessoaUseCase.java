package com.gabrielluciano.core.usecases;

import java.util.concurrent.CompletionStage;

import com.gabrielluciano.core.entities.Pessoa;
import com.gabrielluciano.core.repositories.PessoaRepository;
import com.gabrielluciano.core.shared.UseCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreatePessoaUseCase implements UseCase<Pessoa, CompletionStage<Pessoa>> {

    @Inject
    PessoaRepository repository;

    @Override
    public CompletionStage<Pessoa> execute(Pessoa pessoa) {
        return repository.create(pessoa);
    }
}
