package br.ufrpe.artemis.pessoa.dominio;

import br.ufrpe.artemis.endereco.dominio.Endereco;
import br.ufrpe.artemis.usuario.dominio.Usuario;

public class Pessoa {
    private int id;
    private String nome;
    private String email;
    private Usuario usuario;
    private String telefone;
    private Endereco endereco;

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
