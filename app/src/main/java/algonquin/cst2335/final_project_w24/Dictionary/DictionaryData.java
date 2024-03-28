package algonquin.cst2335.final_project_w24.Dictionary;

import java.util.ArrayList;

public class DictionaryData {
   /**
    * search Term
    */
   private String searchTerm;
   /**
    * definitions of the term
    */
   private ArrayList<String> definitionsOfTerm;


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