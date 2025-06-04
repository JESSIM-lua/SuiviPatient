package com.example.suivipatientjavafx.dao;

import com.example.suivipatientjavafx.model.Traitements;
import com.example.suivipatientjavafx.util.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

public class TraitementsDAO {


    public Traitements getTraitementById(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("SELECT t FROM Traitements t, Patients p WHERE t.patient_id = p.id AND p.id = :id");
            query.setParameter("id", id);
            return (Traitements) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Traitements> getPatientTraitement(int patientId) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query<Traitements> query = session.createQuery("SELECT t FROM Traitements t WHERE t.patient.id = :id", Traitements.class);
            query.setParameter("id", patientId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // Retourne une liste vide au lieu de `null`
        }
    }



    public List<Object[]> getTraitementDetails() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            String hql = "SELECT p.nom, m.nom, t.dosage, t.duree_jours, t.frequence " +
                    "FROM Patients p, Medicament m, Traitements t " +
                    "WHERE p.id = t.patient_id AND m.id = t.medicament_id";
            org.hibernate.query.Query<Object[]> query = session.createQuery(hql, Object[].class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public void saveTraitement(Traitements traitement) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(traitement);
            session.getTransaction().commit();
            System.out.println("Ajout traitement : " + traitement.getId() + " réussi");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
