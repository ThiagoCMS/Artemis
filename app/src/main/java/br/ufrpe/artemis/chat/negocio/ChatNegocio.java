package br.ufrpe.artemis.chat.negocio;

import java.util.ArrayList;

import br.ufrpe.artemis.chat.dao.ChatDao;
import br.ufrpe.artemis.chat.dominio.Chat;
import br.ufrpe.artemis.chat.dominio.Mensagem;
import br.ufrpe.artemis.infra.Sessao;
import br.ufrpe.artemis.pessoa.dominio.Pessoa;
import br.ufrpe.artemis.pessoa.negocio.PessoaNegocio;

public class ChatNegocio {

    public void inserirMensagem(Mensagem mensagem){
        ChatDao dao = new ChatDao();
        dao.inserirMensagem(mensagem);
    }

    public void criarChat(Chat chat){
        ChatDao dao = new ChatDao();
        dao.inserirChat(chat);
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

    public Chat iniciarChat(Pessoa pessoa, Pessoa pessoa1){
        ChatDao chatDao = new ChatDao();
        Chat chat = chatDao.recuperarChat(pessoa, pessoa1);
        if(chat == null){
            chat = new Chat();
            chat.setPessoa1(pessoa);
            chat.setPessoa2(pessoa1);
            chatDao.inserirChat(chat);
            chat = chatDao.recuperarChat(pessoa,pessoa1);
        }
        return chat;

    }
}
