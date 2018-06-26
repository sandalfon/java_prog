
/**
 * Décrivez votre classe PhraseFilter ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class DistanceFilter  implements Filter{
    private float max;
    private Location loc;
    
    public DistanceFilter(float maxRef, Location locRef) { 
        max = maxRef;
        loc =locRef;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getLocation().distanceTo(loc) <=max; 
    } 
}
