package com.baxter.connection.monitor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MultipleConnection<T extends SingleConnection> implements ConnectionList
{
  List<T> connections;

  public MultipleConnection()
  {
	connections = new CopyOnWriteArrayList<T>();
  }

  public void addConnection(T connection)
  {
	connections.add(connection);
  }

  public void removeConnection(String name)
  {
	connections.remove(getConnection(name));
  }

  public void setStatus(String name, Status status)
  {
	for (T singleConnection : connections)
	{
	  if (singleConnection.getName().equals(name))
	  {
		singleConnection.setStatus(status);
	  }
	}
  }

  public T getConnection(String name)
  {
	for (T singleConnection : connections)
	{
	  if (singleConnection.getName().equals(name))
	  {
		return singleConnection;
	  }
	}
	return null;
  }

  @Override
  public List<T> getConnections()
  {
	return connections;
  }
}
