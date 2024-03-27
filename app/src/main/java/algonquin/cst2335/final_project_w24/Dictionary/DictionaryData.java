package algonquin.cst2335.final_project_w24.Dictionary;
public class DictionaryData {
    private Meaning[] meanings;
}

 class Meaning {
    private String partOfSpeech;
    private Definition[] definitions;
    private String[] synonyms;
    private String[] antonyms;
}

 class Definition {
    private String definition;
}