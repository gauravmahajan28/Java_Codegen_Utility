package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ToBeGenerated implements RPCInterface
{
	
	public int add(int a, int b) {
		// TODO Auto-generated method stub
		String packageName = "";
		String classNameOnServer = "";
		String functionName = "";
		int noOfParams = 2;
		String numberOfParameters = "";
		ArrayList<Object> objects = new ArrayList();
		objects.add(packageName);
		objects.add(classNameOnServer);
		objects.add(functionName);
		objects.add(numberOfParameters);
		for(int i = 0; i < noOfParams; i++)
		{
			String type = "";
			String data = "";
			objects.add(type);
			objects.add(data);
		}
		String ipAddress = "";
		String port = "";
		
		String wireProtocolString = RPCCodegenUtilityMethodsWireProtocol.getWireProtocolStringFromInterface(objects);
		int answer = (Integer) sendToServer(wireProtocolString, ipAddress, port);
		return answer;
				
	}
	
	
	public Object sendToServer(String wireProtocolString, String ipAddress, String port)
	{
		try
		{      
			Socket s=new Socket(ipAddress,Integer.parseInt(port));  
			DataInputStream din=new DataInputStream(s.getInputStream());  
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());    
			dout.writeUTF(wireProtocolString);  
			dout.flush();  
			byte[] byteArray = new byte[1000];
			din.read(byteArray);
 
			System.out.println("Server says: "+byteArray);  
			
			  
			dout.close();  
			s.close();
			return byteArray;
			}
		catch(Exception e)
		{
			System.out.println(e);
			}  
		return null;
	}


	@Override
	public float subtract(float quantity, float price) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}

