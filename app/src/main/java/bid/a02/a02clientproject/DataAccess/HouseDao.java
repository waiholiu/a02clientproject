package bid.a02.a02clientproject.DataAccess;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface HouseDao {
    @Query("SELECT * FROM House")
    List<House> getAll();

    @Query("Select * From House where id = :id")
    House getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(House house);

    @Delete
    void delete(House house);
}
