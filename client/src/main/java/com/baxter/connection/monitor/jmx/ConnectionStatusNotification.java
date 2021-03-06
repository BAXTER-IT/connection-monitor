/*
 * Baxter Connection Monitor Client
 * Copyright (C) 2012-2014 BAXTER Technologies
 * This software is a property of BAXTER Technologies
 * and should remain that way. If you got this source
 * code from elsewhere please immediately inform Franck.
 */
package com.baxter.connection.monitor.jmx;

import javax.management.Notification;
import javax.management.ObjectName;

import com.baxter.connection.monitor.Status;

/**
 * A Notification related to connection status.
 * This one will be fired by monitors when the connection status changes. Source of this notification is the ObjectName of monitor MBean.
 * @author yura
 * @since 1.1
 */
public class ConnectionStatusNotification extends Notification
{

  private static final String CONNECTION_STATUS = "com.baxter.connection.status";

  private static final long serialVersionUID = 1L;

  private final Status status;

  public ConnectionStatusNotification(final ObjectName source, final long sequenceNumber, final long timeStamp,
	  final Status status)
  {
	super(CONNECTION_STATUS, source, sequenceNumber, timeStamp);
	this.status = status;
  }

  /**
   * Returns the recent status of the connection.
   * @return
   */
  public Status getStatus()
  {
	return status;
  }

  @Override
  public String toString()
  {
	return String.format("ConnectionStatusNotification [status=%1$s]", status);
  }
}
