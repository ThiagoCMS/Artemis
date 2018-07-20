package br.ufrpe.artemis.Servico.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.ufrpe.artemis.Infra.ArtemisApp;
import br.ufrpe.artemis.Infra.DataBase.Dao.DB;
import br.ufrpe.artemis.Servico.Dominio.Categoria;
import br.ufrpe.artemis.Servico.Dominio.Servico;
import br.ufrpe.artemis.Servico.Dominio.Subcategoria;

public class ServicoDao {
    private SQLiteDatabase banco;

    public ServicoDao(){
        escreverNoBanco(ArtemisApp.getContext());
    }

    private SQLiteDatabase escreverNoBanco(Context context){
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
        ArrayList<Servico> list = new ArrayList<Servico>();
        String where = "SELECT * FROM servico WHERE idsubcategoria = '" + idSub + "'";
        Cursor cursor = banco.rawQuery(where, null);
        if(cursor.getCount()>0){cursor.moveToFirst();}
        for(int i = 0; i < cursor.getCount(); i++){
            Servico servico = new Servico();
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
        ArrayList<Servico> list = new ArrayList<Servico>();
        String where = "SELECT * FROM servico WHERE idusuario = '" + idUsuario + "'";
        Cursor cursor = banco.rawQuery(where, null);
        if(cursor.getCount()>0){cursor.moveToFirst();}
        for(int i = 0; i < cursor.getCount(); i++){
            Servico servico = new Servico();
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

    public ArrayList<Categoria> recuperarListaCategoria(){
        ArrayList<Categoria> lista = new ArrayList<>();
        Cursor cursor = banco.query("categoria", new String[]{"*"}, null, null, null, null,null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++){
            Categoria categoria = new Categoria();
            categoria.setId(cursor.getInt(0));
            categoria.setNome(cursor.getString(1));
            lista.add(categoria);
            cursor.moveToNext();
        }
        return lista;
    }
    public ArrayList<Subcategoria> recuperarListaSubcategoria(int idcategoria) {
        ArrayList<Subcategoria> lista = new ArrayList<>();
        String numCategoria = String.valueOf(idcategoria);
        Cursor cursor = banco.query("subcategoria", new String[]{"*"}, "idcategoria = ?", new String[]{numCategoria}, null, null, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            Subcategoria subcategoria = new Subcategoria();
            subcategoria.setId(cursor.getInt(0));
            subcategoria.setNome(cursor.getString(1));
            subcategoria.setIdCategoria(cursor.getInt(2));
            lista.add(subcategoria);
            cursor.moveToNext();
        }
        return lista;
    }

    public void atualizarServico(Servico servico){
        String where = "id = ?";
        ContentValues valores = new ContentValues();
        valores.put("nome", servico.getNome());
        valores.put("texto", servico.getTexto());
        banco.update("servico", valores, where, new String[]{String.valueOf(servico.getId())});
        banco.close();
    }
}

