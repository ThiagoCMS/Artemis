package br.ufrpe.artemis.Pessoa.GUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.ufrpe.artemis.Infra.Sessao;
import br.ufrpe.artemis.Pessoa.Dominio.Pessoa;
import br.ufrpe.artemis.Pessoa.Negocio.PessoaNegocio;
import br.ufrpe.artemis.R;

public class EditarPerfil extends AppCompatActivity {
    private TextView nome;
    private TextView telefone;
    private TextView email;
    private TextView rua;
    private TextView ruaNumero;
    private TextView cidade;
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
        Pessoa pessoa = pessoaNegocio.recuperarPessoaPorId(idUsuario);
        nome.setText(pessoa.getNome());
        email.setText(pessoa.getEmail());
        telefone.setText(pessoa.getTelefone());

    }

    public void alterarPessoa(){
        if(validarCampos()) {

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
        String Telefone = telefone.getText().toString();
        if(Telefone.isEmpty()){
            erro = true;
            telefone.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarRua(){
        boolean erro = false;
        String Rua = rua.getText().toString();
        if(Rua.isEmpty()){
            erro = true;
            rua.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarNumero(){
        boolean erro = false;
        String Numero = ruaNumero.getText().toString();
        if(Numero.isEmpty()){
            erro = true;
            ruaNumero.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarCidade(){
        boolean erro = false;
        String Cidade = cidade.getText().toString();
        if(Cidade.isEmpty()){
            erro = true;
            cidade.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarNome(){
        boolean erro = false;
        String Nome = nome.getText().toString();
        if(Nome.isEmpty()){
            erro = true;
            nome.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarEmail(){
        boolean erro = false;
        String Email = email.getText().toString();
        if(Email.isEmpty()){
            erro = true;
            email.setError("Campo em branco");
        }
        return erro;
    }



}
