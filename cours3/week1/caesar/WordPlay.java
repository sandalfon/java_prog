
/**
 * Décrivez votre classe WordPlay ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class WordPlay {
    
    
    public void testEmphasize(){
       print( emphasize("dna ctgaaactgA", 'a') );
       print(emphasize("Mary Bella Abracadabra", 'a')); 
        
        
    }
    
    private String emphasize(String phrase, char ch){
        StringBuilder ans = new StringBuilder(phrase);
        ch =  Character.toUpperCase(ch);
        for(int i=0; i<phrase.length();i++){
            if(Character.toUpperCase(ans.charAt(i)) == ch){
                if(isEven(i)){
                    ans.setCharAt(i,'+');
                }else{
                    ans.setCharAt(i,'*');
                }
                
            }
            
        }
        
        return ans.toString(); 
    }
    
    private boolean isEven(int val){
        if( (val % 2)== 0){
            return false;
        }else{
            
            return true;
        }
        
    }
    

    private String replaceVowels (String phrase, char ch){
        StringBuilder ans = new StringBuilder(phrase);
        for(int i=0; i< phrase.length();i++ ){
            if(isVowel(phrase.charAt(i))){
                ans.setCharAt(i,ch);
            }
        }

        return ans.toString();   
    }

    public void testReplaceVowels(){
        print(replaceVowels("Hello World", '*'));
        
    }
    
    private boolean isVowel(char ch){
        ch = Character.toUpperCase(ch);
        switch(ch){
            case 'A': return true;
            case 'E': return true;
            case 'I': return true;
            case 'O': return true;
            case 'U': return true;
            default:
            return false;
        }
    }

    private void print(String  s){
        System.out.println(s);   
    }

    public void testIsVowel(){
        print(""+isVowel('A'));
        print(""+isVowel('F'));
        print(""+isVowel('e'));
        print(""+isVowel('w'));

    }
}
