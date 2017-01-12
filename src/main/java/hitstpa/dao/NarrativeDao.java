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
		
		rowMapper = new NarrativeRowMapper(
						new IncidentGenerator(){
							public Incident getIncident(ResultSet rs)
							{
								Incident incident;
								try {
									incident = new IncidentDao(dataSource).get(rs.getInt("incident"));
								} catch (NotFoundException nfe) {
									logger.error("Database fault", nfe);
					        		incident = null;
								} catch (ReferentialIntegrityException rie) {
									logger.error("Database fault", rie);
					        		incident = null;
								} catch (InternalServerException ise) {
									logger.error("Database fault", ise);
					        		incident = null;
								} catch (SQLException sqle) {
									logger.error("Database fault", sqle);
					        		incident = null;
								}
		
								return incident;
							}
						}
					);
	}
	
	public NarrativeDao(DataSource dataSource, Incident incident)
	{
		super(Narrative.class, dataSource);
		
		rowMapper = new NarrativeRowMapper(
						new IncidentGenerator(){
							public Incident getIncident(ResultSet rs)
							{
								return incident;
							}
						});
	}
	
	private interface IncidentGenerator
	{
		public Incident getIncident(ResultSet rs) throws NotFoundException, ReferentialIntegrityException, InternalServerException, SQLException;
	}
	
	private class NarrativeRowMapper implements RowMapper<Narrative>
	{
		private IncidentGenerator incidentGenerator;
		
		public NarrativeRowMapper(IncidentGenerator incidentGenerator)
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
        		incident = incidentGenerator.getIncident(rs);
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
	}
}
