package DataPreparationPackage;

import java.util.List;

public class Data {
    private List<Character> letters;
    private String language;

    public Data(List<Character> letters, String language) {
        this.letters = letters;
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return "Dany text: " + letters + " Jezyk( z pliku ): " + language;
    }
}
