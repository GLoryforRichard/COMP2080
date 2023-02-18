import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dictionary {
    private static final int MAX_SIZE = 1500;
    private WordInfo[] wordList;
    private int count;

    public Dictionary() {
        this.wordList = new WordInfo[MAX_SIZE];
        this.count = 0;
//        System.out.println("Class start");
        loadWordsFromFile("wordList.txt");
    }

    private void loadWordsFromFile(String fileName) {
        try {
            File file = new File(fileName);
            String absolutePath = file.getAbsolutePath();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().toLowerCase();
                WordInfo wordInfo = new WordInfo(word, "Undefined word");
                wordList[count] = wordInfo;
                count++;
            }
            quickSort(0,count-1);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.out.println(e);
        }

    }
    // Quick sort function requirements
    private void quickSort(int left, int right) {
        if (left >= right) {
            return;
        }
        int pivotIndex = partition(left, right);
        quickSort(left, pivotIndex - 1);
        quickSort(pivotIndex + 1, right);
    }

    private int partition(int left, int right) {
        WordInfo pivot = wordList[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (wordList[j].getWord().compareTo(pivot.getWord()) < 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, right);
        return i + 1;
    }

    private void swap(int i, int j) {
        WordInfo temp = wordList[i];
        wordList[i] = wordList[j];
        wordList[j] = temp;
    }

    // binarySearch get ready
    private int binarySearch(String word) {
        int low = 0;
        int high = count - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int compare = wordList[mid].getWord().compareTo(word);
            if (compare < 0) {
                low = mid + 1;
            } else if (compare > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public boolean add(String word, String meaning) {
        if (exists(word)) {
            return false;
        }

        if (count >= MAX_SIZE) {
            return false;
        }

        WordInfo newWord = new WordInfo(word, meaning);
        wordList[count] = newWord;
        count++;
        quickSort(0,count-1);
        return true;
    }

    public boolean delete(String word) {
        int index = binarySearch(word);
        if (index < 0) {
            return false;
        }
        wordList[index] = null;
        for (int i = index; i < count - 1; i++) {
            wordList[i] = wordList[i+1];
        }
        wordList[count-1] = null;
        count--;
        return true;
    }

    public boolean exists(String word) {
        int index = binarySearch(word);
        return (index >= 0);
    }


    public String getMeaning(String word) {
        int index = binarySearch(word);
        if (index >= 0) {
            return wordList[index].getMeaning();
        } else {
            return null;
        }
    }

    public int getCount() {
        return count;
    }

    public String printWordList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(wordList[i].getWord()).append("\n");
        }
        return sb.toString();
    }

    public void printDictionary() {
        for (int i = 0; i < count; i++) {
            System.out.println(wordList[i].getWord() + " : " + wordList[i].getMeaning());
        }
    }
}