
/**
 * Décrivez votre classe PhraseFilter ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class PhraseFilter implements Filter{
    private String type;
    private String phrase;

    public PhraseFilter(String typeRef, String phraseRef){
        type = typeRef;
        phrase = phraseRef;
    }

    public boolean satisfies(QuakeEntry qe) { 
        String info =qe.getInfo();
        switch (type) {
            case "start":
            if (info.startsWith(phrase)) {
                return true;
            }
            break;
            case "end":
            if (info.endsWith(phrase)) {
                return true;
            }
            break;
            case "any":
            if (info.contains(phrase)) {
                return true;
            }
            break;
        }
        return false;
    }
}
