package com.baxter.connection.monitor;

import java.lang.management.ManagementFactory;

//import javax.management.MBeanNotificationInfo;
import javax.management.MBeanServer;
import javax.management.NotificationBroadcasterSupport;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO can I instantiate and use this class?
public class ConnectionMBeanSupport extends NotificationBroadcasterSupport
{
  //TODO using a concrete class here eliminates the possibility for subclasses to be identified
  protected static Logger logger = LoggerFactory.getLogger(ConnectionMBeanSupport.class);
  
  private final String mbeanName;

  // TODO final modifier for parameters - everywhere!
  public ConnectionMBeanSupport(String mbeanName)
  {
	super();
	this.mbeanName = mbeanName;
  }

  // TODO no lava code! do not commit this.
  //  @Override
  //  public javax.management.MBeanNotificationInfo[] getNotificationInfo()
  //  {
  //	String[] types = new String[] { ApplicationLifecycleNotification.APPLICATION_LIFECYCLE };
  //
  //	String name = ApplicationLifecycleNotification.class.getName();
  //	String description = "Applictaion lifecycle state has changed";
  //	MBeanNotificationInfo info = new MBeanNotificationInfo(types, name, description);
  //	return new MBeanNotificationInfo[] { info };
  //  }

  // TODO who is a client of this method? why is it public and why is it static?
  public static void registerMBean(Object object, String mbeanName)
  {
	try
	{
	  // TODO use final modifier where possible - always!
	  MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
	  ObjectName objectName = new ObjectName(mbeanName);
	  unregisterMBean(mbeanName);
	  mbs.registerMBean(object, objectName);
	  logger.info("JMX agent registered! {}", objectName);
	}
	catch (Exception e)
	{
	  logger.error("Error while registering JMX agent!", e);
	  e.printStackTrace(); // TODO why polluting the output? isn't logger enough?
	}
  }

  public static void unregisterMBean(String mbeanName)
  {
	try
	{
	  MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
	  ObjectName objectName = new ObjectName(mbeanName);
	  if (mbs.isRegistered(objectName))
	  {
		mbs.unregisterMBean(objectName);
		logger.info("JMX agent unregistered! {}", objectName);
	  }
	}
	catch (Exception e)
	{
	  logger.error("Error while unregistering JMX agent!", e);
	  e.printStackTrace();
	}
  }

  // TODO what is a purpose of this method in this class?
  public long getTimeStamp()
  {
	return System.currentTimeMillis();
  }

  // TODO what is this methid for?
  public void sendAttributeChangeNotification(Class<?> c, String attributeName, Object oldValue, Object newValue)
  {
  }

  public String getMbeanName()
  {
	return mbeanName;
  }

}
