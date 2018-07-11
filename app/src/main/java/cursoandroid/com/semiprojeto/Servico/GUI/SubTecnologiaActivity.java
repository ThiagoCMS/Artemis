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

import cursoandroid.com.semiprojeto.R;

public class SubTecnologiaActivity extends AppCompatActivity {
    private String[] subCategoriaTecnologia = {"Computadores", "Eletrodomésticos","Celulares"};
    private ListView listaSubTecnologia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_tecnologia);

        listaSubTecnologia = findViewById(R.id.tecnologiaListaId);

        ArrayAdapter<String> teAdaptador = new ArrayAdapter<String>(
                getApplicationContext(),android.R.layout.simple_list_item_1, android.R.id.text1, subCategoriaTecnologia
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

        listaSubTecnologia.setAdapter(teAdaptador);

        listaSubTecnologia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){//5
                    startActivity(new Intent(SubTecnologiaActivity.this, ServicosListActivity.class).putExtra("id", "5"));
                }else if(position == 1){//6
                    startActivity(new Intent(SubTecnologiaActivity.this, ServicosListActivity.class).putExtra("id", "6"));
                }else if(position == 1){//7
                    startActivity(new Intent(SubTecnologiaActivity.this, ServicosListActivity.class).putExtra("id", "7"));
                }
                //star....
            }
        });





    }
}
