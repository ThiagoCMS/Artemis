package br.ufrpe.artemis.pessoa.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufrpe.artemis.R;
import br.ufrpe.artemis.servico.dao.ServicoDao;
import br.ufrpe.artemis.servico.dominio.Servico;
import br.ufrpe.artemis.servico.gui.ServicoActivity;
import br.ufrpe.artemis.servico.gui.ServicoAdapter;
import br.ufrpe.artemis.servico.gui.ServicosListActivity;
import br.ufrpe.artemis.servico.negocio.ServicoNegocio;

public class SearchResultsActivity extends AppCompatActivity {
    private ArrayList<Servico> arrayListServico;
    private ListView listView;
    private ServicoNegocio servicoNegocio;
    private ArrayAdapter servicoAdapter;
    private Bundle extra;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        seteTela();
        String texto = extra.getString("pesquisa");
        textView.setText("Resultados da busca para '"+texto+"' :");
        listView.setAdapter(servicoAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                escolherServico(position);

            }
        });
    }

    private void seteTela(){
        extra = getIntent().getExtras();
        listView = findViewById(R.id.listaResultados);
        textView = findViewById(R.id.textId);
        servicoNegocio = new ServicoNegocio();
        ArrayList<Servico> servico = servicoNegocio.listarServicos();
        arrayListServico = listarServicosNomes(servico);
        servicoAdapter = new ServicoAdapter(arrayListServico);

    }
    private ArrayList<Servico> listarServicosNomes(ArrayList<Servico> servicos){
        ArrayList<Servico> listServico = new ArrayList<>();
        String texto = extra.getString("pesquisa");
        for(int i = 0; i < servicos.size() ; i++){
            String servicoTitulo = servicos.get(i).getNome();
            if(servicoTitulo.toLowerCase().contains(texto.toLowerCase())){
                listServico.add(servicos.get(i));
            }
        }
        return listServico;
    }

    private void escolherServico(int position){
        Servico servico = arrayListServico.get(position);
        int idServico = servico.getId();
        Intent intent = new Intent(SearchResultsActivity.this, ServicoActivity.class);
        intent.putExtra("id", String.valueOf(idServico));
        startActivity(intent);
    }
}
