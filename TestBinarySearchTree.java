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

      long wordsFound = 0;
      long wordsNotFound = 0;
      long compsFound = 0;
      long compsNotFound = 0;
      int count = 0;
      TestBinarySearchTree obj = new TestBinarySearchTree();
      /*
      String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", 
                             "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", 
                             "u", "v", "w", "x" ,"y", "z"};
      */
      BinarySearchTree<String> dictionary = new BinarySearchTree<>();
      
      File f = new File ("random_dictionary.txt");
      String inputWord;
      try{
      Scanner in = new Scanner(f);
      while ( in.hasNext())
      {
        inputWord = in.next();
        inputWord = inputWord.toLowerCase();
        dictionary.insert(inputWord);
        
        
      }
      System.out. println("Processing complete");
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
        count ++;
        if (dictionary.search(inputWord))
            {
                wordsFound++;
                compsFound += count;
            }
        else
            {
                wordsNotFound++;
                compsNotFound += count;
            }
      }
      System.out. println("Processing complete");
      in.close();
    }
    catch(IOException e)
        {
            System.out.println("Unable to read file");
        }
    System.out.println("wordsfound " + wordsFound);
    System.out.println("wordsnotfound " + wordsNotFound);
    System.out.println("compsfound " + compsFound);
    System.out.println("compsnotfound " + compsNotFound);
    double avgcompswordsfound = (double) compsFound / wordsFound;
    System.out.println("avgcompswordsfound " + avgcompswordsfound);
    double avgcompswordsnotfound = (double) compsNotFound / wordsNotFound;
    System.out.println("avgcompswordsnotfound " + avgcompswordsnotfound);
  }
}