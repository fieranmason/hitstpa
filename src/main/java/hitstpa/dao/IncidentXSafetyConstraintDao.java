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
import hitstpa.model.Incident;
import hitstpa.model.IncidentXSafetyConstraint;
import hitstpa.model.SafetyConstraint;

public class IncidentXSafetyConstraintDao extends AbstractDao <IncidentXSafetyConstraint>{

	protected IncidentXSafetyConstraintDao(DataSource dataSource) {
		super(IncidentXSafetyConstraint.class, dataSource);
		
		rowMapper = new RowMapper<IncidentXSafetyConstraint>(){

			@Override
			public IncidentXSafetyConstraint mapRow(ResultSet rs, int rowNum) throws SQLException {
				IncidentXSafetyConstraint incidentXSafetyConstraint;
				
				Integer id;
				Incident incident; 
				SafetyConstraint safetyConstraint;
				
				try
				{
					id = rs.getInt("id");
					incident = new IncidentDao(dataSource).get(rs.getInt("incident"));
					safetyConstraint = new SafetyConstraintDao(dataSource).get(rs.getInt("safetyConstraint"));
					incidentXSafetyConstraint = new IncidentXSafetyConstraint(id, safetyConstraint, incident);
				}
				catch(NotFoundException nfe)
				{
					logger.error("Database Fault", nfe);
					incidentXSafetyConstraint = null;
				}
				catch(ReferentialIntegrityException rie)
				{
					logger.error("Database Fault", rie);
					incidentXSafetyConstraint = null;
				}
				catch(InternalServerException ise)
				{
					logger.error("Database Fault", ise);
					incidentXSafetyConstraint = null;
				}
				
				return incidentXSafetyConstraint;
			}
			
		};
		
	}

}
