/*
 * Baxter Connection Monitor
 * Copyright (C) 2012-2014  BAXTER Technologies
 * 
 * This software is a property of BAXTER Technologies
 * and should remain that way. If you got this source
 * code from elsewhere please immediately inform Franck.
 */
package com.baxter.connection.monitor;

import java.io.Serializable;

/**
 * Simple implementation of the connection.
 * @author bela
 * @sinceDevelopmentVersion
 */
public class SimpleConnection implements Connection, Serializable
{
  private static final long serialVersionUID = 20150220102230L;

  private final String name;

  private Status status;

  public SimpleConnection(final String name, final Status status)
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
  public Status getStatus()
  {
	return status;
  }

  public void setStatus(final Status status)
  {
	this.status = status;
  }

  @Override
  public String toString()
  {
	return String.format("Connection %1$s is %2$s]", name, status);
  }

}
