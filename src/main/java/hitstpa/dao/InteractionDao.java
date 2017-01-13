package hitstpa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;

import com.leverage.util.InternalServerException;
import com.leverage.util.NotFoundException;
import com.leverage.util.ReferentialIntegrityException;

import hitstpa.model.EntityCouple;
import hitstpa.model.Interaction;

public class InteractionDao extends GenericDao <Interaction>{

	protected InteractionDao(DataSource dataSource) {
		super(Interaction.class, dataSource);
		
		rowMapper = new RowMapper<Interaction>(){

			@Override
			public Interaction mapRow(ResultSet rs, int rowNum) throws SQLException {
				Interaction interaction;
				Integer id;
				EntityCoupleDao entityCoupleDao = new EntityCoupleDao(dataSource);
				EntityCouple entityCouple;
				
				try
				{
					id = rs.getInt("id");
					entityCouple = entityCoupleDao.get(rs.getInt("id"));
					interaction = new Interaction(id, entityCouple);
				}
				catch(NotFoundException nfe)
				{
					logger.error("Database Fault", nfe);
					interaction = null;
				}
				catch(ReferentialIntegrityException rie)
				{
					logger.error("Database Fault", rie);
					interaction = null;
				}
				catch(InternalServerException ise)
				{
					logger.error("Database Fault", ise);
					interaction = null;
				}
				
				return interaction;
			}
			
		};
		
	}

}
