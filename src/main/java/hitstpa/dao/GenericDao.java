package hitstpa.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.leverage.util.InternalServerException;
import com.leverage.util.NotFoundException;
import com.leverage.util.ReferentialIntegrityException;

public  class GenericDao<T> implements IDao<T>{

	protected Class<T> modelClass;
	protected JdbcTemplate jdbcTemplate;
	protected String list = "SELECT * FROM ";
	protected String get1 = "SELECT * FROM ";
	protected String get2 = " WHERE id=?";
	protected String filter;
	protected String get;
	protected RowMapper<T> rowMapper;
	
    protected Logger logger = Logger.getLogger(this.getClass());

    public GenericDao(){};
    
	protected GenericDao(Class<T> modelClass, DataSource dataSource){
		this.modelClass = modelClass;
		String table = this.modelClass.getSimpleName();
		list = list.concat(table);
		get = get1.concat(table).concat(get2);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public T get(int id) throws NotFoundException, 
								ReferentialIntegrityException, 
								InternalServerException{
		
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
		return checkForNullEntities(jdbcTemplate.query(list, rowMapper));
	}
	
	public List<T> filterList(String column, Integer id) throws InternalServerException{
		return checkForNullEntities(jdbcTemplate.query(filter, new Object[] {id}, rowMapper));
	}
	
	protected List<T> checkForNullEntities(List<T> entities) throws InternalServerException
	{
		if(entities.contains(null))
		{
			throw new InternalServerException("unreferenced element or child");
		}
		
		return entities;
	}
}
