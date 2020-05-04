package vbx.com.ua.dao;

import java.util.Optional;

public interface DAO<T, I> {

    void create(T t);

    Optional<T> read(I i);

    Iterable<T> readAll();

    void update(I i);

    void delete(I i);


}
