package br.ufrpe.artemis.avaliacao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;

import br.ufrpe.artemis.avaliacao.dominio.Avaliacao;
import br.ufrpe.artemis.infra.ArtemisApp;
import br.ufrpe.artemis.infra.database.dao.DB;

public class AvaliacaoDao {
    private SQLiteDatabase banco;
    public AvaliacaoDao(){
        habilitarBanco(ArtemisApp.getContext());
    }
    private SQLiteDatabase habilitarBanco(Context ctx){
        DB auxDB = new DB(ctx);
        banco = auxDB.getWritableDatabase();
        return banco;
    }

    public void inserirAvaliacao(Avaliacao avaliacao){
        ContentValues values = new ContentValues();
        values.put("preco", avaliacao.getNotaPreco());
        values.put("qualidade", avaliacao.getNotaQualidade());
        values.put("atendimento", avaliacao.getNotaAtendimento());
        values.put("comentario", avaliacao.getComentario());
        values.put("idpessoa", avaliacao.getPrestadora().getId());
        values.put("idpessoa2", avaliacao.getCliente().getId());
        banco.insert("classificacao", null, values);
        banco.close();
    }

    public ArrayList<Avaliacao> recuperarNotas(int idPrestadora){
        ArrayList<Avaliacao> list = new ArrayList<>();
        Cursor cursor = banco.query("classificacao", new String[]{"*"}, "idpessoa = ?", new String[]{String.valueOf(idPrestadora)}, null, null, null);
        if(cursor.moveToFirst()){}
        for (int i = 0; i < cursor.getCount(); i++) {
            Avaliacao avaliacao = new Avaliacao();
            avaliacao.setNotaPreco(cursor.getDouble(0));
            avaliacao.setNotaQualidade(cursor.getDouble(1));
            avaliacao.setNotaAtendimento(cursor.getDouble(2));
            list.add(avaliacao);
            cursor.moveToNext();
        }
        return list;
    }
}
