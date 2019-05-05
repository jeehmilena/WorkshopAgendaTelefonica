package br.com.digitalhouse.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "agendaDeContato")
public class Agenda {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idContato")
    private long id;

    @ColumnInfo(name = "contato")
    private String nomeContato;

    @ColumnInfo(name = "numeroTelefone")
    private String telefone;

    public Agenda() {
    }

    public Agenda(long id, String nomeContato, String telefone) {
        this.id = id;
        this.nomeContato = nomeContato;
        this.telefone = telefone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
