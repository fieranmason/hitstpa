package hitstpa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;

import com.leverage.util.InternalServerException;
import com.leverage.util.NotFoundException;
import com.leverage.util.ReferentialIntegrityException;

import hitstpa.model.Entity;
import hitstpa.model.EntityCouple;
import hitstpa.model.Event;
import hitstpa.model.Incident;
import hitstpa.model.IndividualAction;
import hitstpa.model.Narrative;

public class IndividualActionDao extends GenericDao <IndividualAction>{
	
	public IndividualActionDao()
	{
		super();
	}
	
	public IndividualActionDao(DataSource dataSource){
		super(IndividualAction.class, dataSource);
		DaoGenerator<Entity, EntityDao> generator = 
				new DaoGenerator<Entity, EntityDao>(dataSource, Entity.class, EntityDao.class);
		rowMapper = new IndividualActionRowMapper(dataSource, generator);
	}
	
	public IndividualActionDao(DataSource dataSource, Entity entity){
		super(IndividualAction.class, dataSource);
		ReferenceGenerator<Entity> generator = new ReferenceGenerator<Entity>(entity);
		rowMapper = new IndividualActionRowMapper(dataSource, generator);
	}
	
	private class IndividualActionRowMapper implements RowMapper<IndividualAction>{
		
		Generator<Entity> entityGenerator;
		DataSource dataSource;
		
		public IndividualActionRowMapper(DataSource dataSource, Generator<Entity> entityGenerator)
		{
			this.dataSource = dataSource;
			this.entityGenerator = entityGenerator;
		}
		
		@Override
		public IndividualAction mapRow(ResultSet rs, int rowNum) throws SQLException {
			Entity entity;
			Integer id;
			IndividualAction individualAction;
			
			try
			{
				id = rs.getInt("id");
				entity = entityGenerator.get(rs);
				individualAction = new IndividualAction(id, entity);
				
				//reverse reference
	        	List<Event> events = new EventDao(dataSource, individualAction).filterList("individualAction", id);
	        	if(events.size() > 1)
	        	{
	        		logger.error("Database fault", new ReferentialIntegrityException("Too many events for individual action: " + id));
	        	}
	        	else if(events.size() < 1)
	        	{
	        		logger.error("Database fault", new ReferentialIntegrityException("Not enough events for individual action: " + id));
	        	}
	        	
	            individualAction.setEvent(events.iterator().next());
			}
			catch(NotFoundException nfe)
			{
				logger.error("Database Fault", nfe);
				individualAction = null;
			}
			catch(ReferentialIntegrityException rie)
			{
				logger.error("Database Fault", rie);
				individualAction = null;
			}
			catch(InternalServerException ise)
			{
				logger.error("Database Fault", ise);
				individualAction = null;
			}
			
			return individualAction;
		}
	}
}
