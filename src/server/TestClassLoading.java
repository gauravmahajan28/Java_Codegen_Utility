package server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

 class TestClassLoading {

	public static void loadClasses() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub

		try {
			JarFile jarFile = new JarFile("C://Users//gaurav//Downloads//AdditionService.jar");
			Enumeration<JarEntry> e = jarFile.entries();
			URL[] urls = { new URL("jar:file:" + "C://Users//gaurav//Downloads//AdditionService.jar" +"!/") };
			URLClassLoader cl = URLClassLoader.newInstance(urls);
			String className = "";
			
			URLClassLoader classLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();
			Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			method.setAccessible(true);
			method.invoke(classLoader, urls);
			
			while (e.hasMoreElements()) 
			{
			    JarEntry je = e.nextElement();
			    if(je.isDirectory() || !je.getName().endsWith(".class")){
			        continue;
			    }
			    // -6 because of .class
			     className = je.getName().substring(0,je.getName().length()-6);
			    className = className.replace('/', '.');
			    Class c = cl.loadClass(className);
			    
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
