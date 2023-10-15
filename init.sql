CREATE TABLE IF NOT EXISTS pessoas (
    id uuid NOT NULL,
    apelido VARCHAR(32) NOT NULL,
    nascimento CHAR(10) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    stack VARCHAR(1000),
    search_trgm TEXT GENERATED ALWAYS AS (
        LOWER(apelido || nome || stack)
    ) STORED
);

ALTER TABLE pessoas OWNER TO quarkus;

ALTER TABLE ONLY pessoas
    ADD CONSTRAINT pessoas_pkey PRIMARY KEY (id);

ALTER TABLE ONLY pessoas
    ADD CONSTRAINT uk_9mhl83yy7w1fwiaggfprv90qr UNIQUE (apelido);

CREATE EXTENSION PG_TRGM;
CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_search_tgrm ON pessoas USING GIST (search_trgm GIST_TRGM_OPS(SIGLEN=64));
