/**
 * 
 */
package com.baxter.connection.monitor.jmx;

/**
 * @author xpdev
 *
 */
public interface ConnectionMXBean
{

  String COM_BAXTER_CONNECTION_TYPE = "com.baxter.connection:type=";

  String getConnectionName();

}
