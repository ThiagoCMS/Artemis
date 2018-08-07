package br.ufrpe.artemis.infra;

import java.util.HashMap;
import java.util.Map;

import br.ufrpe.artemis.pessoa.dominio.Pessoa;

public class Sessao {
    public static final Sessao instance = new Sessao();
    private final Map<String, Object> values = new HashMap<>();

    public void setPessoa(Pessoa pessoa){
        setValue("sessao.pessoa", pessoa);
    }

    public Pessoa getPessoa(){
        return (Pessoa) values.get("sessao.pessoa");
    }

    private void setValue(String key, Object value){
        values.put(key, value);
    }

    public void reset(){
        setPessoa(null);
    }
}
