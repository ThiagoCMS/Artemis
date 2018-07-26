package br.ufrpe.artemis.Pessoa.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.ufrpe.artemis.Infra.ArtemisApp;
import br.ufrpe.artemis.Infra.DataBase.Dao.DB;
import br.ufrpe.artemis.Pessoa.Dominio.Pessoa;
import br.ufrpe.artemis.Usuario.Dao.UsuarioDao;
import br.ufrpe.artemis.Usuario.Dominio.Usuario;

public class PessoaDao {
    private SQLiteDatabase banco;

    public PessoaDao(){
        escreverNoBanco(ArtemisApp.getContext());
    }

    private SQLiteDatabase escreverNoBanco(Context context){
        DB auxDB = new DB(context);
        banco = auxDB.getWritableDatabase();
        return banco;
    }

    public void inserirNoBanco(Pessoa pessoa){
        ContentValues valores = new ContentValues();
        valores.put("nome", pessoa.getNome());
        valores.put("idusuario", pessoa.getUsuario().getId());
        valores.put("email", pessoa.getEmail());
        banco.insert("pessoa", null, valores);
        banco.close();
    }

    public Pessoa recuperarDoBancoPorUsuario(int id){
        Pessoa pessoa = new Pessoa();
        Cursor cursor = banco.query("pessoa", new String[]{"*"}, "idusuario = ?", new String[]{String.valueOf(id)}, null, null, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            pessoa.setId(cursor.getInt(0));
            pessoa.setNome(cursor.getString(1));
            pessoa.setEmail(cursor.getString(3));
            UsuarioDao bancoUsuario = new UsuarioDao();
            Usuario usuario = bancoUsuario.recuperarDoBanco(cursor.getInt(2));
            pessoa.setUsuario(usuario);
        }
        return pessoa;
    }

    public Pessoa recuperarDoBanco(int id){
        Pessoa pessoa = new Pessoa();
        Cursor cursor = banco.query("pessoa", new String[]{"*"}, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            pessoa.setId(cursor.getInt(0));
            pessoa.setNome(cursor.getString(1));
            pessoa.setEmail(cursor.getString(3));
            UsuarioDao bancoUsuario = new UsuarioDao();
            Usuario usuario = bancoUsuario.recuperarDoBanco(cursor.getInt(2));
            pessoa.setUsuario(usuario);
        }
        return pessoa;
    }
}
