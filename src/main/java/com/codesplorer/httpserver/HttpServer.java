package com.codesplorer.httpserver;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.codesplorer.httpserver.config.Configuration;
import com.codesplorer.httpserver.config.ConfigurationManager;

public class HttpServer {
public static void main(String[] args) {
	System.out.println("Server Starting...");
	
	ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
    Configuration conf=ConfigurationManager.getInstance().getCurrentConfiguration();
    
    System.out.println("Using Port: "+conf.getPort());
    System.out.println("Using WebRoot: "+conf.getWebroot());
    
    
    try {
		ServerSocket serverSocket=new ServerSocket(conf.getPort());
		Socket socket=serverSocket.accept();
		
		InputStream inputStream=socket.getInputStream();
		OutputStream outputStream=socket.getOutputStream();
		
		String html="<html><head><title>Http Server</title></head><body><h1>Http Server in Java</h1></body></html>";
		
		
		final String CRLF="\n\r";
		
		String response=
				"HTTP/1.1 200 OK"+CRLF+ "Content-Length:"+html.getBytes().length+CRLF+CRLF+html+CRLF+CRLF;
		
		outputStream.write(response.getBytes());
		System.out.println("Executed this line");
		
		inputStream.close();
		outputStream.close();
		socket.close();
		serverSocket.close();
		
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
}
}
