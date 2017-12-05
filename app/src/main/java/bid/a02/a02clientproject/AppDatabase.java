package bid.a02.a02clientproject;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {House.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HouseDao houseDao();
}
