package com.example.suivipatientjavafx.dao;

import com.example.suivipatientjavafx.model.Medicament;
import com.example.suivipatientjavafx.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class MedicamentDAO {

    public Medicament getMedicamentByName(String name) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query<Medicament> query = session.createQuery("FROM Medicament WHERE nom = :name", Medicament.class);
            query.setParameter("name", name);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
