/*
 * Baxter Connection Monitor Client
 * Copyright (C) 2012-2014 BAXTER Technologies
 * This software is a property of BAXTER Technologies
 * and should remain that way. If you got this source
 * code from elsewhere please immediately inform Franck.
 */
package com.baxter.connection.monitor.jmx;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

/**
 * @author xpdev
 * @since 1.1
 */
public class ConnectionMonitorName extends ObjectName
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private static final String JMX_DOMAIN = "com.baxter.connection";

  ConnectionMonitorName(final Map<String, String> params) throws MalformedObjectNameException
  {
	super(JMX_DOMAIN, new Hashtable<>(params));
  }

  ObjectName toObjectName()
  {
	try
	{
	  return new ObjectName(getCanonicalName());
	}
	catch (final MalformedObjectNameException e)
	{
	  throw new IllegalStateException("The ObjectName cannot be constructed", e);
	}
  }

  /**
   * Constructs the connection monitor object name for specified monitor type and connection name.
   * @param monitorType
   * @param connectionName
   * @return new instance of ConnectionMonitorName
   */
  public static ConnectionMonitorName getInstance(final String monitorType, final String connectionName)
  {
	final Map<String, String> params = new HashMap<>(2);
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
