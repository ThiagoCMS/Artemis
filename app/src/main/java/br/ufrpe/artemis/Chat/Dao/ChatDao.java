package br.ufrpe.artemis.Chat.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

import br.ufrpe.artemis.Chat.Dominio.Chat;
import br.ufrpe.artemis.Chat.Dominio.Mensagem;
import br.ufrpe.artemis.Infra.ArtemisApp;
import br.ufrpe.artemis.Infra.DataBase.Dao.DB;
import br.ufrpe.artemis.Pessoa.Dominio.Pessoa;

public class ChatDao {
    private SQLiteDatabase banco;

    public ChatDao(){
        habilitarBanco(ArtemisApp.getContext());
    }

    private SQLiteDatabase habilitarBanco(Context ctx){
        DB auxDB = new DB(ctx);
        banco = auxDB.getWritableDatabase();
        return banco;
    }

    public void inesrirChat(Chat chat){
        ContentValues valores = new ContentValues();
        valores.put("idpessoa1", chat.getPessoa1().getId());
        valores.put("idpessoa2", chat.getPessoa2().getId());
        banco.insert("chat", null, valores);
        banco.close();
    }

    public Chat recuperarChat(int id){
        Cursor cursor = banco.query("chat", new String[]{"*"}, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        Chat chat = new Chat();
        if(cursor.moveToFirst()){
            chat.setId(cursor.getInt(0));
            Pessoa pessoa1 = new Pessoa();
            pessoa1.setId(cursor.getInt(1));
            chat.setPessoa1(pessoa1);
            Pessoa pessoa2 = new Pessoa();
            pessoa2.setId(cursor.getInt(2));
            chat.setPessoa2(pessoa2);
        }else{
            chat = null;
        }
        return chat;
    }

    public ArrayList<Chat> recuperarChats(int id){
        Cursor cursor = banco.query("chat", new String[]{"*"}, "idpessoa1 = ? OR idpessoa2 = ?", new String[]{String.valueOf(id), String.valueOf(id)}, null, null, null);
        ArrayList<Chat> chatList = new ArrayList<>();
        if(cursor.moveToFirst()){}
        for(int i = 0; i < cursor.getCount(); i++){
            Chat chat = new Chat();
            chat.setId(cursor.getInt(0));
            Pessoa pessoa1 = new Pessoa();
            pessoa1.setId(cursor.getInt(1));
            chat.setPessoa1(pessoa1);
            Pessoa pessoa2 = new Pessoa();
            pessoa2.setId(cursor.getInt(2));
            chat.setPessoa2(pessoa2);
            chatList.add(chat);
        }
        return chatList;
    }


    public void inserirMensagem(Mensagem mensagem){
        ContentValues values = new ContentValues();
        values.put("idchat", mensagem.getChat().getId());
        values.put("idpessoa", mensagem.getPessoa().getId());
        values.put("data", mensagem.getDate().getTime());
        values.put("texto", mensagem.getMensagem());
        banco.insert("mensagem", null, values);
        banco.close();
    }

    public ArrayList<Mensagem> recuperarMensagens(Chat chat){
        Cursor cursor = banco.query("mensagem", new String[]{"*"}, "idchat = ?", new String[]{String.valueOf(chat.getId())}, null, null, null);
        ArrayList<Mensagem> mensagemArrayList = new ArrayList<>();
        if(cursor.moveToFirst()){}
        for(int i = 0; i < cursor.getCount(); i++){
            Mensagem mensagem = new Mensagem();
            mensagem.setId(cursor.getInt(0));
            mensagem.setChat(chat);
            int idPessoa = cursor.getInt(2);
            if(chat.getPessoa1().getId() == idPessoa){
                mensagem.setPessoa(chat.getPessoa1());
            }else{
                mensagem.setPessoa(chat.getPessoa2());
            }
            /*Pessoa pessoa = new Pessoa();
            pessoa.setId(cursor.getInt(2));
            mensagem.setPessoa(pessoa);*/
            Date date = new Date();
            date.setTime(cursor.getInt(3));
            mensagem.setDate(date);
            mensagem.setMensagem(cursor.getString(4));
            mensagemArrayList.add(mensagem);
        }
        return mensagemArrayList;
    }
}
