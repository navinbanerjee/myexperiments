package com.nb.elevator;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyElevator {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket clientSoc = new Socket("localhost", 9000);
		
		OutputStream outStream = clientSoc.getOutputStream();
		
		DataOutputStream out = new DataOutputStream(outStream);
		
		out.writeUTF("call 12 up");
		
		clientSoc.close();
	}

}
