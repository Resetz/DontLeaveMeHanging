import java.util.*;
import java.io.*;
public class WordSet {
  private static ArrayList<HashSet<String>> allWords;
  
  static {
    File f;
    Scanner scanner;
    allWords = new ArrayList<HashSet<String>>();

    try {
        f = new File("./words_alpha.txt");
        scanner = new Scanner(f);

        for (int i = 0; i < 100; i++) {
            allWords.add(new HashSet<String>());
        }

        while (scanner.hasNextLine()) {
            String word = scanner.nextLine();
            allWords.get(word.length()).add(word);
        }

        for (int i = 0; i < 100; i++) {
            System.out.println("words of length " + i + ": " + allWords.get(i).size());
        }
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("There was an error.");
    }
  }

  public HashSet<String> words;
  public int length;
  public ArrayList<String> successfulGuesses = new ArrayList<String>();
  public ArrayList<String> unsuccessfulGuesses = new ArrayList<String>();
  public ArrayList<String> possibleGuesses = new ArrayList<String>();
  public ArrayList<String> guess = new ArrayList<String>();
  
  public WordSet(int length) {
    this.length = length;
    this.words = new HashSet<String>();
    Iterator<String> it = allWords.get(length).iterator();
    while (it.hasNext()) {
        words.add(it.next());
    }
    String alp = "abcdefghijklmnopqrstuvwxyz";
    for (int i = 0; i < alp.length(); i++) {
      possibleGuesses.add(i, alp.substring(i, i+1));
    }
    for (int i = 0; i < length; i++) {
      guess.add("_");
    }
    

  }

  public int[] filter(String s) {
    // Filter the set by the character "s"
    // HashSet.remove
    // Returns the ID taken by the filter
    Iterator<String> it = words.iterator();
    ArrayList<Integer> ID = new ArrayList<Integer>();
    for (int i = 0; i < Math.pow(2,this.length); i++) {
        ID.add(0);
    }

    while (it.hasNext()) {
        int a = id(it.next(), s);
        ID.set(a, ID.get(a) + 1);
    }


    int highestID = 0;
    for (int i = 0; i < ID.size(); i++) {
        if (ID.get(i) > ID.get(highestID)) {
            highestID = i;
        }
    }
    System.out.println(words.size());
    keepID(highestID, s);
    System.out.println(words.size());

    int[] ret = new int[this.length];
    for (int i = 0; i < length; i++) {
        ret[length - 1 - i] = (highestID % 2);
        highestID /= 2;
    }

    return ret;
  }

  public void keepID(int id, String target) {
    ArrayList<String> toRemove = new ArrayList<String>();
    //Iterator<String> it = words.iterator();
    for (String s : words) {
        if (id != id(s, target)) {
            toRemove.add(s);
        }
    }
    for (String s : toRemove) {
        this.words.remove(s);
    }
  }

  public static int id(String given, String target) {
    // returns the id of the string "s" if the target is "target"
    int ret = 0;
    for (int i = 0; i < given.length(); i++) {
        ret *= 2;
        if (given.substring(i, i + 1).equals(target)) {
            ret++;
        }
    }
    return ret;
  }

  public void submitGuess(String letter) {
    int[] aID = filter(letter);
    boolean foundLetter = false;
    //can combine for loops if wanted
    for (int i = 0 ; i < aID.length ; i++) {
      if (aID[i] == 1) {
        guess.set(i, letter);
        foundLetter = true;
      }
    }
    if (foundLetter) {
      successfulGuesses.add(letter);
    } else {
      unsuccessfulGuesses.add(letter);
    }

    int ind = 0;
    boolean b = false;
    for (int i = 0; i < possibleGuesses.size(); i++)
    {
       if (possibleGuesses.get(i).equals(letter)) {
         ind = i;
         b = true;
       }
    }
    if (b) {
      possibleGuesses.remove(ind);
    }
  }

  public boolean isComplete() {
    int count = 0;
    for (int i = 0 ; i < guess.size() ; i++) {
      if (guess.get(i) != "_") {
        count++;
      }
    }
    if (count == length) {
      return true;
    }
    return false;
  }
}