package br.ufrpe.artemis.Usuario.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.ufrpe.artemis.Infra.ArtemisApp;
import br.ufrpe.artemis.Infra.DataBase.Dao.DB;
import br.ufrpe.artemis.Usuario.Dominio.Usuario;

public class UsuarioDao {
    private SQLiteDatabase banco;

    public UsuarioDao(){
        escreverNoBanco(ArtemisApp.getContext());
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

    public boolean existeNoBanco(String cpf){
        Cursor cursor = banco.query("usuario", new String[]{"*"}, "cpf = ?", new String[]{cpf}, null, null, null);
        boolean resposta = false;
        if(cursor.getCount() > 0){
            resposta = true;
        }
        banco.close();
        cursor.close();
        return resposta;
    }

    public Usuario verificarLogin(String cpf, String senha){
        Usuario usuario;
        Cursor cursor = banco.query("usuario", new String[]{"*"}, "cpf = ? and senha = ?", new String[]{cpf, senha}, null, null, null);
        if(cursor.getCount() > 0){
            usuario = montarUsuario(cursor);
        }else{
            usuario = null;
        }
        cursor.close();
        banco.close();
        return usuario;
    }

    private Usuario montarUsuario(Cursor cursor) {
        Usuario usuario = new Usuario();
        cursor.moveToFirst();
        usuario.setId(cursor.getInt(0));
        usuario.setSenha(cursor.getString(1));
        usuario.setCpf(cursor.getString(2));
        return usuario;
    }

    public Usuario recuperarDoBanco(String cpf){
        Usuario usuario;
        Cursor cursor = banco.query("usuario", new String[]{"*"}, "cpf = ?", new String[]{cpf}, null, null, null);
        if(cursor.getCount() > 0){
            usuario = montarUsuario(cursor);
        }else{
            usuario = null;
        }
        cursor.close();
        banco.close();
        return usuario;
    }

    public Usuario recuperarDoBanco(int id){
        Usuario usuario;
        String where = "SELECT * FROM usuario WHERE id = '" + id + "'";
        Cursor cursor = banco.rawQuery(where, null);
        if(cursor.getCount()>0){
            usuario = montarUsuario(cursor);
        }else{
            usuario = null;
        }
        banco.close();
        cursor.close();
        return usuario;
    }

    public void alterarSenhaUsuario(Usuario usuario) {
        String where = "id = ?";
        ContentValues valores = new ContentValues();
        valores.put("cpf", usuario.getCpf());
        valores.put("senha", usuario.getSenha());
        banco.update("usuario", valores, where, new String[]{String.valueOf(usuario.getId())});
        banco.close();
    }
}
