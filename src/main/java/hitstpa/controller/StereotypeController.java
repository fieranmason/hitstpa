package hitstpa.controller;

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
import hitstpa.dao.StereotypeDao;
import hitstpa.model.Stereotype;

@RestController
public class StereotypeController implements IResource<Stereotype>{
	
	protected static Logger logger = Logger.getLogger(Application.class);
	
	@Autowired
	protected StereotypeDao stereotypeDao;
	
    @RequestMapping(value="/stereotype/{id}", method = RequestMethod.GET)
    public Stereotype get(@PathVariable(value="id") Integer id) throws NotFoundException, InternalServerException{
    	
    	Stereotype stereotype;
    	
    	try
    	{
    		stereotype = stereotypeDao.get(id);
    	}
    	catch(ReferentialIntegrityException rie)
    	{
    		logger.error("Database fault", rie);
    		throw new InternalServerException("Database fault");
    	}
    	
    	return stereotype;
    }
    
    @RequestMapping(value="/stereotype", method = RequestMethod.GET)
    public List<Stereotype> list() throws InternalServerException{
    	List<Stereotype> stereotypes = stereotypeDao.list();
    	return stereotypes;
    }

}
