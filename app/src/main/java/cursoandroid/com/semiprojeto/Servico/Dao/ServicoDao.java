package cursoandroid.com.semiprojeto.Servico.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cursoandroid.com.semiprojeto.DataBase.Dao.DB;
import cursoandroid.com.semiprojeto.Servico.Dominio.Servico;

public class ServicoDao {
    private SQLiteDatabase banco;

    public SQLiteDatabase escreverNoBanco(Context context){
        DB auxDB = new DB(context);
        banco = auxDB.getWritableDatabase();
        return banco;
    }

    public void inserirNoBanco(Servico servico){
        ContentValues valores = new ContentValues();
        valores.put("nome", servico.getNome());
        valores.put("texto", servico.getTexto());
        valores.put("idusuario", servico.getIdUsuario());
        valores.put("idsubcategoria", servico.getIdSubCategoria());
        banco.insert("servico", null, valores);
        banco.close();
    }

    public void deletarDoBanco(Servico servico){
        String where = "id = '" + servico.getId() + "'";
        banco.delete("servico", where, null);
        banco.close();
    }

    public ArrayList<Servico> recuperarDoBancoSub(int idSub){
        Servico servico = new Servico();
        ArrayList<Servico> list = new ArrayList<Servico>();
        String where = "SELECT * FROM usuario WHERE idsubcategoria = '" + idSub + "'";
        Cursor cursor = banco.rawQuery(where, null);
        while(cursor.getCount() > 0){
            cursor.moveToFirst();
            servico.setId(cursor.getInt(0));
            servico.setNome(cursor.getString(1));
            servico.setTexto(cursor.getString(2));
            servico.setIdUsuario(cursor.getInt(3));
            servico.setIdSubCategoria(cursor.getInt(4));
            list.add(servico);
            cursor.moveToNext();
        }
        return list;
    }

    public ArrayList<Servico> recuperarDoBancoUs(int idUsuario){
        Servico servico = new Servico();
        ArrayList<Servico> list = new ArrayList<Servico>();
        String where = "SELECT * FROM usuario WHERE idusuario = '" + idUsuario + "'";
        Cursor cursor = banco.rawQuery(where, null);
        while(cursor.getCount() > 0){
            cursor.moveToFirst();
            servico.setId(cursor.getInt(0));
            servico.setNome(cursor.getString(1));
            servico.setTexto(cursor.getString(2));
            servico.setIdUsuario(cursor.getInt(3));
            servico.setIdSubCategoria(cursor.getInt(4));
            list.add(servico);
            cursor.moveToNext();
        }
        return list;
    }
}
