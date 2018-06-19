
/**
 * Décrivez votre classe CaesarCipher ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
import edu.duke.*;
import java.io.*;

public class CaesarCipher {

    private String alphabet;
    private String shiftedAlpha;

    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlpha = alphabet.substring(key)+ alphabet.substring(0, key);
    }
  
    public CaesarCipher() {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlpha = alphabet.substring(3)+ alphabet.substring(0, 3);
    }
    
    
    public String encrypt(String input){
        char currentChar;
        int idx;
        char codedChar;
        StringBuilder ans = new StringBuilder(input);
        for(int i = 0; i<ans.length(); i++){
            currentChar = ans.charAt(i);
            idx = alphabet.indexOf(Character.toUpperCase(currentChar));
            if(idx !=-1){
                codedChar = shiftedAlpha.charAt(idx);
                if(Character.isUpperCase(input.charAt(i))){
                    ans.setCharAt(i,(Character.toUpperCase(codedChar)));

                }else{

                    ans.setCharAt(i,(Character.toLowerCase(codedChar)));
                }

            }
        }
        print(ans.toString());
        return ans.toString(); 
    }
    
    
    private void print(String s){
        
     System.out.println(s);   
    }

    public void testSplitMerge(){
        String input="1234567890";
        String[] split = Utils.splitEvenString(input);
        String output = Utils.mergeEvenString(split);
        print(input);
        print(split[0]);
        print(split[1]);
        print(output);
        
    }
    

    
    public String encryptTwoKeys(String input, int key1, int key2){
    String[] split = Utils.splitEvenString(input); 
    CaesarCipher cc1 = new CaesarCipher(key1);
    CaesarCipher cc2 = new CaesarCipher(key2);
    split[0]= cc1.encrypt(split[0]);
    split[1]= cc2.encrypt(split[1]);
    print(Utils.mergeEvenString(split));
    return Utils.mergeEvenString(split); 
    }
    
    
   public void testEncryptTwoKeys(){
    print(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 23, 17));
    }

    
}
