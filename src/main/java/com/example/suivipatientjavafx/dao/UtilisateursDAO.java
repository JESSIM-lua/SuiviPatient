package com.example.suivipatientjavafx.dao;

import com.example.suivipatientjavafx.model.Utilisateurs;
import com.example.suivipatientjavafx.util.HibernateSessionFactory;
import com.example.suivipatientjavafx.util.HashUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UtilisateursDAO {

    public Utilisateurs getUserByEmail(String email) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query<Utilisateurs> query = session.createQuery("FROM Utilisateurs WHERE email = :email", Utilisateurs.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getRoleByEmail(String email) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query<String> query = session.createQuery("SELECT role FROM Utilisateurs WHERE email = :email", String.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean validateLogin(String email, String motDePasse) {
        Utilisateurs user = getUserByEmail(email);
        if (user != null) {
            return HashUtil.verifyPassword(motDePasse, user.getMotDePasse()); // Vérification du mot de passe haché
        }
        return false;
    }
}
