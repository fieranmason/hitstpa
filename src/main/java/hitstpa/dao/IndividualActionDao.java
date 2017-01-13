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
import hitstpa.model.IndividualAction;

public class IndividualActionDao extends GenericDao <IndividualAction>{

	protected IndividualActionDao(DataSource dataSource) {
		super(IndividualAction.class, dataSource);
		
		rowMapper = new RowMapper<IndividualAction>(){

			@Override
			public IndividualAction mapRow(ResultSet rs, int rowNum) throws SQLException {
				Entity entity;
				Integer id;
				IndividualAction individualAction;
				EntityDao entityDao = new EntityDao(dataSource);
				
				try
				{
					id = rs.getInt("id");
					entity = entityDao.get(rs.getInt("entity"));
					individualAction = new IndividualAction(id, entity);
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
			
		};
		
	}

}
