package hitstpa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import hitstpa.model.Service;

@Repository
public class ServiceDao extends AbstractDao<Service>{
	
	public ServiceDao(DataSource dataSource) {
		
		super(Service.class, dataSource);
		
		rowMapper = new RowMapper<Service>() {
    		@Override
    		public Service mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Integer id = rs.getInt("id");
	        	Integer subsetId = rs.getInt("subsetId");
	        	String section = rs.getString("section");
	        	String subsection = rs.getString("subsection");
	        	Integer dtkConceptId = rs.getInt("dtkConceptId");
	        	Integer code = rs.getInt("code");
	        	String descriptor = rs.getString("descriptor");
	            Service service = new Service(id, subsetId, section, subsection, dtkConceptId, code, descriptor);
	            return service;
	        }
		};
		
	}
}
