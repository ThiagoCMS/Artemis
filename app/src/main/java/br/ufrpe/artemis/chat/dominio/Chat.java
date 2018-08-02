package br.ufrpe.artemis.chat.dominio;

import br.ufrpe.artemis.pessoa.dominio.Pessoa;

public class Chat {
    private int id;
    private Pessoa pessoa1;
    private Pessoa pessoa2;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Pessoa getPessoa1() {
        return pessoa1;
    }
    public void setPessoa1(Pessoa pessoa1) {
        this.pessoa1 = pessoa1;
    }
    public Pessoa getPessoa2() {
        return pessoa2;
    }
    public void setPessoa2(Pessoa pessoa2) {
        this.pessoa2 = pessoa2;
    }
}
