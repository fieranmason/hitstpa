package hitstpa.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leverage.util.InternalServerException;

import config.DBConfig;
import hitstpa.Application;
import hitstpa.model.Entity;
import hitstpa.model.IndividualAction;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ContextConfiguration(classes = DBConfig.class)
public class EntityDaoTest {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	private EntityDao entityDao;
	
	private static final String CREATE_DB_T_SQL_SCRIPT = "database/schema/create/createHitstpa.sql";
	private static final String DROP_DB_T_SQL_SCRIPT = "database/schema/drop/dropHitstpa.sql";
	
	private static final String ADD_STEREOTYPES_T_SQL_SCRIPT = "database/data/addStereotypes.sql";
	private static final String CREATE_SIMPLE_ENTITY_T_SQL_SCRIPT = "database/data/createSimpleEntity.sql";
	private static final String CREATE_INDIVIDUAL_ACTION_T_SQL_SCRIPT = "database/data/createIndividualActionForSimpleEntity.sql";
	private static final String CREATE_EVENT_T_SQL_SCRIPT = "database/data/createEventForIndividualAction.sql";
	
	@Before
	public void setup() throws ScriptException, SQLException
	{
		DataSource dataSource = jdbc.getDataSource();
		entityDao = new EntityDao(dataSource);
		ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource(CREATE_DB_T_SQL_SCRIPT));
		ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource(ADD_STEREOTYPES_T_SQL_SCRIPT));
	}
	
	@After
	public void teardown() throws ScriptException, SQLException
	{
		ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(), new ClassPathResource(DROP_DB_T_SQL_SCRIPT));
	}
	
	@Test
	public void noEntitiesToStart() throws InternalServerException
	{
		List<Entity> entities = entityDao.list();
		assert(entities.size()==0);
	}
	
	@Test
	public void simpleEntity() throws InternalServerException, ScriptException, SQLException
	{
		ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(), new ClassPathResource(CREATE_SIMPLE_ENTITY_T_SQL_SCRIPT));
		
		List<Entity> entities = entityDao.list();
		assert(entities.size()==1);
		
		Entity entity = entities.iterator().next();
		
		String entityName = entity.getName();
		String expectedEntityName = "Care Provider";
		assert(entityName.compareToIgnoreCase(expectedEntityName) == 0);
		
		String stereotypeName = entity.getStereotype().getName();
		String expectedStereotypeName = "Controller";
		assert(stereotypeName.compareToIgnoreCase(expectedStereotypeName)==0);
	}
	
	@Test
	public void cyclicDependenciesIndividualAction() throws ScriptException, SQLException, InternalServerException
	{
		ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(), new ClassPathResource(CREATE_SIMPLE_ENTITY_T_SQL_SCRIPT));
		ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(), new ClassPathResource(CREATE_INDIVIDUAL_ACTION_T_SQL_SCRIPT));
		ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(), new ClassPathResource(CREATE_EVENT_T_SQL_SCRIPT));
		List<Entity> entities = entityDao.list();
		
		assert(entities.size() == 1);
		
		Entity entity = entities.iterator().next();
		
		List<IndividualAction> individualActions = entity.getIndividualActions();
		
		assert(individualActions.size() == 1);
		
		IndividualAction individualAction = individualActions.iterator().next();
		
		assert(individualAction.getEntity() == entity);//assert that this is a reference
		
		assert(individualAction.getId()==1);//need this to be true because of Event script
		
		assert(individualAction.getEvent().getIndividualAction() == individualAction);
		
	}
}
