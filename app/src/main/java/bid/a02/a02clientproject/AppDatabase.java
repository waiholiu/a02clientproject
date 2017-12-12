package bid.a02.a02clientproject;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {House.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HouseDao houseDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "myfirst-db2")
                            .build();
        }
        return INSTANCE;
    }
}
