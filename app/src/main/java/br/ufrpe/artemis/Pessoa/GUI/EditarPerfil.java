package br.ufrpe.artemis.Pessoa.GUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.ufrpe.artemis.Endereco.Dominio.Endereco;
import br.ufrpe.artemis.Infra.Sessao;
import br.ufrpe.artemis.Pessoa.Dominio.Pessoa;
import br.ufrpe.artemis.Pessoa.Negocio.PessoaNegocio;
import br.ufrpe.artemis.R;

public class EditarPerfil extends AppCompatActivity {
    private EditText nome;
    private EditText telefone;
    private EditText email;
    private EditText rua;
    private EditText ruaNumero;
    private EditText cidade;
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
        nome = findViewById(R.id.nomeId);
        telefone = findViewById(R.id.telefoneId);
        email = findViewById(R.id.pessoaEmailId);
        rua = findViewById(R.id.ruaEnderecoId);
        ruaNumero = findViewById(R.id.numEnderecoId);
        cidade = findViewById(R.id.cidadeEnderecoId);
        alterar = findViewById(R.id.btAlterarId);

        PessoaNegocio pessoaNegocio = new PessoaNegocio();
        int idUsuario = Sessao.instance.getUsuario().getId();
        Pessoa pessoa = pessoaNegocio.recuperarPessoaPorUsuario(idUsuario);
        nome.setText(pessoa.getNome());
        email.setText(pessoa.getEmail());
        telefone.setText(pessoa.getTelefone());
        Endereco endereco = pessoa.getEndereco();
        rua.setText(endereco.getRua());
        ruaNumero.setText(endereco.getNumero());
        cidade.setText(endereco.getCidade());
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
        String Telefone = telefone.getText().toString().trim();
        if(Telefone.isEmpty()){
            erro = true;
            telefone.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarRua(){
        boolean erro = false;
        String Rua = rua.getText().toString().trim();
        if(Rua.isEmpty()){
            erro = true;
            rua.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarNumero(){
        boolean erro = false;
        String Numero = ruaNumero.getText().toString().trim();
        if(Numero.isEmpty()){
            erro = true;
            ruaNumero.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarCidade(){
        boolean erro = false;
        String Cidade = cidade.getText().toString().trim();
        if(Cidade.isEmpty()){
            erro = true;
            cidade.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarNome(){
        boolean erro = false;
        String Nome = nome.getText().toString().trim();
        if(Nome.isEmpty()){
            erro = true;
            nome.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarEmail(){
        boolean erro = false;
        String Email = email.getText().toString().trim();
        if(Email.isEmpty()){
            erro = true;
            email.setError("Campo em branco");
        }
        return erro;
    }

    private void alterarInformacoes(){
        PessoaNegocio pessoaNegocio = new PessoaNegocio();
        int idUsuario = Sessao.instance.getUsuario().getId();
        Pessoa pessoa = pessoaNegocio.recuperarPessoaPorUsuario(idUsuario);
        String nomeS = nome.getText().toString().trim();
        String telefoneS = telefone.getText().toString().trim();
        String emailS = email.getText().toString().trim();
        pessoa.setNome(nomeS);
        pessoa.setTelefone(telefoneS);
        pessoa.setEmail(emailS);
        String ruaS = rua.getText().toString().trim();
        String numeroS = ruaNumero.getText().toString().trim();
        String cidadeS = cidade.getText().toString().trim();
        Endereco endereco = pessoa.getEndereco();
        endereco.setNumero(numeroS);
        endereco.setRua(ruaS);
        endereco.setCidade(cidadeS);
        pessoa.setEndereco(endereco);
        pessoaNegocio.alterarPessoa(pessoa);
        startActivity(new Intent(EditarPerfil.this, PerfilActivity.class));
        EditarPerfil.this.finish();
    }

    public void onBackPressed(){
        startActivity(new Intent(EditarPerfil.this, PerfilActivity.class));
        EditarPerfil.this.finish();
    }
}
