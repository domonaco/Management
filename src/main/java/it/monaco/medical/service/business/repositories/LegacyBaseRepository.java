package it.monaco.medical.service.business.repositories;

import org.springframework.stereotype.Repository;

@Repository
public interface LegacyBaseRepository<T> {

    abstract void deleteAll(Iterable iterable);
}
