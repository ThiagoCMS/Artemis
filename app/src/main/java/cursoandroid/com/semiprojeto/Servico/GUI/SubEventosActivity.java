package cursoandroid.com.semiprojeto.Servico.GUI;

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

import java.util.Objects;

import cursoandroid.com.semiprojeto.R;

import static java.lang.Integer.valueOf;

public class SubEventosActivity extends AppCompatActivity {
    private String[] subCategoriaEventos = {"Festa de Aniversários", "Casamentos"};
    private ListView listaSubEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_eventos);

        listaSubEventos = findViewById(R.id.eventosListaId);

        ArrayAdapter<String> teAdaptador = new ArrayAdapter<String>(
                getApplicationContext(),android.R.layout.simple_list_item_1, android.R.id.text1, subCategoriaEventos
        ){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.parseColor("#ED0621"));

                // Generate ListView Item using TextView
                return view;
            }
        };

        listaSubEventos.setAdapter(teAdaptador);

        listaSubEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){//11
                    startActivity(new Intent(SubEventosActivity.this, ServicosListActivity.class).putExtra("id", "11"));
                }else if(position == 1){//12
                    startActivity(new Intent(SubEventosActivity.this, ServicosListActivity.class).putExtra("id", "12"));
                }
                //star....
            }
        });





    }
}
