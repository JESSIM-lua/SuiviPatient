package com.example.suivipatientjavafx.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml"); // Correction : utilisation d'une String
            sessionFactory = configuration.buildSessionFactory(); // Suppression du deuxième appel inutile
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Initialisation de HibernateSessionFactory échouée !");
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
