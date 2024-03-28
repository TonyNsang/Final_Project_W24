package algonquin.cst2335.final_project_w24.Dictionary;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class DetailedSearchTerm implements Serializable {
    /**
     * search Term
     */
    @ColumnInfo(name = "searchTerm")
    private String searchTerm;
    /**
     * definitions of the term
     */
    @ColumnInfo(name = "definitionsOfTerm")
    private ArrayList<String> definitionsOfTerm;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    public int id;


    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public ArrayList<String> getDefinitionsOfTerm() {
        return definitionsOfTerm;
    }

    public void setDefinitionsOfTerm(ArrayList<String> definitionsOfTerm) {
        this.definitionsOfTerm = definitionsOfTerm;
    }
}
