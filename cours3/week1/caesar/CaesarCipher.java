
/**
 * Décrivez votre classe CaesarCipher ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
import edu.duke.*;
import java.io.*;

public class CaesarCipher {
    
     private void print(String  s){
        System.out.println(s);   
    }
    
    public void testEncryptTwoKeys(){
        print(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
        
    }
    
    
    private String encryptTwoKeys(String input, int key1, int key2){
        String ans = input;
        ans = encrypt(ans,key1,0,2);
        ans = encrypt(ans,key2,1,2);
        
        return ans; 
    }
   
    private String encrypt(String input, int key,int start, int incr){
        StringBuilder ans = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char currentChar ;
        int idx;
        char codedChar;
        String shiftedAlphabet = alphabet.substring(key)+ alphabet.substring(0, key);
        for(int i = start; i<ans.length(); i = i + incr){
            currentChar = ans.charAt(i);
            idx = alphabet.indexOf(Character.toUpperCase(currentChar));
            if(idx !=-1){
                codedChar = shiftedAlphabet.charAt(idx);
                if(Character.isUpperCase(input.charAt(i))){
                    ans.setCharAt(i,(Character.toUpperCase(codedChar)));
                    
                }else{
                    
                    ans.setCharAt(i,(Character.toLowerCase(codedChar)));
                }
                
                
            }

        }

        return ans.toString(); 
    }
    

    private String encrypt(String input, int key){
        return encrypt(input, key, 0,1);/*
        StringBuilder ans = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char currentChar ;
        int idx;
        char codedChar;
        String shiftedAlphabet = alphabet.substring(key)+ alphabet.substring(0, key);
        for(int i =0; i<ans.length(); i++){
            currentChar = ans.charAt(i);
            idx = alphabet.indexOf(Character.toUpperCase(currentChar));
            if(idx !=-1){
                codedChar = shiftedAlphabet.charAt(idx);
                if(Character.isUpperCase(input.charAt(i))){
                    ans.setCharAt(i,(Character.toUpperCase(codedChar)));
                    
                }else{
                    
                    ans.setCharAt(i,(Character.toLowerCase(codedChar)));
                }
                
                
            }

        }

        return ans.toString(); */
    }

    public void  testEncrypt(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 15;
        String encrypted = encrypt(message, key);
        print("key is " + key + "\n" + encrypted);
    }
}
