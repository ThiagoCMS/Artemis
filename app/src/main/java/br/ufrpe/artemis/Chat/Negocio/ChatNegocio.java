package br.ufrpe.artemis.Chat.Negocio;

import java.util.ArrayList;

import br.ufrpe.artemis.Chat.Dao.ChatDao;
import br.ufrpe.artemis.Chat.Dominio.Chat;
import br.ufrpe.artemis.Chat.Dominio.Mensagem;
import br.ufrpe.artemis.Infra.Sessao;
import br.ufrpe.artemis.Pessoa.Dominio.Pessoa;
import br.ufrpe.artemis.Pessoa.Negocio.PessoaNegocio;

public class ChatNegocio {

    public void inserirMensagem(Mensagem mensagem){
        ChatDao dao = new ChatDao();
        dao.inserirMensagem(mensagem);
    }

    public void criarChat(Chat chat){
        ChatDao dao = new ChatDao();
        dao.inesrirChat(chat);
    }

    public Chat retornarChat(int id){
        ChatDao dao = new ChatDao();
        Chat chat = dao.recuperarChat(id);
        PessoaNegocio negocio = new PessoaNegocio();
        chat.setPessoa1(negocio.recuperarPessoaPorId(chat.getPessoa1().getId()));
        chat.setPessoa2(negocio.recuperarPessoaPorId(chat.getPessoa2().getId()));
        return chat;
    }

    public ArrayList<Chat> recuperarChats(){
        ChatDao dao = new ChatDao();
        PessoaNegocio pessoaNegocio = new PessoaNegocio();
        Pessoa pessoa = pessoaNegocio.recuperarPessoaPorUsuario(Sessao.instance.getUsuario().getId());
        ArrayList<Chat> chatList = dao.recuperarChats(pessoa.getId());
        for (Chat chat:chatList) {
            chat.setPessoa1(pessoaNegocio.recuperarPessoaPorId(chat.getPessoa1().getId()));
            chat.setPessoa2(pessoaNegocio.recuperarPessoaPorId(chat.getPessoa2().getId()));
        }
        return chatList;
    }

    public ArrayList<Mensagem> recuperarMensagens(int idChat){
        ChatDao dao = new ChatDao();
        Chat chat = retornarChat(idChat);
        ArrayList<Mensagem> mensagens = dao.recuperarMensagens(chat);
        return mensagens;
    }
}
