package hitstpa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;

import com.leverage.util.InternalServerException;
import com.leverage.util.NotFoundException;
import com.leverage.util.ReferentialIntegrityException;

import hitstpa.model.Event;
import hitstpa.model.EventSafetyConstraint;

public class EventSafetyConstraintDao extends GenericDao <EventSafetyConstraint>{

	protected EventSafetyConstraintDao(DataSource dataSource) {
		super(EventSafetyConstraint.class, dataSource);
		
		rowMapper = new RowMapper<EventSafetyConstraint>(){

			@Override
			public EventSafetyConstraint mapRow(ResultSet rs, int rowNum) throws SQLException {
				EventSafetyConstraint eventSafetyConstraint;
				Integer id;
				EventDao eventDao = new EventDao(dataSource);
				Event event;
				
				try
				{
					id = rs.getInt("id");
					event = eventDao.get(rs.getInt("id"));
					eventSafetyConstraint = new EventSafetyConstraint(id, event);
				}
				catch(NotFoundException nfe)
				{
					logger.error("Database Fault", nfe);
					eventSafetyConstraint = null;
				}
				catch(ReferentialIntegrityException rie)
				{
					logger.error("Database Fault", rie);
					eventSafetyConstraint = null;
				}
				catch(InternalServerException ise)
				{
					logger.error("Database Fault", ise);
					eventSafetyConstraint = null;
				}
				
				return eventSafetyConstraint;
			}
			
		};
		
	}

}
