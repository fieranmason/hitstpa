package hitstpa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import hitstpa.model.Stereotype;


@Repository
public class StereotypeDao extends GenericDao<Stereotype>{
	
	public StereotypeDao(DataSource dataSource) {
		
		super(Stereotype.class, dataSource);
		
		rowMapper = new RowMapper<Stereotype>() {
    		@Override
    		public Stereotype mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Integer id = rs.getInt("id");
	        	String name = rs.getString("name");
	            String description = rs.getString("description");
	            return new Stereotype(id, name, description);
	        }
		};
		
	}
}
