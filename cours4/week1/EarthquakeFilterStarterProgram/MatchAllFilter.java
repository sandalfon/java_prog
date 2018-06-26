import java.util.*;
/**
 * Décrivez votre classe MatchAllFilter ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filters;

    public MatchAllFilter(){
        filters = new ArrayList<Filter>();

    }

    public void addFilter(Filter f){
        filters.add(f);
    }

    public boolean satisfies(QuakeEntry qe){
        for (int i = 0; i < filters.size(); i++) {
            if(! filters.get(i).satisfies(qe))
                return false;
        }
        return true;
    }
}
