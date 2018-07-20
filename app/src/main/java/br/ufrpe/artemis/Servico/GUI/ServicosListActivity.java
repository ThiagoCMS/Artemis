package br.ufrpe.artemis.Servico.GUI;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

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
        setLists();
        setListView();
    }

    private int getId(){
        Bundle extras = getIntent().getExtras();
        return Integer.parseInt(extras.getString("id"));
    }

    private void setLists(){
        ServicoNegocio negocio = new ServicoNegocio();
        arrayListServico = negocio.listarSevicosSub(getId());
    }

    private ArrayList<String> listarNomeServicos(){
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < arrayListServico.size() ; i++){
            list.add(arrayListServico.get(i).getNome());
        }
        return list;
    }

    private void setListView(){
        listViewGeral = findViewById(R.id.reformaListaId);
        ArrayAdapter<String> teAdaptador = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, listarNomeServicos()
        ){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.parseColor("#ED0621"));
                return view;
            }
        };
        listViewGeral.setAdapter(teAdaptador);
    }
}
