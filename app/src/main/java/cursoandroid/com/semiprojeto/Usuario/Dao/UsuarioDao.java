package cursoandroid.com.semiprojeto.Usuario.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cursoandroid.com.semiprojeto.DataBase.Dao.DB;
import cursoandroid.com.semiprojeto.Usuario.Dominio.Usuario;

public class UsuarioDao {
    private SQLiteDatabase banco;

    public SQLiteDatabase escreverNoBanco(Context context){
        DB auxDB = new DB(context);
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

    public Boolean lerDoBanco(Usuario usuario){
        String where = "SELECT cpf FROM usuario WHERE cpf = '" + usuario.getCpf() + "' AND senha = '" + usuario.getSenha() + "'";
        Cursor cursor = banco.rawQuery(where, null);
        if(cursor.getCount() > 0){
            return true;
        }
        return false;
    }

    public Usuario recuperarDoBanco(Usuario usuario){
        Usuario usuario1 = new Usuario();
        String where = "SELECT * FROM usuario WHERE cpf = '" + usuario.getCpf() + "'";
        Cursor cursor = banco.rawQuery(where, null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            usuario1.setId(cursor.getInt(0));
            usuario1.setCpf(cursor.getString(2));
            usuario1.setSenha(cursor.getString(1));
        }
        return usuario1;
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
