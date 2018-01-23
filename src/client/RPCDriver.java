package client;

public class RPCDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			RPCInterfaceImplementation rpcInterfaceImplementation = new RPCInterfaceImplementation();
			try {
				System.out.println(rpcInterfaceImplementation.add(10, 20));
				System.out.println(rpcInterfaceImplementation.subtract(13.3f, 0.3f));
			//	System.out.println(rpcInterfaceImplementation.concat("abc", "pqr"));
			/*	CustomClass customClassTest = new CustomClass();
				customClassTest.a = 10;
				customClassTest.b = 20;
				
				CustomClass customClass = rpcInterfaceImplementation.add2(customClassTest);
				System.out.println(customClass.a + customClass.b);*/
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//
	
			
	}

}
