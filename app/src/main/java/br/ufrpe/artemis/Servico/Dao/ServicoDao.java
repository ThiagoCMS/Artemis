package br.ufrpe.artemis.Servico.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.artemis.DataBase.Dao.DB;
import br.ufrpe.artemis.Servico.Dominio.Servico;

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
        String where = "SELECT * FROM servico WHERE idsubcategoria = '" + idSub + "'";
        Cursor cursor = banco.rawQuery(where, null);
        if(cursor.getCount()>0){cursor.moveToFirst();}
        for(int i = 0; i < cursor.getCount(); i++){
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
        String where = "SELECT * FROM servico WHERE idusuario = '" + idUsuario + "'";
        Cursor cursor = banco.rawQuery(where, null);
        if(cursor.getCount()>0){cursor.moveToFirst();}
        for(int i = 0; i < cursor.getCount(); i++){
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

    public Servico recuperarServico(int id){
        Servico servico = new Servico();
        String where = "SELECT * FROM servico WHERE id = '" + id + "'";
        Cursor cursor = banco.rawQuery(where, null);
        int i = cursor.getCount();
        cursor.moveToFirst();
        servico.setId(cursor.getInt(0));
        servico.setNome(cursor.getString(1));
        servico.setTexto(cursor.getString(2));
        servico.setIdUsuario(cursor.getInt(3));
        servico.setIdSubCategoria(cursor.getInt(4));
        return servico;
    }

    public String recuperarCategoria(int id){
        int idCategoria = recuperarIdCategoria(id);
        String where = "SELECT nome FROM categoria WHERE id = '" + idCategoria + "'";
        Cursor cursor = banco.rawQuery(where, null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    private int recuperarIdCategoria(int id){
        String where = "SELECT idcategoria FROM subcategoria WHERE id = '" + id + "'";
        Cursor cursor = banco.rawQuery(where, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public String recuperarSubCategoria(int id){
        String where = "SELECT nome FROM subcategoria WHERE id = '" + id + "'";
        Cursor cursor = banco.rawQuery(where, null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }
}
