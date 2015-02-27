package com.baxter.connection.monitor.tcp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represent a network host.<br>
 * Notify the registered observers when the connection status of this host changes.
 * @author Goszi, Bela
 *
 */
public class Host extends Observable
{
  private final String hostName;
  private final int timeout;
  private InetAddress host = null;
  private final AtomicBoolean reachable;

  /**
   * Represent a network host.<br>
   * 
   * @param hostName name of the host
   * @param timeout The timeout value, in milliseconds, indicates the maximum amount of time the try should take. 
   * If the operation times out before getting an answer, the host is deemed unreachable. 
   * A negative value will result in an IllegalArgumentException being thrown.
   */
  public Host(final String hostName, final int timeout)
  {
	this.hostName = hostName;
	this.timeout = timeout;
	this.reachable = new AtomicBoolean(false);
  }

  protected void connect() throws UnknownHostException
  {
	host = InetAddress.getByName(getHostName());
  }

  /**
   * Returns the network connection status of this host.
   * @return true if the host is reachable, false if not reachable.
   */
  public boolean isReachable()
  {
	if (host == null)
	{
	  try
	  {
		connect();
	  }
	  catch (UnknownHostException e)
	  {
		return false;
	  }
	}
	try
	{
	  return host.isReachable(timeout);
	}
	catch (IOException e)
	{
	  return false;
	}
  }

  /**
   * Sets the network connection status of this host.
   * Notify the registered observers when the connection status of this host changes.
   * @param isReachable
   */
  public void setReachable(boolean isReachable)
  {
	if (this.reachable.compareAndSet(!isReachable, isReachable))
	{
	  setChanged();
	  notifyObservers(isReachable);
	}
  }

  /**
   * Returns connection status flag.
   * @return true if the current connection status reachable otherwise false.
   */
  public boolean getReachable()
  {
	return this.reachable.get();
  }

  /**
   * Returns host name.
   * @return host name
   */
  public String getHostName()
  {
	return hostName;
  }

}
