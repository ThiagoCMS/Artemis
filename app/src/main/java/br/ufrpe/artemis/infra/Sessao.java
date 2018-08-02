package br.ufrpe.artemis.infra;

import java.util.HashMap;
import java.util.Map;

import br.ufrpe.artemis.usuario.dominio.Usuario;

public class Sessao {
    public static final Sessao instance = new Sessao();
    private final Map<String, Object> values = new HashMap<>();

    public void setUsuario(Usuario usuario){
        setValue("sessao.usuario", usuario);
    }

    public Usuario getUsuario(){
        return (Usuario) values.get("sessao.usuario");
    }

    private void setValue(String key, Object value){
        values.put(key, value);
    }

    public void reset(){
        setUsuario(null);
    }
}
