package br.com.digitalhouse.view;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.R;
import br.com.digitalhouse.adapter.RecyclerViewAdapter;
import br.com.digitalhouse.data.AgendaBancoDados;
import br.com.digitalhouse.data.AgendaDAO;
import br.com.digitalhouse.interfaces.OnClickRecyclerView;
import br.com.digitalhouse.model.Agenda;

public class HomeActivity extends AppCompatActivity implements OnClickRecyclerView {
    private TextInputLayout contato;
    private TextInputLayout telefone;
    private FloatingActionButton btnAdd;
    private FloatingActionButton btnDelete;
    private RecyclerView recyclerView;
    private List<Agenda> listaAgenda = new ArrayList<>();
    private AgendaDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews(listaAgenda);

        //VAMOS COLOCAR AS AÇÕES DOS BOTÕES


//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addContato();
//            }
//        });

//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                removerContato();
//            }
//        });

    }

    private void initViews(List<Agenda> listaAgenda) {
        contato = findViewById(R.id.textInputLayoutNome);
        telefone = findViewById(R.id.textInputLayoutTelefone);
        btnAdd = findViewById(R.id.floatingActionButtonAdd);
        btnDelete = findViewById(R.id.floatingActionButtonRemove);
        recyclerView = findViewById(R.id.recyclerViewContatos);

        AgendaBancoDados bancoDados = AgendaBancoDados.getDatabase(this);
        dao = bancoDados.agendaDAO();

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(listaAgenda, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        retornaTodosOsDadosDoBanco(adapter);
        ViewCompat.setNestedScrollingEnabled(recyclerView, false);
    }

    private void addContato() {
        final String nomeContato = contato.getEditText().getText().toString();
        final String telefoneContato = telefone.getEditText().getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {

                Agenda agenda = dao.getNomeContato(nomeContato);

                if (agenda != null) {
                    agenda.setNomeContato(nomeContato);
                    agenda.setTelefone(telefoneContato);
                    dao.atualizarAgenda(agenda);

                } else {

                    agenda = new Agenda();
                    agenda.setNomeContato(nomeContato);
                    agenda.setTelefone(telefoneContato);
                    dao.inserirAgenda(agenda);
                }

            }
        }).start();

        contato.getEditText().setText("");
        telefone.getEditText().setText("");
    }

    private void removerContato() {

        final String nomeContato = contato.getEditText().getText().toString();
        final String telefoneContato = telefone.getEditText().getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Agenda agenda = dao.getNomeContato(nomeContato);
                if (agenda != null) {
                    dao.deletarContato(agenda);
                } else {
                    System.out.println("Não encontrado!");
                }
            }
        }).start();

        contato.getEditText().setText("");
        telefone.getEditText().setText("");

    }

    private void retornaTodosOsDadosDoBanco(final RecyclerViewAdapter adapter) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.todosContatos().observe(HomeActivity.this, new Observer<List<Agenda>>() {
                    @Override
                    public void onChanged(@Nullable List<Agenda> agenda) {
                        adapter.atualizaListaRecyclerView(agenda);
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(Agenda agenda) {
        contato.getEditText().setText(agenda.getNomeContato());
        telefone.getEditText().setText(agenda.getTelefone());
    }
}
