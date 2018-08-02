package br.ufrpe.artemis.servico.gui;

import android.content.Intent;
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
import br.ufrpe.artemis.infra.ArtemisApp;
import br.ufrpe.artemis.infra.Sessao;
import br.ufrpe.artemis.pessoa.dominio.Pessoa;
import br.ufrpe.artemis.R;
import br.ufrpe.artemis.servico.dominio.Categoria;
import br.ufrpe.artemis.servico.dominio.Servico;
import br.ufrpe.artemis.servico.dominio.Subcategoria;
import br.ufrpe.artemis.servico.negocio.ServicoNegocio;

public class CriarServicoActivity extends AppCompatActivity {
    private EditText titulo;
    private EditText descricao;
    private Spinner subcategoriaSpinner;
    private Spinner categoriaSpinner;
    private Button cadastrar;
    private ArrayList<Categoria> listaCategoria;
    private ArrayList<Subcategoria> listaSubcategoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_servico);
        setTela();
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarServico();
            }
        });
        categoriaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setSpinnerSubcategoria(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        subcategoriaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void setTela(){
        titulo = findViewById(R.id.tituloId);
        descricao = findViewById(R.id.textoId);
        subcategoriaSpinner = findViewById(R.id.subcategoriaId);
        categoriaSpinner = findViewById(R.id.categoriasId);
        cadastrar = findViewById(R.id.cadastrarId);
        ServicoNegocio negocio = new ServicoNegocio();
        listaCategoria = negocio.listarCategoria();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item , listarNomesCategoria());
        categoriaSpinner.setAdapter(adapter);
    }

    private ArrayList<String> listarNomesCategoria(){
        ArrayList<String> nomesCategoria = new ArrayList<>();
        for (int i = 0; i < listaCategoria.size(); i++){
            nomesCategoria.add(listaCategoria.get(i).getNome());
        }
        return nomesCategoria;
    }

    private ArrayList<String> listarNomesSubcategoria(){
        ArrayList<String> nomesSubcategoria = new ArrayList<>();
        for (int i = 0; i < listaSubcategoria.size(); i++){
            nomesSubcategoria.add(listaSubcategoria.get(i).getNome());
        }
        return nomesSubcategoria;
    }

    private void setSpinnerSubcategoria(int position){
        ServicoNegocio negocio = new ServicoNegocio();
        int idcategoria = listaCategoria.get(position).getId();
        listaSubcategoria = negocio.listarSubcategoria(idcategoria);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item , listarNomesSubcategoria());
        subcategoriaSpinner.setAdapter(adapter);
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
            titulo.setError("Título deve ter no máximo 20 caracteres");
        }else if (nome.length() < 4){
            erro = true;
            titulo.setError("Título deve ter no mínimo 4 caracteres");
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
            descricao.setError("Texto deve ter no máximo 450 caracteres");
        }else if (texto.length() < 6){
            erro = true;
            descricao.setError("Texto deve ter no mínimo 6 caracteres");
        }
        return erro;
    }

    private void cadastrarServico(){
        if(validarCampos()) {
            Servico servico = new Servico();
            servico.setNome(titulo.getText().toString().trim());
            servico.setTexto(descricao.getText().toString().trim());
            Subcategoria subcategoria = listaSubcategoria.get(subcategoriaSpinner.getSelectedItemPosition());
            servico.setSubcategoria(subcategoria);
            ServicoNegocio servicoNegocio = new ServicoNegocio();
            int idusuario = Sessao.instance.getUsuario().getId();
            Pessoa pessoa = servicoNegocio.recuperarPessoa(idusuario);
            servico.setPessoa(pessoa);
            servicoNegocio.inserirServicoNoBanco(servico);
            Toast.makeText(ArtemisApp.getContext(), "Serviço cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(CriarServicoActivity.this, MeusServicosActivity.class));
            CriarServicoActivity.this.finish();
        }
    }

    public void onBackPressed(){
        startActivity(new Intent(CriarServicoActivity.this, MeusServicosActivity.class));
        CriarServicoActivity.this.finish();
    }
}


