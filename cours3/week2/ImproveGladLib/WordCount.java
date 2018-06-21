
/**
 * Décrivez votre classe WordCount ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import edu.duke.*;

public class WordCount {

    private HashMap<Integer,HashMap<String, Integer>> countShift = new   HashMap<Integer,HashMap<String, Integer>>();   
    private HashMap<Integer, List<String> > condons = new HashMap<Integer, List<String> > ();

    public WordCount(String s){
        init();
        count(s);
        System.out.println(countShift.toString());
    }
    
        public WordCount(){
        init();
        FileResource fr = new FileResource();
        String s = fr.asString();
        count(s);
        System.out.println(countShift.toString());
    }
    

    private void init(){
        condons.put(0,Arrays.asList("CGT", "TCA", "AGT" , "TCA"));
        condons.put(1,Arrays.asList("GTT", "CAA"));
        condons.put(2,Arrays.asList("TTC", "AAG", "TTC")); 
        HashMap<String, Integer> tmp;
        for(int i=0; i<3; i++){
            tmp = new HashMap<String, Integer>();
            //System.out.println("size "+condons.get(i).size());
            for(int j=0; j< condons.get(i).size();j++){
                tmp.put(condons.get(i).get(j),0);
            }
            countShift.put(i,tmp);
        }
    }

    //.put(sub,countShift.get(j).get(sub)+1)
    private void count(String s){
        String sub;
        for(int i= 0; i< s.length()-3; i++){
            //System.out.println("pos: "+i);
            for(int j=0;j<3;j++){
                //System.out.println("shift: "+j);
                if(i+j+3<=s.length()){
                    sub = s.substring(i+j,i+j+3);
                   // System.out.println("sub: "+sub);
                    if(condons.get(j).contains(sub)){
                        if(countShift.get(j).containsKey(sub)){
                            //System.out.println("HM :"+ countShift.get(j).get(sub));
                            countShift.get(j).put(sub,countShift.get(j).get(sub)+1);
                        }
                    }

                }
            }

        }
    }
    
    public String maxCodon(){
        String res = "none";
        String codon;
        int maxCount = 0;
        int value;
        for(int i = 0; i<3;i++){
            for(int j=0;j< condons.get(i).size();j++){
                codon = condons.get(i).get(j);
                value = countShift.get(i).get(codon);
                if(value> maxCount){
                    maxCount =value;
                    res = codon;
                }
            }
        }
        System.out.println("Max codon: "+ res+ " with "+maxCount);
        return res;
        
    }

}
