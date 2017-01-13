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
public class NarrativeDao extends GenericDao<Narrative>{
	
	public NarrativeDao()
	{
		super();
	}
	public NarrativeDao(DataSource dataSource) {
		
		super(Narrative.class, dataSource);
		
		rowMapper = new NarrativeRowMapper(new DaoGenerator<Incident, IncidentDao>(dataSource, Incident.class, IncidentDao.class));
	}
	
	public NarrativeDao(DataSource dataSource, Incident incident)
	{
		super(Narrative.class, dataSource);
		rowMapper = new NarrativeRowMapper(new ReferenceGenerator<Incident>(incident));
	}
	
	private class NarrativeRowMapper implements RowMapper<Narrative>
	{
		Generator<Incident> incidentGenerator;
		
		public NarrativeRowMapper(Generator<Incident> incidentGenerator)
		{
			this.incidentGenerator = incidentGenerator;
		}
		
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
        		incident = incidentGenerator.get(rs);
        		narrativePassage = rs.getString("narrative");
	        	reporter = rs.getString("reporter");
	        	narrative = new Narrative(id, incident, narrativePassage, reporter);
        	}
        	catch(ReferentialIntegrityException |
        		  NotFoundException |
        		  InternalServerException e){
            	logger.error("Database fault", e);
            	narrative = null;
        	}
        	
            return narrative;
        }
	}
}
