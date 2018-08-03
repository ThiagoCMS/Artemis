package br.ufrpe.artemis.avaliacao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
        values.put("idpessoa", avaliacao.getPessoaAvaliada().getId());
        values.put("idpessoa2", avaliacao.getPessoaAvaliadora().getId());
        banco.insert("classificacao", null, values);
        banco.close();
    }
}
