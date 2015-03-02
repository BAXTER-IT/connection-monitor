/**
 * 
 */
package com.baxter.connection.monitor.simple.sample;

import java.io.IOException;
import java.util.List;
import java.util.Timer;

import javax.management.InstanceNotFoundException;
import javax.management.MalformedObjectNameException;
import javax.management.Notification;
import javax.management.NotificationListener;

import com.baxter.connection.monitor.jmx.ConnectionNotification;
import com.baxter.connection.monitor.simple.jmx.SimpleConnectionMXBean;
import com.baxter.connection.monitor.simple.jmx.client.JMXConnectionMonitor;

/**
 * Sample client for JMX connection monitor
 * @author Goszi, Bela
 *
 */
public class SamplePingerClient
{

  public static void main(String[] args) throws IOException, MalformedObjectNameException, InstanceNotFoundException
  {
	String jmxHost = args[0];
	int jmxPort = Integer.parseInt(args[1]);

	//initialize connection monitor client
	JMXConnectionMonitor connectionMonitor = new JMXConnectionMonitor(jmxHost, jmxPort);
	//connects to the server host
	connectionMonitor.connect();
	//queries registered connections
	List<SimpleConnectionMXBean> queryConnections = connectionMonitor.queryConnections();
	//print them out
	for (SimpleConnectionMXBean simpleConnectionMXBean : queryConnections)
	{
	  System.out.println(simpleConnectionMXBean.getConnectionName() + " : " + simpleConnectionMXBean.getConnectionStatus());
	}
	//add connection listener
	connectionMonitor.addNotificationListenerToAll(new NotificationListener()
	{
	  @Override
	  public void handleNotification(Notification notification, Object handback)
	  {
		if (notification instanceof ConnectionNotification)
		{
		  ConnectionNotification cn = (ConnectionNotification) notification;
		  System.out.println("Received ConnectionNotification: " + cn.getConnection().toString());
		}
	  }
	});
	//wait for exit
	new Timer("wait for...");
  }
}
