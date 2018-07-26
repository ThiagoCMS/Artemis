package br.ufrpe.artemis.Endereço.Dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import br.ufrpe.artemis.Endereço.Dominio.Endereco;
import br.ufrpe.artemis.Infra.ArtemisApp;
import br.ufrpe.artemis.Infra.DataBase.Dao.DB;

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
        values.put("cep", endereco.getCep());
        values.put("rua", endereco.getRua());
        values.put("numero", endereco.getNumero());
        values.put("cidade", endereco.getCidade());
        banco.insert("endereco", null, values);
    }
}
