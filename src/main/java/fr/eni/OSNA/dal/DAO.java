package fr.eni.OSNA.dal;

import java.util.List;

public interface DAO<T, I> {
	
	public abstract void insert(T  t) throws Exception;

	public abstract void update(T t) throws Exception;

	public abstract void deleteById(I id) throws Exception;

	public abstract T selectById(I id) throws Exception;

	public abstract List<T> selectAll() throws Exception;
}
