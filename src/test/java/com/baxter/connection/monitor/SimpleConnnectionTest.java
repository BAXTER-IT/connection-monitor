package com.baxter.connection.monitor;

import org.junit.Assert;
import org.junit.Test;

public class SimpleConnnectionTest
{

  @Test
  public void testGetStatus()
  {
	String conn = "testConnection";
	SimpleConnection connection = new SimpleConnection(conn, SimpleStatus.disconnected);
	Assert.assertEquals(conn, connection.getName());
	Assert.assertEquals(SimpleStatus.disconnected, connection.getStatus());
	connection.setStatus(SimpleStatus.connected);
	Assert.assertEquals(SimpleStatus.connected, connection.getStatus());
  }

}