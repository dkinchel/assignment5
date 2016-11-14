/**
 * C202 Assignment 5
 * TestBinarySearchTree.java
 * Purpose: This program creates a spell checking “dictionary” then uses it to 
 * spell check a book.     
 *  
 * @author Dewey Kincheloe
 * @version 1.0 11/11/2016
 */

import java.util.*;
import java.io.*;


public class TestBinarySearchTree {
    
    //Attributes
        //public int count = 0;
    //Constructors
    //Methods
    
    /**
    * The removeSpecials method receives a word from the oliver text file and
    * removes all special characters thus ensuring it is only composed of the
    * letters a-z. If it is a word composed entirely of special characters, it is
    * converted to an x so it will still count as a word to be checked.
    *
    */

    public String removeSpecials(String word)
    {
        String cleanWord = "";
        for (int i=0;i<word.length();i++)
        {
            //Ascci range for a-z 97-122
            if (word.charAt(i)>96&&word.charAt(i)<123)
            {
                cleanWord += word.charAt(i);
            }
        }
        if (cleanWord == "")
        cleanWord = "x";
        return cleanWord;
    }
    
  /**
   * The main method controls program execution.   
   *
   */
  public static void main(String[] args) {

      float wordsFound = 0;
      float wordsNotFound = 0;
      float compsFound = 0;
      float compsNotFound = 0;
      int[] count = new int[1];
      BinarySearchTree[] list = new BinarySearchTree[26];
      TestBinarySearchTree obj = new TestBinarySearchTree();
      BinarySearchTree<String> dictionary = new BinarySearchTree<>();
      
      for(int i = 0; i<list.length; i++)
      {
          list[i] = new BinarySearchTree<String>();
      }
      
      File f = new File ("random_dictionary.txt");
      String inputWord;
      try{
      Scanner in = new Scanner(f);
      while ( in.hasNext())
      {
        inputWord = in.next();
        inputWord = inputWord.toLowerCase();
        int i = (inputWord.charAt(0)-97);
        list[i].insert(inputWord);
      }
      System.out. println("Processing  dictionary complete");
      in.close();
      }
      catch(IOException e)
      {
        System.out.println("Unable to read file");
      }
      
      File book = new File ("oliver.txt");
      try{
      Scanner in = new Scanner(book);
      while ( in.hasNext())
      {
        inputWord = in.next();
        inputWord = inputWord.toLowerCase();
        inputWord = obj.removeSpecials(inputWord);
        int i = (inputWord.charAt(0)-97);
        count[0] = 0;
        if (list[i].search(inputWord, count))
            {
                wordsFound++;
                compsFound += count[0];
            }
        else
            {
                wordsNotFound++;
                compsNotFound += count[0];
            }
      }
      System.out. println("Processing Oliver complete");
      in.close();
    }
    catch(IOException e)
        {
            System.out.println("Unable to read file");
        }
    //System.out.println("count " + count);
    System.out.println("Words found = " + wordsFound);
    System.out.println("Words not found = " + wordsNotFound);
    System.out.println("Comparisons for words found = " + compsFound);
    System.out.println("Comparisons for words not found = " + compsNotFound);
    double avgcompswordsfound = (double) compsFound / wordsFound;
    System.out.println("avgcompswordsfound " + avgcompswordsfound);
    double avgcompswordsnotfound = (double) compsNotFound / wordsNotFound;
    System.out.println("avgcompswordsnotfound " + avgcompswordsnotfound);
  }
}