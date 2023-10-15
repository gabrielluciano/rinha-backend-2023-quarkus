package com.gabrielluciano.core.shared;

public interface UseCase<I, O> {

    O execute(I inputs);
}
