package br.com.digitalhouse.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.digitalhouse.R;
import br.com.digitalhouse.interfaces.OnClickRecyclerView;
import br.com.digitalhouse.model.Agenda;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Agenda> agendaList;
    private OnClickRecyclerView listener;

    public RecyclerViewAdapter(List<Agenda> agendaList, OnClickRecyclerView listener) {
        this.agendaList = agendaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Agenda agenda =agendaList.get(i);
        viewHolder.bind(agenda);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(agenda);
            }
        });
    }

    @Override
    public int getItemCount() {
        return agendaList.size();
    }

    public void atualizaListaRecyclerView(List<Agenda> novaLista) {
        this.agendaList.clear();
        this.agendaList = novaLista;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nome;
        private TextView telefone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.nome);
            telefone = itemView.findViewById(R.id.telefone);
        }

        public void bind(Agenda agenda) {
            nome.setText(agenda.getNomeContato());
            telefone.setText(agenda.getTelefone());
        }
    }
}
