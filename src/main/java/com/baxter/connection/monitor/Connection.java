/*
 * Baxter Connection Monitor
 * Copyright (C) 2012-2014 BAXTER Technologies
 * This software is a property of BAXTER Technologies
 * and should remain that way. If you got this source
 * code from elsewhere please immediately inform Franck.
 */
package com.baxter.connection.monitor;

/**
 * The user gets information about the connection.
 * 
 * The <tt>Connection</tt> interface provides two methods for connection details.
 * 
 * @author bela
 * @since 1.0
 */
public interface Connection
{
  /**
   * Returns the name of connection.
   * @return connection name
   */
  String getName();

  /**
   * Determines the status of connection.
   * @return connection status
   */
  Status getStatus();

}
