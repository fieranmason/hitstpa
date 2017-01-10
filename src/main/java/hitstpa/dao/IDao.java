package hitstpa.dao;

import java.util.List;

import com.leverage.util.NotFoundException;
import com.leverage.util.ReferentialIntegrityException;

public interface IDao<T> {

	public T get(int id) throws NotFoundException, ReferentialIntegrityException;
	
	public List<T> list();
}
