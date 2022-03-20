package ro.info.iasi.fiipractic;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.apache.jasper.servlet.JasperInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ro.info.iasi.fiipractic.config.WebMvcConfig;

import java.io.File;

public class Application {

    public static void main(String[] args) throws LifecycleException {
        File base = new File("WebContent");

        final Tomcat tomcat = new Tomcat();
        tomcat.setPort(9000);
        tomcat.setBaseDir("target");

        Context rootCtx = tomcat.addContext("", base.getAbsolutePath());
        AnnotationConfigWebApplicationContext annotationContext = new AnnotationConfigWebApplicationContext();
        annotationContext.register(WebMvcConfig.class);

        DispatcherServlet dispatcher = new DispatcherServlet(annotationContext);
        Tomcat.addServlet(rootCtx, "SpringMVCDispatcher", dispatcher);
        rootCtx.addServletMappingDecoded("/", "SpringMVCDispatcher");

        Wrapper jspServlet = rootCtx.createWrapper();
        jspServlet.setName("jsp");
        jspServlet.setServletClass("org.apache.jasper.servlet.JspServlet");
        jspServlet.setLoadOnStartup(2);

        rootCtx.addChild(jspServlet);
        rootCtx.addServletMappingDecoded("*.jsp", "jsp");
        rootCtx.addServletContainerInitializer(new JasperInitializer(), null);

        tomcat.start();
        tomcat.getServer().await();
    }
}
