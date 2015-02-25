package com.baxter.connection.monitor;

import java.util.List;

import javax.management.Notification;

public class MultipleConnectionMBeanSupport<T extends SingleConnection> extends DefaultConnectionMBeanSupport implements
    MultipleConnectionMXBean
{

  MultipleConnection<T> connections;

  public MultipleConnectionMBeanSupport(MultipleConnection<T> connections)
  {
	super(MBEAN_NAME);
	this.connections = connections;
  }

  public void setStatus(String name, Status status)
  {
	T connection = connections.getConnection(name);
	connection.setStatus(status);
	Notification notification = new ConnectionNotification(this, sequenceNumber.incrementAndGet(), getTimeStamp(), connection);
	sendNotification(notification);
	logger.debug("Send a connection status {}", notification);
  }

  @Override
  public List<T> getConnections()
  {
	return connections.getConnections();
  }
}
