package hitstpa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;

import com.leverage.util.InternalServerException;
import com.leverage.util.NotFoundException;
import com.leverage.util.ReferentialIntegrityException;

import hitstpa.model.EntityCouple;
import hitstpa.model.Event;
import hitstpa.model.EventSafetyConstraint;
import hitstpa.model.Interaction;
import hitstpa.model.Sequence;

public class SequenceDao extends GenericDao <Sequence>{

	protected SequenceDao(DataSource dataSource) {
		super(Sequence.class, dataSource);
		
		rowMapper = new RowMapper<Sequence>(){

			@Override
			public Sequence mapRow(ResultSet rs, int rowNum) throws SQLException {
				Sequence sequence;
				Integer id;
				EventSafetyConstraint eventSafetyConstraint;
				Event event;
				Integer sequencePosition;
				
				try
				{
					id = rs.getInt("id");
					eventSafetyConstraint = new EventSafetyConstraintDao(dataSource).get(rs.getInt("eventSafetyConstraint"));
					event = new EventDao(dataSource).get(rs.getInt("event"));
					sequencePosition = rs.getInt("sequence");
					sequence = new Sequence(id, eventSafetyConstraint, event, sequencePosition);
				}
				catch(NotFoundException nfe)
				{
					logger.error("Database Fault", nfe);
					sequence = null;
				}
				catch(ReferentialIntegrityException rie)
				{
					logger.error("Database Fault", rie);
					sequence = null;
				}
				catch(InternalServerException ise)
				{
					logger.error("Database Fault", ise);
					sequence = null;
				}
				
				return sequence;
			}
			
		};
		
	}

}
