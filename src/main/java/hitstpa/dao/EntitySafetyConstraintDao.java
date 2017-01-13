package hitstpa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;

import com.leverage.util.InternalServerException;
import com.leverage.util.NotFoundException;
import com.leverage.util.ReferentialIntegrityException;

import hitstpa.model.Entity;
import hitstpa.model.EntitySafetyConstraint;

public class EntitySafetyConstraintDao extends GenericDao <EntitySafetyConstraint>{

	protected EntitySafetyConstraintDao(DataSource dataSource) {
		super(EntitySafetyConstraint.class, dataSource);
		
		rowMapper = new RowMapper<EntitySafetyConstraint>(){

			@Override
			public EntitySafetyConstraint mapRow(ResultSet rs, int rowNum) throws SQLException {
				EntitySafetyConstraint entitySafetyConstraint;
				Integer id;
				EntityDao entityDao = new EntityDao(dataSource);
				Entity entity;
				
				try
				{
					id = rs.getInt("id");
					entity = entityDao.get(rs.getInt("id"));
					entitySafetyConstraint = new EntitySafetyConstraint(id, entity);
				}
				catch(NotFoundException nfe)
				{
					logger.error("Database Fault", nfe);
					entitySafetyConstraint = null;
				}
				catch(ReferentialIntegrityException rie)
				{
					logger.error("Database Fault", rie);
					entitySafetyConstraint = null;
				}
				catch(InternalServerException ise)
				{
					logger.error("Database Fault", ise);
					entitySafetyConstraint = null;
				}
				
				return entitySafetyConstraint;
			}
			
		};
		
	}

}
