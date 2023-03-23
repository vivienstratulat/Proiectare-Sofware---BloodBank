package DAO;

import java.util.List;

public interface BasicDAO<T,I> {
    void create(T t);
    T read(I i);
    void update(T t);
    void delete(T t);
    List<T> readAll();
}
