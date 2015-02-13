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
 * TODO always put the javadoc for API public elements.
 * @author bela
 * @sinceDevelopmentVersion
 */
public class SimpleConnectionStatus implements ConnectionStatus
{
  private final String name; // TODO make immutable where possible

  private boolean status;

  public SimpleConnectionStatus(final String name, final boolean status)
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
  public boolean isStatus()
  {
	return status;
  }

  // TODO Really public? are you going to subclass? 
  public void setStatus(boolean status)
  {
	this.status = status;
  }

  @Override
  public String toString()
  {
	// TODO this is very archaic representation, the modern way would be
	// 'Connection <name> is on' or 'Connection <name> is dead' 
	return String.format("ConnectionStatus [name=%1$s, status=%2$s]", name, status);
  }

}
