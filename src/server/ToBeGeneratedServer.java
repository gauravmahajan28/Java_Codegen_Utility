package server;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ToBeGeneratedServer {

	public static void main(String[] args) throws Exception  {
		// TODO Auto-generated method stub
		
		
		ServerSocket ss=new ServerSocket(3333);  
		System.out.println("server listening on ");
		while(true)
		{
			try
			{
				Socket s=ss.accept();
				System.out.println("client accepted");
				DataInputStream din=new DataInputStream(new BufferedInputStream(s.getInputStream()));  
				DataOutputStream dout=new DataOutputStream(s.getOutputStream()); 
				String str=din.readUTF();  
				System.out.println("client says: "+str);  
					
				String object = dynamicFunctionCaller(str);
				
				dout.writeUTF(object); 
				dout.flush();  
				 
				din.close();  
				s.close();  
				
			}
			catch(Exception e)
			{
				ss.close();  
				System.out.println("error occured :" + e.getMessage());
			}
		}

	}
	public static  String  dynamicFunctionCaller(String wireProtocolString) throws Exception
	{
		//"AdditionService#server#add#2#int#10#quantity#int#20#price"
		
		StringTokenizer st = new StringTokenizer(wireProtocolString, "#");
		String className = st.nextToken();
		String packageName = st.nextToken();
		String methodName = st.nextToken();
		System.out.println(className + "-" + methodName  + "-" + packageName);
		
		int numberOfParameters = Integer.parseInt(st.nextToken());
		HashMap<String, Object> parameterMap = new HashMap<>();
		HashMap<String, String> parameterType = new HashMap<>();
		Class params[] = new Class[numberOfParameters];
		Object paramsObj[] = new Object[numberOfParameters];
		
		Boolean isCustomClass = false;
		for(int i = 0; i < numberOfParameters; i++)
		{
			String type = st.nextToken();
			String data = st.nextToken();
			String name = st.nextToken();
			parameterMap.put(name, data);
			parameterType.put(name,  type);
			if(type.contains("class"))
				type = type.split(" ")[1];
			
			
			switch(type)
			{
			
			
			
			
			case "int":
				params[i] = int.class;
				paramsObj[i] = Integer.parseInt(data);
				break;
				
			case "char":
				params[i] = char.class;
				paramsObj[i] = data;
				break;
				
			case "float":
				params[i] = float.class;
				paramsObj[i] = Float.parseFloat(data);
				break;
				
			case "double":
				params[i] = double.class;
				paramsObj[i] = Double.parseDouble(data);
				break;
				
			case "java.lang.Integer":
				params[i] = Integer.class;
				paramsObj[i] = Integer.parseInt(data);
				break;
				
			case "java.lang.Double":
				params[i] = Double.class;
				paramsObj[i] = Double.parseDouble(data);
				break;
				
			case "Float":
				params[i] = Float.class;
				paramsObj[i] = Float.parseFloat(data);
				break;
				
			case "java.lang.Character":
				params[i] = Character.class;
				paramsObj[i] = data;
				break;
				
			case "java.lang.String":
				params[i] = String.class;
				paramsObj[i] = data;
				break;
				
			case "java.lang.Boolean":
				params[i] = Boolean.class;
				paramsObj[i] = data;
				
			case "boolean":
				params[i] = boolean.class;
				paramsObj[i] = data;
				
			 default:
				 params[i] = Class.forName(type);
				 byte b[] = data.getBytes(); 
				 ByteArrayInputStream bi = new ByteArrayInputStream(b);
				 ObjectInputStream si = new ObjectInputStream(bi);
				 paramsObj[i] = si.readObject();
				 isCustomClass = true;
			}
			
			
		}
		
		String returnType = st.nextToken();
		
		
		Class c = Class.forName(packageName+"." + className);
		
		Method[] methods = c.getDeclaredMethods();
		System.out.println(methods);
		
		Method method = c.getMethod(methodName, params);
		
		Object result = method.invoke(c.newInstance(), paramsObj);
		
		
		if(returnType.contains(".") && !returnType.contains("java.lang"))
		{
			 ByteArrayOutputStream bo = new ByteArrayOutputStream(); 
			   ObjectOutputStream so = new ObjectOutputStream(bo); 
			   so.writeObject(result); 
			    so.flush(); 
			    return bo.toString();
		}
		System.out.println(wireProtocolString);
		return result.toString();
	}
}
