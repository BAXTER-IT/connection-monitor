/*
 * Baxter Connection Monitor
 * Copyright (C) 2012-2014 BAXTER Technologies
 * This software is a property of BAXTER Technologies
 * and should remain that way. If you got this source
 * code from elsewhere please immediately inform Franck.
 */
package com.baxter.connection.monitor;

import java.util.concurrent.atomic.AtomicInteger;

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
	final AtomicInteger externalCriteria = new AtomicInteger(20);
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
		return new Status()
		{
		  @Override
		  public String toString()
		  {
			return "Status now " + externalCriteria.get();
		  }
		};
	  }
	};
	externalCriteria.set(50);
	Assert.assertEquals("Status now 50", conn.getStatus().toString());
	Assert.assertEquals("Status now 50", conn.getStatus().toString());
	externalCriteria.set(40);
	Assert.assertEquals("Status now 40", conn.getStatus().toString());
  }
}
