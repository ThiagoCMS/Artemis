package br.ufrpe.artemis.pessoa.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import br.ufrpe.artemis.infra.Sessao;
import br.ufrpe.artemis.pessoa.dominio.Pessoa;
import br.ufrpe.artemis.pessoa.negocio.PessoaNegocio;
import br.ufrpe.artemis.R;
import br.ufrpe.artemis.servico.gui.MeusServicosActivity;
import br.ufrpe.artemis.servico.gui.ComentariosActivity;


public class PerfilActivity extends AppCompatActivity {
    private ImageView imagemUsuario;
    private TextView nomeUsuario;
    private TextView pessoaEmail;
    private TextView endereco;
    private TextView telefone;
    private Button botaoAnuncios;
    private Button botaoComentarios;
    private Button botaoEditar;
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
        botaoComentarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opnioes();
            }
        });
        botaoEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar();
            }
        });


    }
    private void setTela(){
        imagemUsuario = findViewById(R.id.imagemPerfilId);
        nomeUsuario = findViewById(R.id.nomeUsuarioId);
        botaoAnuncios = findViewById(R.id.botaoanuncioId);
        botaoComentarios = findViewById(R.id.botaoComentariosId);
        botaoEditar = findViewById(R.id.botaoEditarId);
        pessoaEmail = findViewById(R.id.pessoaEmailId);
        endereco = findViewById(R.id.enderecoId);
        telefone = findViewById(R.id.telefoneId);
        setPessoa();
    }

    private void setPessoa(){
        PessoaNegocio pessoaNegocio = new PessoaNegocio();
        int idUsuario = Sessao.instance.getUsuario().getId();
        Pessoa pessoa = pessoaNegocio.recuperarPessoaPorUsuario(idUsuario);
        nomeUsuario.setText(pessoa.getNome());
        pessoaEmail.setText(pessoa.getEmail());
        telefone.setText(pessoa.getTelefone());
        endereco.setText(pessoa.getEndereco().getCidade());
    }

    public void anuncios(){
        startActivity(new Intent(PerfilActivity.this, MeusServicosActivity.class));
    }

    public void opnioes(){

        startActivity(new Intent(PerfilActivity.this, ComentariosActivity.class));
    }

    public void editar(){
        startActivity(new Intent(PerfilActivity.this, EditarPerfilActivity.class));
        PerfilActivity.this.finish();
    }

}

