package br.ufrpe.artemis.usuario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.ufrpe.artemis.infra.ArtemisApp;
import br.ufrpe.artemis.infra.database.dao.DB;
import br.ufrpe.artemis.usuario.dominio.Usuario;

public class UsuarioDao {
    private SQLiteDatabase banco;
    public UsuarioDao(){
        habilitarBanco(ArtemisApp.getContext());
    }

    private SQLiteDatabase habilitarBanco(Context ctx){
        DB auxDB = new DB(ctx);
        banco = auxDB.getWritableDatabase();
        return banco;
    }

    public void inserirUsuario(Usuario usuario){
        ContentValues valores = new ContentValues();
        valores.put("cpf", usuario.getCpf());
        valores.put("senha", usuario.getSenha());
        banco.insert("usuario", null, valores);
        banco.close();
    }

    public boolean existeUsuario(String cpf){
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

    public Usuario recuperarUsuario(String cpf){
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

    public Usuario recuperarUsuario(int id){
        Usuario usuario;
        Cursor cursor = banco.query("usuario", new String[]{"*"}, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
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
