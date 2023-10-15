package com.gabrielluciano.data.external.jpa.repositories;

import com.gabrielluciano.core.entities.Pessoa;
import com.gabrielluciano.core.repositories.PessoaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class PessoaRepositoryImpl implements PessoaRepository {

    private static final String COUNT_NATIVE_QUERY = "SELECT COUNT(*) FROM pessoas";
    private static final String SEARCH_NATIVE_QUERY = "SELECT * FROM pessoas WHERE search_trgm LIKE LOWER(:term) LIMIT 50";

    @Inject
    Mutiny.SessionFactory sf;

    @Override
    public CompletionStage<Pessoa> create(Pessoa pessoa) {
        return sf.withTransaction(session -> session.persist(pessoa))
                .replaceWith(() -> pessoa)
                .subscribeAsCompletionStage();
    }

    @Override
    public CompletionStage<Pessoa> findByUUID(UUID uuid) {
        return sf.withSession(session -> session.find(Pessoa.class, uuid))
                .subscribeAsCompletionStage();
    }

    @Override
    public CompletionStage<List<Pessoa>> searchByTerm(String term) {
        return sf.withSession(session -> session.createNativeQuery(SEARCH_NATIVE_QUERY, Pessoa.class)
                .setParameter("term", '%' + term + '%')
                .getResultList()
        ).subscribeAsCompletionStage();
    }

    @Override
    public CompletionStage<Long> count() {
        return sf.withStatelessSession(session -> session.createNativeQuery(COUNT_NATIVE_QUERY, Long.class)
                .getSingleResult()
        ).subscribeAsCompletionStage();
    }
}
