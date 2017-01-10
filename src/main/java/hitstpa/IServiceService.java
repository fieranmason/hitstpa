package hitstpa;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import hitstpa.model.Service;

public interface IServiceService<Service> extends IController<Service>{
	public List<Service> filterBySubset(Integer id);
}