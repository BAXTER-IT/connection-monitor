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
 * It contains the types of status.
 * @author bela
 * @sinceDevelopmentVersion
 */
public enum Status
{
  ON(0, "connected"), WAITING(1, "unstable"), OFF(2, "disconnected");

  private int statusId;
  private String displayText;

  private Status(final int statusId, final String displayText)
  {
	this.statusId = statusId;
	this.displayText = displayText;
  }

  public int getStatusId()
  {
	return statusId;
  }

  public String getDisplayText()
  {
	return displayText;
  }

  public static Status findItemById(int id)
  {
	for (Status item : Status.values())
	{
	  if (id == item.statusId)
	  {
		return item;
	  }
	}
	return Status.OFF;
  }

  @Override
  public String toString()
  {
	return displayText;
  }
}
