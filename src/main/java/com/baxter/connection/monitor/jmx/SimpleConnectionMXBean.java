package com.baxter.connection.monitor.jmx;

import com.baxter.connection.monitor.SimpleStatus;

/**
 * A simple MBean for connection status. 
 * @author Goszi, Bela
 *
 */
public interface SimpleConnectionMXBean extends ConnectionMXBean
{

  SimpleStatus getConnectionStatus();

}
