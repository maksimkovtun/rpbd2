package com.example.test2.Hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;

import java.net.URISyntaxException;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Ошибка при инициализации SessionFactory: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static DatabaseConfig config;
    public static SessionFactory buildSessionFactory() throws URISyntaxException {
        if (sessionFactory != null) {
            return sessionFactory;
        }

        config = new DatabaseConfigDialog().showDialog();

        if (config == null) {
            System.out.println("Config = NULL");
            return null;
        }

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .applySetting("hibernate.connection.url", config.getUrl())
                .applySetting("hibernate.connection.username", config.getUsername())
                .applySetting("hibernate.connection.password", config.getPassword())
                .build();

        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            ThreadLocalSessionContext.bind(sessionFactory.openSession());
        } catch (Exception e) {
            System.err.println("Ошибка при инициализации SessionFactory: " + e.getMessage());
            e.printStackTrace();
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory(DatabaseConfig config) {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.configure("hibernate.cfg.xml");

                configuration.setProperty("hibernate.connection.url", config.getUrl());
                configuration.setProperty("hibernate.connection.username", config.getUsername());
                configuration.setProperty("hibernate.connection.password", config.getPassword());

                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
                registryBuilder.applySettings(configuration.getProperties());
                StandardServiceRegistry serviceRegistry = registryBuilder.build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("SessionFactory != NULL");
        }
        return sessionFactory;
    }
    public static DatabaseConfig getConfig(){
        return config;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public static boolean isSessionOpen() {
        if (sessionFactory != null) {
            return sessionFactory.getCurrentSession().isOpen();
        }
        return false;
    }

    public static void closeSession() {
        if (sessionFactory != null) {
            ThreadLocalSessionContext.unbind(sessionFactory);
        }
    }
}