package com.baxter.connection.monitor;

import javax.management.Notification;

public class ConnectionNotification extends Notification
{

  public static final String CONNECTION_STATUS = "jmx.connection";
  private static final long serialVersionUID = 1L;
  private Connection connection;

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

  public ConnectionNotification(Object source, long sequenceNumber, String message, Connection connection)
  {
	super(CONNECTION_STATUS, source, sequenceNumber, message);
	this.connection = connection;
  }

  public ConnectionNotification(Object source, long sequenceNumber, Connection connection)
  {
	super(CONNECTION_STATUS, source, sequenceNumber);
	this.connection = connection;
  }

  public Connection getConnection()
  {
	return connection;
  }

  public void setConnection(Connection connection)
  {
	this.connection = connection;
  }

  @Override
  public String toString()
  {
	return super.toString() + "[connection=" + connection + "]";
  }
}
