package hitstpa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.leverage.util.InternalServerException;
import com.leverage.util.NotFoundException;
import com.leverage.util.ReferentialIntegrityException;


public interface Generator <S>{
	public S get(ResultSet rs) throws NotFoundException, ReferentialIntegrityException, InternalServerException, SQLException;
}
