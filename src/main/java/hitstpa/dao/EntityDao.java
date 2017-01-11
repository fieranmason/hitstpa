package hitstpa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.leverage.util.InternalServerException;
import com.leverage.util.NotFoundException;
import com.leverage.util.ReferentialIntegrityException;

import hitstpa.model.Entity;
import hitstpa.model.Stereotype;

@Repository
public class EntityDao extends AbstractDao<Entity>{
	
	public EntityDao(DataSource dataSource) {
		
		super(Entity.class, dataSource);
		
		rowMapper = new RowMapper<Entity>() {
    		@Override
    		public Entity mapRow(ResultSet rs, int rowNum) throws SQLException{
	        	Integer id = rs.getInt("id");
	        	
	        	Entity entity;
	        	Stereotype stereotype;
	        	
	        	try
	        	{
	        		stereotype = new StereotypeDao(dataSource).get(rs.getInt("stereotype"));
	        		String name = rs.getString("name");
		        	String description = rs.getString("description");
		            entity = new Entity(id, stereotype, name, description);
	        	}
	        	catch(ReferentialIntegrityException rie)
	        	{
	        		stereotype = null;
	        		logger.error("Database fault", rie);
	        		entity = null;
	        	}
	        	catch(NotFoundException nfe)
	        	{
	        		stereotype = null;
	        		logger.error("Database fault", nfe);
	        		entity = null;
	        	}
	            catch(InternalServerException ise)
	        	{
	            	stereotype = null;
	            	logger.error("Database fault", ise);
	            	entity = null;
	        	}
	            return entity;
	        }
		};
		
	}
}
