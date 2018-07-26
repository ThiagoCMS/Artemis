package br.ufrpe.artemis.Pessoa.Dominio;

import br.ufrpe.artemis.Usuario.Dominio.Usuario;

public class Pessoa {
    private int id;
    private String nome;
    private String email;
    private Usuario usuario;
    private String telefone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTelefone(){ return telefone;}

    public void setTelefone(String telefone) { this.telefone = telefone;}
}
