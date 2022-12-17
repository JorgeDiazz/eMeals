package com.jorgediaz.usecases.interfaces;

public abstract class UseCase<T, R> {
    public abstract R execute(T input);
}
