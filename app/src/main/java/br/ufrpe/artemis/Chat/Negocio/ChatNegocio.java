package br.ufrpe.artemis.Chat.Negocio;

import br.ufrpe.artemis.Chat.Dao.ChatDao;
import br.ufrpe.artemis.Chat.Dominio.Chat;
import br.ufrpe.artemis.Chat.Dominio.Mensagem;
import br.ufrpe.artemis.Pessoa.Negocio.PessoaNegocio;

public class ChatNegocio {

    public void inserirMensagem(Mensagem mensagem){
        ChatDao banco = new ChatDao();
        banco.inserirMensagem(mensagem);
    }

    public void criarChat(Chat chat){
        ChatDao banco = new ChatDao();
        banco.inesrirChat(chat);
    }

    public Chat retornarChat(int id){
        ChatDao banco = new ChatDao();
        Chat chat = banco.recuperarChat(id);
        PessoaNegocio negocio = new PessoaNegocio();
        chat.setPessoa1(negocio.recuperarPessoaPorId(chat.getPessoa1().getId()));
        chat.setPessoa2(negocio.recuperarPessoaPorId(chat.getPessoa2().getId()));
        return chat;
    }
}
