package com.excilys.formation.java.projet.DAO;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
 
public final class ContextListener implements ServletContextListener {
 
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionManager.configureConnPool();
    }
 
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionManager.shutdownConnPool();
    }
 
}