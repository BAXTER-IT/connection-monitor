package com.baxter.connection.monitor.jmx.mbean;

import java.lang.management.ManagementFactory;
import java.util.concurrent.atomic.AtomicLong;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanServer;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxter.connection.monitor.Connection;
import com.baxter.connection.monitor.jmx.ConnectionMonitorMXBean;
import com.baxter.connection.monitor.jmx.ConnectionMonitorName;

/**
 * This is an abstract base implementation for a Connection MBean. The system
 * may have multiple Connection MBeans registered at the same time, all those
 * differ to each other by means of names or types .....
 * 
 * @author xpdev
 * @param <C> a concrete connection type for this monitor
 */
public abstract class AbstractConnectionMonitor<C extends Connection> extends NotificationBroadcasterSupport implements
    ConnectionMonitorMXBean
{

  protected Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * The name of this mbean.
   */
  private final ObjectName name;

  private final C connection;

  protected final AtomicLong sequenceNumber = new AtomicLong(0);

  /**
   * Creates the mbean instance. The object name is constructed based on passed parameters.
   * @param connection the connection to monitor
   * @throws IllegalArgumentException if type or  name are illegal for JMX ObjectName
   */
  protected AbstractConnectionMonitor(final C connection)
  {
	this.connection = connection;
	this.name = ConnectionMonitorName.getInstance(getMonitorType(), connection.getName());
  }

  @Override
  public String getConnectionName()
  {
	return this.connection.getName();
  }

  protected C getConnection()
  {
	return this.connection;
  }

  /**
   * Registers this MBean within a platform server.
   */
  public void register()
  {
	try
	{
	  final MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
	  unregisterMBean(name);
	  mbs.registerMBean(this, name);
	  logger.info("JMX agent registered! {}", name);
	}
	catch (final Exception e)
	{
	  logger.error("Error while registering JMX agent!", e);
	}
  }

  private void unregisterMBean(final ObjectName objectName)
  {
	try
	{
	  final MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
	  if (mbs.isRegistered(objectName))
	  {
		mbs.unregisterMBean(objectName);
		logger.info("JMX agent unregistered! {}", objectName);
	  }
	}
	catch (final Exception e)
	{
	  logger.error("Error while unregistering JMX agent!", e);
	}
  }

  public void sendAttributeChangeNotification(Class<?> c, String attributeName, Object oldValue, Object newValue)
  {
	Notification notification = new AttributeChangeNotification(this, sequenceNumber.incrementAndGet(),
	    System.currentTimeMillis(), attributeName + " has been changed from " + oldValue + " to " + newValue, attributeName,
	    c.getName(), oldValue, newValue);
	sendNotification(notification);
	logger.debug("Send an AttributeChangeNotification {}", notification);
  }
}
