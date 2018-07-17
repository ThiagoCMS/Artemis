package br.ufrpe.artemis.Usuario.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.telecom.Connection;

import java.sql.PreparedStatement;

import br.ufrpe.artemis.DataBase.Dao.DB;
import br.ufrpe.artemis.Usuario.Dominio.Usuario;

public class UsuarioDao {
    private SQLiteDatabase banco;

    public UsuarioDao(Context ctx){
        escreverNoBanco(ctx);
    }

    private SQLiteDatabase escreverNoBanco(Context ctx){
        DB auxDB = new DB(ctx);
        banco = auxDB.getWritableDatabase();
        return banco;
    }

    public void inserirNoBanco(Usuario usuario){
        ContentValues valores = new ContentValues();
        valores.put("cpf", usuario.getCpf());
        valores.put("senha", usuario.getSenha());
        banco.insert("usuario", null, valores);
        banco.close();
    }

    public void deletarDoBanco(Usuario usuario){
        String where = "id = '" + usuario.getId() + "'";
        banco.delete("usuario", where, null);
        banco.close();
    }

    public Boolean existeNoBanco(Usuario usuario){
        String where = "SELECT cpf FROM usuario WHERE cpf = '" + usuario.getCpf() + "'";
        Cursor cursor = banco.rawQuery(where, null);
        if(cursor.getCount() > 0){
            return true;
        }
        return false;
    }

    public Boolean verificarLogin(String cpf, String senha){
        String where = "SELECT cpf FROM usuario WHERE cpf = '" + cpf + "' AND senha = '" + senha + "'";
        Cursor cursor = banco.rawQuery(where, null);
        if(cursor.getCount() > 0){
            return true;
        }
        return false;
    }

    public Usuario recuperarDoBanco(String cpf){
        Usuario usuario = new Usuario();
        String where = "SELECT * FROM usuario WHERE cpf = '" + cpf + "'";
        Cursor cursor = banco.rawQuery(where, null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            usuario.setId(cursor.getInt(0));
            usuario.setCpf(cursor.getString(2));
            usuario.setSenha(cursor.getString(1));
        }
        return usuario;
    }

    public Usuario recuperarDoBanco(int id){
        Usuario usuario = new Usuario();
        String where = "SELECT * FROM usuario WHERE id = '" + id + "'";
        Cursor cursor = banco.rawQuery(where, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            usuario.setId(cursor.getInt(0));
            usuario.setCpf(cursor.getString(2));
            usuario.setSenha(cursor.getString(1));
        }
        return usuario;
    }

    public void alterarSenhaUsuario(Usuario usuario) {
        String where = "id = '" + usuario.getId() + "'";
        ContentValues valores = new ContentValues();
        valores.put("cpf", usuario.getCpf());
        valores.put("senha", usuario.getSenha());
        banco.update("usuario", valores, where, null);
        banco.close();
    }
}
