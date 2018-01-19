package client;

public class RPCClientCodegen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// assuming we get name of class to be generated, interface name, ip, port, server side class name
		try
		{
			Class c = Class.forName("RPCInterface");
			System.out.println(c);
		}
		catch(Exception e)
		{
			System.out.println("error in generating code :" + e.getMessage());
		}
		
		
		
	}

}
