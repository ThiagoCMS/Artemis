package br.ufrpe.artemis.pessoa.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.ufrpe.artemis.endereco.dominio.Endereco;
import br.ufrpe.artemis.infra.ArtemisApp;
import br.ufrpe.artemis.infra.database.dao.DB;
import br.ufrpe.artemis.pessoa.dominio.Pessoa;
import br.ufrpe.artemis.usuario.dao.UsuarioDao;
import br.ufrpe.artemis.usuario.dominio.Usuario;

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
        valores.put("telefone", pessoa.getTelefone());
        valores.put("idendereco", pessoa.getEndereco().getId());
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
            pessoa.setTelefone(cursor.getString(4));
            UsuarioDao bancoUsuario = new UsuarioDao();
            Usuario usuario = bancoUsuario.recuperarDoBanco(cursor.getInt(2));
            pessoa.setUsuario(usuario);
            Endereco endereco = new Endereco();
            endereco.setId(cursor.getInt(5));
            pessoa.setEndereco(endereco);
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
            pessoa.setTelefone(cursor.getString(4));
            UsuarioDao bancoUsuario = new UsuarioDao();
            Usuario usuario = bancoUsuario.recuperarDoBanco(cursor.getInt(2));
            pessoa.setUsuario(usuario);
            Endereco endereco = new Endereco();
            endereco.setId(cursor.getInt(5));
            pessoa.setEndereco(endereco);
        }
        return pessoa;
    }

    public void alterarPerfil(Pessoa pessoa){
        ContentValues values = new ContentValues();
        values.put("nome", pessoa.getNome());
        values.put("email", pessoa.getEmail());
        values.put("telefone", pessoa.getTelefone());
        banco.update("pessoa", values, "id = ?", new String[]{String.valueOf(pessoa.getId())});
        banco.close();
    }
}
