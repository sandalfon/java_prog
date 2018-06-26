import java.util.*;
import edu.duke.*;
/**
 * Décrivez votre classe LargestQuakes ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class LargestQuakes {
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        System.out.println("max magn "+indexOfLargest(list));
        ArrayList<QuakeEntry> magnitude = getLargest(list,20);

        System.out.println("read data for "+magnitude.size()+"closest to");
        for (int k=0; k< magnitude.size(); k++) {
            QuakeEntry entry = magnitude.get(k);
            System.out.println(entry);

        }
    }

    private int indexOfLargest(ArrayList<QuakeEntry> data){
        int res =0;
        double magnitude =0.0;
        for(int i =0; i< data.size(); i++){
            double currentMagnitude = data.get(i).getMagnitude();
            if(currentMagnitude>magnitude){
             res = i;
             magnitude = currentMagnitude;
            }
        }

        return res;   
    }
    
    ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
         ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        if(quakeData.size()<=howMany)
            return quakeData;
        ArrayList<Double> magnitudes = new ArrayList<Double>();
        for(int i=0; i<quakeData.size();i++)
            magnitudes.add(quakeData.get(i).getMagnitude());
        ArrayList<Double> nstore = new ArrayList<Double>(magnitudes);
        Collections.sort(magnitudes, Collections.reverseOrder());
        int[] indexes = new int[magnitudes.size()];
        for (int n = 0; n < magnitudes.size(); n++){
            indexes[n] = nstore.indexOf(magnitudes.get(n));
        }   
        for(int i = 0; i<howMany;i++){
            answer.add(quakeData.get(indexes[i]));
        }
        return answer;
        
    }
}
