
/**
 * Décrivez votre classe WordLength ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */

import edu.duke.*;
import java.io.*;

public class WordLength {

    public void countWordLengths(FileResource resource, int[] counts){
        String[] message = resource.asString().toLowerCase().split("\\s+");
        int len;
        for(String word: message){
            len =0;
            for(int i =0; i< word.length();i++){
                if(Character.isLetter(word.charAt(i)))
                    len +=1;
            }
            
            if(len > counts.length)
                len = counts.length - 1;
            counts[len] += 1;
        }
        
    for(int i = 0; i < counts.length; i++)
        System.out.println(""+counts[i]);
        
      System.out.println("Max occurrence at length: "+indexOfMax(counts));
}

 public void testCountWordLengths(){
     FileResource fr = new FileResource();
        String message = fr.asString();
        countWordLengths(fr, new int[31]); 
    }
    
    public int indexOfMax(int[] value){
     int max =-1;
     int memo =-1;
     for(int i=0; i <value.length;i++){
         if(value[i] > max){
             max =value[i];
             memo = i;
        }
    }
     return memo ;
    }
    

}
