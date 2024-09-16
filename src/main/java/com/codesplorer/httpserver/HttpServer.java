package com.codesplorer.httpserver;



import com.codesplorer.httpserver.config.Configuration;
import com.codesplorer.httpserver.config.ConfigurationManager;

public class HttpServer {
public static void main(String[] args) {
	System.out.println("Server Starting...");
	
	ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
    Configuration conf=ConfigurationManager.getInstance().getCurrentConfiguration();
    
    System.out.println("Using Port: "+conf.getPort());
    System.out.println("Using WebRoot: "+conf.getWebroot());
	
}
}
