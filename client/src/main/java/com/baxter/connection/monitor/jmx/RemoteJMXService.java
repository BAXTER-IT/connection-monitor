/**
 * 
 */
package com.baxter.connection.monitor.jmx;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * @author yura
 *
 */
public class RemoteJMXService implements JMXService
{

  private static final String JMXRMI_SERVICE_URL = "service:jmx:rmi:///jndi/rmi://%1$s:%2$s/jmxrmi";

  /**
   * Host of remote JMX.
   */
  private final String host;

  /**
   * Port where remote JMX is listening.
   */
  private final int port;

  private JMXConnector connector;

  public RemoteJMXService(final String host, final int port)
  {
	this.host = host;
	this.port = port;
  }

  @Override
  public JMXConnector getJMXConnector() throws IOException
  {
	if (connector == null)
	{
	  final JMXServiceURL url = new JMXServiceURL(String.format(JMXRMI_SERVICE_URL, host, port)); // TODO consider using the other constructor with 3 parameters
	  final Map<String, Object> env = new HashMap<String, Object>();
	  env.put("jmx.remote.x.client.connection.check.period", 1000);
	  connector = JMXConnectorFactory.connect(url, env);
	}
	return connector;
  }

}
