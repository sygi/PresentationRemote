package com.example.presentationserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.*;

public class Server {
	Socket client;
	PrintWriter out;
	BufferedReader in;
	ServerSocket ss;
	Robot robot;
	public Server(ServerSocket serverS) {
		ss = serverS;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	private void serve(String command){
		if (command.equals("next")){
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			//robot.keyPress(KeyEvent.KeyCode.KEYRIGHT_ARROW);
		} else if (command.equals("prev")){
			robot.keyPress(KeyEvent.VK_UP);
			robot.keyRelease(KeyEvent.VK_UP);
		} else if (command.equals("connected")){
			out.println("ok");
		} else {
			System.out.printf("Unknown command: %s\n", command);
		}
	}
	public void listen() {
		System.out.printf("Waiting for messages\n");
		String msg;
		try {
		while((msg = in.readLine()) != null) {
			System.out.printf("get: %s\n", msg);
			serve(msg);
		} 
		} catch (IOException e){
		}
	}
}
