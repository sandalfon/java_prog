
/**
 * Décrivez votre classe TitleAndDepthComparator ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */

import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
        public int compare(QuakeEntry qe1, QuakeEntry qe2) {
            String info1 = qe1.getInfo();
            String info2 = qe2.getInfo();
            if(info1.compareTo(info2)>0)
                return 1;
            
            if(info1.compareTo(info2)<0)
                return -1;
             
            Double depth1 = qe1.getDepth();
            Double depth2 = qe2.getDepth();
            
            if (depth1 < depth2){
	        return -1;
	       }
	    if (depth1 > depth2){
	        return 1;
	       }
                
    return 0;        
    }
}
