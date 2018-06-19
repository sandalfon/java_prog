
/**
 * Décrivez votre classe Utils ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Utils {
    public static String[] splitEvenString(String s){
        String[] res = new String[2];
        res[1]="";
        res[0]="";
        for(int i =0; i<s.length();i++){
            if(isEven(i)){
                res[1]=res[1]+s.charAt(i);
            }else{
                res[0]=res[0]+s.charAt(i);
            }
        }

        return res;
    }

    public static String mergeEvenString(String[] sArray){
        String res="";
        int oddMax = sArray[0].length();
        int evenMax = sArray[1].length();
        int maxLength = Math.min(oddMax,evenMax);
        for(int i =0; i < maxLength;i++){
            res = res+sArray[0].charAt(i)+sArray[1].charAt(i);
        }

        if(evenMax > oddMax){
            res = res + sArray[1].charAt(evenMax-1);
        }
        if(evenMax < oddMax){
            res = res + sArray[0].charAt(oddMax-1);
        }

        return res;
    }

    public static boolean isEven(int val){
        if( (val % 2)== 0){
            return false;
        }else{

            return true;
        }

    }
}
