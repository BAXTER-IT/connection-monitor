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
 * What is the purpose of this interface? Just few sentences...
 * 
 * @author bela
 * @sinceDevelopmentVersion
 */
public interface ConnectionStatus
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
  boolean isStatus();

}
