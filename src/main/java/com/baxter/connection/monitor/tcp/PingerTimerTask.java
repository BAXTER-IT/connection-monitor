package com.baxter.connection.monitor.tcp;

import java.util.TimerTask;

/**
 * A task that can be scheduled for one-time or repeated execution by a Timer.
 * Checks the given network host is reachable. 
 * @author Goszi, Bela
 *
 */
public class PingerTimerTask extends TimerTask
{
  private final Host host;

  public PingerTimerTask(Host host)
  {
	this.host = host;

  }

  @Override
  public void run()
  {
	host.setReachable(host.isReachable());
  }

}
