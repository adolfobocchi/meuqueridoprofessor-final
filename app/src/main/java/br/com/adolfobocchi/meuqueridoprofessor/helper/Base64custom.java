package br.com.adolfobocchi.meuqueridoprofessor.helper;

import android.util.Base64;

public class Base64custom {

    public static String codificarBase64(String texto) {
        return Base64.encodeToString(
                texto.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
    }

    public static String decodificadorBase64(String textoCodificado) {
        return new String(Base64.decode(textoCodificado, Base64.DEFAULT));
    }
}
