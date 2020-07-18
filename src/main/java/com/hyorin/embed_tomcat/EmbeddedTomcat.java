package com.hyorin.embed_tomcat;

import org.apache.catalina.*;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import javax.servlet.ServletException;
import java.io.File;

/**
 * 嵌入式Tomcat服务器
 */
public class EmbeddedTomcat {
    public static void main(String[] args){
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.getInteger("port", 8080));
        tomcat.getConnector();
        Context context = null;
        try {
            context = tomcat.addWebapp("", new File("src/main/webapp").getAbsolutePath());
            WebResourceRoot resources = new StandardRoot(context);
            resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                    new File("target/classes").getAbsolutePath(), "/"));
            context.setResources(resources);
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

       /* Tomcat tomcat = new Tomcat();
        Server server = tomcat.getServer();
        Service service = tomcat.getService();
        service.setName("Tomcat_embedded");
        Connector connector = tomcat.getConnector();
        Context context = tomcat.addWebapp("", "/");
        tomcat.addServlet("", "Test", new Testservlet());
        context.addServletMappingDecoded("/*", "Test");
        try {
            server.start();
            server.await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }*/
    }
}
