/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletContext
 *  javax.servlet.ServletContextEvent
 *  javax.servlet.ServletContextListener
 *  javax.servlet.annotation.WebListener
 */
package com.testing;

import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class FileLocationContextListener
implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String rootPath = System.getProperty("catalina.home");
        ServletContext ctx = servletContextEvent.getServletContext();
        String relativePath = ctx.getInitParameter("tempfile.dir");
        File file = new File(String.valueOf(rootPath) + File.separator + relativePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        System.out.println("File Directory created to be used for storing files");
        ctx.setAttribute("FILES_DIR_FILE", (Object)file);
        ctx.setAttribute("FILES_DIR", (Object)(String.valueOf(rootPath) + File.separator + relativePath));
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
