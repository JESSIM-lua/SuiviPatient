package com.example.suivipatientjavafx.util;

import com.example.suivipatientjavafx.model.Utilisateurs;

public class Session {
    private static Utilisateurs currentUser;

    public static void setCurrentUser(Utilisateurs user) {
        currentUser = user;
    }

    public static Utilisateurs getCurrentUser() {
        return currentUser;
    }

    public static String getCurrentUserRole() {
        return currentUser != null ? currentUser.getRole() : null;
    }

    public static int getCurrentUserId() {
        return currentUser != null ? currentUser.getId() : -1;
    }
}
