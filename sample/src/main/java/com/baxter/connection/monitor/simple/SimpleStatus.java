/*
 * Baxter Connection Monitor
 * Copyright (C) 2012-2014 BAXTER Technologies
 * This software is a property of BAXTER Technologies
 * and should remain that way. If you got this source
 * code from elsewhere please immediately inform Franck.
 */
package com.baxter.connection.monitor.simple;

import com.baxter.connection.monitor.Status;

/**
 * Default implementation of Status. It contains the types of status.
 * 
 * @author bela
 * @since 1.0
 */
public enum SimpleStatus implements Status
{
  connected, unstable, disconnected
}
