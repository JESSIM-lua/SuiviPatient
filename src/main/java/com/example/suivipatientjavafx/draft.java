package com.example.suivipatientjavafx;

import com.example.suivipatientjavafx.dao.*;
import com.example.suivipatientjavafx.model.Medicament;
import com.example.suivipatientjavafx.model.Patients;
import com.example.suivipatientjavafx.model.Rendezvous;
import com.example.suivipatientjavafx.model.Traitements;
import javafx.collections.ObservableList;

import java.util.List;

public class draft {
    public static void main(String[] args) {
//        MedicamentDAO medicamentDAO = new MedicamentDAO();
//        ObservableList<Medicament> medicaments = medicamentDAO.getAllMedicaments();
//        System.out.println("Contrôleur : Nombre de médicaments récupérés = " + medicaments.size());
//        for (Medicament medicament : medicaments) {
//            System.out.println(medicament);
//        }

//        UtilisateursDAO utilisateursDAO = new UtilisateursDAO();
//        System.out.println(utilisateursDAO.getRoleByEmail("alice.medina@example.com"));

//        PatientsDAO patientsDAO = new PatientsDAO();
//        System.out.println(patientsDAO.getPatientById(1));

//        TraitementsDAO traitementsDAO = new TraitementsDAO();
//        List<Object[]> traitementDetails = traitementsDAO.getTraitementDetails();
//
//        for (Object[] detail : traitementDetails) {
//            System.out.println("Patient: " + detail[0] + ", Medicament: " + detail[1] + ", Dosage: " + detail[2] + ", Durée: " + detail[3] + " jours, Fréquence: " + detail[4]);
//        }
//
//        RendezvousDAO rendezvousDAO = new RendezvousDAO();
//
//        ObservableList<Rendezvous> rendezvous = rendezvousDAO.getAllRendezvous();
//
//        for (Rendezvous rdv : rendezvous) {
//            System.out.println(rdv);
//        }

        PatientsDAO patientsDAO = new PatientsDAO();

        System.out.println("-------------------");

        ObservableList<Patients> lesDupont = patientsDAO.getPatientByName("Dupont");
        System.out.println("-------------------");

        System.out.println(lesDupont);
        System.out.println("-------------------");


        for (Patients leDupont : lesDupont) {
            List<Traitements> traitements = patientsDAO.getPatientTraitement(leDupont.getId());
            System.out.println(leDupont.getId());
            System.out.println("-------------------");


                if (traitements != null && !traitements.isEmpty()) {
                for (Traitements t : traitements) {
                System.out.println(t);
                }
                } else {
                System.out.println("Aucun traitement trouvé pour ce patient.");
             }

        }
//
//        List<Traitements> traitements = patientsDAO.getPatientTraitement(1);
//
//        if (traitements != null && !traitements.isEmpty()) {
//            for (Traitements t : traitements) {
//                System.out.println(t);
//            }
//        } else {
//            System.out.println("Aucun traitement trouvé pour ce patient.");
//        }





    }
}
