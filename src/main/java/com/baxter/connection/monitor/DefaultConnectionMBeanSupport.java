package com.baxter.connection.monitor;

import java.util.concurrent.atomic.AtomicLong;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultConnectionMBeanSupport extends ConnectionMBeanSupport
{
  public static final String COM_BAXTER_CONNECTION_TYPE = "com.baxter.connection:type=";
  protected static Logger logger = LoggerFactory.getLogger(DefaultConnectionMBeanSupport.class);
  protected final AtomicLong sequenceNumber = new AtomicLong(0);

  public DefaultConnectionMBeanSupport(String mbeanName)
  {
	super(mbeanName);
	registerMBean(this, COM_BAXTER_CONNECTION_TYPE + mbeanName);
  }

  @Override
  public void sendAttributeChangeNotification(Class<?> c, String attributeName, Object oldValue, Object newValue)
  {
	Notification notification = new AttributeChangeNotification(this, sequenceNumber.incrementAndGet(), getTimeStamp(),
		attributeName + " has been changed from " + oldValue + " to " + newValue, attributeName, c.getName(), oldValue, newValue);
	sendNotification(notification);
	logger.debug("Send an AttributeChangeNotification {}", notification);
  }
}
