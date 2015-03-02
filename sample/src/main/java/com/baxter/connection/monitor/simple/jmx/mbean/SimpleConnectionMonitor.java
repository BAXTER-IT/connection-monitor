package com.baxter.connection.monitor.simple.jmx.mbean;

import com.baxter.connection.monitor.jmx.ConnectionMonitorMXBean;
import com.baxter.connection.monitor.jmx.mbean.AbstractConnectionMonitor;
import com.baxter.connection.monitor.simple.SimpleConnection;
import com.baxter.connection.monitor.simple.SimpleStatus;

/**
 * 
 * @author xpdev
 *
 */
public class SimpleConnectionMonitor extends AbstractConnectionMonitor<SimpleConnection> implements ConnectionMonitorMXBean
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

  public void setConnectionStatus(final SimpleStatus status)
  {
	getConnection().setStatus(status);
	fireConnectionStatusChanged(status);
  }

}
