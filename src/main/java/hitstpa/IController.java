package hitstpa;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import hitstpa.model.Service;

public interface IController<T> {

	public T get(Integer id) throws Exception;

	public List<T> list();

}