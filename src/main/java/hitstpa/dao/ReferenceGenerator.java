package hitstpa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.leverage.util.InternalServerException;
import com.leverage.util.NotFoundException;
import com.leverage.util.ReferentialIntegrityException;

public class ReferenceGenerator<S> implements Generator<S> {

	private S element;
	
	//lock down constructor
	private ReferenceGenerator(){}
	
	public ReferenceGenerator(S element)
	{
		this.element = element;
	}
	
	@Override
	public S get(ResultSet rs)
			throws NotFoundException, ReferentialIntegrityException, InternalServerException, SQLException {
		return element;
	}

}
