package hitstpa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.leverage.util.InternalServerException;
import com.leverage.util.NotFoundException;
import com.leverage.util.ReferentialIntegrityException;

import hitstpa.model.Incident;
import hitstpa.model.Narrative;

@Repository
public class NarrativeDao extends AbstractDao<Narrative>{
	
	public NarrativeDao(DataSource dataSource) {
		
		super(Narrative.class, dataSource);
		
		rowMapper = new RowMapper<Narrative>() {
    		@Override
    		public Narrative mapRow(ResultSet rs, int rowNum) throws SQLException{
    			Narrative narrative;
	        	Integer id;
	        	Incident incident;
	        	String narrativePassage;
	        	String reporter;
	        	
	        	try
	        	{
	        		id = rs.getInt("id");
	        		incident = new IncidentDao(dataSource).get(rs.getInt("incident"));
	        		narrativePassage = rs.getString("narrative");
		        	reporter = rs.getString("reporter");
		        	narrative = new Narrative(id, incident, narrativePassage, reporter);
	        	}
	        	catch(ReferentialIntegrityException rie)
	        	{
	        		logger.error("Database fault", rie);
	        		narrative = null;
	        	}
	        	catch(NotFoundException nfe)
	        	{
	        		logger.error("Database fault", nfe);
	        		narrative = null;
	        	}
	            catch(InternalServerException ise)
	        	{
	            	logger.error("Database fault", ise);
	            	narrative = null;
	        	}
	        	
	            return narrative;
	        }
		};
		
	}
}
