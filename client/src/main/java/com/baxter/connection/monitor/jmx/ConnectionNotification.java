package com.baxter.connection.monitor.jmx;

import javax.management.Notification;

import com.baxter.connection.monitor.Connection;

public class ConnectionNotification extends Notification
{

  private static final String CONNECTION_STATUS = "jmx.connection";
  private static final long serialVersionUID = 1L;
  private final Connection connection;

  // TODO do you use this constructor? do you need it at all?
  public ConnectionNotification(Object source, long sequenceNumber, long timeStamp, String message, Connection connection)
  {
	super(CONNECTION_STATUS, source, sequenceNumber, timeStamp, message);
	this.connection = connection;
  }

  public ConnectionNotification(Object source, long sequenceNumber, long timeStamp, Connection connection)
  {
	super(CONNECTION_STATUS, source, sequenceNumber, timeStamp);
	this.connection = connection;
  }

  ConnectionNotification(Object source, long sequenceNumber, String message, Connection connection)
  {
	super(CONNECTION_STATUS, source, sequenceNumber, message);
	this.connection = connection;
  }

  ConnectionNotification(Object source, long sequenceNumber, Connection connection)
  {
	super(CONNECTION_STATUS, source, sequenceNumber);
	this.connection = connection;
  }

  public Connection getConnection()
  {
	return connection;
  }

  @Override
  public String toString()
  {
	return super.toString() + "[connection=" + connection + "]";
  }
}
