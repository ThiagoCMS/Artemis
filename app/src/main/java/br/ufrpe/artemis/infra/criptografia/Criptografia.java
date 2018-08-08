package br.ufrpe.artemis.infra.criptografia;

import android.util.Base64;

public class Criptografia {

    public String criptografarString(String string){
        byte[] encodeValue = Base64.encode(string.getBytes(),Base64.DEFAULT);
        return new String(encodeValue);
    }

    public String descriptografarString(String stringCodificada){
        byte[] decodeValue = Base64.decode(stringCodificada.getBytes(), Base64.DEFAULT);
        return new String(decodeValue);
    }
}
