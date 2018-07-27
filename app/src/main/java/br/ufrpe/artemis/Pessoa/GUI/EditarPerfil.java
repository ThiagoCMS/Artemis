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
        ruaNumero = findViewById(R.id.ruaEnderecoId);
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

    }
}
