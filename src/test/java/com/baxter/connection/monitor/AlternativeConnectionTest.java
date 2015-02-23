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
import org.mockito.Mockito;

public class AlternativeConnectionTest
{

  @Test
  public void testAlternativeStatus()
  {
	final Connection conn = Mockito.mock(Connection.class);
	Mockito.doReturn("mocked connection").when(conn).getName();
	final Status ok = Mockito.mock(Status.class);
	Mockito.doReturn("I am fine").when(ok).toString();
	final Status nok = Mockito.mock(Status.class);
	Mockito.doReturn("I am bad").when(nok).toString();
	Mockito.doReturn(nok).when(conn).getStatus();
	Assert.assertEquals("I am bad", conn.getStatus().toString());
	Mockito.doReturn(ok).when(conn).getStatus();
	Assert.assertEquals("I am fine", conn.getStatus().toString());
  }

  @Test
  public void testRandomStatus()
  {
	final Connection conn = new Connection()
	{
	  @Override
	  public String getName()
	  {
		return "random";
	  }

	  @Override
	  public Status getStatus()
	  {
		return System.currentTimeMillis() % 2 == 0 ? new Status()
		{
		  @Override
		  public String toString()
		  {
			return "odd";
		  }
		} : new Status()
		{
		  @Override
		  public String toString()
		  {
			return "even";
		  }
		};
	  }
	};
	for (int i = 0; i < 10; i++)
	{
	  System.out.println(conn.getStatus());
	}
  }
}
