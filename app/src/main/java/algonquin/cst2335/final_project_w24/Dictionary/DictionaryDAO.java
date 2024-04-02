package algonquin.cst2335.final_project_w24.Dictionary;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;
/**
 * purpose of the file: DAO to take care of inserting, updating, creating and deleting.
 * Data Access Object, which is responsible for running the sql commands
 * author: Tony Nsang
 * lab section: 022
 * creation date: March 23, 2023.
 */
/**
 * DAO to take care of inserting, updating, creating and deleting.
 * Data Access Object, which is responsible for running the sql commands
 */
@Dao
public interface DictionaryDAO {
    @Insert
    public  void insertWord(DictionaryData w);
    @Query("Select * from DictionaryData")
    public List<DictionaryData> getAllWords();
    @Query("SELECT * FROM DictionaryData WHERE id = :id")
    DictionaryData getWordById(long id);

    @Query("SELECT * FROM DictionaryData WHERE searchTerm = :searchTerm")
   DictionaryData getWordBySearchTerm(String searchTerm);

    @Query("SELECT * FROM DictionaryData WHERE searchTerm = :searchTerm")
    List<DictionaryData> getAllWordsWithSearchTerm(String searchTerm);


    @Query("DELETE FROM DictionaryData WHERE searchTerm = :searchTerm AND definitionsOfTerm = :definition")
    void deleteDefinition(String searchTerm, String definition);

    @Delete
    void deleteWord(DictionaryData m);

}
