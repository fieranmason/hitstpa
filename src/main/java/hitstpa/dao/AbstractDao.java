package hitstpa.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.leverage.util.NotFoundException;
import com.leverage.util.ReferentialIntegrityException;

public  class AbstractDao<T> implements IDao<T>{

	protected Class<T> modelClass;
	protected JdbcTemplate jdbcTemplate;
	protected String list = "SELECT * FROM ";
	protected String get1 = "SELECT * FROM ";
	protected String get2 = " WHERE id=?";
	protected String get3 = " WHERE code=?";
	protected String get;
	protected String getByCode;
	protected RowMapper<T> rowMapper;
	
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Value("${spring.datasource.username}")
	private String DB_USERNAME;
	
	protected AbstractDao(Class<T> modelClass, DataSource dataSource) {
		this.modelClass = modelClass;
		String table = this.modelClass.getSimpleName().toLowerCase();
		list = list.concat(table);
		get = get1.concat(table).concat(get2);
		getByCode = get1.concat(table).concat(get3);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public T get(int id) throws NotFoundException, ReferentialIntegrityException{
		List<T> list = jdbcTemplate.query(get, new Object[] {id}, rowMapper);
		
		if(list.size() > 1) {
			throw new ReferentialIntegrityException("Non-unique result found: " + this.modelClass.getSimpleName().toLowerCase());
		}
		else if(list.isEmpty()) {
			throw new NotFoundException(this.modelClass.getSimpleName().toLowerCase() + " " + id + " not found" );
		}
		
		return list.get(0);
	}

	@Override
	public List<T> list() {
		return jdbcTemplate.query(list, rowMapper);
	}
}
