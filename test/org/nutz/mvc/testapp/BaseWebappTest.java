package org.nutz.mvc.testapp;

import java.io.File;
import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.After;
import org.junit.Before;
import org.nutz.http.Http;
import org.nutz.http.Request;
import org.nutz.http.Request.METHOD;
import org.nutz.http.Response;
import org.nutz.http.Sender;
import org.nutz.lang.Files;

/**
 * 需要Jetty 7.3.1 的jar包
 * @author wendal
 *
 */
public abstract class BaseWebappTest {
	
	protected Server server;
	
	protected Response resp;

	@Before
	public void startServer() throws Throwable{
		
		try {
			String WEBAPPDIR = "org/nutz/mvc/testapp/ROOT";
			File root = Files.findFile(WEBAPPDIR);
			server = new Server(8888);
			String warUrlString = root.toURI().toURL().toExternalForm();
			server.setHandler(new WebAppContext(warUrlString, getContextPath()));
			server.start();
		} catch (Throwable e) {
			if(server != null)
				server.stop();
		}
	}
	
	@After
	public void shutdownServer() throws Throwable{
		server.stop();
	}
	
	public String getContextPath(){
		return "/nutztest";
	}
	
	public Response get(String path){
		resp = Http.get("http://localhost:8888"+getContextPath()+path);
		return resp;
	}
	
	public Response post(String path,Map<String, Object> params){
		resp = Sender.create(Request.create("http://localhost:8888"+getContextPath()+path, METHOD.POST, params, null)).send();
		return resp;
	}
}
