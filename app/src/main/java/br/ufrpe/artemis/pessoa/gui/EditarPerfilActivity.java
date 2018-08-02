package br.ufrpe.artemis.pessoa.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.ufrpe.artemis.endereco.dominio.Endereco;
import br.ufrpe.artemis.infra.Sessao;
import br.ufrpe.artemis.pessoa.dominio.Pessoa;
import br.ufrpe.artemis.pessoa.negocio.PessoaNegocio;
import br.ufrpe.artemis.R;

public class EditarPerfilActivity extends AppCompatActivity {
    private EditText nomeEditar;
    private EditText telefoneEditar;
    private EditText emailEditar;
    private EditText ruaEditar;
    private EditText numeroEditar;
    private EditText cidadeEditar;
    private Button alterar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);
        setTela();
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alterarPessoa();
            }
        });
    }

    private void setTela(){
        nomeEditar = findViewById(R.id.nomeId);
        telefoneEditar = findViewById(R.id.telefoneId);
        emailEditar = findViewById(R.id.pessoaEmailId);
        ruaEditar = findViewById(R.id.ruaEnderecoId);
        numeroEditar = findViewById(R.id.numEnderecoId);
        cidadeEditar = findViewById(R.id.cidadeEnderecoId);
        alterar = findViewById(R.id.btAlterarId);
        setPessoa();
    }

    private void setPessoa(){
        PessoaNegocio pessoaNegocio = new PessoaNegocio();
        int idUsuario = Sessao.instance.getUsuario().getId();
        Pessoa pessoa = pessoaNegocio.recuperarPessoaPorUsuario(idUsuario);
        nomeEditar.setText(pessoa.getNome());
        emailEditar.setText(pessoa.getEmail());
        telefoneEditar.setText(pessoa.getTelefone());
        Endereco endereco = pessoa.getEndereco();
        ruaEditar.setText(endereco.getRua());
        numeroEditar.setText(endereco.getNumero());
        cidadeEditar.setText(endereco.getCidade());
    }

    public void alterarPessoa(){
        if(validarCampos()) {
            alterarInformacoes();
        }
    }


    private boolean validarCampos(){
        boolean erro = true;
        if(validarNome()){
            erro = false;
        }
        if(validarEmail()){
            erro = false;
        }
        if(validarTelefone()){
            erro= false;
        }
        if(validarRua()){
            erro = false;
        }
        if(validarNumero()){
            erro = false;
        }
        if(validarCidade()){
            erro= false;
        }
        return erro;
    }

    private boolean validarTelefone(){
        boolean erro = false;
        String telefoneS = telefoneEditar.getText().toString().trim();
        if(telefoneS.isEmpty()){
            erro = true;
            telefoneEditar.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarRua(){
        boolean erro = false;
        String rua = ruaEditar.getText().toString().trim();
        if(rua.isEmpty()){
            erro = true;
            ruaEditar.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarNumero(){
        boolean erro = false;
        String numero = numeroEditar.getText().toString().trim();
        if(numero.isEmpty()){
            erro = true;
            numeroEditar.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarCidade(){
        boolean erro = false;
        String Cidade = cidadeEditar.getText().toString().trim();
        if(Cidade.isEmpty()){
            erro = true;
            cidadeEditar.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarNome(){
        boolean erro = false;
        String nome = nomeEditar.getText().toString().trim();
        if(nome.isEmpty()){
            erro = true;
            nomeEditar.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarEmail(){
        boolean erro = false;
        String Email = emailEditar.getText().toString().trim();
        if(Email.isEmpty()){
            erro = true;
            emailEditar.setError("Campo em branco");
        }
        return erro;
    }

    private void alterarInformacoes(){
        PessoaNegocio pessoaNegocio = new PessoaNegocio();
        int idUsuario = Sessao.instance.getUsuario().getId();
        Pessoa pessoa = pessoaNegocio.recuperarPessoaPorUsuario(idUsuario);
        String nome = nomeEditar.getText().toString().trim();
        String telefone = telefoneEditar.getText().toString().trim();
        String email = emailEditar.getText().toString().trim();
        pessoa.setNome(nome);
        pessoa.setTelefone(telefone);
        pessoa.setEmail(email);
        String rua = ruaEditar.getText().toString().trim();
        String numero = numeroEditar.getText().toString().trim();
        String cidade = cidadeEditar.getText().toString().trim();
        Endereco endereco = pessoa.getEndereco();
        endereco.setNumero(numero);
        endereco.setRua(rua);
        endereco.setCidade(cidade);
        pessoa.setEndereco(endereco);
        pessoaNegocio.alterarPessoa(pessoa);
        startActivity(new Intent(EditarPerfilActivity.this, PerfilActivity.class));
        EditarPerfilActivity.this.finish();
    }

    public void onBackPressed(){
        startActivity(new Intent(EditarPerfilActivity.this, PerfilActivity.class));
        EditarPerfilActivity.this.finish();
    }
}
