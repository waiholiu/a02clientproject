package bid.a02.a02clientproject.DataAccess;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class House {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "address")
    public String address;

    @ColumnInfo(name = "noOfRooms")
    public int noOfRooms;

    @ColumnInfo(name = "noOfBathrooms")
    public int noOfBathrooms;

    @ColumnInfo(name = "noOfCarParks")
    public int noOfCarParks;

    @ColumnInfo(name = "houseSize")
    public int houseSize;

    @ColumnInfo(name = "landSize")
    public int landSize;

    @ColumnInfo(name = "notes")
    public String notes;
}
