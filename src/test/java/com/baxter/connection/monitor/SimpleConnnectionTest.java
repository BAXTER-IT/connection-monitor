/*
 * Baxter Connection Monitor
 * Copyright (C) 2012-2014 BAXTER Technologies
 * This software is a property of BAXTER Technologies
 * and should remain that way. If you got this source
 * code from elsewhere please immediately inform Franck.
 */
package com.baxter.connection.monitor;

import org.junit.Assert;
import org.junit.Test;

public class SimpleConnnectionTest
{

  @Test
  public void testGetStatus()
  {
	String conn = "testConnection";
	SingleConnection connection = new SingleConnection(conn, DefaultStatus.disconnected);
	Assert.assertEquals(conn, connection.getName());
	Assert.assertEquals(DefaultStatus.disconnected, connection.getStatus());
	connection.setStatus(DefaultStatus.connected);
	Assert.assertEquals(DefaultStatus.connected, connection.getStatus());
  }

}
