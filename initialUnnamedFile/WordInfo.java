package initialUnnamedFile;

public class WordInfo {
    private String word;
    private String meaning;

    public WordInfo(String word, String meaning) {
        this.word = word.toLowerCase();
        this.meaning = meaning;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word.toLowerCase();
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}