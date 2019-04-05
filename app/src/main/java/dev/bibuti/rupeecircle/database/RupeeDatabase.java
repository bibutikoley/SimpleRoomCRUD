package dev.bibuti.rupeecircle.database;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import dev.bibuti.rupeecircle.database.models.Users;

@Database(entities = {Users.class}, version = 1)
public abstract class RupeeDatabase extends RoomDatabase {

    private static volatile RupeeDatabase databaseInstance;

    static RupeeDatabase getInstance(Application application) {
        if (databaseInstance == null) {
            synchronized (RupeeDatabase.class) {
                if (databaseInstance == null) {
                    databaseInstance = Room.databaseBuilder(application.getApplicationContext(), RupeeDatabase.class, "rupee_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return databaseInstance;
    }

    public abstract UserDao userDao();

}
