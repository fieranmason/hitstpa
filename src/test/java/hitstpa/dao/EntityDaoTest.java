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


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ContextConfiguration(classes = DBConfig.class)
public class EntityDaoTest {
	
	@Autowired 
	protected EntityDao entityDao;
	
	@Autowired
	private JdbcTemplate jdbc;
	
	private static final String CREATE_DB_T_SQL_SCRIPT = "database/schema/create/createHitstpa.sql";
	private static final String DROP_DB_T_SQL_SCRIPT = "database/schema/drop/dropHitstpa.sql";

	@Before
	public void setup() throws ScriptException, SQLException
	{
		DataSource dataSource = jdbc.getDataSource();
		ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource(CREATE_DB_T_SQL_SCRIPT));
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
}
