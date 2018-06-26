import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;              
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {      
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {      
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            double depth  =qe.getDepth();
            if (depth < maxDepth && depth > minDepth) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByPhrase (ArrayList<QuakeEntry> quakeData, String phrase, String where) {      
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            String info =qe.getInfo();
            switch (where) {
                case "start":
                if (info.startsWith(phrase)) {
                    answer.add(qe);
                }
                break;
                case "end":
                if (info.endsWith(phrase)) {
                    answer.add(qe);
                }
                break;
                case "any":
                if (info.contains(phrase)) {
                    answer.add(qe);
                }
                break;
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry>  bigList = filterByMagnitude(list,5.0);
        System.out.println("read data for "+list.size()+" quakes");

        System.out.println("read data for "+bigList.size()+"Big quakes");
        for (int k=0; k< bigList.size(); k++) {
            QuakeEntry entry = list.get(k);
            System.out.println( entry);

        }

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        Location city = new Location(35.988, -78.907);

        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());

        }
    }

    public void quakesOfDepth (){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        double minDepth = -4000.0;
        double maxDepth = -2000.0;
        // This location is Durham, NC

        ArrayList<QuakeEntry> depth = filterByDepth(list,minDepth,maxDepth);

        System.out.println("read data for "+depth.size()+"depth");
        for (int k=0; k< depth.size(); k++) {
            QuakeEntry entry = depth.get(k);
            System.out.println(entry);

        }
    }

    public void quakesByPhrase  (){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        double minDepth = -10000.0;
        double maxDepth = -5000.0;
        // This location is Durham, NC

        ArrayList<QuakeEntry> title = filterByPhrase (list,"Can","any");

        System.out.println("read data for "+title.size()+"phrase");
        for (int k=0; k< title.size(); k++) {
            QuakeEntry entry = title.get(k);
            System.out.println(entry);

        }
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry>  quakeData,  Location  current, int  howMany){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        if(quakeData.size()<=howMany)
            return quakeData;
        ArrayList<Float> distances = new ArrayList<Float>();
        for(int i=0; i<quakeData.size();i++)
            distances.add(quakeData.get(i).getLocation().distanceTo(current));
        ArrayList<Float> nstore = new ArrayList<Float>(distances); 
        Collections.sort(distances);
        int[] indexes = new int[distances.size()];
        for (int n = 0; n < distances.size(); n++){
            indexes[n] = nstore.indexOf(distances.get(n));
        }   
        for(int i = 0; i<howMany;i++){
            answer.add(quakeData.get(indexes[i]));
        }
        return answer;

    }

    public void findClosestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        Location city = new Location(-6.211,106.845);
        ArrayList<QuakeEntry> title = getClosest(list,city,3);

        System.out.println("read data for "+title.size()+"closest to");
        for (int k=0; k< title.size(); k++) {
            QuakeEntry entry = title.get(k);
            System.out.println(entry);

        }
    }

}
