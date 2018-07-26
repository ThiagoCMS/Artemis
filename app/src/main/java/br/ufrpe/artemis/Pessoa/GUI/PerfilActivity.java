package br.ufrpe.artemis.Pessoa.GUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import br.ufrpe.artemis.R;
import br.ufrpe.artemis.Servico.GUI.MeusServicosActivity;
import br.ufrpe.artemis.Servico.GUI.Opinioes;


public class PerfilActivity extends AppCompatActivity {
    private ImageView imagemUsuario;
    private TextView nomeUsuario;
    private Button botaoAnuncios;
    private Button botaoOpinioes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        setTela();
        botaoAnuncios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anuncios();
            }
        });
        botaoOpinioes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opnioes();
            }
        });

    }
    private void setTela(){
        imagemUsuario = findViewById(R.id.imagemPerfilId);
        nomeUsuario = findViewById(R.id.nomeUsuarioId);
        botaoAnuncios = findViewById(R.id.botaoanuncioId);
        botaoOpinioes = findViewById(R.id.btopiniaoId);

    }

    public void anuncios(){
        startActivity(new Intent(PerfilActivity.this, MeusServicosActivity.class));
    }

    public void opnioes(){
        startActivity(new Intent(PerfilActivity.this, Opinioes.class));
    }
}

