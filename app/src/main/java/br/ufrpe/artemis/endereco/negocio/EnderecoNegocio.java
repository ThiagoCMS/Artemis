package br.ufrpe.artemis.endereco.negocio;

import br.ufrpe.artemis.endereco.dao.EnderecoDao;
import br.ufrpe.artemis.endereco.dominio.Endereco;

public class EnderecoNegocio {

    public void inserirEndereco(Endereco endereco){
        EnderecoDao dao = new EnderecoDao();
        dao.inserirEndereço(endereco);
    }

    public void alterarEndereco(Endereco endereco){
        EnderecoDao dao = new EnderecoDao();
        dao.alterarEndereco(endereco);
    }

    public Endereco recuperarEndereco(int id){
        EnderecoDao dao = new EnderecoDao();
        return dao.recuperarEndereco(id);
    }

    public Endereco recuperarUltimoEndereco(){
        EnderecoDao dao = new EnderecoDao();
        return dao.recuperarUltimoEndereco();
    }
}
