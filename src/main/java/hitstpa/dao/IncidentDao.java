package hitstpa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.leverage.util.InternalServerException;

import hitstpa.model.Incident;
import hitstpa.model.Narrative;

@Repository
public class IncidentDao extends AbstractDao<Incident>{
	
	public IncidentDao(DataSource dataSource) {
		
		super(Incident.class, dataSource);
		
		rowMapper = new RowMapper<Incident>() {
    		@Override
    		public Incident mapRow(ResultSet rs, int rowNum) throws SQLException{
	        	
	        	Incident incident;
	        	Integer id;
	        	String name;
	        	String description;
	        	String outcome;
	        	Date startTime;
	        	Date endTime;
	        	String vendorProductModel;
	        	List<Narrative> narratives;

        		id = rs.getInt("id");
        		name = rs.getString("name");
	        	description = rs.getString("description");
	        	outcome = rs.getString("outcome");
	        	startTime = rs.getDate("startTime");
	        	endTime = rs.getDate("endTime");
	        	vendorProductModel = rs.getString("vendorProductModel");
	        	incident = new Incident(id, name, description, outcome, startTime, endTime, vendorProductModel);
	        	try {
					narratives = new NarrativeDao(dataSource, incident).list();
					incident.setNarratives(narratives);
				} catch (InternalServerException ise) {
					logger.error("Database fault", ise);
					incident = null;
				}
	            return incident;
	        }
		};
		
	}
}
