package bid.a02.a02clientproject;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface HouseDao {
    @Query("SELECT * FROM House")
    List<House> getAll();

    @Insert
    void insert(House house);

    @Delete
    void delete(House house);
}
