package br.ufrpe.artemis.usuario.gui;

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

public class ConfiguracoesActivity extends AppCompatActivity {
    private ListView lv;
    private ArrayList<String> listaConfig =  new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);
        setTela();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    startActivity(new Intent(ConfiguracoesActivity.this, AlterarSenhaActivity.class));
                }
            }
        });
    }

    private void setTela(){
        listaConfig.add("Alterar senha");
        lv = findViewById(R.id.ListViewConfig);
        ArrayAdapter<String> teAdaptador = new ArrayAdapter<String>(
                getApplicationContext(),android.R.layout.simple_list_item_1, android.R.id.text1, listaConfig
        ){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.parseColor("#ED0621"));
                return view;
            }
        };
        lv.setAdapter(teAdaptador);
    }
}
