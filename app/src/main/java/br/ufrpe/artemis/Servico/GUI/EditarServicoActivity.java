package br.ufrpe.artemis.Servico.GUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.ufrpe.artemis.R;
import br.ufrpe.artemis.Servico.Dominio.Servico;
import br.ufrpe.artemis.Servico.Negocio.ServicoNegocio;

public class EditarServicoActivity extends AppCompatActivity {
    private EditText titulo;
    private EditText descricao;
    private TextView categoria;
    private TextView subcategoria;
    private Button botaoEditar;
    private Button botaoDeletar;
    private Servico servico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_servico);

        setTela();

        botaoEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar();
            }
        });

        botaoDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletar();
            }
        });
    }

    private void setTela(){
        setView();
        setServico();
        setInfo();
    }

    private void setView(){
        titulo = findViewById(R.id.tituloEditarId);
        descricao = findViewById(R.id.textoEditarId);
        categoria = findViewById(R.id.categoriasEditarId);
        subcategoria = findViewById(R.id.subcategoriaEditarId);
        botaoEditar = findViewById(R.id.botaoEditarId);
        botaoDeletar = findViewById(R.id.botaoDeletarId);
    }

    private void setInfo(){
        ServicoNegocio negocio = new ServicoNegocio();
        titulo.setText(servico.getNome().toString());
        descricao.setText(servico.getTexto().toString());
        categoria.setText(negocio.pegarCategoria(servico.getIdSubCategoria()));
        subcategoria.setText(negocio.pegarSubcategoria(servico.getIdSubCategoria()));
    }

    private int getId(){
        Bundle extras = getIntent().getExtras();
        return Integer.parseInt(extras.getString("id"));
    }

    private void setServico(){
        ServicoNegocio negocio = new ServicoNegocio();
        servico = negocio.pegarServico(getId());
    }

    private void editar(){
        if(validarCampos()) {
            servico.setNome(titulo.getText().toString().trim());
            servico.setTexto(descricao.getText().toString().trim());
            ServicoNegocio negocio = new ServicoNegocio();
            negocio.editarServico(servico);
            Toast.makeText(this, "Serviço editado com sucesso", Toast.LENGTH_SHORT).show();
            EditarServicoActivity.this.finish();
        }
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

    private void deletar(){
        ServicoNegocio negocio = new ServicoNegocio();
        negocio.deletarServicoDoBanco(servico);
        Toast.makeText(this, "Serviço deletado com sucesso", Toast.LENGTH_SHORT).show();
        EditarServicoActivity.this.finish();
    }
}
