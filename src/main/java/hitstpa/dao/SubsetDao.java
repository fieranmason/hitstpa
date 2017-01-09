package hitstpa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import hitstpa.model.Subset;

@Repository
public class SubsetDao extends AbstractDao<Subset>{
	
	public SubsetDao(DataSource dataSource) {
		
		super(Subset.class, dataSource);
		
		rowMapper = new RowMapper<Subset>() {
    		@Override
    		public Subset mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Integer id = rs.getInt("id");
	        	String name = rs.getString("name");
	            Subset subset = new Subset(id, name);
	            return subset;
	        }
		};
		
	}
}
