package br.ufrpe.artemis.chat.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Date;
import br.ufrpe.artemis.chat.dominio.Chat;
import br.ufrpe.artemis.chat.dominio.Mensagem;
import br.ufrpe.artemis.infra.ArtemisApp;
import br.ufrpe.artemis.infra.database.dao.DB;
import br.ufrpe.artemis.pessoa.dominio.Pessoa;

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

    public void inserirChat(Chat chat){
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
            cursor.moveToNext();
        }
        return chatList;
    }


    public void inserirMensagem(Mensagem mensagem){
        ContentValues values = new ContentValues();
        values.put("idchat", mensagem.getChat().getId());
        values.put("idpessoa", mensagem.getPessoa().getId());
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
            Date date = new Date();
            date.setTime(cursor.getInt(3));
            mensagem.setMensagem(cursor.getString(4));
            mensagemArrayList.add(mensagem);
            cursor.moveToNext();
        }
        return mensagemArrayList;
    }

    public Chat recuperarChat(Pessoa pessoa, Pessoa pessoa1){
        Chat chat;
        Cursor cursor = banco.query("chat",new String[]{"*"},"idpessoa1 = ? and idpessoa2 = ?", new String[]{String.valueOf(pessoa.getId()),String.valueOf(pessoa1.getId())},null,null,null);
        if (!(cursor.getCount()>0)){
            cursor = banco.query("chat",new String[]{"*"},"idpessoa1 = ? and idpessoa2 = ?", new String[]{String.valueOf(pessoa1.getId()),String.valueOf(pessoa.getId())},null,null,null);
        }if(cursor.getCount()>0){
            cursor.moveToFirst();
            chat = new Chat();
            chat.setId(cursor.getInt(0));
            if(cursor.getInt(1)==pessoa.getId()){
                chat.setPessoa1(pessoa);
                chat.setPessoa2(pessoa1);
            }else{
                chat.setPessoa2(pessoa);
                chat.setPessoa1(pessoa1);
            }
        }else{
            chat = null;

        }
        return chat;
    }
}
