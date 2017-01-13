package hitstpa.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
	
	@Before
	public void setup()
	{
		
	}
	
	@Test
	public void test() throws InternalServerException
	{
		List<Entity> entities = entityDao.list();
		assert(entities.size()==0);
	}
}
