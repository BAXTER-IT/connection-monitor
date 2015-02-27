package com.baxter.connection.monitor.jmx.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.InstanceNotFoundException;
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.NotificationListener;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxter.connection.monitor.jmx.ConnectionMXBean;
import com.baxter.connection.monitor.jmx.SimpleConnectionMXBean;
import com.sun.jmx.remote.util.EnvHelp;

public class JMXConnectionMonitor
{
  protected Logger logger = LoggerFactory.getLogger(getClass());

  private final String host;
  private final int port;
  private JMXConnector jmxConnector;

  public JMXConnectionMonitor(final String host, final int port)
  {
	this.host = host;
	this.port = port;
  }

  /**
   * Connects to the JMX service on the host:port.
   * @throws IOException if the connector client or the connection cannot be made because of a communication problem.
   */
  public void connect() throws IOException
  {
	JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + host + ":" + port + "/jmxrmi");
	logger.debug("JMXConnector.connect() host: {}, port: {}, url: {}", host, port, url);
	final Map<String, Object> env = new HashMap<String, Object>();
	env.put(EnvHelp.CLIENT_CONNECTION_CHECK_PERIOD, 1000);
	jmxConnector = JMXConnectorFactory.connect(url, env);
  }

  /**
   * Queries all the registered connections.
   * @return MBeans of the connections.
   * @throws IOException
   * @throws MalformedObjectNameException
   */
  public List<SimpleConnectionMXBean> queryConnections() throws IOException, MalformedObjectNameException
  {
	List<SimpleConnectionMXBean> connections = new ArrayList<SimpleConnectionMXBean>();
	if (jmxConnector != null)
	{
	  MBeanServerConnection mbsc = jmxConnector.getMBeanServerConnection();
	  ObjectName mbeanName = new ObjectName(ConnectionMXBean.COM_BAXTER_CONNECTION_TYPE + "*");
	  Set<ObjectInstance> queryMBeans = mbsc.queryMBeans(mbeanName, null);
	  for (ObjectInstance objectInstance : queryMBeans)
	  {
		final SimpleConnectionMXBean mbeanProxy = JMX.newMXBeanProxy(mbsc, objectInstance.getObjectName(),
		    SimpleConnectionMXBean.class, true);
		connections.add(mbeanProxy);
	  }
	}
	return connections;
  }

  /**
   * TODO
   * @param connectionName
   * @param listener
   * @throws IOException
   * @throws MalformedObjectNameException
   * @throws InstanceNotFoundException
   */
  public void addNotificationListener(String connectionName, NotificationListener listener) throws IOException,
	  MalformedObjectNameException, InstanceNotFoundException
  {
	if (jmxConnector != null)
	{
	  MBeanServerConnection mbsc = jmxConnector.getMBeanServerConnection();
	  ObjectName mbeanName = new ObjectName(ConnectionMXBean.COM_BAXTER_CONNECTION_TYPE + connectionName);
	  mbsc.addNotificationListener(mbeanName, listener, null, null);
	}
  }

  /**
   * TODO
   * @param listener
   * @throws IOException
   * @throws MalformedObjectNameException
   * @throws InstanceNotFoundException
   */
  public void addNotificationListenerToAll(NotificationListener listener) throws IOException, MalformedObjectNameException,
	  InstanceNotFoundException
  {
	if (jmxConnector != null)
	{
	  MBeanServerConnection mbsc = jmxConnector.getMBeanServerConnection();
	  ObjectName mbeanName = new ObjectName(ConnectionMXBean.COM_BAXTER_CONNECTION_TYPE + "*");
	  Set<ObjectInstance> queryMBeans = mbsc.queryMBeans(mbeanName, null);
	  for (ObjectInstance objectInstance : queryMBeans)
	  {
		mbsc.addNotificationListener(objectInstance.getObjectName(), listener, null, null);
	  }
	}
  }

  public String getHost()
  {
	return host;
  }

  public int getPort()
  {
	return port;
  }

}
