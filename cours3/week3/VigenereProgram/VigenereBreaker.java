import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String sliced ="";
        for(int i = whichSlice; i < message.length(); i = i +totalSlices){
            sliced= sliced + message.charAt(i);
        }
        return sliced;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker  cc =new CaesarCracker(mostCommon);
        for(int i =0 ; i < klength; i++){
            key[i]=cc.getKey(sliceString(encrypted,i,klength));  
        }
        return key;
    }

    public void BreakVigenere (int kl) {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();

        FileResource dicoFr = new FileResource();
        HashSet<String> dico  =readDictionary(dicoFr);

        int[] res = tryKeyLength(encrypted,kl,'e');
        VigenereCipher vc = new VigenereCipher(res);
        String decrypted = vc.decrypt(encrypted);
        System.out.println(countWords(decrypted, dico)+"");
        //System.out.println(decrypted);

    }

    public void BreakVigenere () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        HashMap<String,HashSet<String>> dicos = new  HashMap<String,HashSet<String>> (); 
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            String language = f.getName();
           
        dicos.put(language,readDictionary(new FileResource(f)));
    }
        breakForAllLangs(encrypted,dicos);
        //System.out.println(breakForLanguage(encrypted,dico));
        /*
        int[] res = tryKeyLength(encrypted,5,'e');
        VigenereCipher vc = new VigenereCipher(res);
        String decrypted = vc.decrypt(encrypted);
        System.out.println(decrypted);*/
    }

    public void test(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int[] res = tryKeyLength(encrypted,5,'e');
        for(int i =0; i< res.length;i++){
            System.out.println(""+res[i]);
        }
    }

    public void quiz1(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();

        int[] res = tryKeyLength(encrypted,4,'e');

        VigenereCipher vc = new VigenereCipher(res);
        String decrypted = vc.decrypt(encrypted);
        System.out.println(decrypted);
        for(int i =0; i< res.length;i++){
            System.out.println(""+res[i]);
        }
    }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dico = new HashSet<String>();

        for(String s : fr.asString().split("\\n")){
            dico.add(s.toLowerCase());
        }
        System.out.println("Dico size : "+dico.size());
        return dico;
    }

    public int countWords(String message, HashSet<String> dictionary){
        int valid =0;
        String[] splited = message.toLowerCase().split("\\W+");
        for(String w : splited){
            if(dictionary.contains(w))
                valid+=1;
        }
        return valid;
    }

    //<ValidCount,<Character, keys>
    public HashMap<Integer,HashMap<Character,int[]>> breakForLanguage(String encrypted, HashSet<String> dictionary){
        int numberTest =100;
        int[] res= new int[numberTest+1];
         char mostChar = mostCommonCharIn(dictionary);
        for(int keyLength =1; keyLength<=100; keyLength++){
            System.out.println("test key "+keyLength+" over "+numberTest);
            int[] keys = tryKeyLength(encrypted,keyLength,mostChar);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            res[keyLength] = countWords(decrypted, dictionary);
        }
        int max =0;
        int index = 0;
        for(int i =1; i<= numberTest;i++){
            if(max <res[i]){
                max = res[i];
                index = i;
            }
        }
        System.out.println("Key length "+index+" with valid "+max);
        int[] keys = tryKeyLength(encrypted,index,mostChar);

        /*for(int i =0; i< keys.length;i++){
            System.out.println(""+keys[i]);
        }*/
        HashMap<Integer,HashMap<Character,int[]>> log  = new HashMap<Integer,HashMap<Character,int[]>>();
        HashMap<Character,int[]> sublog = new HashMap<Character,int[]>();
        sublog.put(mostChar,keys);
        log.put(max,sublog);
        return log;
        /*VigenereCipher vc = new VigenereCipher(keys);
        
        return vc.decrypt(encrypted);*/
    }

    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character, Integer> counts = new HashMap<Character, Integer>();
        String alphabet = "abcdefghijklmnopqrstuvwxyzóéèà";
        for(int i=0; i<alphabet.length();i++){
            counts.put(alphabet.charAt(i),0);
        }
        for(String s : dictionary){
            for(int i=0; i<s.length();i++){
                char c =s.charAt(i);
                
                if(counts.containsKey(c))
                counts.put(c,counts.get(c)+1);
            }
        }
        char res='a';
        int max = 0;
        for(char c : counts.keySet()){
            if(counts.get(c)> max){
                max =counts.get(c);
                res =c;
            }
        }
        return res;
    }
//<language,<ValidCount,<Character, keys>>>
    public void breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){
        HashMap<String, HashMap<Integer,HashMap<Character,int[]>>> memory = new HashMap<String, HashMap<Integer,HashMap<Character,int[]>>>();
        for(String language : languages.keySet()){
           HashSet<String> dictionary = languages.get(language);
              memory.put(language,  breakForLanguage(encrypted,dictionary));     
            
        }
        int maxWordValid = -1;
        String languageWordValid ="None";
        for(String language : memory.keySet()){
             for(int val : memory.get(language).keySet()){
                 if(val> maxWordValid){
                     maxWordValid = val;
                     languageWordValid = language;
                    }
                }
        }
        
        makeDecoder(encrypted,languageWordValid,memory.get(languageWordValid));
            
    }
    //<ValidCount,<Character, key>>
    public void makeDecoder(String encrypted, String language, HashMap<Integer,HashMap<Character,int[]>> codex){
        char mostCom='-';
        int validWord=-1;
        int[] keys= new int[0];
        for(int val : codex.keySet()){
            validWord = val;
            for(char c : codex.get(val).keySet()){
                mostCom=c;
                keys =  codex.get(val).get(c);
            }
        }
        VigenereCipher vc = new VigenereCipher(keys);
                /*for(int i =0; i< keys.length;i++){
            System.out.println(""+keys[i]);
        }*/
        System.out.println(vc.decrypt(encrypted));
        
        System.out.println("The codex is language : "+ language);
        System.out.println("             Character : "+ mostCom);
        System.out.println("             key : ");
        for(int i =0; i< keys.length;i++){
        System.out.println("                   "+keys[i]);
    }
        System.out.println("             word valid : "+ validWord);
        
        
    }
}



