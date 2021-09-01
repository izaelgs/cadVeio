package com.gsiza.crudex;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 1;

    private static final String BANCO_VEIO = "bd_veios";

    //TABELA DOS VELHO
    private static final String TABELA_VEIO = "tb_veios";

    private static final String COLUNA_ID = "id_veio";
    private static final String COLUNA_IDADE = "idade";
    private static final String COLUNA_TELEFONE = "telefone";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_SOBRENOME = "sobrenome";

    //TABELA SEMANA
    private static final String TABELA_SEMANA = "tb_semana";

    private static final String COLUNA_ID_SEMANA = "id_semana";
    private static final String COLUNA_ID_VEIO = "id_veio";
    private static final String COLUNA_ID_SABADO = "id_sab";
    private static final String COLUNA_ID_SEGUNDA = "id_seg";
    private static final String COLUNA_ID_TERCA = "id_ter";
    private static final String COLUNA_ID_QUARTA = "id_qua";
    private static final String COLUNA_ID_QUINTA = "id_qui";
    private static final String COLUNA_ID_SEXTA = "id_sex";
    private static final String COLUNA_ID_DOMINGO = "id_dom";


    public DataBase(@Nullable Context context) {
        super(context, BANCO_VEIO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String QUERY_VEIO = "CREATE TABLE " + TABELA_VEIO + "(" +
                COLUNA_ID + " INTEGER PRIMARY KEY," + COLUNA_IDADE +
                " INTEGER," + COLUNA_TELEFONE + " VARCHAR(15)," +
                COLUNA_NOME + " VARCHAR(50), " + COLUNA_SOBRENOME + " VARCHAR(50))";

        String QUERY_SEMANA = "CREATE TABLE " + TABELA_SEMANA + "(" +
                COLUNA_ID_SEMANA + " INTEGER PRIMARY KEY," + COLUNA_ID_VEIO + " INTEGER," +
                COLUNA_ID_SABADO + " INT," + COLUNA_ID_SEGUNDA + " INTEGER, "+ COLUNA_ID_TERCA + " INTEGER, "+
                COLUNA_ID_QUARTA + " INTEGER, "+ COLUNA_ID_QUINTA + " INTEGER, "+ COLUNA_ID_SEXTA + " INTEGER, "+
                COLUNA_ID_DOMINGO + " INTEGER)";

        db.execSQL(QUERY_VEIO);
        db.execSQL(QUERY_SEMANA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    /* CRUD VEIO*/

    void cadVeio(Veio veio){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_IDADE, veio.getIdade());
        values.put(COLUNA_TELEFONE, veio.getTelefone());
        values.put(COLUNA_NOME, veio.getNome());
        values.put(COLUNA_SOBRENOME, veio.getSobrenome());

        db.insert(TABELA_VEIO, null, values);
        db.close();
    }

    void deleteVeio(Veio veio){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_VEIO, COLUNA_ID + " = ?", new String[]{String.valueOf(veio.getId())});

        db.close();
    }

    Veio selectVeio(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_VEIO, new String[]{
                COLUNA_ID, COLUNA_IDADE, COLUNA_TELEFONE, COLUNA_NOME, COLUNA_SOBRENOME},
                COLUNA_ID + " = ?",
                new String[]{ String.valueOf(id)},
                null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Veio veio1 = new Veio(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)),
                cursor.getString(2), cursor.getString(3), cursor.getString(4));

        return veio1;
    }

    @SuppressLint("Range")
    Veio verificaVeio(Veio veio){

        SQLiteDatabase db = getWritableDatabase();
        String queryTodosUsers = "SELECT * FROM " + TABELA_VEIO + " WHERE " + TABELA_VEIO +
                "." + COLUNA_NOME + " = " + "'" + veio.nome + "'";

        Cursor c = db.rawQuery(queryTodosUsers, null);

        while (c.moveToNext()){
            if (veio.getNome().equals(c.getString(c.getColumnIndex("nome")))){
                if (veio.getSobrenome().equals(c.getString(c.getColumnIndex("sobrenome")))){

                    Veio veio1 = new Veio(Integer.parseInt(c.getString(0)), Integer.parseInt(c.getString(1)),
                            c.getString(2), c.getString(3), c.getString(4));

                    return veio1;
                }
            }
        }

        return (null);
    }

    void atualizaVeio(Veio veio){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_IDADE, veio.getIdade());
        values.put(COLUNA_TELEFONE, veio.getTelefone());
        values.put(COLUNA_NOME, veio.getNome());
        values.put(COLUNA_SOBRENOME, veio.getSobrenome());

        db.update(TABELA_VEIO, values, COLUNA_ID + " = ?", new String[]{
                String.valueOf(veio.getId())
        });
    }

    public List<Veio> listaTodosVeios(){
        List<Veio> listaVeios = new ArrayList<Veio>();

        String query = "SELECT * FROM " + TABELA_VEIO;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()){
            do{
                Veio veio = new Veio();
                veio.setId(Integer.parseInt(c.getString(0)));
                veio.setIdade(Integer.parseInt(c.getString(1)));
                veio.setTelefone(c.getString(2));
                veio.setNome(c.getString(3));
                veio.setSobrenome(c.getString(4));

                listaVeios.add(veio);
            } while (c.moveToNext());
        }

        return listaVeios;
    }
}

    /* CRUD SEMANA*/


