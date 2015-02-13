package com.baxter.monitor;

public class ConnectionStatus implements IConnectionStatus
{
  private String name;
  private boolean status;

  public ConnectionStatus(String name, boolean status)
  {
	this.name = name;
	this.status = status;
  }

  @Override
  public String getName()
  {
	return name;
  }

  @Override
  public boolean isStatus()
  {
	return status;
  }

  public void setStatus(boolean status)
  {
	this.status = status;
  }

  @Override
  public String toString()
  {
	return "ConnectionStatus [name=" + name + ", status=" + status + "]";
  }

}
