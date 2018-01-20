package server;

import client.CustomClass;

public class AdditionService {

	public int add(int a, int b)
	{
		return a+b;
	}
	public float subtract(float quantity, float price)
	{
		return quantity-price;
	}
	
	public String concat(String a, String b)
	{
		return a.concat(b); 
	}
	
	public  CustomClass add2(CustomClass obe)
	{
		CustomClass customClass = new CustomClass();
		customClass.a = obe.a  + 10;
		customClass.b = obe.b + 20;
		return customClass;
	}
	
	
}
