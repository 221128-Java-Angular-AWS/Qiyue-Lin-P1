package com.revature.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DependencyLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("server context start");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("server context end");
    }
}
