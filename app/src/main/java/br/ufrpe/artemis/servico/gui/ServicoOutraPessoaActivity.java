package br.ufrpe.artemis.servico.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.ufrpe.artemis.R;
import br.ufrpe.artemis.servico.dominio.Servico;
import br.ufrpe.artemis.servico.negocio.ServicoNegocio;

public class ServicoOutraPessoaActivity extends AppCompatActivity {
    private List<Servico> arrayListServico;
    private ListView listViewGeral;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico_outra_pessoa);
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
        Intent intent = new Intent(ServicoOutraPessoaActivity.this, ServicoActivity.class);
        intent.putExtra("id", String.valueOf(idServico));
        startActivity(intent);
    }

    private void setTela() {
        final ServicoNegocio negocio = new ServicoNegocio();
        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("id");
        arrayListServico = negocio.listarSevicosPessoa(id);
        listViewGeral = findViewById(R.id.anunciosOutraId);
        final ArrayAdapter servicoAdapter = new ServicoAdapter(arrayListServico);
        listViewGeral.setAdapter(servicoAdapter);
    }
}
