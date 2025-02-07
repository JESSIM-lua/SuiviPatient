package com.example.suivipatientjavafx.dao;

import com.example.suivipatientjavafx.model.Medicament;
import com.example.suivipatientjavafx.util.HibernateSessionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class MedicamentDAO {

    /**
     * Récupère tous les médicaments depuis la base de données.
     *
     * @return ObservableList contenant tous les médicaments.
     */
    public ObservableList<Medicament> getAllMedicaments() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query<Medicament> query = session.createQuery("FROM Medicament", Medicament.class);
            List<Medicament> list = query.list();
            System.out.println("DAO : Nombre de médicaments trouvés = " + list.size());
            return FXCollections.observableArrayList(list);
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    public Medicament updateMedicament(Medicament medicament) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(medicament);
            session.getTransaction().commit();
            return medicament;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<String> getMedicamentsNames() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query<String> query = session.createQuery("SELECT nom FROM Medicament", String.class);
            List<String> medicamentNames = query.list();
            return FXCollections.observableArrayList(medicamentNames);
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

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
