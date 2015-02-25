package com.baxter.connection.monitor;

import javax.management.Notification;

public class SingleConnectionMBeanSupport extends DefaultConnectionMBeanSupport implements SingleConnectionMXBean
{

  SingleConnection connection;
  Status status;

  public SingleConnectionMBeanSupport(SingleConnection connection)
  {
	super(MBEAN_NAME);
	this.connection = connection;
  }

  @Override
  public String getName()
  {
	return connection.getName();
  }

  @Override
  public Status getStatus()
  {
	return connection.getStatus();
  }

  public void setStatus(Status status)
  {
	connection.setStatus(status);
	Notification notification = new ConnectionNotification(this, sequenceNumber.incrementAndGet(), getTimeStamp(), connection);
	sendNotification(notification);
	logger.debug("Send a connection status {}", notification);
  }
}
