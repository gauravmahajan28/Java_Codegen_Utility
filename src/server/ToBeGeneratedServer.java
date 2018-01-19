package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ToBeGeneratedServer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		ServerSocket ss=new ServerSocket(3333);  
		
		while(true)
		{
			try
			{
				Socket s=ss.accept();  
				DataInputStream din=new DataInputStream(s.getInputStream());  
				DataOutputStream dout=new DataOutputStream(s.getOutputStream()); 
				String str=din.readUTF();  
				System.out.println("client says: "+str);  
				
				ArrayList<Object> objects = RPCCodegenUtilityMethodsWireProtocolServer.getWireProtocolStringFromInterface(str);
				
				Object object = RPCCodegenUtilityMethodsWireProtocolServer.dynamicFunctionCaller(objects);
				
				dout.writeUTF(object.toString());  
				dout.flush();  
				 
				din.close();  
				s.close();  
				ss.close();  
			}
			catch(Exception e)
			{
				System.out.println("error occured :" + e.getMessage());
			}
		}

	}
}
