package com.codesplorer.httpserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpConnectionWorkerThread extends Thread{

	private Socket socket;
	private final static Logger LOGGER=LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
	public HttpConnectionWorkerThread(Socket socket) {
		this.socket=socket;
	}
	
	@Override
	public void run() {
		InputStream inputStream=null;
		OutputStream outputStream=null;
		try {
			inputStream=socket.getInputStream();
			outputStream=socket.getOutputStream();
			
			int _byte;
			while((_byte=inputStream.read())>=0) {
				System.out.print((char)_byte);
			}
		
			
			String html="<html><head><title>Http Server</title></head><body><h1>Http Server in Java</h1></body></html>";
			
			
			final String CRLF="\n\r";
			
			String response=
					"HTTP/1.1 200 OK"+CRLF+ "Content-Length:"+html.getBytes().length+CRLF+CRLF+html+CRLF+CRLF;
			
			outputStream.write(response.getBytes());
			LOGGER.info("Worker Thread Execution:");
			
			
		} catch (IOException e) {
          LOGGER.error("Problem with communication",e);
			e.printStackTrace();
		}finally {
			if(inputStream!=null) {
				try {
					inputStream.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			
			if(outputStream!=null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(socket!=null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
		}
	}

}
