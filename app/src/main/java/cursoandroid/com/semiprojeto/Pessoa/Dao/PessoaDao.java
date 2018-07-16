package cursoandroid.com.semiprojeto.Pessoa.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cursoandroid.com.semiprojeto.DataBase.Dao.DB;
import cursoandroid.com.semiprojeto.Pessoa.Dominio.Pessoa;
import cursoandroid.com.semiprojeto.Usuario.Dominio.Usuario;

public class PessoaDao {
    private SQLiteDatabase banco;

    public SQLiteDatabase escreverNoBanco(Context context){
        DB auxDB = new DB(context);
        banco = auxDB.getWritableDatabase();
        return banco;
    }

    public void inserirNoBanco(Pessoa pessoa){
        ContentValues valores = new ContentValues();
        valores.put("nome", pessoa.getNome());
        valores.put("idusuario", pessoa.getIdUsuario());
        banco.insert("pessoa", null, valores);
        banco.close();
    }

    public void deletarDoBanco(Pessoa pessoa){
        String where = "id = '" + pessoa.getId() + "'";
        banco.delete("pessoa", where, null);
        banco.close();
    }

    public Pessoa recuperarDoBanco(Usuario usuario){
        Pessoa pessoa = new Pessoa();
        String where = "SELECT * FROM pessoa WHERE idusuario = '" + usuario.getId() + "'";
        Cursor cursor = banco.rawQuery(where, null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            pessoa.setId(cursor.getInt(0));
            pessoa.setNome(cursor.getString(1));
            pessoa.setIdUsuario(cursor.getInt(2));
        }
        return pessoa;
    }

    public Pessoa recuperarDoBanco(int id){
        Pessoa pessoa = new Pessoa();
        String where = "SELECT * FROM pessoa WHERE idusuario = '" + id + "'";
        Cursor cursor = banco.rawQuery(where, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            pessoa.setId(cursor.getInt(0));
            pessoa.setNome(cursor.getString(1));
            pessoa.setIdUsuario(cursor.getInt(2));
        }
        return pessoa;
    }
}
