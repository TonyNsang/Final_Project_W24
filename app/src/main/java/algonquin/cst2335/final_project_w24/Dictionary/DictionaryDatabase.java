package algonquin.cst2335.final_project_w24.Dictionary;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
/**
 * purpose of the file: Extends Room. This Database class is meant for storing DictionaryData objects, and uses the DictionaryDAO class for querying data.
 * author: Tony Nsang
 * lab section: 022
 * creation date: March 23, 2023.
 */
/**
 * Extends Room. This Database class is meant for storing DictionaryData objects, and uses the DictionaryDAO class for querying data.
 */
@Database(entities = {DictionaryData.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class DictionaryDatabase extends RoomDatabase {
    public abstract DictionaryDAO stDAO();
}
