package hitstpa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;

import com.leverage.util.InternalServerException;
import com.leverage.util.NotFoundException;
import com.leverage.util.ReferentialIntegrityException;

import hitstpa.model.EntitySafetyConstraint;
import hitstpa.model.EventSafetyConstraint;
import hitstpa.model.SafetyConstraint;

public class SafetyConstraintDao extends AbstractDao <SafetyConstraint>{

	protected SafetyConstraintDao(DataSource dataSource) {
		super(SafetyConstraint.class, dataSource);
		
		rowMapper = new RowMapper<SafetyConstraint>(){

			@Override
			public SafetyConstraint mapRow(ResultSet rs, int rowNum) throws SQLException {
				SafetyConstraint safetyConstraint;
				Integer id;
				EntitySafetyConstraint entitySafetyConstraint;
				EventSafetyConstraint eventSafetyConstraint;
				String name;
				String description;
				
				try
				{
					id = rs.getInt("id");
					name = rs.getString("name");
					description = rs.getString("description");
					Integer entitySafetyConstraintId = rs.getInt("individualAction");
					boolean entitySafetyConstraintIdWasNull = rs.wasNull();
					Integer eventSafetyConstraintId = rs.getInt("interaction");
					boolean eventSafetyConstraintIdWasNull = rs.wasNull();
					
					if(entitySafetyConstraintIdWasNull && eventSafetyConstraintIdWasNull)
					{
						throw new InternalServerException("Event " + id + 
								" is not associated with either an entity safety constraint or an event safety constraint");
					}
					else if(!entitySafetyConstraintIdWasNull && !eventSafetyConstraintIdWasNull)
					{
						throw new InternalServerException("Event " + id + 
								" is associated with both an entity safety constraint and an event safety constraint");
					}
					else if(!entitySafetyConstraintIdWasNull)
					{
						entitySafetyConstraint = new EntitySafetyConstraintDao(dataSource).get(entitySafetyConstraintId);
						safetyConstraint = new SafetyConstraint(id, entitySafetyConstraint, name, description);
					}
					else if(!eventSafetyConstraintIdWasNull)
					{
						eventSafetyConstraint = new EventSafetyConstraintDao(dataSource).get(eventSafetyConstraintId);
						safetyConstraint = new SafetyConstraint(id, eventSafetyConstraint, name, description);
					}
					else
					{
						throw new InternalServerException("Illogical data");
					}
				}
				catch(NotFoundException nfe)
				{
					logger.error("Database Fault", nfe);
					safetyConstraint = null;
				}
				catch(ReferentialIntegrityException rie)
				{
					logger.error("Database Fault", rie);
					safetyConstraint = null;
				}
				catch(InternalServerException ise)
				{
					logger.error("Database Fault", ise);
					safetyConstraint = null;
				}
				
				return safetyConstraint;
			}
			
		};
		
	}

}
