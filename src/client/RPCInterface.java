package client;

public interface RPCInterface 
{
	
	public int add(int quantity, int price) throws Exception;
	
	public float subtract(float quantity, float price) throws Exception;

	
	public String concat(float quantity, float price) throws Exception;

	
	public CustomClass add2(CustomClass obe) throws Exception;
	
	public Integer addInteger(Integer a, int b) throws Exception;
	
}
