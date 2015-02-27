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

import com.baxter.connection.monitor.jmx.ConnectionMXBean;

/**
 * This is an abstract base implemenmtation for a Connection MBean. The system
 * may have multiple Connection MBeans registerdd at the same time, all those
 * differ to each other by means of names or types .....
 * 
 * @author xpdev
 * 
 */
public abstract class AbstractConnectionMBean extends NotificationBroadcasterSupport //implements ConnectionMBeanSupport
{

  protected Logger logger = LoggerFactory.getLogger(getClass());
  private final String mbeanName;
  protected final AtomicLong sequenceNumber = new AtomicLong(0);

  public AbstractConnectionMBean(final String type)
  {
	this.mbeanName = ConnectionMXBean.COM_BAXTER_CONNECTION_TYPE + type;
  }

  /**
   * Registers this MBean within a platform server.
   */
  public void register()
  {
	try
	{
	  final MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
	  final ObjectName objectName = new ObjectName(mbeanName);
	  unregisterMBean(mbeanName);
	  mbs.registerMBean(this, objectName);
	  logger.info("JMX agent registered! {}", objectName);
	}
	catch (final Exception e)
	{
	  logger.error("Error while registering JMX agent!", e);
	}
  }

  private void unregisterMBean(final String mbeanName)
  {
	try
	{
	  final MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
	  final ObjectName objectName = new ObjectName(mbeanName);
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
