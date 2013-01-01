package com.cyberavenue.jcatest;

public class JCATestConnectionImpl implements JCATestConnection {
	
	JCATestManagedConnection mc;
	JCATestManagedConnectionFactory mcf;
	
	public JCATestConnectionImpl(JCATestManagedConnection mc, JCATestManagedConnectionFactory mcf) {
		this.mc = mc;
		this.mcf = mcf;
	}

	@Override
	public void close() {
		mc.closeHandle(this);
	}

}
