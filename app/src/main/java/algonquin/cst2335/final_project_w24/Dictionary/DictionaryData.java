package algonquin.cst2335.final_project_w24.Dictionary;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
/**
 * purpose of the file: Object class to model the words and its definitions
 * Represents a Table in the Database to store the words and its definitions
 * author: Tony Nsang
 * lab section: 022
 * creation date: March 23, 2023.
 */
/**
 * Object class to model the words and its definitions
 * Represents a Table in the Database to store the words and its definitions
 */
@Entity
public class DictionaryData {
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