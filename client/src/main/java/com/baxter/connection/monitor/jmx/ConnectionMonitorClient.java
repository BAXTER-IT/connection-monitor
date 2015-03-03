/*
 * Baxter Connection Monitor Client
 * Copyright (C) 2012-2014 BAXTER Technologies
 * This software is a property of BAXTER Technologies
 * and should remain that way. If you got this source
 * code from elsewhere please immediately inform Franck.
 */
package com.baxter.connection.monitor.jmx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.management.InstanceNotFoundException;
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.NotificationListener;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionMonitorClient
{
  protected Logger logger = LoggerFactory.getLogger(getClass());

  private final JMXService jmxService;

  public ConnectionMonitorClient(final JMXService jmxService)
  {
	this.jmxService = jmxService;
  }

  /**
   * Queries all the registered connections.
   * @return MBeans of the connections.
   * @throws IOException
   */
  public List<ConnectionMonitorMXBean> queryConnections() throws IOException
  {
	final List<ConnectionMonitorMXBean> connections = new ArrayList<>();
	final MBeanServerConnection mbsc = jmxService.getJMXConnector().getMBeanServerConnection();
	final ObjectName mbeanName = ConnectionMonitorName.getInstance("*", "*");
	final Set<ObjectInstance> queryMBeans = mbsc.queryMBeans(mbeanName, null);
	for (ObjectInstance objectInstance : queryMBeans)
	{
	  final ConnectionMonitorMXBean mbeanProxy = JMX.newMXBeanProxy(mbsc, objectInstance.getObjectName(),
		  ConnectionMonitorMXBean.class, true);
	  connections.add(mbeanProxy);
	}
	return connections;
  }

  /**
   * Adds a notification listener for specific monitor.
   * @param connectionName
   * @param listener
   * @throws IOException
   * @throws MalformedObjectNameException
   * @throws InstanceNotFoundException
   */
  public void addNotificationListener(final String monitorType, final String connectionName, final NotificationListener listener)
	  throws IOException, MalformedObjectNameException, InstanceNotFoundException
  {
	final MBeanServerConnection mbsc = jmxService.getJMXConnector().getMBeanServerConnection();
	final ObjectName mbeanName = ConnectionMonitorName.getInstance(monitorType, connectionName);
	mbsc.addNotificationListener(mbeanName, listener, null, null);
  }

  /**
   * Adds notification listener for all connection monitors.
   * @param listener
   * @throws IOException
   * @throws MalformedObjectNameException
   * @throws InstanceNotFoundException
   */
  public void addNotificationListenerToAll(final NotificationListener listener) throws IOException, MalformedObjectNameException,
	  InstanceNotFoundException
  {
	final MBeanServerConnection mbsc = jmxService.getJMXConnector().getMBeanServerConnection();
	final ObjectName mbeanName = ConnectionMonitorName.getInstance("*", "*");
	final Set<ObjectInstance> queryMBeans = mbsc.queryMBeans(mbeanName, null);
	for (ObjectInstance objectInstance : queryMBeans)
	{
	  mbsc.addNotificationListener(objectInstance.getObjectName(), listener, null, null);
	}
  }

}
