package hitstpa;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hitstpa.dao.ServiceDao;
import hitstpa.model.Service;

@RestController
public class ServiceController implements IServiceService<Service>{
	
	@Autowired
	protected ServiceDao serviceDao;
	
    @Override
	@RequestMapping(value="/service/{id}", method = RequestMethod.GET)
    public Service get(@PathVariable(value="id") Integer id) throws Exception{    	
    	Service service = serviceDao.get(id);
    	return service;
    }
	
    @Override
	@RequestMapping(value="/service", method = RequestMethod.GET)
    public List<Service> list() {
    	List<Service> subsets = serviceDao.list();
    	return subsets;
    }
    
    @RequestMapping(value="/service/filterBySubset/{id}", method = RequestMethod.GET)
    public List<Service> filterBySubset(@PathVariable(value="id") Integer id) {
    	List<Service> list = serviceDao.list();
    	return list.stream().filter(p->p.getSubsetId() == id).collect(Collectors.toList());
    }
}
