package hitstpa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;

import com.leverage.util.InternalServerException;
import com.leverage.util.NotFoundException;
import com.leverage.util.ReferentialIntegrityException;

import hitstpa.model.Entity;
import hitstpa.model.EntityCouple;

public class EntityCoupleDao extends GenericDao <EntityCouple>{

	protected EntityCoupleDao(DataSource dataSource) {
		super(EntityCouple.class, dataSource);
		
		rowMapper = new RowMapper<EntityCouple>(){

			@Override
			public EntityCouple mapRow(ResultSet rs, int rowNum) throws SQLException {
				EntityCouple entityCouple;
				
				Integer id;
				Entity entityA; 
				Entity entityB; 
				EntityDao entityDao = new EntityDao(dataSource);
				
				try
				{
					id = rs.getInt("id");
					entityA = entityDao.get(rs.getInt("entityA"));
					entityB = entityDao.get(rs.getInt("entityB"));
					entityCouple = new EntityCouple(id, entityA, entityB);
				}
				catch(NotFoundException nfe)
				{
					logger.error("Database Fault", nfe);
					entityCouple = null;
				}
				catch(ReferentialIntegrityException rie)
				{
					logger.error("Database Fault", rie);
					entityCouple = null;
				}
				catch(InternalServerException ise)
				{
					logger.error("Database Fault", ise);
					entityCouple = null;
				}
				
				return entityCouple;
			}
			
		};
		
	}

}
