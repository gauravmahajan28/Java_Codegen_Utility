package client;

 
 import java.io.BufferedReader; 
 
 import java.io.DataInputStream; 
 
 import java.io.DataOutputStream; 
 
 import java.io.InputStreamReader; 

 import java.net.ServerSocket; 
 
 import java.net.Socket; 
 
 import java.util.ArrayList; 


 public class RPCInterfaceImplementation implements RPCInterface

 
 { 
 
 
             public  client.CustomClass  add2  (  client.CustomClass obe ) throws Exception
  { 
 
  String packageNameOnServer =  "AdditionService"; 
  String classNameOnServer =  "server"; 
  String functionName =  "add2"; 
  int noOfParams  =  1; 
  String numberOfParameters = "1"; 
  ArrayList<Object> objects = new ArrayList(); 
  objects.add(packageNameOnServer);  
  objects.add(classNameOnServer); 
  objects.add(functionName);  
  objects.add(numberOfParameters);  
  objects.add("class client.CustomClass");  
  objects.add(obe);  
  objects.add("obe");  
  String ipAddress = "localhost"; 
  String port = "3333"; 
  String wireProtocolString = RPCCodegenUtilityMethodsWireProtocol.getWireProtocolStringFromInterface(objects); 
client.CustomClass answer = (CustomClass)Class.forName(( sendToServer(wireProtocolString, ipAddress, port))).newInstance(); 
 return answer; 
 } 
 
  
                public  int  add  (  int quantity, int price ) 
  { 
 
  String packageNameOnServer =  "AdditionService"; 
  String classNameOnServer =  "server"; 
  String functionName =  "add"; 
  int noOfParams  =  2; 
  String numberOfParameters = "2"; 
  ArrayList<Object> objects = new ArrayList(); 
  objects.add(packageNameOnServer);  
  objects.add(classNameOnServer); 
  objects.add(functionName);  
  objects.add(numberOfParameters);  
  objects.add("int");  
  objects.add(quantity);  
  objects.add("quantity");  
  objects.add("int");  
  objects.add(price);  
  objects.add("price");  
  String ipAddress = "localhost"; 
  String port = "3333"; 
  String wireProtocolString = RPCCodegenUtilityMethodsWireProtocol.getWireProtocolStringFromInterface(objects); 
int answer = Integer.parseInt( sendToServer(wireProtocolString, ipAddress, port)); 
 return answer; 
 } 
 
  
  
                public  java.lang.String  concat  (  float quantity, float price ) 
  { 
 
  String packageNameOnServer =  "AdditionService"; 
  String classNameOnServer =  "server"; 
  String functionName =  "concat"; 
  int noOfParams  =  2; 
  String numberOfParameters = "2"; 
  ArrayList<Object> objects = new ArrayList(); 
  objects.add(packageNameOnServer);  
  objects.add(classNameOnServer); 
  objects.add(functionName);  
  objects.add(numberOfParameters);  
  objects.add("float");  
  objects.add(quantity);  
  objects.add("quantity");  
  objects.add("float");  
  objects.add(price);  
  objects.add("price");  
  String ipAddress = "localhost"; 
  String port = "3333"; 
  String wireProtocolString = RPCCodegenUtilityMethodsWireProtocol.getWireProtocolStringFromInterface(objects); 
java.lang.String answer = (String)( sendToServer(wireProtocolString, ipAddress, port)); 
 return answer; 
 } 
 
  
                public  float  subtract  (  float quantity, float price ) 
  { 
 
  String packageNameOnServer =  "AdditionService"; 
  String classNameOnServer =  "server"; 
  String functionName =  "subtract"; 
  int noOfParams  =  2; 
  String numberOfParameters = "2"; 
  ArrayList<Object> objects = new ArrayList(); 
  objects.add(packageNameOnServer);  
  objects.add(classNameOnServer); 
  objects.add(functionName);  
  objects.add(numberOfParameters);  
  objects.add("float");  
  objects.add(quantity);  
  objects.add("quantity");  
  objects.add("float");  
  objects.add(price);  
  objects.add("price");  
  String ipAddress = "localhost"; 
  String port = "3333"; 
  String wireProtocolString = RPCCodegenUtilityMethodsWireProtocol.getWireProtocolStringFromInterface(objects); 
float answer = Float.parseFloat( sendToServer(wireProtocolString, ipAddress, port)); 
 return answer; 
 } 
 
  
                public  java.lang.Integer  addInteger  (  java.lang.Integer a, int b ) 
  { 
 
  String packageNameOnServer =  "AdditionService"; 
  String classNameOnServer =  "server"; 
  String functionName =  "addInteger"; 
  int noOfParams  =  2; 
  String numberOfParameters = "2"; 
  ArrayList<Object> objects = new ArrayList(); 
  objects.add(packageNameOnServer);  
  objects.add(classNameOnServer); 
  objects.add(functionName);  
  objects.add(numberOfParameters);  
  objects.add("class java.lang.Integer");  
  objects.add(a);  
  objects.add("a");  
  objects.add("int");  
  objects.add(b);  
  objects.add("b");  
  String ipAddress = "localhost"; 
  String port = "3333"; 
  String wireProtocolString = RPCCodegenUtilityMethodsWireProtocol.getWireProtocolStringFromInterface(objects); 
java.lang.Integer answer = Integer.parseInt( sendToServer(wireProtocolString, ipAddress, port)); 
 return answer; 
 } 

 
 public String sendToServer(String wireProtocolString, String ipAddress, String port) 
 
 { 
	 try 
  { 
  Socket s=new Socket(ipAddress,Integer.parseInt(port)); 

 DataInputStream din=new DataInputStream(s.getInputStream()); 
 DataOutputStream dout=new DataOutputStream(s.getOutputStream());
 dout.writeUTF(wireProtocolString);  
 dout.flush();  
 String obj = din.readUTF();
 dout.close();  
 s.close();
 return obj;
 }
 catch(Exception e)
 {
 System.out.println(e);
 }  
 return null;
 } 
 } 
 
