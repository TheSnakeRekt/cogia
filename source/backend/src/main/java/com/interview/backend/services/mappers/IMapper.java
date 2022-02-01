package com.interview.backend.services.mappers;


public interface IMapper<T, P> {
    T fromDto(P p);

    P toDto(T t);
}
