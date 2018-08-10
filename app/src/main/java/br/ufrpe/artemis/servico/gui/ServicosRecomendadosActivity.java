package br.ufrpe.artemis.servico.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.ufrpe.artemis.R;
import br.ufrpe.artemis.servico.dominio.Servico;
import br.ufrpe.artemis.servico.negocio.ServicoNegocio;

public class ServicosRecomendadosActivity extends AppCompatActivity {
    private ListView listView;
    private List<Servico> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos_recomendados);
        listView = findViewById(R.id.listViewRecomendados);
        ServicoNegocio negocio = new ServicoNegocio();
        list = negocio.buscarRecomendados();
        ArrayAdapter servicoAdapter = new ServicoAdapter(list);
        listView.setAdapter(servicoAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String i = String.valueOf(list.get(position).getId());
                startActivity(new Intent(ServicosRecomendadosActivity.this, ServicoActivity.class).putExtra("id", i));
            }
        });
    }
}
