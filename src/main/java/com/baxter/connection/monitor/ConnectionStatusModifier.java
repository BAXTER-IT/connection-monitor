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
 * Change the status of the connection.
 * 
 * @author bela
 * @sinceDevelopmentVersion
 */
public interface ConnectionStatusModifier<T>
{

  /**
   * Modify the status of this connection.
   * @param connection status
   */
  void setStatus(T status);
}
