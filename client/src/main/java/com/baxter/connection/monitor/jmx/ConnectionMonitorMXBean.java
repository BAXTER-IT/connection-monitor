/**
 * 
 */
package com.baxter.connection.monitor.jmx;

import com.baxter.connection.monitor.Status;

/**
 * @author xpdev
 *
 */
public interface ConnectionMonitorMXBean
{

  String getMonitorType();

  String getConnectionName();

  Status getConnectionStatus();

}
