
/**
 * Décrivez votre classe CaesarBreaker ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */

import edu.duke.*;
import java.io.*;


public class CaesarBreaker {
    private void print(String s){

        System.out.println(s);   
    }

    private String decryptTwoKey(String encrypted,int dec1, int dec2){
        String[] split = Utils.splitEvenString(encrypted); 
        print("");
        print("key1");
        split[0]= decrypt(split[0],dec1);
        print("");
        print("key2");
        split[1]= decrypt(split[1],dec2);
        return Utils.mergeEvenString(split); 
    }
    
    public void decryptTwoKeyFromKeys(String encrypted, int key1, int key2){
        CaesarCipher cc = new CaesarCipher();
print(        cc.encryptTwoKeys(encrypted,26-key1,26-key2));
        
    }

    private String decryptTwoKeyAA(String encrypted){
        return decryptTwoKey(encrypted,0,0);
    }

    private String decryptTwoKeyAE(String encrypted){
        return decryptTwoKey(encrypted,0,4);
    }

    private String decryptTwoKeyAO(String encrypted){
        return decryptTwoKey(encrypted,0,14);
    }

    private String decryptTwoKeyEA(String encrypted){
        return decryptTwoKey(encrypted,4,0);
    }

    private String decryptTwoKeyEE(String encrypted){
        return decryptTwoKey(encrypted,4,4);
    }

    private String decryptTwoKeyEO(String encrypted){
        return decryptTwoKey(encrypted,4,14);
    }

    private String decryptTwoKeyOA(String encrypted){
        return decryptTwoKey(encrypted,14,0);
    }

    private String decryptTwoKeyOE(String encrypted){
        return decryptTwoKey(encrypted,14,4);
    }

    private String decryptTwoKeyOO(String encrypted){
        return decryptTwoKey(encrypted,14,14);
    }

    public void testDecryotTwoKeyFromFile(){
        CaesarCipher cc = new CaesarCipher();
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        print(encrypted);
        print("");
        print("AA: "+ decryptTwoKeyAE(encrypted));
        print("AE: "+decryptTwoKeyAE(encrypted));
        print("AO: "+decryptTwoKeyAO(encrypted));

        print("EA: "+decryptTwoKeyEA(encrypted));
        print("EE: "+decryptTwoKeyEE(encrypted));
        print("EO: "+decryptTwoKeyEO(encrypted));

        print("OA: "+decryptTwoKeyOA(encrypted));
        print("OE: "+decryptTwoKeyOE(encrypted));
        print("OO: "+decryptTwoKeyOO(encrypted));
        
    }
    
    
    public void testDecryotTwoKey(String input){
        CaesarCipher cc = new CaesarCipher();
        print(input);
        print("");
        print(Utils.splitEvenString(input)[0]);
        print(Utils.splitEvenString(input)[1]);
        print("");
        String encrypted = cc.encryptTwoKeys(input,17,23);
        print(encrypted);
        print("");
        print("AA: "+ decryptTwoKeyAE(encrypted));
        print("AE: "+decryptTwoKeyAE(encrypted));
        print("AO: "+decryptTwoKeyAO(encrypted));

        print("EA: "+decryptTwoKeyEA(encrypted));
        print("EE: "+decryptTwoKeyEE(encrypted));
        print("EO: "+decryptTwoKeyEO(encrypted));

        print("OA: "+decryptTwoKeyOA(encrypted));
        print("OE: "+decryptTwoKeyOE(encrypted));
        print("OO: "+decryptTwoKeyOO(encrypted));

    }

    //countLetters, maxIndex, and decrypt. 
    private int[] countLetters(String s){
        s =s.toUpperCase();
        int maxSize = s.length();
        int counts[] = new int[26];   
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i =0 ; i< 26;i++){
            counts[i]=maxSize - s.replaceAll(""+alphabet.charAt(i),"").length();
        }
        return counts;
    }

    private String decrypt(String encrypted,int dec){

        int[] freqs = countLetters(encrypted);
        int maxDex= maxIndex(freqs);
        int dkey = maxDex -dec;

        if(maxDex <dec){

            dkey = 26 - (dec-maxDex);
        }
        print(""+(26-dkey));
        CaesarCipher cc = new CaesarCipher(26-dkey);
        return cc.encrypt(encrypted);
    }

    private String decryptA(String encrypted){

        return decrypt( encrypted, 0);
    }

    private String decryptE(String encrypted){

        return decrypt( encrypted, 4);
    }

    private String decryptO(String encrypted){

        return decrypt( encrypted, 14);
    }

    private int maxIndex(int[] vals){
        int maxDex =0;
        for(int i =0; i<vals.length;i++){
            if(vals[i] > vals[maxDex]){
                maxDex = i;
            }
        }
        //print("maxDex : "+maxDex);
        return maxDex;
    }

    public void testDecrypt(){
        CaesarCipher cc = new CaesarCipher(15);
        String input = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        print(input);
        String encrypted = cc.encrypt(input);
        print(encrypted);

        print("A: "+decryptA(encrypted));
        print("E: "+decryptE(encrypted));
        print("O: "+decryptO(encrypted));

    }

}
