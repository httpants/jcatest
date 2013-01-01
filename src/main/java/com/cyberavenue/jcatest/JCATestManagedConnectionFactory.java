package com.cyberavenue.jcatest;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionFactory;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.ResourceAdapterAssociation;
import javax.security.auth.Subject;

public class JCATestManagedConnectionFactory 
	implements ManagedConnectionFactory, ResourceAdapterAssociation {

	private static final long serialVersionUID = 1L;
	
	ResourceAdapter ra;
	PrintWriter logWriter;

	@Override
	public ResourceAdapter getResourceAdapter() {
		return ra;
	}

	@Override
	public void setResourceAdapter(ResourceAdapter ra)
			throws ResourceException {
		this.ra = ra;
	}

	@Override
	public Object createConnectionFactory() throws ResourceException {
		throw new ResourceException("This resource adapter doesn't support non-managed environments.");
	}

	@Override
	public Object createConnectionFactory(ConnectionManager cxm)
			throws ResourceException {
		return new JCATestConnectionFactoryImpl(this, cxm);
	}

	@Override
	public ManagedConnection createManagedConnection(Subject subject,
			ConnectionRequestInfo cri) throws ResourceException {
		return new JCATestManagedConnection(this, cri);
	}

	@Override
	public PrintWriter getLogWriter() throws ResourceException {
		// TODO Auto-generated method stub
		return this.logWriter;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ManagedConnection matchManagedConnections(Set suppliedManagedConnectionSet, Subject subject,
			ConnectionRequestInfo suppliedCRI) throws ResourceException {
		
		ManagedConnection result = null;
		Iterator it = suppliedManagedConnectionSet.iterator();
		while (result == null && it.hasNext()) {
			ManagedConnection mc = (ManagedConnection)it.next();
			if (mc instanceof JCATestManagedConnection) {
				JCATestManagedConnection smc = (JCATestManagedConnection)mc;
				if (suppliedCRI == null || suppliedCRI.equals(smc.cri)) {
					result = smc;
					break;
				} 
			}
		}
		
		System.out.println("Returning matched connection: " + result);
		return result;
	}

	@Override
	public void setLogWriter(PrintWriter logWriter) throws ResourceException {
		this.logWriter = logWriter;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
