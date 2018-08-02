package br.ufrpe.artemis.endereco.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.ufrpe.artemis.endereco.dominio.Endereco;
import br.ufrpe.artemis.infra.ArtemisApp;
import br.ufrpe.artemis.infra.database.dao.DB;

public class EnderecoDao {
    private SQLiteDatabase banco;
    public EnderecoDao(){
        habilitarBanco();
    }
    private SQLiteDatabase habilitarBanco(){
        DB auxDB = new DB(ArtemisApp.getContext());
        banco = auxDB.getWritableDatabase();
        return banco;
    }

    public void inserirEndereço(Endereco endereco){
        ContentValues values = new ContentValues();
        values.put("rua", endereco.getRua());
        values.put("numero", endereco.getNumero());
        values.put("cidade", endereco.getCidade());
        banco.insert("endereco", null, values);
        banco.close();
    }

    public Endereco recuperarEndereco(int id){
        Cursor cursor = banco.query("endereco", new String[]{"*"}, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        Endereco endereco = new Endereco();
        endereco.setId(cursor.getInt(0));
        endereco.setRua(cursor.getString(1));
        endereco.setNumero(cursor.getString(2));
        endereco.setCidade(cursor.getString(3));
        banco.close();
        cursor.close();
        return endereco;
    }

    public void alterarEndereco(Endereco endereco){
        ContentValues values = new ContentValues();
        values.put("rua", endereco.getRua());
        values.put("numero", endereco.getNumero());
        values.put("cidade", endereco.getCidade());
        banco.update("endereco", values, "id = ?", new String[]{String.valueOf(endereco.getId())});
        banco.close();
    }

    public Endereco recuperarUltimoEndereco(){
        Cursor cursor = banco.rawQuery("select ROWID from endereco", null);
        cursor.moveToLast();
        int id = cursor.getInt(0);
        return recuperarEndereco(id);
    }
}
