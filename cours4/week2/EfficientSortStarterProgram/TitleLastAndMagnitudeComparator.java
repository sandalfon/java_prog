
/**
 * Décrivez votre classe TitleLastAndMagnitudeComparator ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
import java.util.*;
public class TitleLastAndMagnitudeComparator 
implements Comparator<QuakeEntry> {
        public int compare(QuakeEntry qe1, QuakeEntry qe2) {
            String info1 = qe1.getInfo();
            info1 =info1.substring(info1.lastIndexOf(" ")+1);
            String info2 = qe2.getInfo();
            info2 = info2.substring(info2.lastIndexOf(" ")+1);
            if(info1.compareTo(info2)>0)
                return 1;
            
            if(info1.compareTo(info2)<0)
                return -1;
             
            Double magn1 = qe1.getMagnitude();
            Double magn2 = qe2.getMagnitude();
            
            if (magn1 < magn2){
	        return -1;
	       }
	    if (magn1 > magn2){
	        return 1;
	       }
                
    return 0;        
    }
}


