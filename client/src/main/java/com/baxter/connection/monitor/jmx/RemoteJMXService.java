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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yura
 *
 */
public class RemoteJMXService implements JMXService
{

  private static final String JMXRMI_SERVICE_URL = "service:jmx:rmi:///jndi/rmi://%1$s:%2$s/jmxrmi";

  private static final Logger LOGGER = LoggerFactory.getLogger(RemoteJMXService.class);

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
	  connect();
	}
	else
	{
	  // TODO on practice we may require a loop here
	  // and maybe the loop can be limited with number of attempts
	  try
	  {
		final String id = connector.getConnectionId();
		LOGGER.trace("Connection {} is still alive", id);
	  }
	  catch (final IOException e)
	  {
		LOGGER.warn("The JMX Connection was failed. Will reconnect", e);
		connect();
	  }
	}
	return connector;
  }

  private void connect() throws IOException
  {
	final JMXServiceURL url = new JMXServiceURL(String.format(JMXRMI_SERVICE_URL, host, port)); // TODO consider using the other constructor with 3 parameters
	final Map<String, Object> env = new HashMap<String, Object>();
	env.put("jmx.remote.x.client.connection.check.period", 1000);
	connector = JMXConnectorFactory.connect(url, env);
  }

}
