
/**
 * Décrivez votre classe DepthFilter ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class DepthFilter implements Filter{
    
    private double min;
    private double max;
    
    public DepthFilter(double minTarg, double maxTarg){
        min = minTarg;
        max = maxTarg;
    }
    
    public boolean satisfies(QuakeEntry qe) { 
        return (qe.getDepth() >= min && qe.getDepth()<= max); 
    } 

}
