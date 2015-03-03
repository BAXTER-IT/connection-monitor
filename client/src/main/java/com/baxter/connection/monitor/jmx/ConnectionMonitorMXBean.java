/*
 * Baxter Connection Monitor Client
 * Copyright (C) 2012-2014 BAXTER Technologies
 * This software is a property of BAXTER Technologies
 * and should remain that way. If you got this source
 * code from elsewhere please immediately inform Franck.
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
