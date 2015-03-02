package com.baxter.connection.monitor.simple.jmx;

import com.baxter.connection.monitor.jmx.ConnectionMonitorMXBean;
import com.baxter.connection.monitor.simple.SimpleStatus;

/**
 * A simple MBean for connection status. 
 * @author Goszi, Bela
 *
 */
public interface SimpleConnectionMXBean extends ConnectionMonitorMXBean
{

  SimpleStatus getConnectionStatus();

}
