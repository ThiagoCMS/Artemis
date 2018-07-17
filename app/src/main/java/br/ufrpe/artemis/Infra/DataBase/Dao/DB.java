package br.ufrpe.artemis.Infra.DataBase.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
    private static final String NOME_DO_BANCO = "Banco_Artemis";
    private static final int VERSAO = 1;

    public DB(Context ctx) { super(ctx, NOME_DO_BANCO, null, VERSAO);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuario (id integer primary key autoincrement, senha text not null, cpf text not null);");
        db.execSQL("create table servico (id integer primary key autoincrement, nome text not null, texto text not null, idusuario integer, idsubcategoria integer);");
        db.execSQL("create table categoria (id integer primary key autoincrement, nome text not null);");
        db.execSQL("create table subcategoria (id integer primary key autoincrement, nome text not null, idcategoria integer);");
        db.execSQL("create table pessoa (id integer primary key autoincrement, nome text not null, idusuario integer);");
        criarCategoria(db);
        criarSubCategoria(db);
        criarServico(db);
    }

    /*@Override
    public void onOpen(SQLiteDatabase db){
        db.execSQL("DELETE FROM usuario;");
        db.execSQL("DELETE FROM pessoa;");
        db.execSQL("DELETE FROM servico;");
        db.execSQL("DELETE FROM categoria;");
        db.execSQL("DELETE FROM subcategoria;");
        criarCategoria(db);
        criarSubCategoria(db);
        criarServico(db);

    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table usuario;");
        db.execSQL("drop table pessoa;");
        db.execSQL("drop table servico;");
        db.execSQL("drop table categoria;");
        db.execSQL("drop table subcategoria;");
    }

    public void delete(SQLiteDatabase db){
        db.execSQL("drop table usuario");
        db.execSQL("drop table pessoa");
        db.execSQL("drop table servico");
        db.execSQL("drop table categoria");
        db.execSQL("drop table subcategoria");
    }

    public void criarCategoria(SQLiteDatabase db){
        String addCategoria = "INSERT INTO categoria (nome) values";

        db.execSQL(addCategoria + "('Saude');");
        db.execSQL(addCategoria + "('Tecnologia');");
        db.execSQL(addCategoria + "('Reformas');");
        db.execSQL(addCategoria + "('Eventos');");
        db.execSQL(addCategoria + "('Moda e Beleza');");
        db.execSQL(addCategoria + "('Domiciliares');");
    }

    public void criarSubCategoria(SQLiteDatabase db) {
        String addSubCategoria = "INSERT INTO subcategoria (nome, idcategoria) values";

        db.execSQL(addSubCategoria + "('Cuidadora de Idosos','1');");
        db.execSQL(addSubCategoria + "('Personal','1');");
        db.execSQL(addSubCategoria + "('Nutricionista','1');");
        db.execSQL(addSubCategoria + "('Massagista','1');");
        db.execSQL(addSubCategoria + "('Computadores','2');");
        db.execSQL(addSubCategoria + "('Eletrodomésticos','2');");
        db.execSQL(addSubCategoria + "('Celulares','2');");
        db.execSQL(addSubCategoria + "('Serviços Elétricos','3');");
        db.execSQL(addSubCategoria + "('Ajuste de Móveis','3');");
        db.execSQL(addSubCategoria + "('Marcenarias','3');");
        db.execSQL(addSubCategoria + "('Festas de Aniversário','4');");
        db.execSQL(addSubCategoria + "('Casamentos','4');");
        db.execSQL(addSubCategoria + "('Costura','5');");
        db.execSQL(addSubCategoria + "('Comida Caseira','6');");
        db.execSQL(addSubCategoria + "('Limpeza Doméstica','6');");
        db.execSQL(addSubCategoria + "('Cabelo','5');");
    }

    public void criarServico(SQLiteDatabase db){
        String addServico = " INSERT INTO servico (nome, texto, idusuario, idsubcategoria) values";

        db.execSQL(addServico + "('Formatar', 'teste', '1', '5');");
    }
}
