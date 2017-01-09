package hitstpa.dao;

import java.util.List;

public interface IDao<T> {

	public T get(int id) throws Exception;
	
	public T getByCode(int code) throws Exception;
	
	public List<T> list();
}
