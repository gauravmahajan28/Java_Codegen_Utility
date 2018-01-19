import java.io.DataOutputStream;
import java.net.Socket;


public class TestImplementation
    implements Test
{


    public int add(int a, int b) {
         
 try{   
 		Socket s=new Socket("localhost",6666); 
 		DataOutputStream dout=new DataOutputStream(s.getOutputStream()); 
 		dout.writeUTF("Hello Server");  
 		dout.flush(); 	
 		dout.close();  	
 		s.close(); 
 	return a+b; 		}
 catch(Exception e)
{	System.out.println(e); 
	 return -1;
} 
    }

}
