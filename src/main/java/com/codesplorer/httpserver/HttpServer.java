package com.codesplorer.httpserver;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codesplorer.httpserver.config.Configuration;
import com.codesplorer.httpserver.config.ConfigurationManager;
import com.codesplorer.httpserver.core.ServerListenerThread;

public class HttpServer {
	
	private final static Logger LOGGER=LoggerFactory.getLogger(HttpServer.class);
public static void main(String[] args) {

	LOGGER.info("Server starting...");
	
	ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
    Configuration conf=ConfigurationManager.getInstance().getCurrentConfiguration();
    
    LOGGER.info("Using Port: "+conf.getPort());
    
    LOGGER.info("Using WebRoot: "+conf.getWebroot());
    
    try {
		ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
		serverListenerThread.start();
	} catch (IOException e) {
		e.printStackTrace();
	}
    
   
    System.out.println("Executed this line in main");
}
}
