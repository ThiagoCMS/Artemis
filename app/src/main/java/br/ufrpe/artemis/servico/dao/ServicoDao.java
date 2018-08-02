package br.ufrpe.artemis.servico.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import br.ufrpe.artemis.infra.ArtemisApp;
import br.ufrpe.artemis.infra.database.dao.DB;
import br.ufrpe.artemis.pessoa.dao.PessoaDao;
import br.ufrpe.artemis.pessoa.dominio.Pessoa;
import br.ufrpe.artemis.servico.dominio.Categoria;
import br.ufrpe.artemis.servico.dominio.Servico;
import br.ufrpe.artemis.servico.dominio.Subcategoria;

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
        valores.put("idpessoa", servico.getPessoa().getId());
        valores.put("idsubcategoria", servico.getSubcategoria().getId());
        banco.insert("servico", null, valores);
        banco.close();
    }

    public void deletarDoBanco(Servico servico){
        banco.delete("servico", "id = ?", new String[]{String.valueOf(servico.getId())});
        banco.close();
    }

    public ArrayList<Servico> recuperarDoBancoSub(int idSub){
        ArrayList<Servico> list = new ArrayList<Servico>();
        Cursor cursor = banco.query("servico", new String[]{"*"}, "idsubcategoria = ?", new String[]{String.valueOf(idSub)}, null, null, null);
        if(cursor.getCount()>0){cursor.moveToFirst();}
        for(int i = 0; i < cursor.getCount(); i++){
            Servico servico = new Servico();
            servico.setId(cursor.getInt(0));
            servico.setNome(cursor.getString(1));
            servico.setTexto(cursor.getString(2));
            PessoaDao bancoPessoa = new PessoaDao();
            Pessoa pessoa = bancoPessoa.recuperarDoBanco(cursor.getInt(3));
            servico.setPessoa(pessoa);
            Subcategoria subcategoria = retornarSubcategoria(cursor.getInt(4));
            servico.setSubcategoria(subcategoria);
            list.add(servico);
            cursor.moveToNext();
        }
        return list;
    }

    public ArrayList<Servico> recuperarDoBancoUs(int idPessoa){
        ArrayList<Servico> list = new ArrayList<Servico>();
        Cursor cursor = banco.query("servico", new String[]{"*"}, "idpessoa = ?", new String[]{String.valueOf(idPessoa)}, null, null, null);
        if(cursor.getCount()>0){cursor.moveToFirst();}
        for(int i = 0; i < cursor.getCount(); i++){
            Servico servico = new Servico();
            servico.setId(cursor.getInt(0));
            servico.setNome(cursor.getString(1));
            servico.setTexto(cursor.getString(2));
            PessoaDao bancoPessoa = new PessoaDao();
            Pessoa pessoa = bancoPessoa.recuperarDoBanco(cursor.getInt(3));
            servico.setPessoa(pessoa);
            Subcategoria subcategoria = retornarSubcategoria(cursor.getInt(4));
            servico.setSubcategoria(subcategoria);
            list.add(servico);
            cursor.moveToNext();
        }
        return list;
    }

    public Servico recuperarServico(int id){
        Servico servico = new Servico();
        Cursor cursor = banco.query("servico", new String[]{"*"}, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        servico.setId(cursor.getInt(0));
        servico.setNome(cursor.getString(1));
        servico.setTexto(cursor.getString(2));
        PessoaDao bancoPessoa = new PessoaDao();
        Pessoa pessoa = bancoPessoa.recuperarDoBanco(cursor.getInt(3));
        servico.setPessoa(pessoa);
        Subcategoria subcategoria = retornarSubcategoria(cursor.getInt(4));
        servico.setSubcategoria(subcategoria);
        return servico;
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
            Categoria categoria = new Categoria();
            categoria = retornarCategoria(cursor.getInt(2));
            subcategoria.setCategoria(categoria);
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

    public Subcategoria retornarSubcategoria(int id){
        Cursor cursor = banco.query("subcategoria", new String[]{"*"}, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        Subcategoria subcategoria = new Subcategoria();
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            subcategoria.setId(cursor.getInt(0));
            subcategoria.setNome(cursor.getString(1));
            Categoria categoria = retornarCategoria(cursor.getInt(2));
            subcategoria.setCategoria(categoria);
        } else{
            subcategoria = null;
        }
        return subcategoria;
    }

    public Categoria retornarCategoria(int id){
        Cursor cursor = banco.query("categoria", new String[]{"*"}, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        Categoria categoria =  new Categoria();
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            categoria.setId(cursor.getInt(0));
            categoria.setNome(cursor.getString(1));
        } else{
            categoria = null;
        }
        return categoria;
    }
}

