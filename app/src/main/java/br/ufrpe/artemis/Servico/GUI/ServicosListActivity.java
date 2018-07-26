package br.ufrpe.artemis.Servico.GUI;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.artemis.R;
import br.ufrpe.artemis.Servico.Dominio.Servico;
import br.ufrpe.artemis.Servico.Negocio.ServicoNegocio;

public class ServicosListActivity extends AppCompatActivity {
    private ArrayList<Servico> arrayListServico;
    private ListView listViewGeral;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos_list);


        setTela();

        listViewGeral.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    escolherServico(position);
                }
            });
    }

    private void escolherServico(int position){
        Servico servico = arrayListServico.get(position);
        int idServico = servico.getId();
        Intent intent = new Intent(ServicosListActivity.this, ServicoActivity.class);
        intent.putExtra("id", String.valueOf(idServico));
        startActivity(intent);
    }

    private void setTela(){
        final ServicoNegocio negocio = new ServicoNegocio();
        Bundle extras = getIntent().getExtras();
        int id = Integer.parseInt(extras.getString("id"));
        arrayListServico = negocio.listarSevicosSub(id);
        listViewGeral = findViewById(R.id.reformaListaId);
        //change
        final ArrayList<Servico> servicos = listarServicos();
        final ArrayAdapter servicoAdapter = new ServicoAdapter(servicos);
        listViewGeral.setAdapter(servicoAdapter);







    }

    private ArrayList<String> listarNomeServicos(){
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < arrayListServico.size() ; i++){
            list.add(arrayListServico.get(i).getNome());
        }
        return list;
    }

    private ArrayList<Servico> listarServicos(){
        ArrayList<Servico> list = new ArrayList<>();
        for(int i = 0; i < arrayListServico.size() ; i++){
            list.add(arrayListServico.get(i));
        }
        return list;
    }
}
