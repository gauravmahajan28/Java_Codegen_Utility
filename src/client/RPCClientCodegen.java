package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.net.Socket;
import java.util.ArrayList;

public class RPCClientCodegen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// assuming we get name of class to be generated, interface name, ip, port, server side class name
		// args[0] = name of interface
		// args[1] = package name
		// args[2] = name of class to be generated
		// args[3] = ip of server
		// args[4] = port on which server will listen
		// args[5] = server side class name
		// args[6] = server side package name
		
		try
		{
			
			String packageName = "package " + args[1] + ";\n";
			String imports = " \n import java.io.BufferedReader; \n"+
							  " \n import java.io.DataInputStream; \n"+
							  " \n import java.io.DataOutputStream; \n"+
							  " \n import java.io.InputStreamReader; \n"+
							  "\n import java.net.ServerSocket; \n"+
							  " \n import java.net.Socket; \n"+
							  " \n import java.util.ArrayList; \n";
			
			
			String className = "\n public class " + args[2] + " implements " + args[0] + "\n";
			
			String startBrace = " \n { \n ";
			String endBrace = " \n } \n ";
			
			
			String serverCode = "\n" + 
					" \n public Object sendToServer(String wireProtocolString, String ipAddress, String port) \n"+
			" \n { "+
			"\n	 try "+
			"\n  { "+      
					"\n  Socket s=new Socket(ipAddress,Integer.parseInt(port)); \n"+  
					"\n DataInputStream din=new DataInputStream(s.getInputStream()); "+ 
					"\n DataOutputStream dout=new DataOutputStream(s.getOutputStream());"+    
					"\n dout.writeUTF(wireProtocolString);  "+
					"\n dout.flush();  "+
					"\n byte[] byteArray = new byte[1000];"+
					"\n din.read(byteArray);"+
					"\n dout.close();  "+
					"\n s.close();"+
					"\n return byteArray;"+
					"\n }"+
					"\n catch(Exception e)"+
					"\n {"+
					"\n System.out.println(e);"+
					"\n }  "+
					"\n return null;"+
					"\n }";
					
						
			
			ArrayList<String> methodsGenerated = new ArrayList();
			
			Class c = Class.forName(args[1]+"." + args[0]);
			
			Method[] methods = c.getDeclaredMethods();
			System.out.println(c);
			for (Method method : methods) 
			{
				
				String methodString = "";
				if(Modifier.isPublic(method.getModifiers()))
						methodString = methodString + " public ";
				
				methodString  = "  " + methodString + " " + method.getReturnType()  + " ";
				methodString  = "  " + methodString + " " + method.getName() + " ";
				
				
			    // do what you have to do with the method
			    System.out.println(method.getName());
			    System.out.println(method.getReturnType());
			   
			    
			    methodString  = " " + methodString + " ( ";
			    int parametersCount = method.getParameterCount();
			    
			    Parameter[] parameters = method.getParameters();
			    for(Parameter parameter : parameters)
			    {
			    	methodString  = " " + methodString + " " +  parameter.getType() ;
			    	methodString  = " " + methodString + " " +  parameter.getName();
			    	methodString  = " " + methodString + ",";
			    	System.out.println(parameter.getType());
			    	System.out.println(parameter.getName());
			    }
			    methodString = methodString.substring(0, methodString.length() - 1);
			    methodString  = " " + " " + methodString + " ) \n ";
			    methodString  = " \n " + " " + methodString + " { \n";
			    
			    methodString  = " \n " + methodString + " \n " + " String packageNameOnServer =  " + "\"" + args[5] + "\";";
			    methodString  = " \n " + methodString + " \n " +  " String classNameOnServer =  " + "\"" + args[6] + "\";";
			    methodString  = " \n " + methodString + " \n " + " String functionName =  " + "\"" + method.getName() + "\";";
			    methodString  = " \n " + methodString + " \n " + " int noOfParams  =  " + parametersCount + ";";
			    
			    methodString  = " \n " + methodString + " \n " +" String numberOfParameters = " +  "\"" + parametersCount + "\";";
			    
			    methodString  = " \n " + methodString + " \n " + " ArrayList<Object> objects = new ArrayList();";
			    
			    methodString  = " \n " + methodString + " \n " + " objects.add(packageNameOnServer); ";
			    
			    methodString  = " \n " + methodString + " \n " + " objects.add(classNameOnServer);";
			    
			    methodString  = " \n " + methodString + " \n " + " objects.add(functionName); ";
			    
			    methodString  = " \n " + methodString + " \n " + " objects.add(numberOfParameters); ";
			    
			   for(int i =0; i < parametersCount; i++)
			   {
				   methodString  = " \n " + methodString  + " \n " + " objects.add(" + "\"" +  parameters[i].getType() + "\"" + "); ";
				   methodString  = " \n " + methodString +" \n " +  " objects.add(" +  parameters[i].getName()  + "); ";
					   
			   }
			    
			   methodString  = " \n " + methodString + " \n " +" String ipAddress = " + "\"" + args[3] + "\";";
			   methodString  = " \n " + methodString + " \n " +" String port = " + "\"" + args[4] + "\";";
				  
			   methodString  = " \n " + methodString + " \n " +" objects.add(numberOfParameters); ";
			   methodString  = " \n " + methodString + " \n " +" String wireProtocolString = RPCCodegenUtilityMethodsWireProtocol.getWireProtocolStringFromInterface(objects); \n";
			   
			   methodString  = " \n " + methodString + method.getReturnType() + " answer = " +   "( " + RPCCodegenUtilityMethodsWireProtocol.getObjectType(method.getReturnType().toString()) + ")" + "sendToServer(wireProtocolString, ipAddress, port); \n";
			   
			   methodString  = " \n " + methodString + " return answer; ";
		  
			    
			    methodString  = " \n " + methodString + " } ";
			    methodsGenerated.add(methodString);
			    
			} // for
			
			
			methodsGenerated.add(serverCode);
			
			
			//File file = new File("client/" + args[2] + ".java");
			PrintWriter writer = new PrintWriter("src/client/"+args[2] + ".java");
				writer.println(packageName);
			writer.println(imports);
			writer.println(className);
			writer.println(startBrace);
			
			for(int i = 0; i < methodsGenerated.size(); i++)
			{
				writer.println(methodsGenerated.get(i));
			}
			writer.println(endBrace);
			writer.close();
			
			
		}
		catch(Exception e)
		{
			System.out.println("error in generating code :" + e.getMessage());
		}
		
		
		
	}

}
