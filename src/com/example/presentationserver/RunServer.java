package com.example.presentationserver;

import java.io.IOException;
import java.net.ServerSocket;

public class RunServer {

	/**
	 * @param args
	 */
	private static ServerSocket ss;
	public static void main(String[] args) {
		try {
			ss = new ServerSocket(4444);
		} catch (IOException e){
			System.out.printf("Could not listen to port 4444\n");
			System.exit(-1);
		}
		Server s = new Server(ss);
		s.estabilishConnection();
		s.listen();
	}

}
