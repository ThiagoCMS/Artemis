package br.ufrpe.artemis.Servico.GUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View;
import android.widget.Toast;

import br.ufrpe.artemis.R;

public class CriarServicoActivity extends AppCompatActivity {
    private EditText nome;
    private EditText texto;
    private Spinner subcategoria;
    private Spinner categoria;
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_servico);
        setButtons();

    }

    private void setButtons() {
        nome = findViewById(R.id.tituloId);
        texto = findViewById(R.id.textoId);
        subcategoria = findViewById(R.id.subcategoriaId);
        categoria = findViewById(R.id.categoriasId);
        cadastrar = findViewById(R.id.cadastrarId);
    }

//    private boolean validarCampos(){
//        boolean erro = true;
//        if(validarNome()){
//            erro = false;
//        }
//        if(validarTexto()){
//            erro = false;
//        }
//        return erro;
//    }
//    private boolean validarNome(){
//        boolean erro = false;
//        String nome = nome.getText().toString();
//        if(nome.isEmpty()){
//            erro = true;
//            nome.setError("Campo em branco");
//        }
//        return erro;
//    }
//
//    private boolean validarTexto() {
//        boolean erro = false;
//        String texto = texto.getText().toString();
//        if (texto.isEmpty()) {
//            erro = true;
//            texto.setError("Campo em branco");
//        }
//        return erro;
//    }
//
    private void criarServico(){

    }
}

