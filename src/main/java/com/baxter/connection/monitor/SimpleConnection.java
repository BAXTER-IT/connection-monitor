/*
 * Baxter Connection Monitor
 * Copyright (C) 2012-2014 BAXTER Technologies
 * This software is a property of BAXTER Technologies
 * and should remain that way. If you got this source
 * code from elsewhere please immediately inform Franck.
 */
package com.baxter.connection.monitor;

import java.io.Serializable;

/**
 * Simple implementation of the connection.
 * @author bela
 * @since 1.0
 */
public class SimpleConnection implements Connection, Serializable
{
  private static final long serialVersionUID = 1L;

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
	this.status = status;
  }

  @Override
  public String toString()
  {
	return String.format("Connection %1$s is %2$s]", name, status);
  }

}
