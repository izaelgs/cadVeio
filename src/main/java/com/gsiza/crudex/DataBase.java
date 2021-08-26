package com.gsiza.crudex;

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

    private static final String TABELA_VEIO = "tb_veios";

    private static final String COLUNA_ID = "id_veio";
    private static final String COLUNA_IDADE = "idade";
    private static final String COLUNA_TELEFONE = "telefone";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_SOBRENOME = "sobrenome";

    public DataBase(@Nullable Context context) {
        super(context, BANCO_VEIO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String QUERY_COLUNA = "CREATE TABLE " + TABELA_VEIO + "(" +
                COLUNA_ID + " INTEGER PRIMARY KEY," + COLUNA_IDADE +
                " INTEGER," + COLUNA_TELEFONE + " VARCHAR(15)," +
                COLUNA_NOME + " VARCHAR(50), " + COLUNA_SOBRENOME + " VARCHAR(50))";

        db.execSQL(QUERY_COLUNA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    /* CRUD ABAIXO*/

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
