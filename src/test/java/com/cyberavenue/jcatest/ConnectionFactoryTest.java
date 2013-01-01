package com.cyberavenue.jcatest;

import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import javax.resource.ResourceException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConnectionFactoryTest {
	
	private static EJBContainer ejbContainer;
	
	@BeforeClass
    public static void beforeClass() throws Exception {
        Properties p = new Properties();
        p.setProperty("jcatestPoolA.poolMinSize", "7");
		p.setProperty("jcatestPoolA.poolMaxSize", "10");
		p.setProperty("jcatestPoolA.partitionStrategy", "by-connector-properties");
		p.setProperty("jcatestPoolA.allConnectionsEqual", "false");
        ejbContainer = EJBContainer.createEJBContainer(p);
    }
	
	@AfterClass
	public static void afterClass() throws Exception {
		if (ejbContainer != null) {
			ejbContainer.close();
		}
	}
	
	@Resource
	JCATestConnectionFactory jcatestPoolA;
	
	@Before
	public void setUp() throws NamingException {
		ejbContainer.getContext().bind("inject", this);
	}
	
	@Test
	public void testGetConnection() throws ResourceException {
		JCATestConnection conn = jcatestPoolA.getConnection();
		conn.close();
	}
	
	@Test
	public void testGetting2Connections() throws ResourceException {
		JCATestConnection conn1 = jcatestPoolA.getConnection();
		JCATestConnection conn2 = jcatestPoolA.getConnection();
		conn1.close();
		conn2.close();
	}

}
