package com.cyberavenue.jcatest;

import java.io.Serializable;

import javax.resource.Referenceable;
import javax.resource.ResourceException;

public interface JCATestConnectionFactory extends Serializable, Referenceable {
	public JCATestConnection getConnection() throws ResourceException;
}
