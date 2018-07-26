package br.ufrpe.artemis.Pessoa.GUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import br.ufrpe.artemis.R;

public class EditarPerfil extends AppCompatActivity {
    private TextView telefone;
    private TextView email;
    private TextView rua;
    private TextView ruaNumero;
    private TextView cidade;
    private Button btAlterar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);
    }
}
