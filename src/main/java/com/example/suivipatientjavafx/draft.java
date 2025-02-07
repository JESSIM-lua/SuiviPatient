package com.example.suivipatientjavafx;

import com.example.suivipatientjavafx.dao.MedicamentDAO;
import com.example.suivipatientjavafx.model.Medicament;
import javafx.collections.ObservableList;

public class draft {
    public static void main(String[] args) {
        MedicamentDAO medicamentDAO = new MedicamentDAO();
        ObservableList<Medicament> medicaments = medicamentDAO.getAllMedicaments();
        System.out.println("Contrôleur : Nombre de médicaments récupérés = " + medicaments.size());
        for (Medicament medicament : medicaments) {
            System.out.println(medicament);
        }
    }
}
