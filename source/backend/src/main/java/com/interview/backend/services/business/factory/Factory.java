package com.interview.backend.services.business.factory;

public abstract class Factory<T, P> implements IFactory<T, P> {

    public abstract T create(P p);
}
