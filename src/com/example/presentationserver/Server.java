package com.example.presentationserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	Socket client;
	PrintWriter out;
	BufferedReader in;
	ServerSocket ss;

	public Server(ServerSocket serverS) {
		ss = serverS;
	}

	public void estabilishConnection() {
		System.out.printf("Waiting for client\n");
		try {
			client = ss.accept();
		} catch (IOException e) {
			System.out.printf("Could not accept connection\n");
			System.exit(-1);
		}
		try {
			out = new PrintWriter(client.getOutputStream(), true); // autoFlush
			in = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
		} catch (IOException e) {
			System.out.printf("Error connecting to socket in/out streams\n");
			System.exit(-1);
		}
	}
	public void listen() {
		System.out.printf("Waiting for messages\n");
		String msg;
		try {
		while((msg = in.readLine()) != null) {
			System.out.printf("client: %s", msg);
		} 
		} catch (IOException e){
		}
	}
}
