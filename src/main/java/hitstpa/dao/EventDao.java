package hitstpa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;

import com.leverage.util.InternalServerException;
import com.leverage.util.NotFoundException;
import com.leverage.util.ReferentialIntegrityException;

import hitstpa.model.Event;
import hitstpa.model.IndividualAction;
import hitstpa.model.Interaction;

public class EventDao extends GenericDao <Event>{

	protected EventDao(DataSource dataSource) {
		super(Event.class, dataSource);
		
		rowMapper = new RowMapper<Event>(){

			@Override
			public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
				Event event;
				Integer id;
				IndividualAction individualAction;
				Interaction interaction;
				String name;
				String description;
				
				try
				{
					id = rs.getInt("id");
					name = rs.getString("name");
					description = rs.getString("description");
					Integer individualActionId = rs.getInt("individualAction");
					boolean individualActionIdWasNull = rs.wasNull();
					Integer interactionId = rs.getInt("interaction");
					boolean interactionIdWasNull = rs.wasNull();
					
					if(individualActionIdWasNull && interactionIdWasNull)
					{
						throw new InternalServerException("Event " + id + 
								" is not associated with either an individual action or an interaction");
					}
					else if(!individualActionIdWasNull && !interactionIdWasNull)
					{
						throw new InternalServerException("Event " + id + 
								" is associated with both an individual action and an interaction");
					}
					else if(!individualActionIdWasNull)
					{
						individualAction = new IndividualActionDao(dataSource).get(individualActionId);
						event = new Event(id, individualAction, name, description);
					}
					else if(!interactionIdWasNull)
					{
						interaction = new InteractionDao(dataSource).get(interactionId);
						event = new Event(id, interaction, name, description);
					}
					else
					{
						throw new InternalServerException("Illogical data");
					}
				}
				catch(NotFoundException nfe)
				{
					logger.error("Database Fault", nfe);
					event = null;
				}
				catch(ReferentialIntegrityException rie)
				{
					logger.error("Database Fault", rie);
					event = null;
				}
				catch(InternalServerException ise)
				{
					logger.error("Database Fault", ise);
					event = null;
				}
				
				return event;
			}
			
		};
		
	}

}
