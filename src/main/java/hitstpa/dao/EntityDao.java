package hitstpa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.leverage.util.InternalServerException;
import com.leverage.util.NotFoundException;
import com.leverage.util.ReferentialIntegrityException;

import hitstpa.model.Entity;
import hitstpa.model.IndividualAction;
import hitstpa.model.Stereotype;

@Repository
public class EntityDao extends GenericDao<Entity>{
	
	public EntityDao(DataSource dataSource) {
		
		super(Entity.class, dataSource);
		
		rowMapper = new RowMapper<Entity>() {
    		@Override
    		public Entity mapRow(ResultSet rs, int rowNum) throws SQLException{
	        	
	        	Entity entity;
	        	
	        	try
	        	{
	        		//native
	        		Integer id = rs.getInt("id");
	        		Stereotype stereotype = new StereotypeDao(dataSource).get(rs.getInt("stereotype"));
	        		String name = rs.getString("name");
		        	String description = rs.getString("description");
		        	entity = new Entity(id, stereotype, name, description);
		        	IndividualActionDao individualActionDao = new IndividualActionDao(dataSource, entity);
		        	
		        	//reverse reference
		        	List<IndividualAction> individualActions = individualActionDao.filterList("entity", id);
		            entity.setIndividualActions(individualActions);
	        	}
	        	catch(ReferentialIntegrityException rie)
	        	{
	        		logger.error("Database fault", rie);
	        		entity = null;
	        	}
	        	catch(NotFoundException nfe)
	        	{
	        		logger.error("Database fault", nfe);
	        		entity = null;
	        	}
	            catch(InternalServerException ise)
	        	{
	            	logger.error("Database fault", ise);
	            	entity = null;
	        	}
	            return entity;
	        }
		};
		
	}
}
