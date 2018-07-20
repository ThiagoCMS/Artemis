package br.ufrpe.artemis.Servico.GUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufrpe.artemis.Infra.ArtemisApp;
import br.ufrpe.artemis.Infra.Sessao;
import br.ufrpe.artemis.R;
import br.ufrpe.artemis.Servico.Dominio.Categoria;
import br.ufrpe.artemis.Servico.Dominio.Servico;
import br.ufrpe.artemis.Servico.Dominio.Subcategoria;
import br.ufrpe.artemis.Servico.Negocio.ServicoNegocio;

public class CriarServicoActivity extends AppCompatActivity {
    private EditText titulo;
    private EditText descricao;
    private Spinner subcategoria;
    private Spinner categoria;
    private Button cadastrar;
    private ArrayList<Categoria> listaCategoria;
    private ArrayList<Subcategoria> listaSubcategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_servico);

        setListaCategoria();
        setView();
        setSpinnerCategoria();

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarServico();
            }
        });

        categoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setListaSubcategoria(i);
                setSpinnerSubcategoria();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        subcategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void setView() {
        titulo = findViewById(R.id.tituloId);
        descricao = findViewById(R.id.textoId);
        subcategoria = findViewById(R.id.subcategoriaId);
        categoria = findViewById(R.id.categoriasId);
        cadastrar = findViewById(R.id.cadastrarId);
    }

    private void setSpinnerCategoria(){
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item , listarNomesCategoria());
        categoria.setAdapter(adapter);
    }



    private ArrayList<String> listarNomesCategoria(){
        ArrayList<String> nomesCategoria = new ArrayList<>();
        for (int i = 0; i < listaCategoria.size(); i++){
            nomesCategoria.add(listaCategoria.get(i).getNome());
        }
        return nomesCategoria;
    }

    private void setListaCategoria(){
        ServicoNegocio negocio = new ServicoNegocio();
        listaCategoria = negocio.listarCategoria();
    }

    private void setListaSubcategoria(int position){
        ServicoNegocio negocio = new ServicoNegocio();
        int idcategoria = listaCategoria.get(position).getId();
        listaSubcategoria = negocio.listarSubcategoria(idcategoria);
    }

    private ArrayList<String> listarNomesSubcategoria(){
        ArrayList<String> nomesSubcategoria = new ArrayList<>();
        for (int i = 0; i < listaSubcategoria.size(); i++){
            nomesSubcategoria.add(listaSubcategoria.get(i).getNome());
        }
        return nomesSubcategoria;
    }

    private void setSpinnerSubcategoria(){
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item , listarNomesSubcategoria());
        subcategoria.setAdapter(adapter);
    }

    private boolean validarCampos(){
        boolean erro = true;
        if(validarNome()){
            erro = false;
        }
        if(validarTexto()){
            erro = false;
        }
        return erro;
    }
    private boolean validarNome(){
        boolean erro = false;
        String nome = titulo.getText().toString().trim();
        if(nome.isEmpty()){
            erro = true;
            titulo.setError("Campo em branco");
        }else if (nome.length() > 20){
            erro = true;
            titulo.setError("Título muito grande");
        }else if (nome.length() < 4){
            erro = true;
            titulo.setError("Título muito pequeno");
        }
        return erro;
    }

    private boolean validarTexto() {
        boolean erro = false;
        String texto = descricao.getText().toString().trim();
        if (texto.isEmpty()) {
            erro = true;
            descricao.setError("Campo em branco");
        }else if (texto.length() > 450){
            erro = true;
            descricao.setError("Texto muito grande");
        }else if (texto.length() < 6){
            erro = true;
            descricao.setError("Texto muito pequeno");
        }
        return erro;
    }


    private void cadastrarServico(){
        if(validarCampos()) {
            Servico servico = new Servico();
            servico.setNome(titulo.getText().toString().trim());
            servico.setTexto(descricao.getText().toString().trim());
            int idsub = listaSubcategoria.get(subcategoria.getSelectedItemPosition()).getId();
            servico.setIdSubCategoria(idsub);
            int idusuario = Sessao.instance.getUsuario().getId();
            servico.setIdUsuario(idusuario);
            ServicoNegocio servicoNegocio = new ServicoNegocio();
            servicoNegocio.inserirServicoNoBanco(servico);
            Toast.makeText(ArtemisApp.getContext(), "Serviço cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            CriarServicoActivity.this.finish();
        }
    }
}


