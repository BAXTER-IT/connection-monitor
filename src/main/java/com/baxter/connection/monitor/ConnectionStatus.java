/*
 * Baxter Connection Monitor
 * Copyright (C) 2012-2014  BAXTER Technologies
 * 
 * This software is a property of BAXTER Technologies
 * and should remain that way. If you got this source
 * code from elsewhere please immediately inform Franck.
 */
package com.baxter.connection.monitor;

/**
 * Get information about the status of connection.
 * 
 * @author bela
 * @sinceDevelopmentVersion
 */
public interface ConnectionStatus<T>
{
  /**
   * Returns the connection name.
   * @return connection name
   */
  String getName();

  /**
   * Determines the status of this connection.
   * @return connection status
   */
  T getStatus();

}
