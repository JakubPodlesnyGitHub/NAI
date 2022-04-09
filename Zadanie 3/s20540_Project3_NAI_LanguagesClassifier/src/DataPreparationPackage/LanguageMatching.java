package DataPreparationPackage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanguageMatching {
    private Map<String, int[]> languageAnsw;

    public LanguageMatching(List<String> languages) {
        languageAnsw = new HashMap<>();
        putMap(languages);
    }

    private void putMap(List<String> languages) {
        for (int i = 0; i < languages.size(); i++) {
            int[] tabTmp = new int[languages.size()];
            tabTmp[i] = 1;
            languageAnsw.put(languages.get(i), tabTmp);
        }
    }

///////////////
    public int[] getVectorLanguage(String langString) {
        return languageAnsw.get(langString);
    }

    public String getNameOfLanguage(int[] languageVector) {
        for (String lang : languageAnsw.keySet()) {
            int[] tab = languageAnsw.get(lang);
            if (checkAnswers(languageVector, tab)) {
                return lang;
            }
        }
        return "";
    }

    private boolean checkAnswers(int[] corTab, int[] comTab) {
        for (int i = 0; i < corTab.length; i++) {
            if (corTab[i] != comTab[i]) {
                return false;
            }
        }
        return true;
    }
////////////////

    private String printTab() {
        StringBuilder allLang = new StringBuilder();
        for (String lang : languageAnsw.keySet()) {
            StringBuilder keyLang = new StringBuilder("\nJezyk: " + lang + " : ");
            int[] tab = languageAnsw.get(lang);
            for (int i = 0; i < tab.length; i++) {
                keyLang.append(tab[i]).append("  ");
            }
            allLang.append(keyLang);
        }
        return allLang.toString();
    }

    @Override
    public String toString() {
        return "Niepowtarzajace jezyki: " + printTab();
    }
}
