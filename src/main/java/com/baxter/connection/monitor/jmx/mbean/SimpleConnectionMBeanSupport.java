package com.baxter.connection.monitor.jmx.mbean;

import javax.management.Notification;

import com.baxter.connection.monitor.SimpleConnection;
import com.baxter.connection.monitor.SimpleStatus;
import com.baxter.connection.monitor.jmx.ConnectionNotification;
import com.baxter.connection.monitor.jmx.SimpleConnectionMXBean;

/**
 * 
 * @author xpdev
 *
 */
public class SimpleConnectionMBeanSupport extends AbstractConnectionMBean implements SimpleConnectionMXBean
{

  SimpleConnection connection;

  public SimpleConnectionMBeanSupport(SimpleConnection connection)
  {
	super(connection.getName());
	this.connection = connection;
  }

  @Override
  public String getConnectionName()
  {
	return connection.getName();
  }

  @Override
  public SimpleStatus getConnectionStatus()
  {
	return connection.getStatus();
  }

  public void setConnectionStatus(final SimpleStatus status)
  {
	connection.setStatus(status);
	Notification notification = new ConnectionNotification(this, sequenceNumber.incrementAndGet(), System.currentTimeMillis(),
	    connection);
	sendNotification(notification);
	logger.debug("Send a connection status {}", notification);
  }
}
