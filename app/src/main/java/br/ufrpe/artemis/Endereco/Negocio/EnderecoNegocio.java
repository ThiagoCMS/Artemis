package br.ufrpe.artemis.Endereco.Negocio;

import br.ufrpe.artemis.Endereco.Dao.EnderecoDao;
import br.ufrpe.artemis.Endereco.Dominio.Endereco;

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
