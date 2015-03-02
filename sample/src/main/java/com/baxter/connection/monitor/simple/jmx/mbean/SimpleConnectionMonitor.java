package com.baxter.connection.monitor.simple.jmx.mbean;

import javax.management.Notification;

import com.baxter.connection.monitor.jmx.ConnectionNotification;
import com.baxter.connection.monitor.jmx.mbean.AbstractConnectionMonitor;
import com.baxter.connection.monitor.simple.SimpleConnection;
import com.baxter.connection.monitor.simple.SimpleStatus;
import com.baxter.connection.monitor.simple.jmx.SimpleConnectionMXBean;

/**
 * 
 * @author xpdev
 *
 */
public class SimpleConnectionMonitor extends AbstractConnectionMonitor<SimpleConnection> implements SimpleConnectionMXBean
{

  public SimpleConnectionMonitor(final SimpleConnection connection)
  {
	super(connection);
  }

  @Override
  public String getMonitorType()
  {
	return "simple";
  }

  @Override
  public SimpleStatus getConnectionStatus()
  {
	return getConnection().getStatus();
  }

  public void setConnectionStatus(final SimpleStatus status)
  {
	getConnection().setStatus(status);
	Notification notification = new ConnectionNotification(this, sequenceNumber.incrementAndGet(), System.currentTimeMillis(),
	    getConnection());
	sendNotification(notification);
	logger.debug("Send a connection status {}", notification);
  }
}
