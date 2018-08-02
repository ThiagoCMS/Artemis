package br.ufrpe.artemis.servico.gui;

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
import br.ufrpe.artemis.servico.dominio.Subcategoria;
import br.ufrpe.artemis.servico.negocio.ServicoNegocio;

public class SubCategoriaActivity extends AppCompatActivity {
    private ListView listViewSubcategorias;
    private ArrayList<Subcategoria> listaSubcategorias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_categoria);
        setTela();
        listViewSubcategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                escolherSubcategoria(position);
            }
        });
    }

    private void escolherSubcategoria(int position){
        Intent intent = new Intent(SubCategoriaActivity.this, ServicosListActivity.class);
        intent.putExtra("id", String.valueOf(listaSubcategorias.get(position).getId()));
        startActivity(intent);
    }

    private void setTela(){
        ServicoNegocio negocio = new ServicoNegocio();
        Bundle extras = getIntent().getExtras();
        int id = Integer.parseInt(extras.getString("id"));
        listaSubcategorias = negocio.listarSubcategoria(id);
        listViewSubcategorias = findViewById(R.id.listViewSubcategoriaId);
        ArrayAdapter<String> teAdaptador = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, listarNomeSubcategorias()
        ){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.parseColor("#ED0621"));
                return view;
            }
        };
        listViewSubcategorias.setAdapter(teAdaptador);
    }

    private ArrayList<String> listarNomeSubcategorias(){
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < listaSubcategorias.size() ; i++){
            list.add(listaSubcategorias.get(i).getNome());
        }
        return list;
    }
}
