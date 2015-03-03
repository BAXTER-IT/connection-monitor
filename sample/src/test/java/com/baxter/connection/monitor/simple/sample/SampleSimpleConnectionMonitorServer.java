/**
 * 
 */
package com.baxter.connection.monitor.simple.sample;

import java.util.Random;

import com.baxter.connection.monitor.simple.SimpleConnection;
import com.baxter.connection.monitor.simple.SimpleStatus;
import com.baxter.connection.monitor.simple.jmx.mbean.SimpleConnectionMonitor;

/**
 * The sample Host Connection Monitor server side application.
 * @author xpdev
 * @sinceDevelopmentVersion
 */
public class SampleSimpleConnectionMonitorServer
{

  public static void main(String[] args) throws Exception
  {
	// These have to be provided for JVM
	//	System.setProperty("com.sun.management.jmxremote", "true");
	//	System.setProperty("com.sun.management.jmxremote.port", "1333");
	// com.sun.management.jmxremote. authenticate
	// A server instantiates the simple connection object
	final SimpleConnection connection = new SimpleConnection("RND", null);
	// Set up the monitor for connection
	final SimpleConnectionMonitor monitor = new SimpleConnectionMonitor(connection);
	// Register the mbean on server
	monitor.register();
	Thread.sleep(5000); // We wait here until client starts
	final Random RND = new Random();
	for (int i = 0; i < 10; i++)
	{
	  final int index = RND.nextInt(SimpleStatus.values().length);
	  final SimpleStatus status = SimpleStatus.values()[index];
	  connection.setStatus(status);
	  Thread.sleep(2000);
	}
  }

}
