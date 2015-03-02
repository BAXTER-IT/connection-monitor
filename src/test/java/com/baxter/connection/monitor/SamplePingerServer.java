/**
 * 
 */
package com.baxter.connection.monitor.simple.sample;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

import com.baxter.connection.monitor.simple.SimpleConnection;
import com.baxter.connection.monitor.simple.SimpleStatus;
import com.baxter.connection.monitor.simple.jmx.mbean.SimpleConnectionMBeanSupport;

/**
 * @author xpdev
 *
 */
public class SamplePingerServer
{

  public static void main(String[] args)
  {
	String hostName = args[0];
	int pingTimeout = Integer.parseInt(args[1]);
	int pingInterval = Integer.parseInt(args[2]);

	final SimpleConnection connectionMonitor = new SimpleConnection(hostName, SimpleStatus.unstable);
	final SimpleConnectionMBeanSupport mbeanSupport = new SimpleConnectionMBeanSupport(connectionMonitor);
	mbeanSupport.register();

	Host host = new Host(hostName, pingTimeout);
	PingerTimerTask pingerTask = new PingerTimerTask(host);
	host.addObserver(new Observer()
	{
	  @Override
	  public void update(Observable o, Object arg)
	  {
		if (arg instanceof Boolean)
		{
		  boolean reachable = (Boolean) arg;
		  mbeanSupport.setConnectionStatus(reachable ? SimpleStatus.connected : SimpleStatus.disconnected);
		}
	  }
	});

	Timer pingerTimer = new Timer("Pinger");
	pingerTimer.schedule(pingerTask, 0, pingInterval);
  }

}
