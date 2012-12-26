package com.example.presentationserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {

	/**
	 * @param args
	 */
	static Socket s;
	static PrintWriter out;
	static BufferedReader in;
	public static void main(String[] args) {
		try {
			s = new Socket("192.168.1.105", 4444);
			} catch (UnknownHostException e){
				System.out.printf("Unknown host\n");
			} catch (IOException e) {
				System.out.printf("IOException");
				e.printStackTrace();
			}
			try {
			out = new PrintWriter(s.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			} catch (IOException e){
				System.out.printf("Error creating i/o from socket\n");
			}
			out.println("pisze do servera");
	}

}
