import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.sun.codemodel.ClassType;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;

//import javax.lang.model.element.Modifier;

public class SimpleCodegen {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
	/*	JCodeModel cm = new JCodeModel();
		JDefinedClass dc = cm._class("TestImplementation", ClassType.CLASS)._implements(Test.class);
		JMethod m = dc.method(JMod.PUBLIC, int.class, "add");
		m.param(int.class, "a");
		m.param(int.class, "b");
		
		cm.ref(DataOutputStream.class);
		cm.ref(Socket.class);
		
		
		String socketClientCode = " \n try{  "+     
								  " \n 		Socket s=new Socket(\"localhost\",6666); " +  
								  "\n 		DataOutputStream dout=new DataOutputStream(s.getOutputStream()); " +   
								  "\n 		dout.writeUTF(\"Hello Server\"); "+  
								  " \n 		dout.flush(); "+ 
								  "	\n 		dout.close();  "+ 
								  "	\n 		s.close(); "
								  + "\n 	return a+b;"  
								+ " 		}\n catch(Exception e)\n{	System.out.println(e); \n	 return -1;\n} ";
				
				
		
		
		m.body().directStatement(socketClientCode);
		
	
		File file = new File("./src/");
		file.mkdirs();
		cm.build(file);
*/
	}

}
