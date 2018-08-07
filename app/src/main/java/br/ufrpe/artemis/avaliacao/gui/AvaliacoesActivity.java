package br.ufrpe.artemis.avaliacao.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.ufrpe.artemis.R;
import br.ufrpe.artemis.avaliacao.dominio.Avaliacao;
import br.ufrpe.artemis.avaliacao.negocio.AvaliacaoNegocio;
import br.ufrpe.artemis.infra.ArtemisApp;
import br.ufrpe.artemis.servico.dominio.Servico;

public class AvaliacoesActivity extends AppCompatActivity {
    private ArrayList<Avaliacao> arrayListAvaliacao;
    private ListView listViewGeral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacoes);

        listViewGeral = findViewById(R.id.listViewAvaliacoes);
        Bundle extra = getIntent().getExtras();
        int idUsuarioPerfil = extra.getInt("id");
        AvaliacaoNegocio avaliacaoNegocio = new AvaliacaoNegocio();
        arrayListAvaliacao = avaliacaoNegocio.retornarAvaliacoes(idUsuarioPerfil);
        ArrayAdapter avaliacaoAdapter = new AvaliacaoListAdapter(arrayListAvaliacao);
        listViewGeral.setAdapter(avaliacaoAdapter);
    }
}