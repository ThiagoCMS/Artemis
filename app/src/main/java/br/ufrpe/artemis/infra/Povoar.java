package br.ufrpe.artemis.infra;

import br.ufrpe.artemis.avaliacao.dominio.Avaliacao;
import br.ufrpe.artemis.avaliacao.negocio.AvaliacaoNegocio;
import br.ufrpe.artemis.endereco.dominio.Endereco;
import br.ufrpe.artemis.infra.criptografia.Criptografia;
import br.ufrpe.artemis.pessoa.dominio.Pessoa;
import br.ufrpe.artemis.servico.dominio.Servico;
import br.ufrpe.artemis.servico.dominio.Subcategoria;
import br.ufrpe.artemis.servico.negocio.ServicoNegocio;
import br.ufrpe.artemis.usuario.dominio.Usuario;
import br.ufrpe.artemis.usuario.negocio.UsuarioNegocio;

public class Povoar {

    public static void povoar(){

        Usuario usuario = new Usuario();
        UsuarioNegocio negocio = new UsuarioNegocio();
        Pessoa pessoa = new Pessoa();
        Endereco endereco = new Endereco();
        Subcategoria subcategoria = new Subcategoria();
        Servico servico = new Servico();
        ServicoNegocio servicoNegocio = new ServicoNegocio();

        usuario.setCpf("11533878945");
        Criptografia criptografia = new Criptografia();
        String senhaCriptografada = criptografia.criptografarString("123456");
        usuario.setSenha(senhaCriptografada);
        pessoa.setNome("Roberta Carla");
        pessoa.setEmail("Roberta@gmail.com");
        pessoa.setTelefone("991457825");
        pessoa.setFotoPerfil(Auxiliar.comprimirImagem(Auxiliar.gerarBitmapPadrao()));
        endereco.setCidade("Recife");
        endereco.setRua("Rua de Apipucus");
        endereco.setNumero("111");
        endereco.setLat(-8.026990399999999);
        endereco.setLng(-34.9297431);
        negocio.inserirUsuario(usuario, pessoa, endereco);
        pessoa.setId(1);
        servico.setNome("Conserto Celulares");
        servico.setTexto("Eu conserto seu celular barato.");
        subcategoria.setId(7);
        servico.setSubcategoria(subcategoria);
        servico.setPessoa(pessoa);
        servicoNegocio.inserirServico(servico);

        usuario.setCpf("11433878946");
        senhaCriptografada = criptografia.criptografarString("123456");
        usuario.setSenha(senhaCriptografada);
        pessoa.setNome("Priscila Santos");
        pessoa.setEmail("Priscila@gmail.com");
        pessoa.setTelefone("991757856");
        pessoa.setFotoPerfil(Auxiliar.comprimirImagem(Auxiliar.gerarBitmapPadrao()));
        endereco.setCidade("Recife");
        endereco.setRua("Av 17 de agosto");
        endereco.setNumero("455");
        endereco.setLat( -8.036073199999999);
        endereco.setLng(-34.9134402);
        negocio.inserirUsuario(usuario, pessoa, endereco);
        pessoa.setId(2);
        servico.setNome("Formatar Pc");
        servico.setTexto("Eu formato notebook e faço backup.");
        subcategoria.setId(5);
        servico.setSubcategoria(subcategoria);
        servico.setPessoa(pessoa);
        servicoNegocio.inserirServico(servico);

        usuario.setCpf("11433878950");
        senhaCriptografada = criptografia.criptografarString("123456");
        usuario.setSenha(senhaCriptografada);
        pessoa.setNome("Luana Albuquerque");
        pessoa.setEmail("Luana@gmail.com");
        pessoa.setTelefone("991757870");
        pessoa.setFotoPerfil(Auxiliar.comprimirImagem(Auxiliar.gerarBitmapPadrao()));
        endereco.setCidade("Recife");
        endereco.setRua("Av 17 de agosto");
        endereco.setNumero("789");
        endereco.setLat(-8.0379652);
        endereco.setLng(-34.9156638);
        negocio.inserirUsuario(usuario, pessoa, endereco);
        pessoa.setId(3);
        servico.setNome("Aniversários");
        servico.setTexto("Festas de Aniversário por um preço barato.");
        subcategoria.setId(11);
        servico.setSubcategoria(subcategoria);
        servico.setPessoa(pessoa);
        servicoNegocio.inserirServico(servico);

        usuario.setCpf("11433878150");
        senhaCriptografada = criptografia.criptografarString("123456");
        usuario.setSenha(senhaCriptografada);
        pessoa.setNome("Aristana Carvalho");
        pessoa.setEmail("Aristana@gmail.com");
        pessoa.setTelefone("78945612323");
        pessoa.setFotoPerfil(Auxiliar.comprimirImagem(Auxiliar.gerarBitmapPadrao()));
        endereco.setCidade("Recife");
        endereco.setRua("Rua do sol'");
        endereco.setNumero("15");
        endereco.setLat(-8.060369);
        endereco.setLng(-34.8787366);
        negocio.inserirUsuario(usuario, pessoa, endereco);
        pessoa.setId(4);
        servico.setNome("Casamentos");
        servico.setTexto("Casamentos Personalizados");
        subcategoria.setId(12);
        servico.setSubcategoria(subcategoria);
        servico.setPessoa(pessoa);
        servicoNegocio.inserirServico(servico);

        usuario.setCpf("11433878964");
        senhaCriptografada = criptografia.criptografarString("123456");
        usuario.setSenha(senhaCriptografada);
        pessoa.setNome("Paula Gonçalves");
        pessoa.setEmail("Paula@gmail.com");
        pessoa.setTelefone("991757856");
        pessoa.setFotoPerfil(Auxiliar.comprimirImagem(Auxiliar.gerarBitmapPadrao()));
        endereco.setCidade("Olinda");
        endereco.setRua("Av São João Batista");
        endereco.setNumero("690");
        endereco.setLat(-7.9693988);
        endereco.setLng(-34.8379287);
        negocio.inserirUsuario(usuario, pessoa, endereco);
        pessoa.setId(5);
        servico.setNome("Camisas Personalizadas");
        servico.setTexto("Crio camisas personalizadas.");
        subcategoria.setId(13);
        servico.setSubcategoria(subcategoria);
        servico.setPessoa(pessoa);
        servicoNegocio.inserirServico(servico);

        usuario.setCpf("11433878978");
        senhaCriptografada = criptografia.criptografarString("123456");
        usuario.setSenha(senhaCriptografada);
        pessoa.setNome("Karol Santos");
        pessoa.setEmail("Karol@gmail.com");
        pessoa.setTelefone("991757859");
        pessoa.setFotoPerfil(Auxiliar.comprimirImagem(Auxiliar.gerarBitmapPadrao()));
        endereco.setCidade("Recife");
        endereco.setRua("Rua de Apipucus");
        endereco.setNumero("400");
        endereco.setLat(-8.0245707);
        endereco.setLng(-34.9302035);
        negocio.inserirUsuario(usuario, pessoa, endereco);
        pessoa.setId(6);
        servico.setNome("Roupas Rasgadas");
        servico.setTexto("Eu costuro roupas rasgadas");
        subcategoria.setId(13);
        servico.setSubcategoria(subcategoria);
        servico.setPessoa(pessoa);
        servicoNegocio.inserirServico(servico);

        usuario.setCpf("11433878989");
        senhaCriptografada = criptografia.criptografarString("123456");
        usuario.setSenha(senhaCriptografada);
        pessoa.setNome("Karol Carvalho");
        pessoa.setEmail("KarolC@gmail.com");
        pessoa.setTelefone("991757856");
        pessoa.setFotoPerfil(Auxiliar.comprimirImagem(Auxiliar.gerarBitmapPadrao()));
        endereco.setCidade("Recife");
        endereco.setRua("Av norte");
        endereco.setNumero("540");
        endereco.setLat(-8.0300122);
        endereco.setLng(-34.8994949);
        negocio.inserirUsuario(usuario, pessoa, endereco);
        pessoa.setId(7);
        servico.setNome("Limpeza de Casa");
        servico.setTexto("Limpo sua casa.");
        subcategoria.setId(15);
        servico.setSubcategoria(subcategoria);
        servico.setPessoa(pessoa);
        servicoNegocio.inserirServico(servico);

        usuario.setCpf("11433878914");
        senhaCriptografada = criptografia.criptografarString("123456");
        usuario.setSenha(senhaCriptografada);
        pessoa.setNome("Kaline Almeida");
        pessoa.setEmail("K.Al@gmail.com");
        pessoa.setTelefone("841757856");
        pessoa.setFotoPerfil(Auxiliar.comprimirImagem(Auxiliar.gerarBitmapPadrao()));
        endereco.setCidade("Recife");
        endereco.setRua("Estrada do Encanamento");
        endereco.setNumero("500");
        endereco.setLat(-8.0313538);
        endereco.setLng(-34.9148534);
        negocio.inserirUsuario(usuario, pessoa, endereco);
        pessoa.setId(8);
        servico.setNome("Lavagem de Carro");
        servico.setTexto("Eu lavo seu carro");
        subcategoria.setId(15);
        servico.setSubcategoria(subcategoria);
        servico.setPessoa(pessoa);
        servicoNegocio.inserirServico(servico);

        usuario.setCpf("11433878973");
        senhaCriptografada = criptografia.criptografarString("123456");
        usuario.setSenha(senhaCriptografada);
        pessoa.setNome("Clara Souza");
        pessoa.setEmail("clara@gmail.com");
        pessoa.setTelefone("841757834");
        pessoa.setFotoPerfil(Auxiliar.comprimirImagem(Auxiliar.gerarBitmapPadrao()));
        endereco.setCidade("Recife");
        endereco.setRua("Rua conde de irajá");
        endereco.setNumero("45");
        endereco.setLat(-8.0458801);
        endereco.setLng(-34.904522);
        negocio.inserirUsuario(usuario, pessoa, endereco);
        pessoa.setId(9);
        servico.setNome("Passo pano na casa");
        servico.setTexto("Passo pano na sua casa por 50 reais.");
        subcategoria.setId(15);
        servico.setSubcategoria(subcategoria);
        servico.setPessoa(pessoa);
        servicoNegocio.inserirServico(servico);

        usuario.setCpf("11433878654");
        senhaCriptografada = criptografia.criptografarString("123456");
        usuario.setSenha(senhaCriptografada);
        pessoa.setNome("Lana Almeida");
        pessoa.setEmail("Lanal@gmail.com");
        pessoa.setTelefone("841757867");
        pessoa.setFotoPerfil(Auxiliar.comprimirImagem(Auxiliar.gerarBitmapPadrao()));
        endereco.setCidade("Recife");
        endereco.setRua("Av caxangá");
        endereco.setNumero("500");
        endereco.setLat(-8.054487);
        endereco.setLng(-34.91326);
        negocio.inserirUsuario(usuario, pessoa, endereco);
        pessoa.setId(10);
        servico.setNome("Cuido de Idosos");
        servico.setTexto("Eu cuido de Idosos em horario integral.");
        subcategoria.setId(1);
        servico.setPessoa(pessoa);
        servicoNegocio.inserirServico(servico);
        pessoa.setId(10);
        servico.setNome("Pintura de moveis");
        servico.setTexto("Eu pinto seus moveis");
        subcategoria.setId(9);
        servico.setSubcategoria(subcategoria);
        servico.setPessoa(pessoa);
        servicoNegocio.inserirServico(servico);


        Avaliacao avaliacao = new Avaliacao();
        AvaliacaoNegocio avaliacaoNegocio = new AvaliacaoNegocio();
        Pessoa cliente = new Pessoa();
        Pessoa prestadora = new Pessoa();
        cliente.setId(1);
        prestadora.setId(4);
        avaliacao.setCliente(cliente);
        avaliacao.setPrestadora(prestadora);
        avaliacao.setNotaAtendimento(3.5);
        avaliacao.setNotaPreco(4.0);
        avaliacao.setNotaQualidade(2.0);
        avaliacaoNegocio.inserirAvaliacao(avaliacao);

        cliente.setId(1);
        prestadora.setId(5);
        avaliacao.setCliente(cliente);
        avaliacao.setPrestadora(prestadora);
        avaliacao.setNotaAtendimento(4.0);
        avaliacao.setNotaPreco(4.0);
        avaliacao.setNotaQualidade(3.0);
        avaliacaoNegocio.inserirAvaliacao(avaliacao);

        cliente.setId(1);
        prestadora.setId(6);
        avaliacao.setCliente(cliente);
        avaliacao.setPrestadora(prestadora);
        avaliacao.setNotaAtendimento(2.5);
        avaliacao.setNotaPreco(4.0);
        avaliacao.setNotaQualidade(3.0);
        avaliacaoNegocio.inserirAvaliacao(avaliacao);

        cliente.setId(1);
        prestadora.setId(7);
        avaliacao.setCliente(cliente);
        avaliacao.setPrestadora(prestadora);
        avaliacao.setNotaAtendimento(3.5);
        avaliacao.setNotaPreco(2.0);
        avaliacao.setNotaQualidade(2.0);
        avaliacaoNegocio.inserirAvaliacao(avaliacao);

        cliente.setId(1);
        prestadora.setId(8);
        avaliacao.setCliente(cliente);
        avaliacao.setPrestadora(prestadora);
        avaliacao.setNotaAtendimento(5.0);
        avaliacao.setNotaPreco(3.0);
        avaliacao.setNotaQualidade(4.0);
        avaliacaoNegocio.inserirAvaliacao(avaliacao);

        cliente.setId(1);
        prestadora.setId(9);
        avaliacao.setCliente(cliente);
        avaliacao.setPrestadora(prestadora);
        avaliacao.setNotaAtendimento(3.5);
        avaliacao.setNotaPreco(4.0);
        avaliacao.setNotaQualidade(2.0);
        avaliacaoNegocio.inserirAvaliacao(avaliacao);

        cliente.setId(1);
        prestadora.setId(10);
        avaliacao.setCliente(cliente);
        avaliacao.setPrestadora(prestadora);
        avaliacao.setNotaAtendimento(1.5);
        avaliacao.setNotaPreco(1.5);
        avaliacao.setNotaQualidade(2.0);
        avaliacaoNegocio.inserirAvaliacao(avaliacao);

        cliente.setId(2);
        prestadora.setId(4);
        avaliacao.setCliente(cliente);
        avaliacao.setPrestadora(prestadora);
        avaliacao.setNotaAtendimento(3.5);
        avaliacao.setNotaPreco(3.5);
        avaliacao.setNotaQualidade(3.0);
        avaliacaoNegocio.inserirAvaliacao(avaliacao);

        cliente.setId(2);
        prestadora.setId(5);
        avaliacao.setCliente(cliente);
        avaliacao.setPrestadora(prestadora);
        avaliacao.setNotaAtendimento(4.0);
        avaliacao.setNotaPreco(4.0);
        avaliacao.setNotaQualidade(4.0);
        avaliacaoNegocio.inserirAvaliacao(avaliacao);

        cliente.setId(2);
        prestadora.setId(6);
        avaliacao.setCliente(cliente);
        avaliacao.setPrestadora(prestadora);
        avaliacao.setNotaAtendimento(5.0);
        avaliacao.setNotaPreco(4.0);
        avaliacao.setNotaQualidade(4.0);
        avaliacaoNegocio.inserirAvaliacao(avaliacao);

        cliente.setId(2);
        prestadora.setId(7);
        avaliacao.setCliente(cliente);
        avaliacao.setPrestadora(prestadora);
        avaliacao.setNotaAtendimento(4.0);
        avaliacao.setNotaPreco(4.5);
        avaliacao.setNotaQualidade(2.0);
        avaliacaoNegocio.inserirAvaliacao(avaliacao);

        cliente.setId(2);
        prestadora.setId(8);
        avaliacao.setCliente(cliente);
        avaliacao.setPrestadora(prestadora);
        avaliacao.setNotaAtendimento(3.5);
        avaliacao.setNotaPreco(4.0);
        avaliacao.setNotaQualidade(3.5);
        avaliacaoNegocio.inserirAvaliacao(avaliacao);

        cliente.setId(2);
        prestadora.setId(9);
        avaliacao.setCliente(cliente);
        avaliacao.setPrestadora(prestadora);
        avaliacao.setNotaAtendimento(2.0);
        avaliacao.setNotaPreco(3.0);
        avaliacao.setNotaQualidade(1.0);
        avaliacaoNegocio.inserirAvaliacao(avaliacao);

        cliente.setId(2);
        prestadora.setId(10);
        avaliacao.setCliente(cliente);
        avaliacao.setPrestadora(prestadora);
        avaliacao.setNotaAtendimento(1.0);
        avaliacao.setNotaPreco(1.0);
        avaliacao.setNotaQualidade(1.0);
        avaliacaoNegocio.inserirAvaliacao(avaliacao);

        cliente.setId(3);
        prestadora.setId(4);
        avaliacao.setCliente(cliente);
        avaliacao.setPrestadora(prestadora);
        avaliacao.setNotaAtendimento(4.0);
        avaliacao.setNotaPreco(4.0);
        avaliacao.setNotaQualidade(4.0);
        avaliacaoNegocio.inserirAvaliacao(avaliacao);

        cliente.setId(3);
        prestadora.setId(5);
        avaliacao.setCliente(cliente);
        avaliacao.setPrestadora(prestadora);
        avaliacao.setNotaAtendimento(4.0);
        avaliacao.setNotaPreco(2.0);
        avaliacao.setNotaQualidade(4.0);
        avaliacaoNegocio.inserirAvaliacao(avaliacao);

        cliente.setId(3);
        prestadora.setId(6);
        avaliacao.setCliente(cliente);
        avaliacao.setPrestadora(prestadora);
        avaliacao.setNotaAtendimento(4.0);
        avaliacao.setNotaPreco(3.5);
        avaliacao.setNotaQualidade(3.6);
        avaliacaoNegocio.inserirAvaliacao(avaliacao);

        cliente.setId(3);
        prestadora.setId(7);
        avaliacao.setCliente(cliente);
        avaliacao.setPrestadora(prestadora);
        avaliacao.setNotaAtendimento(1.0);
        avaliacao.setNotaPreco(3.5);
        avaliacao.setNotaQualidade(3.5);
        avaliacaoNegocio.inserirAvaliacao(avaliacao);

        cliente.setId(3);
        prestadora.setId(8);
        avaliacao.setCliente(cliente);
        avaliacao.setPrestadora(prestadora);
        avaliacao.setNotaAtendimento(2.9);
        avaliacao.setNotaPreco(4.2);
        avaliacao.setNotaQualidade(3.6);
        avaliacaoNegocio.inserirAvaliacao(avaliacao);

        cliente.setId(3);
        prestadora.setId(9);
        avaliacao.setCliente(cliente);
        avaliacao.setPrestadora(prestadora);
        avaliacao.setNotaAtendimento(1.7);
        avaliacao.setNotaPreco(3.7);
        avaliacao.setNotaQualidade(4.5);
        avaliacaoNegocio.inserirAvaliacao(avaliacao);

        cliente.setId(3);
        prestadora.setId(10);
        avaliacao.setCliente(cliente);
        avaliacao.setPrestadora(prestadora);
        avaliacao.setNotaAtendimento(1.6);
        avaliacao.setNotaPreco(1.7);
        avaliacao.setNotaQualidade(1.8);
        avaliacaoNegocio.inserirAvaliacao(avaliacao);






    }

}