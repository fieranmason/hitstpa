package hitstpa.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.leverage.util.InternalServerException;
import com.leverage.util.NotFoundException;
import com.leverage.util.ReferentialIntegrityException;

public  class AbstractDao<T> implements IDao<T>{

	protected Class<T> modelClass;
	protected JdbcTemplate jdbcTemplate;
	protected String list = "SELECT * FROM ";
	protected String get1 = "SELECT * FROM ";
	protected String get2 = " WHERE id=?";
	protected String get;
	protected RowMapper<T> rowMapper;
	
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected AbstractDao(Class<T> modelClass, DataSource dataSource) {
		this.modelClass = modelClass;
		String table = this.modelClass.getSimpleName().toLowerCase();
		list = list.concat(table);
		get = get1.concat(table).concat(get2);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public T get(int id) throws NotFoundException, ReferentialIntegrityException, InternalServerException{
		List<T> list = jdbcTemplate.query(get, new Object[] {id}, rowMapper);
		
		if(list.contains(null))
		{
			throw new InternalServerException("Database fault");
		}
		if(list.size() > 1) {
			throw new ReferentialIntegrityException("Non-unique result found: " + this.modelClass.getSimpleName().toLowerCase());
		}
		else if(list.isEmpty()) {
			throw new NotFoundException(this.modelClass.getSimpleName().toLowerCase() + " " + id + " not found" );
		}
		
		return list.get(0);
	}

	@Override
	public List<T> list() throws InternalServerException{
		List<T> entities = jdbcTemplate.query(list, rowMapper);
		if(entities.contains(null))
		{
			throw new InternalServerException("unreferenced element or child");
		}
		return entities;
	}
}
