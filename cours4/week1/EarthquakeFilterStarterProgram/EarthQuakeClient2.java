import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 
    
    public void testMatchAllFilter(){ 
        EarthQuakeParser parser = new EarthQuakeParser();
         String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0,2.0));
        maf.addFilter(new DepthFilter(-100000.0,-10000.0));
        maf.addFilter(new PhraseFilter("any", "a"));
        ArrayList<QuakeEntry> m7 =  filter(list,maf);
              for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
    }
        public void testMatchAllFilter2(){ 
        EarthQuakeParser parser = new EarthQuakeParser();
         String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0,5.0));
        //maf.addFilter(new DepthFilter(-180000.0,-30000.0));
        maf.addFilter(new DistanceFilter(3000000, new Location(55.7308, 9.1153)));
        maf.addFilter(new PhraseFilter("any", "e"));
        ArrayList<QuakeEntry> m7 =  filter(list,maf);
         System.out.println("filter data for "+m7.size()+" quakes");
              for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
    }

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        //Filter f = new MinMagFilter(4.0); 
        Filter f = new MagnitudeFilter(4.0,5.0);
        Filter f2 = new DepthFilter(-35000.0,-12000.0);
        Filter f3 =  new DistanceFilter(10000000, new Location(35.42, 139.43));
        Filter f4 =  new PhraseFilter("end", "Japan");
        ArrayList<QuakeEntry> m7 = new ArrayList<QuakeEntry>(list);
        ArrayList<Filter> filters = new ArrayList<Filter>();
        
        //filters.add(f);
        //filters.add(f2);
        filters.add(f3);
        filters.add(f4);
        
        
        
        for (int i = 0; i < filters.size(); i++) {
            m7  = filter(m7, filters.get(i)); 
        }
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
