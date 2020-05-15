package br.com.adolfobocchi.meuqueridoprofessor.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import br.com.adolfobocchi.meuqueridoprofessor.helper.Base64custom;

public class ConfiguracaoFirebase {

    private static DatabaseReference database;
    private static FirebaseAuth auth;
    private static StorageReference storage;

    public static DatabaseReference getFirebaseDatabase() {
        if(database == null) {
            database = FirebaseDatabase.getInstance().getReference();
        }
        return database;
    }

    public static FirebaseAuth getFirebaseAutenticacao() {
        if(auth == null) {
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }

    public static StorageReference getFirebaseStorage() {
        if(storage == null) {
            storage = FirebaseStorage.getInstance().getReference();
        }
        return storage;
    }

    public static String getIdentificadorUsuario() {
        FirebaseAuth usuario = ConfiguracaoFirebase.getFirebaseAutenticacao();
        String email = usuario.getCurrentUser().getEmail();
        String identificadorUsuario = Base64custom.codificarBase64(email);
        return identificadorUsuario;
    }

}
