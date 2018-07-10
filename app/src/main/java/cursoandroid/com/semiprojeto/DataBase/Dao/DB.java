package cursoandroid.com.semiprojeto.DataBase.Dao;

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
        db.execSQL("create table servico (id integer primary key autoincrement, nome text not null, texto text not null, idusuario integer, idsubcategoria)");
        db.execSQL("create table categoria (id integer primary key autoincrement, nome text not null)");
        db.execSQL("create table subcategoria (id integer primary key autoincrement, nome text not null, idcategoria integer)");
        db.execSQL("create table pessoa (id integer primary key autoincrement, nome text not null, idusuario integer)");
        criarCategoria(db);
        criarSubCategoria(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table usuario;");
    }

    public void criarCategoria(SQLiteDatabase bd){
        String addCategoria = "INSERT INTO categoria (nome) values";

        bd.execSQL(addCategoria + "('Saude')");
        bd.execSQL(addCategoria + "('Tecnologia');");
        bd.execSQL(addCategoria + "('Reformas')");
        bd.execSQL(addCategoria + "('Eventos')");
        bd.execSQL(addCategoria + "('Moda e Beleza')");
        bd.execSQL(addCategoria + "('Domiciliares')");
    }

    public void criarSubCategoria(SQLiteDatabase bd) {
        String addSubCategoria = "INSERT INTO subcategoria (nome, idCategoria) values";

        bd.execSQL(addSubCategoria + "('Cuidadora de Idosos','1');");
        bd.execSQL(addSubCategoria + "('Personal','1');");
        bd.execSQL(addSubCategoria + "('Nutricionista','1');");
        bd.execSQL(addSubCategoria + "('Massagista','1');");
        bd.execSQL(addSubCategoria + "('Computadores','2');");
        bd.execSQL(addSubCategoria + "('Eletrodomésticos','2');");
        bd.execSQL(addSubCategoria + "('Celulares','2');");
        bd.execSQL(addSubCategoria + "('Serviços Elétricos','3');");
        bd.execSQL(addSubCategoria + "('Ajuste de Móveis','3');");
        bd.execSQL(addSubCategoria + "('Marcenarias','3');");
        bd.execSQL(addSubCategoria + "('Festas de Aniversário','4');");
        bd.execSQL(addSubCategoria + "('Casamentos','4');");
        bd.execSQL(addSubCategoria + "('Costura','5');");
        bd.execSQL(addSubCategoria + "('Comida Caseira','6');");
        bd.execSQL(addSubCategoria + "('Limpeza Doméstica','6');");
        bd.execSQL(addSubCategoria + "('Cabelo','5');");
    }
}
