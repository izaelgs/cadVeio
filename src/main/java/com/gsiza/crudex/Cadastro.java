package com.gsiza.crudex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Cadastro extends AppCompatActivity {

    private EditText nome;
    private EditText sobrenome;
    private EditText idade;
    private EditText telefone;
    private ListView lista;
    private Button limpar;
    private Button salvar;
    private Button excluir;
    private TextView identificador;

    DataBase db = new DataBase(this);

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad);

        nome = findViewById(R.id.edtNome);
        sobrenome = findViewById(R.id.edtSNome);
        idade = findViewById(R.id.edtIdade);
        telefone = findViewById(R.id.edtPhone);

        limpar = findViewById(R.id.btnLimpar);
        salvar = findViewById(R.id.btnCad);
        excluir = findViewById(R.id.btnDel);

        identificador = findViewById(R.id.identificador);

        lista = (ListView)findViewById(R.id.lista);

        listarVeios();

        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                limpaCampos();
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String content = (String) lista.getItemAtPosition(i);

                String id = content.substring(0, content.indexOf("-"));

                Veio veio = db.selectVeio(Integer.parseInt(id));

                identificador.setText(String.valueOf(veio.getId()));
                nome.setText(veio.getNome());
                sobrenome.setText(veio.getSobrenome());
                idade.setText(String.valueOf(veio.getIdade()) );
                telefone.setText(veio.getTelefone());
            }
        });

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ident = identificador.getText().toString();
                String nomes = nome.getText().toString();
                String sobrenomes = sobrenome.getText().toString();
                String idades = idade.getText().toString();
                String telefones = telefone.getText().toString();

                if(nomes.isEmpty()){
                        nome.setError("Preenche aqui mano!!!");
                } else if(ident.isEmpty()){
                    Veio novo = new Veio();
                    novo.setIdade(Integer.parseInt(idades));
                    novo.setTelefone(telefones);
                    novo.setNome(nomes);
                    novo.setSobrenome(sobrenomes);

                    db.cadVeio(novo);

                    Toast.makeText(Cadastro.this, "Veio cadastrado com sucesso", Toast.LENGTH_LONG).show();

                    Log.d("Cliente Selecionado", "Id: " + novo.getId() + "; Nome: " + novo.getNome() +
                            "; Sobrenome: " +novo.getSobrenome() + "; Idade: " + novo.getIdade() + "; Telefone: " + novo.getTelefone());

                    limpaCampos();
                    listarVeios();
                } else{
                    db.atualizaVeio(new Veio(Integer.parseInt(ident), Integer.parseInt(idades),
                            telefones, nomes, sobrenomes));

                    Toast.makeText(Cadastro.this, "Veio atualizado com sucesso", Toast.LENGTH_LONG).show();

                    limpaCampos();
                    listarVeios();
                }
            }
        });

        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ident = identificador.getText().toString();

                if(ident.isEmpty()){
                    Toast.makeText(Cadastro.this, "Náo é possível excluir o vento, otario",
                            Toast.LENGTH_LONG).show();
                } else {
                    Veio v = new Veio();
                    v.setId(Integer.parseInt(ident));
                    db.deleteVeio(v);

                    Toast.makeText(Cadastro.this, "Veio sabugado com sucesso", Toast.LENGTH_LONG).show();

                    limpaCampos();
                    listarVeios();
                }
            }
        });
    }

    public void listarVeios(){

        List<Veio> veios = db.listaTodosVeios();

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(Cadastro.this, android.R.layout.simple_list_item_1, arrayList);

        lista.setAdapter(adapter);

        for(Veio v : veios){
            arrayList.add(v.getId()+ "-"+v.getNome());
            adapter.notifyDataSetChanged();
        }
    }

    void limpaCampos(){
        identificador.setText("");
        nome.setText("");
        sobrenome.setText("");
        idade.setText("");
        telefone.setText("");

        nome.requestFocus();
    }
}