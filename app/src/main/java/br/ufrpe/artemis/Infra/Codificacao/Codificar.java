package br.ufrpe.artemis.Infra.Codificacao;

import android.util.Base64;

public class Codificar {

    public String codificarString(String string){
        byte[] encodeValue = Base64.encode(string.getBytes(),Base64.DEFAULT);
        String stringCode = new String(encodeValue);
        return stringCode;

    }

    public String decodificarString(String stringCodificada){
        byte[] decodeValue = Base64.decode(stringCodificada.getBytes(), Base64.DEFAULT);
        String stringDecodificada = new String(decodeValue);
        return stringDecodificada;

    }

}
