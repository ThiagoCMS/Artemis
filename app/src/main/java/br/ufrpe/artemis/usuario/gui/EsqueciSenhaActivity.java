package br.ufrpe.artemis.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufrpe.artemis.R;
import br.ufrpe.artemis.pessoa.dominio.Pessoa;
import br.ufrpe.artemis.pessoa.negocio.PessoaNegocio;
import br.ufrpe.artemis.usuario.dominio.Usuario;
import br.ufrpe.artemis.usuario.negocio.UsuarioNegocio;

public class EsqueciSenhaActivity extends AppCompatActivity {
    private EditText cpfEsqueci;
    private EditText emailEsqueci;
    private EditText novaSenha;
    private EditText confirmarNovaSenha;
    private Button botaoAlterar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);
        setTela();
        botaoAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarAlteracaoSenha();
            }
        });
    }
    private void setTela(){
        cpfEsqueci = findViewById(R.id.cpfValidarId);
        emailEsqueci = findViewById(R.id.emailValidarId);
        novaSenha = findViewById(R.id.novaSenhaId);
        confirmarNovaSenha = findViewById(R.id.confirmarNovaSenhaId);
        botaoAlterar = findViewById(R.id.botaoAlterarSenhaId);
    }
    private void alterarSenha(){
        PessoaNegocio pessoaNegocio = new PessoaNegocio();
        Pessoa pessoa = pessoaNegocio.recuperarPessoaPorCpf(cpfEsqueci.getText().toString().trim());
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
        if(pessoa != null && pessoa.getEmail().equals(emailEsqueci.getText().toString().trim())){
            String senha = novaSenha.getText().toString().trim();
            pessoa.getUsuario().setSenha(senha);
            usuarioNegocio.alterarSenha(pessoa.getUsuario());
            finish();
        } else {
            Toast.makeText(this, "CPF/Email incorretos",Toast.LENGTH_SHORT).show();
        }

    }
    private void validarAlteracaoSenha(){
        if (validarCampos()){
            alterarSenha();
        }
    }
    private boolean validarCampos(){
        boolean erro = true;
        if (validarCpf()) {
            erro = false;
        }
        if (validarEmail()){
            erro = false;
        }
        if(validarSenha()){
            erro = false;
        }
        return erro;
    }
    private boolean validarCpf(){
        boolean erro = false;
        String cpf = cpfEsqueci.getText().toString().trim();
        if(cpf.isEmpty()){
            erro = true;
            cpfEsqueci.setError("Campo em branco");
        }else if(cpf.length() != 11){
            erro = true;
            cpfEsqueci.setError("Cpf inválido");
        }
        return erro;
    }
    private boolean validarEmail(){
        boolean erro = false;
        String email = emailEsqueci.getText().toString().trim();
        if(email.isEmpty()){
            erro = true;
            emailEsqueci.setError("Campo em branco");
        }
        return erro;
    }
    private boolean validarSenha(){
        boolean erro = false;
        String senha = novaSenha.getText().toString();
        String confirmarSenha = confirmarNovaSenha.getText().toString();
        if(senha.isEmpty() && confirmarSenha.isEmpty()){
            erro = true;
            novaSenha.setError("Campo em branco");
            confirmarNovaSenha.setError("Campo em branco");
        } else if(senha.isEmpty()){
            erro = true;
            novaSenha.setError("Campo em branco");
        }else if(senha.length() < 6){
            erro = true;
            novaSenha.setError("A senha deve conter pelo menos 6 caracteres");
        }else if(!senha.equals(confirmarSenha)){
            erro = true;
            novaSenha.setError("As senhas devem ser iguais");
            confirmarNovaSenha.setError("As senhas devem ser iguais");
        }
        return erro;
    }

}