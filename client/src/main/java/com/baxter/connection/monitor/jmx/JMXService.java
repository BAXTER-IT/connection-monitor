/**
 * 
 */
package com.baxter.connection.monitor.jmx;

import java.io.IOException;

import javax.management.remote.JMXConnector;

/**
 * The JMX Service for Connection Monitor Client.
 * @author yura
 * @sinceDevelopmentVersion
 */
public interface JMXService
{

  /**
   * Returns the JMX Connector to be used by the client.
   * @return an initialized connector. Depending on an implementation that can be either new or cached connector.
   */
  JMXConnector getJMXConnector() throws IOException;

}
