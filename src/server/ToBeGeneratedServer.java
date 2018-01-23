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
import java.lang.reflect.Parameter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ToBeGeneratedServer {

	// name of interface file
	// location of jar is hardcoded
	
	
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
		
		Class serverInterface = Class.forName("server.ServerInterface");
		
		Map<String, Integer> parameterPositon = new HashMap<>();
		
		Method[] interfaceMethods = serverInterface.getMethods();
		
		for(Method method : interfaceMethods)
		{
			System.out.println(method.getName());
			System.out.println(method.getParameterCount());
			if(method.getName().equals(methodName) && method.getParameterCount() == numberOfParameters)
			{
				int count = 0;
				for(Parameter parameter : method.getParameters())
				{
					parameterPositon.put(parameter.getName(), count++);
				}
				break;
			}
		}
		
		
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
			
			
			int position = parameterPositon.get(name);
		
			switch(type)
			{
			
			
			
			
			case "int":
				params[position] = int.class;
				paramsObj[position] = Integer.parseInt(data);
				break;
				
			case "char":
				params[position] = char.class;
				paramsObj[position] = data;
				break;
				
			case "float":
				params[position] = float.class;
				paramsObj[position] = Float.parseFloat(data);
				break;
				
			case "double":
				params[position] = double.class;
				paramsObj[position] = Double.parseDouble(data);
				break;
				
			case "java.lang.Integer":
				params[position] = Integer.class;
				paramsObj[position] = Integer.parseInt(data);
				break;
				
			case "java.lang.Double":
				params[position] = Double.class;
				paramsObj[position] = Double.parseDouble(data);
				break;
				
			case "Float":
				params[position] = Float.class;
				paramsObj[position] = Float.parseFloat(data);
				break;
				
			case "java.lang.Character":
				params[position] = Character.class;
				paramsObj[position] = data;
				break;
				
			case "java.lang.String":
				params[position] = String.class;
				paramsObj[position] = data;
				break;
				
			case "java.lang.Boolean":
				params[position] = Boolean.class;
				paramsObj[position] = data;
				break;
				
			case "boolean":
				params[position] = boolean.class;
				paramsObj[position] = data;
				break;
				
			 default:
				 params[position] = Class.forName(type);
				 byte b[] = data.getBytes(); 
				 ByteArrayInputStream bi = new ByteArrayInputStream(b);
				 ObjectInputStream si = new ObjectInputStream(bi);
				 paramsObj[position] = si.readObject();
				 isCustomClass = true;
			}
			
			
		}
		
		String returnType = st.nextToken();
		
		TestClassLoading.loadClasses();
		
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
