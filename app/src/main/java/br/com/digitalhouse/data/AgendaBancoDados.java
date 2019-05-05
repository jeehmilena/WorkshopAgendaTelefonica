package br.com.digitalhouse.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import br.com.digitalhouse.model.Agenda;

@Database(entities = {Agenda.class}, version = 1, exportSchema = false)
public abstract class AgendaBancoDados extends RoomDatabase {

    public abstract AgendaDAO agendaDAO();

    private static volatile AgendaBancoDados INSTANCE;

    public static AgendaBancoDados getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AgendaBancoDados.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AgendaBancoDados.class, "agenda_sqlite_room_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
