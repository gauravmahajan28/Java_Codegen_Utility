package client;

import java.util.ArrayList;

public class RPCCodegenUtilityMethodsWireProtocol 
{
	
	
	public static String getWireProtocolStringFromInterface(ArrayList<Object> objects)
	{
		String wireProtocolString = "";
		for(int i = 0; i < objects.size(); i++)
		{
			wireProtocolString = wireProtocolString + objects.get(i)+"#";
		}
		wireProtocolString = wireProtocolString.substring(0, wireProtocolString.length() - 1);
		return wireProtocolString;
	}
	
	
	public static String getObjectType(String primitiveDataType)
	{
		   
		
		
		if(primitiveDataType.contains("class"))
		{
			
			primitiveDataType = primitiveDataType.split(" ")[1];
	
		}
	
		
		
		switch(primitiveDataType)
		{
		
		case "java.lang.Boolean":
			return "Boolean.parseBoolean";
		
		case "boolean":
			return "Boolean.parseBoolean";
			
		case "java.lang.Integer":
			return "Integer.parseInt";
			
		case "int":
			return "Integer.parseInt";
			
		case "float":
			return "Float.parseFloat";
			
		case "java.lang.Float":
			return "Float.parseFloat";
			
		case "double":
			return "Double.parseDouble";
			
		case "java.lang.Double":
			return "Double.parseDouble";
			
		case "byte":
			return "Double.parseByte";
			
		case "java.lang.Byte":
			return "Double.parseByte";
			
		case "short":
			return "Double.parseShort";
			
		case "java.lang.Short":
			return "Double.parseShort";
			
		case "long":
			return "Double.parseLong";
			
		case "java.lang.Long":
			return "Double.parseLong";
			
		case "char":
				return "(Character)";
				
		case "java.lang.String":
				return "(String)";
				
		default:
			return primitiveDataType;
					
		}
		
	}

}
