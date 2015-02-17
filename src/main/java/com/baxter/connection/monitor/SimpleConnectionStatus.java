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
 * Simple implementation of the connection status 
 * @author bela
 * @sinceDevelopmentVersion
 */
public class SimpleConnectionStatus<T> implements ConnectionStatus<T>, ConnectionStatusModifier<T>
{
  private final String name;

  private T status;

  public SimpleConnectionStatus(final String name, final T status)
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
  public T getStatus()
  {
	return status;
  }

  @Override
  public void setStatus(T status)
  {
	this.status = status;
  }

  @Override
  public String toString()
  {
	return String.format("Connection %1$s is %2$s]", name, status);
  }

}
