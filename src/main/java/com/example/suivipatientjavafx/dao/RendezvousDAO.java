package com.example.suivipatientjavafx.dao;

import com.example.suivipatientjavafx.model.Rendezvous;
import com.example.suivipatientjavafx.util.HibernateSessionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Query;
import org.hibernate.Session;

public class RendezvousDAO {

    public ObservableList<Rendezvous> getAllRendezvous() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query<Rendezvous> query = session.createQuery("FROM Rendezvous", Rendezvous.class);
            return FXCollections.observableArrayList(query.list());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
