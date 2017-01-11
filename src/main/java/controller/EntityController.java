package controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leverage.util.IResource;
import com.leverage.util.InternalServerException;
import com.leverage.util.NotFoundException;
import com.leverage.util.ReferentialIntegrityException;

import hitstpa.Application;
import hitstpa.dao.EntityDao;
import hitstpa.model.Entity;

@RestController
public class EntityController implements IResource<Entity>{
	
	protected static Logger logger = Logger.getLogger(Application.class);
	
	@Autowired
	protected EntityDao entityDao;
	
    @RequestMapping(value="/subset/{id}", method = RequestMethod.GET)
    public Entity get(@PathVariable(value="id") Integer id) throws NotFoundException, InternalServerException{
    	
    	Entity entity;
    	
    	try
    	{
    		entity = entityDao.get(id);
    	}
    	catch(ReferentialIntegrityException rie)
    	{
    		logger.error("Database fault", rie);
    		throw new InternalServerException("Database fault");
    	}
    	
    	return entity;
    }
    
    @RequestMapping(value="/subset", method = RequestMethod.GET)
    public List<Entity> list() throws InternalServerException{
    	List<Entity> subsets = entityDao.list();
    	return subsets;
    }

}
