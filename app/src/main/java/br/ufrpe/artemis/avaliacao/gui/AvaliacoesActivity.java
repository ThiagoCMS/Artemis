package br.ufrpe.artemis.avaliacao.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.ufrpe.artemis.R;
import br.ufrpe.artemis.avaliacao.dominio.Avaliacao;
import br.ufrpe.artemis.avaliacao.negocio.AvaliacaoNegocio;

public class AvaliacoesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacoes);
        ListView listViewGeral = findViewById(R.id.listViewAvaliacoes);
        Bundle extra = getIntent().getExtras();
        int idUsuarioPerfil = extra.getInt("id");
        AvaliacaoNegocio avaliacaoNegocio = new AvaliacaoNegocio();
        List<Avaliacao> arrayListAvaliacao = avaliacaoNegocio.retornarAvaliacoes(idUsuarioPerfil);
        ArrayAdapter avaliacaoAdapter = new AvaliacaoListAdapter(arrayListAvaliacao);
        listViewGeral.setAdapter(avaliacaoAdapter);
    }
}