package br.ufrpe.artemis.Servico.GUI;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import br.ufrpe.artemis.R;

public class SubModaActivity extends AppCompatActivity {
    private String[] subCategoriaModa = {"Costura", "Cabelo"};
    private ListView listaSubModas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_moda);

        listaSubModas = findViewById(R.id.modaListaId);

        ArrayAdapter<String> teAdaptador = new ArrayAdapter<String>(
                getApplicationContext(),android.R.layout.simple_list_item_1, android.R.id.text1,subCategoriaModa
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



        listaSubModas.setAdapter(teAdaptador);

        listaSubModas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){//13
                    startActivity(new Intent(SubModaActivity.this, ServicosListActivity.class).putExtra("id", "13"));
                }else if(position == 1){//16
                    startActivity(new Intent(SubModaActivity.this, ServicosListActivity.class).putExtra("id", "16"));
                }
                //star....
            }
        });





    }

}
