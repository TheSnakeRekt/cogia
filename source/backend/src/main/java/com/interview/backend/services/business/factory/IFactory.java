package com.interview.backend.services.business.factory;

public interface IFactory<T, P> {
    T create(P p);
}
