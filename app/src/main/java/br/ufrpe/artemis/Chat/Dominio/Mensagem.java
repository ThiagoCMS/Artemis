package br.ufrpe.artemis.Chat.Dominio;

import java.util.Date;
import br.ufrpe.artemis.Pessoa.Dominio.Pessoa;

public class Mensagem {
    private int id;
    private Chat chat;
    private Pessoa pessoa;
    private Date date;
    private String mensagem;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
