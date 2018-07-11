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
import android.widget.Toast;

import cursoandroid.com.semiprojeto.R;

public class SubSaudeActivity extends AppCompatActivity {
    private String[] subCategoriaSaude = {"Cuidadra de Idosos","Personal", "Nutricionista", "Massagista"};
    private ListView listaSubSaude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_saude);

        listaSubSaude = findViewById(R.id.saudeListaId);

        ArrayAdapter<String> teAdaptador = new ArrayAdapter<String>(
                getApplicationContext(),android.R.layout.simple_list_item_1, android.R.id.text1, subCategoriaSaude
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

        listaSubSaude.setAdapter(teAdaptador);

        listaSubSaude.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){//1
                    startActivity(new Intent(SubSaudeActivity.this, ServicosListActivity.class).putExtra("id", "1"));
                }else if(position == 1){//2
                    startActivity(new Intent(SubSaudeActivity.this, ServicosListActivity.class).putExtra("id", "2"));
                }else if(position == 1){//3
                    startActivity(new Intent(SubSaudeActivity.this, ServicosListActivity.class).putExtra("id", "3"));
                }else if(position == 1){//4
                    startActivity(new Intent(SubSaudeActivity.this, ServicosListActivity.class).putExtra("id", "4"));
                }
                //star....
            }
        });





    }
}
