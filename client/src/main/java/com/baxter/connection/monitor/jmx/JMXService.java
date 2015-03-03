/*
 * Baxter Connection Monitor Client
 * Copyright (C) 2012-2014 BAXTER Technologies
 * This software is a property of BAXTER Technologies
 * and should remain that way. If you got this source
 * code from elsewhere please immediately inform Franck.
 */
package com.baxter.connection.monitor.jmx;

import java.io.IOException;

import javax.management.remote.JMXConnector;

/**
 * The JMX Service for Connection Monitor Client.
 * @author yura
 * @since 1.1
 */
public interface JMXService
{

  /**
   * Returns the JMX Connector to be used by the client.
   * @return an initialized connector. Depending on an implementation that can be either new or cached connector.
   */
  JMXConnector getJMXConnector() throws IOException;

}
