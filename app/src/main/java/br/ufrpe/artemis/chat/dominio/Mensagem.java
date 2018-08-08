package br.ufrpe.artemis.chat.dominio;

import br.ufrpe.artemis.pessoa.dominio.Pessoa;

public class Mensagem {
    private int id;
    private Chat chat;
    private Pessoa pessoa;
    private String texto;

    public Mensagem(){

    }

    public Mensagem(Chat chat, Pessoa pessoa, String texto) {
        this.chat = chat;
        this.pessoa = pessoa;
        this.texto = texto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
