package algonquin.cst2335.final_project_w24.Dictionary;

import androidx.room.Database;

@Database(entities = {DetailedSearchTerm.class}, version=3)
public abstract class DictionaryDatabase {
    public abstract DetailedSearchTermDAO stDAO();
}
