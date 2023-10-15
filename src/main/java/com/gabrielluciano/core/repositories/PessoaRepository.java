package com.gabrielluciano.core.repositories;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

import com.gabrielluciano.core.entities.Pessoa;

public interface PessoaRepository {

    CompletionStage<Pessoa> create(Pessoa pessoa);

    CompletionStage<Pessoa> findByUUID(UUID uuid);

    CompletionStage<List<Pessoa>> searchByTerm(String term);

    CompletionStage<Long> count();
}
