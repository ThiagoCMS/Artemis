package br.ufrpe.artemis.infra.criptografia;

import android.util.Base64;

public class Criptografia {

    public String criptografarString(String string){
        byte[] encodeValue = Base64.encode(string.getBytes(),Base64.DEFAULT);
        String stringCode = new String(encodeValue);
        return stringCode;
    }

    public String descriptografarString(String stringCodificada){
        byte[] decodeValue = Base64.decode(stringCodificada.getBytes(), Base64.DEFAULT);
        String stringDecode = new String(decodeValue);
        return stringDecode;
    }
}
