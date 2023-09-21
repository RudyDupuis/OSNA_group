package fr.eni.OSNA.dal;

import java.util.List;

public interface DAO<T> {
    void insert(T  t) throws Exception;

    void update(T t) throws Exception;

    void deleteById(int id) throws Exception;

    T selectById(int id) throws Exception;

    List<T> selectAll() throws Exception;
}
