package client;

import java.util.ArrayList;

public class RPCCodegenUtilityMethodsWireProtocol 
{
	
	
	public static String getWireProtocolStringFromInterface(ArrayList<Object> objects)
	{
		
		return "hello";
	}
	
	public static String getObjectType(String primitiveDataType)
	{
		switch(primitiveDataType)
		{
		case "int":
			return "Integer";
			
		case "float":
			return "Float";
			
		case "double":
			return "Double";
			
		case "char":
				return "Character";
				
				
		case "String":
				return "String";
					
		}
		return "String";
	}

}
