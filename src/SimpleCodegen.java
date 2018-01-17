import java.io.File;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JMethod;

import javax.lang.model.element.Modifier;

public class SimpleCodegen {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		JCodeModel cm = new JCodeModel();
		JDefinedClass dc = cm._class("Bar");
		JMethod m = dc.method(0, int.class, "foo");
		m.body()._return(JExpr.lit(5));
		
		JMethod m2 = dc.method(0, int.class, "fun2");
		m2.body()._return(JExpr.lit(5));
		

		File file = new File("./src/");
		file.mkdirs();
		cm.build(file);

	}

}
