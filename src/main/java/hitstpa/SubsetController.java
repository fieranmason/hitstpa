package hitstpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hitstpa.dao.SubsetDao;
import hitstpa.model.Subset;

@RestController
public class SubsetController implements IController<Subset>{
	
	@Autowired
	protected SubsetDao subsetDao;
	
    @RequestMapping(value="/subset/{id}", method = RequestMethod.GET)
    public Subset get(@PathVariable(value="id") Integer id) throws Exception{
    	
    	Subset subset = subsetDao.get(id);
    	return subset;
    }
    
    @RequestMapping(value="/subset", method = RequestMethod.GET)
    public List<Subset> list() {
    	List<Subset> subsets = subsetDao.list();
    	return subsets;
    }

}
