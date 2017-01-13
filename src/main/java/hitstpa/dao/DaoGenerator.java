package hitstpa.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import org.apache.log4j.Logger;

import com.leverage.util.InternalServerException;
import com.leverage.util.NotFoundException;
import com.leverage.util.ReferentialIntegrityException;

public class DaoGenerator<S, T extends GenericDao<S>> implements Generator<S> {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	private GenericDao<S> dao;
	private Class<S> elementClass;
	
	//lock down constructor
	private DaoGenerator(){}
	
	public DaoGenerator(DataSource dataSource, Class<S> elementClass, Class<T> daoClass)
	{
		this.elementClass = elementClass;
		
		try {
			dao = daoClass.getConstructor(DataSource.class).newInstance(dataSource);
			
		} catch (InstantiationException | 
				 IllegalAccessException | 
				 IllegalArgumentException |
				 InvocationTargetException |
				 NoSuchMethodException |
				 SecurityException e
				 ) {
			//this is fatal
			logger.error("DaoGenerator failed to initialize", e);
			System.exit(-1);
		}
	}
	
	@Override
	public S get(ResultSet rs) throws NotFoundException, 
									  ReferentialIntegrityException, 
									  InternalServerException, 
									  SQLException {
		return dao.get(rs.getInt(elementClass.getSimpleName().toLowerCase()));
	}
}
