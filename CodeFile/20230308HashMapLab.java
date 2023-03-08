package CodeFile;

public class LinerHashTable {
    private String[] hashData;
    private int numElements;
    private double loadFactor;
    private int maxSize;

    public LinerhashTable(double lf, int max){
        numElements = 0;
        maxSize = max;
        loadFactor = lf;
        hashData = new String[max];
    }

    private int hashFunction(String key){
        int value = 0;
        for(int i = 0; i < key.l)
        return value%maxSize;
    }

    public boolean insert(String word){
        if (numElements * 1.0/maxSize<loadFactor>){
            int loc = hashFunction(word);
            int startloc = loc;
            int collisions = 0;
            while (hashData[loc] != null && !hashData[loc].equals("-DEL-")){
                collisions++;
                loc = (startloc + collisions*collisions)%maxSize;
            }
            hashData[loc] = word;
            numElements++;
            return true;
        }
        return false;
    }

    public boolean search (String word){
        int loc = hashFunction(word);
        while (hashData[loc] != null && !hashData[loc].equals(word)){
            loc = (loc+1)%maxSize;
        }
        if (hashData[loc] == null) return false;
        return true;
    }

    public boolean delete (String word){
        int loc = hashFunction(word);
        while (hashData[loc] != null && !hashData[loc].equals(word)){
            loc = (loc+1)%maxSize;
        }
        if (hashData[loc] == null) return false;
        hashData[loc] = "-DEL-";
        return true;
    }

    public void printTable(){
        for (int x = maxSize-1;x>=0;x--){
            if (hashData[x] == null){
                System.out.println(x + " --> -E-");
            }else{
                System.out.println(x + " --> " + hashData[x]);
            }
        }
    }
}

public static void main() {
    LinerHashTable lht =  new LinerHashTable(0.85,13);
    lht.insert("apple");
    lht.insert("pear");
    lht.insert("dragonfrult");
    lht.insert("blueberry");
    lht.insert("banana");
    lht.insert("coconut");
    lht.insert("mongo");
    lht.printTable();
}