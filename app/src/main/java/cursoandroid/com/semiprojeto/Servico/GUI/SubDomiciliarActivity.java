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

public class SubDomiciliarActivity extends AppCompatActivity {
    private String[] subCategoriaDomiciliar= {"Comida Caseira", "Limpeza Doméstica"};
    private ListView listaSubDomiciliar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_domiciliar);

        listaSubDomiciliar = findViewById(R.id.domiciliarListaId);

        ArrayAdapter<String> teAdaptador = new ArrayAdapter<String>(
                getApplicationContext(),android.R.layout.simple_list_item_1, android.R.id.text1, subCategoriaDomiciliar
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

        listaSubDomiciliar.setAdapter(teAdaptador);

        listaSubDomiciliar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){//14
                    startActivity(new Intent(SubDomiciliarActivity.this, ServicosListActivity.class).putExtra("id", 14));
                }else if(position == 1){//15
                    startActivity(new Intent(SubDomiciliarActivity.this, ServicosListActivity.class).putExtra("id", 15));
                }
                //star....
            }
        });





    }
}
