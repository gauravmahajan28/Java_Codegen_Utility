package client;

 
 import java.io.BufferedReader; 
 
 import java.io.DataInputStream; 
 
 import java.io.DataOutputStream; 
 
 import java.io.InputStreamReader; 

 import java.net.ServerSocket; 
 
 import java.net.Socket; 
 
 import java.util.ArrayList; 
 
 import java.io.ByteArrayOutputStream; 
 
 import java.io.ObjectOutputStream; 
 
 import java.io.ByteArrayInputStream; 
 
 import java.io.ObjectInputStream; 


 public class RPCInterfaceImplementation implements RPCInterface

 
 { 
 
                public  int  add  (  int quantity, int price ) throws Exception 
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
  objects.add("int");  
  String ipAddress = "localhost"; 
  String port = "3333"; 
  String wireProtocolString = RPCCodegenUtilityMethodsWireProtocol.getWireProtocolStringFromInterface(objects); 
int answer = Integer.parseInt( sendToServer(wireProtocolString, ipAddress, port)); 
 return answer; 
 } 
 
  
  
                public  java.lang.String  concat  (  java.lang.String a, java.lang.String b ) throws Exception 
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
  objects.add("class java.lang.String");  
  objects.add(a);  
  objects.add("a");  
  objects.add("class java.lang.String");  
  objects.add(b);  
  objects.add("b");  
  objects.add("java.lang.String");  
  String ipAddress = "localhost"; 
  String port = "3333"; 
  String wireProtocolString = RPCCodegenUtilityMethodsWireProtocol.getWireProtocolStringFromInterface(objects); 
java.lang.String answer = (String)( sendToServer(wireProtocolString, ipAddress, port)); 
 return answer; 
 } 
 
                public  java.lang.Integer  addInteger  (  java.lang.Integer a, int b ) throws Exception 
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
  objects.add("java.lang.Integer");  
  String ipAddress = "localhost"; 
  String port = "3333"; 
  String wireProtocolString = RPCCodegenUtilityMethodsWireProtocol.getWireProtocolStringFromInterface(objects); 
java.lang.Integer answer = Integer.parseInt( sendToServer(wireProtocolString, ipAddress, port)); 
 return answer; 
 } 
 
  
  
                public  float  subtract  (  float quantity, float price ) throws Exception 
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
  objects.add("float");  
  String ipAddress = "localhost"; 
  String port = "3333"; 
  String wireProtocolString = RPCCodegenUtilityMethodsWireProtocol.getWireProtocolStringFromInterface(objects); 
float answer = Float.parseFloat( sendToServer(wireProtocolString, ipAddress, port)); 
 return answer; 
 } 
 
 
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
  ByteArrayOutputStream bo = new ByteArrayOutputStream(); 
   ObjectOutputStream so = new ObjectOutputStream(bo); 
   so.writeObject(obe); 
    so.flush(); 
    objects.add(bo.toString());   
  objects.add("obe");  
  objects.add("client.CustomClass");  
  String ipAddress = "localhost"; 
  String port = "3333"; 
  String wireProtocolString = RPCCodegenUtilityMethodsWireProtocol.getWireProtocolStringFromInterface(objects); 

 byte b[] = sendToServer(wireProtocolString, ipAddress, port).getBytes();
 ByteArrayInputStream bi = new ByteArrayInputStream(b);
  ObjectInputStream si = new ObjectInputStream(bi);
   return (client.CustomClass)si.readObject(); } 

 
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
 
