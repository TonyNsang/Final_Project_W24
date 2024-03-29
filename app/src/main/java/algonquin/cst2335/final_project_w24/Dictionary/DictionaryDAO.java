package algonquin.cst2335.final_project_w24.Dictionary;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * DAO to take care of inserting, updating, creating and deleting.
 * Data Access Object, which is responsible for running the sql commands
 */
@Dao
public interface DictionaryDAO {
    @Insert
    public  long insertWord(DictionaryData w);
    @Query("Select * from DictionaryData")
    public List<DictionaryData> getAllWords();
    @Query("SELECT * FROM DictionaryData WHERE id = :id")
    DictionaryData getWordById(long id);

    @Delete
    void deleteWord(DictionaryData m);
}
