/*
 * Simple Connection Monitor
 * Copyright (C) 2012-2014 BAXTER Technologies
 * This software is a property of BAXTER Technologies
 * and should remain that way. If you got this source
 * code from elsewhere please immediately inform Franck.
 */
package com.baxter.connection.monitor.simple;

import java.util.Observable;

import com.baxter.connection.monitor.Connection;

/**
 * Simple implementation of the connection.
 * @author bela
 * @since 1.0
 */
public class SimpleConnection extends Observable implements Connection
{
  private final String name;

  private SimpleStatus status;

  public SimpleConnection(final String name, final SimpleStatus status)
  {
	this.name = name;
	this.status = status;
  }

  @Override
  public String getName()
  {
	return name;
  }

  @Override
  public SimpleStatus getStatus()
  {
	return status;
  }

  public void setStatus(final SimpleStatus status)
  {
	if (status == null)
	{
	  throw new IllegalArgumentException("Status cannot be null");
	}
	if (this.status == null || !this.status.equals(status))
	{
	  this.status = status;
	  setChanged(); // we mark as really changed
	  notifyObservers(status); // we notify observers and pass new status there 
	}
  }

  @Override
  public String toString()
  {
	return String.format("SimpleConnection %1$s is %2$s]", name, status);
  }

}
