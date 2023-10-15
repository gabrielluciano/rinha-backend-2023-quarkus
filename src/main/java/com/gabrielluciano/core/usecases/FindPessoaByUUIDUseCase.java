package com.gabrielluciano.core.usecases;

import com.gabrielluciano.core.entities.Pessoa;
import com.gabrielluciano.core.repositories.PessoaRepository;
import com.gabrielluciano.core.shared.UseCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class FindPessoaByUUIDUseCase implements UseCase<UUID, CompletionStage<Pessoa>> {

    @Inject
    PessoaRepository repository;

    @Override
    public CompletionStage<Pessoa> execute(UUID uuid) {
        return repository.findByUUID(uuid);
    }
}
