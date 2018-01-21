package server;

import client.CustomClass;

public interface ServerInterface 
{
	
	public int add(int quantity, int price) throws Exception;
	
	public float subtract(float price, float quantity) throws Exception;

	
	public String concat(String a, String  b) throws Exception;

	
	public CustomClass add2(CustomClass obe) throws Exception;
	
	public Integer addInteger(Integer a, int b) throws Exception;

}
