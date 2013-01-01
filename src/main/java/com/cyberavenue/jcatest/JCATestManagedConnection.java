package com.cyberavenue.jcatest;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.resource.NotSupportedException;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionEvent;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionMetaData;
import javax.security.auth.Subject;
import javax.transaction.xa.XAResource;

public class JCATestManagedConnection implements ManagedConnection {
	
	// Listeners
	List<ConnectionEventListener> listeners = new ArrayList<ConnectionEventListener>();
	PrintWriter logWriter;
	JCATestManagedConnectionFactory mcf;
	ConnectionRequestInfo cri;
	
	public JCATestManagedConnection(JCATestManagedConnectionFactory mcf, 
			ConnectionRequestInfo cri) {
		System.out.println("Calling JCATestManagedConnection constructor");
		this.mcf = mcf;
		this.cri = cri;
	}

	@Override
	public void addConnectionEventListener(ConnectionEventListener listener) {
		listeners.add(listener);
	}

	@Override
	public void associateConnection(Object arg0) throws ResourceException {
		System.out.println("Calling JCATestManagedConnection.associateConnection()");
	}

	@Override
	public void cleanup() throws ResourceException {
		System.out.println("Calling JCATestManagedConnection.cleanup()");
	}

	@Override
	public void destroy() throws ResourceException {
		System.out.println("Calling JCATestManagedConnection.destroy()");
	}

	@Override
	public Object getConnection(Subject subject, ConnectionRequestInfo cri)
			throws ResourceException {
		System.out.println("Calling JCATestManagedConnection.getConnection()");
		return new JCATestConnectionImpl(this, mcf);
	}

	@Override
	public LocalTransaction getLocalTransaction() throws ResourceException {
		throw new NotSupportedException("LocalTransactions are not supported");
	}

	@Override
	public PrintWriter getLogWriter() throws ResourceException {
		return this.logWriter;
	}

	@Override
	public ManagedConnectionMetaData getMetaData() throws ResourceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XAResource getXAResource() throws ResourceException {
		throw new NotSupportedException("getXAResource not supported");
	}

	@Override
	public void removeConnectionEventListener(ConnectionEventListener listener) {
		if (listener == null) {
			throw new IllegalArgumentException("Listener is null");
		}
		listeners.remove(listener);

	}

	@Override
	public void setLogWriter(PrintWriter pw) throws ResourceException {
		this.logWriter = pw;
	}
	
	/**
	 * Close handle
	 * @param handle The handle
	 */
	void closeHandle(JCATestConnection handle) {
		ConnectionEvent event = new ConnectionEvent(this, ConnectionEvent.CONNECTION_CLOSED);
		event.setConnectionHandle(handle);
		for (ConnectionEventListener listener: listeners) {
			listener.connectionClosed(event);
		}
	}

}
