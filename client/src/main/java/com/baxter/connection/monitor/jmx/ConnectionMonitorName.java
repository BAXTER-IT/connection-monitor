/**
 * 
 */
package com.baxter.connection.monitor.jmx;

import java.util.Hashtable;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

/**
 * @author xpdev
 * @sinceDevelopmentVersion
 */
public class ConnectionMonitorName extends ObjectName
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private static final String JMX_DOMAIN = "com.baxter.connection";

  ConnectionMonitorName(final Hashtable<String, String> params) throws MalformedObjectNameException
  {
	super(JMX_DOMAIN, params);
  }

  public static ConnectionMonitorName getInstance(final String monitorType, final String connectionName)
  {
	final Hashtable<String, String> params = new Hashtable<>(2);
	params.put("type", monitorType);
	params.put("name", connectionName);
	try
	{
	  return new ConnectionMonitorName(params);
	}
	catch (final MalformedObjectNameException e)
	{
	  throw new IllegalArgumentException("Invalid type or name", e);
	}
  }

}
