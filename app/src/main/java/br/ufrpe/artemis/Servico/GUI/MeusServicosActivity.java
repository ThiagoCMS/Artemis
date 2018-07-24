package br.ufrpe.artemis.Servico.GUI;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufrpe.artemis.Infra.Sessao;
import br.ufrpe.artemis.R;
import br.ufrpe.artemis.Servico.Dominio.Servico;
import br.ufrpe.artemis.Servico.Negocio.ServicoNegocio;

public class MeusServicosActivity extends AppCompatActivity {
    private Button button;
    private ListView list;
    private ArrayList<Servico> listaServicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_servicos);

        setTela();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irCriarServico();
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editarServico(position);
            }
        });
    }

    private void editarServico(int position){
        Intent intent = new Intent(MeusServicosActivity.this, EditarServicoActivity.class);
        intent.putExtra("id", String.valueOf(listaServicos.get(position).getId()));
        startActivity(intent);
    }

    private void irCriarServico(){
        startActivity(new Intent(MeusServicosActivity.this, CriarServicoActivity.class));
    }

    private void setTela(){
        button = findViewById(R.id.buttonId);
        list = findViewById(R.id.listId);
        ServicoNegocio negocio = new ServicoNegocio();
        listaServicos = negocio.listarSevicosUs(Sessao.instance.getUsuario().getId());
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
        list.setAdapter(teAdaptador);
    }

    private ArrayList<String> listarNomeServicos(){
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < listaServicos.size() ; i++){
            list.add(listaServicos.get(i).getNome());
        }
        return list;
    }
}
