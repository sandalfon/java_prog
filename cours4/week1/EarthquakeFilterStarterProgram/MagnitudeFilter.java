
/**
 * Décrivez votre classe MagnitudeFilter ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class MagnitudeFilter implements Filter{
    private double min;
    private double max;
    
    public MagnitudeFilter(double minTarg, double maxTarg){
        min = minTarg;
        max = maxTarg;
    }
    
    public boolean satisfies(QuakeEntry qe) { 
        return (qe.getMagnitude() >= min && qe.getMagnitude()<= max); 
    } 
}
