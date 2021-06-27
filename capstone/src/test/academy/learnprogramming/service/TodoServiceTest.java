package academy.learnprogramming.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import academy.learnprogramming.entity.Todo;
import academy.learnprogramming.entity.User;
import academy.learnprogramming.producers.CDIProducers;
import academy.learnprogramming.security.SecurityUtil;

/**
 * @author Jonathan Vinh
 */
@RunWith(Arquillian.class)
public class TodoServiceTest {

    @Inject
    private User user;

    @Inject
    TodoService todoService;

    @Inject
    Logger logger;

    @Deployment
    public static JavaArchive createDeployment() {
	return ShrinkWrap.create(JavaArchive.class)
		    .addPackage(CDIProducers.class.getPackage())
		    .addPackage(SecurityUtil.class.getPackage())
		    .addPackage(Todo.class.getPackage())
		    .addClasses(TodoService.class, Todo.class)
		    .addAsResource("persistence.xml", "META-INF/persistence.xml")
		    .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void setUp() throws Exception {
	user.setEmail("test@test.com");
	user.setFullName("Jonathan Vinh");
	user.setPassword("MyPa$$w0rd");

	todoService.saveUser(user);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveUser() {
	assertNotNull(user.getId());
	logger.log(Level.INFO, "############### " + user.getId().toString());
	assertNotEquals("The user's password is not same as hashed", "MyPa$$w0rd", user.getPassword());
	logger.log(Level.INFO, "############### " + user.getPassword());
    }
}
