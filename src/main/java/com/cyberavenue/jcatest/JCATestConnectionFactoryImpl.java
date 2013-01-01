package com.cyberavenue.jcatest;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;

public class JCATestConnectionFactoryImpl implements JCATestConnectionFactory {
	
	private static final long serialVersionUID = 1L;
	
	Reference ref;
	JCATestManagedConnectionFactory mcf;
	ConnectionManager cxm;
	
	public JCATestConnectionFactoryImpl(JCATestManagedConnectionFactory mcf, ConnectionManager cxm) {
		this.mcf = mcf;
		this.cxm = cxm;
	}

	@Override
	public void setReference(Reference ref) {
		this.ref = ref;
	}

	@Override
	public Reference getReference() throws NamingException {
		return this.ref;
	}

	@Override
	public JCATestConnection getConnection() throws ResourceException {
		return (JCATestConnection)cxm.allocateConnection(mcf, null);
	}

}
