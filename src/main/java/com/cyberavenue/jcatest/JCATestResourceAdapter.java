package com.cyberavenue.jcatest;

import java.io.Serializable;

import javax.resource.ResourceException;
import javax.resource.spi.ActivationSpec;
import javax.resource.spi.BootstrapContext;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.ResourceAdapterInternalException;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.transaction.xa.XAResource;

public class JCATestResourceAdapter implements ResourceAdapter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void endpointActivation(MessageEndpointFactory arg0,
			ActivationSpec arg1) throws ResourceException {
	}

	@Override
	public void endpointDeactivation(MessageEndpointFactory arg0,
			ActivationSpec arg1) {
	}

	@Override
	public XAResource[] getXAResources(ActivationSpec[] arg0)
			throws ResourceException {
		return null;
	}

	@Override
	public void start(BootstrapContext arg0)
			throws ResourceAdapterInternalException {
		System.out.println("JCATestResourceAdapter.start()...");
	}

	@Override
	public void stop() {
		System.out.println("JCATestResourceAdapter.stop()...");
	}

}
