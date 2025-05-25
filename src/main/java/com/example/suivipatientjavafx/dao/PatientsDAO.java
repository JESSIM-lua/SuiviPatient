package com.example.suivipatientjavafx.dao;

import com.example.suivipatientjavafx.model.Patients;
import com.example.suivipatientjavafx.model.Rendezvous;
import com.example.suivipatientjavafx.model.Traitements;
import com.example.suivipatientjavafx.util.HibernateSessionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PatientsDAO {

    public Patients getPatientById(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query<Patients> query = session.createQuery("FROM Patients WHERE id = :id", Patients.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Patients> getPatientByName(String name) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query<Patients> query = session.createQuery("From Patients WHERE nom = :name", Patients.class);
            query.setParameter("name", name);
            return FXCollections.observableArrayList(query.list());

        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    public ObservableList<Patients> getAllPatients() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query<Patients> query = session.createQuery("FROM Patients", Patients.class);
            return FXCollections.observableArrayList(query.list());
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    public Rendezvous getPatientRendezVous(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query<Rendezvous> query = session.createQuery("SELECT rv FROM Rendezvous rv, Patients p WHERE rv.patient_id = p.id AND p.id = :id", Rendezvous.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<Traitements> getPatientTraitement(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query<Traitements> query = session.createQuery("SELECT t FROM Traitements t WHERE t.patient.id = :id", Traitements.class);
            query.setParameter("id", id);
            return query.getResultList(); // Retourne une liste de traitements
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




    public Patients getTraitementPatient(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query<Patients> query = session.createQuery("SELECT p FROM Patients p, Traitements t WHERE p.id = t.patient_id AND t.id = :id", Patients.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void savePatient(Patients patient) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(patient);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
