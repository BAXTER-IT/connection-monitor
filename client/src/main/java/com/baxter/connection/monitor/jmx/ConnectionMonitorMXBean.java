/**
 * 
 */
package com.baxter.connection.monitor.jmx;


/**
 * @author xpdev
 *
 */
public interface ConnectionMonitorMXBean
{

  String getMonitorType();

  String getConnectionName();

  String getConnectionStatus();

}
