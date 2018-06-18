/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                    " Gender " + rec.get(1) +
                    " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
    }
    
        public void numberOfnames (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += 1;
            if (rec.get(1).equals("M")) {
                totalBoys += 1;
            }
            else {
                totalGirls += 1;
            }
        }
        System.out.println("total names = " + totalBirths);
        System.out.println("names girls = " + totalGirls);
        System.out.println("names boys = " + totalBoys);
    }

    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("data/yob1900.csv");
        numberOfnames(fr);
        
        fr = new FileResource("data/yob1905.csv");
        numberOfnames(fr);
        
        System.out.println(getRank(1960,"Emily","F"));
        System.out.println(getRank(1971,"Frank","M"));
        System.out.println( getName( 1980, 350, "F"));
        System.out.println( getName( 1982, 450, "M"));
        String name ="Susan";
        int year = 1972;
        String gender = "F";
        int newYear = 2014;
        System.out.println(name+" born in "+year+" woyld be "+ whatIsNameInYear( name,  year,  newYear,  gender)+" if she was born in "+newYear);
        name ="Owen";
        year = 1974;
        gender = "M";
        newYear = 2014;
        System.out.println(name+" born in "+year+" woyld be "+ whatIsNameInYear( name,  year,  newYear,  gender)+" if she was born in "+newYear);
        name ="Genevieve";
        gender ="F";
        System.out.println("Best year rank for "+name+" is "+yearOfHighestRank(name,gender));
        name ="Mich";
        gender ="M";
        System.out.println("Best year rank for "+name+" is "+yearOfHighestRank(name,gender));
        name ="Susan";
        gender = "F";
        System.out.println("AVG rank for "+name+" is "+getAverageRank(name,gender));
        name ="Robert";
        gender = "M";
        System.out.println("AVG rank for "+name+" is "+getAverageRank(name,gender));
        name ="Emily";
        year = 1990;
        gender = "F";        
        System.out.println("nb of birth above "+name+" is "+getTotalBirthsRankedHigher(year,name,gender));
        name ="Drew";
        year = 1990;
        gender = "M";        
        System.out.println("nb of birth above "+name+" is "+getTotalBirthsRankedHigher(year,name,gender));
        
        /*
        String name ="Isabella";
        int year = 2012;
        String gender = "F";
        int newYear = 2014;
        System.out.println(name+" born in "+year+" woyld be "+ whatIsNameInYear( name,  year,  newYear,  gender)+" if she was born in "+newYear);
        name ="Mason";
        gender ="M";
        System.out.println("Best year rank for "+name+" is "+yearOfHighestRank(name,gender));
        System.out.println("AVG rank for "+name+" is "+getAverageRank(name,gender));
        name ="Jacob";
        System.out.println("AVG rank for "+name+" is "+getAverageRank(name,gender));
        name ="Ethan";
        System.out.println("nb of birth above "+name+" is "+getTotalBirthsRankedHigher(year,name,gender));*/
    }

    public int getRank(int year, String  name, String gender){
        int rank = 1;
        FileResource fr = new FileResource("data/yob"+year+".csv");
        String nameTemp;
        String genderTemp;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            nameTemp =rec.get(0);
            genderTemp = rec.get(1);
            if(genderTemp.equals(gender)){
                if(nameTemp.equals(name) ){
                    return rank;
                }

                rank += 1;
            }
        }
        return -1;
    }

    public String getName(int year, int  rank, String gender){
        int count =1;
        FileResource fr = new FileResource("data/yob"+year+".csv");
        String nameTemp;
        String genderTemp;

        for (CSVRecord rec : fr.getCSVParser(false)) {

            genderTemp = rec.get(1);
            if(genderTemp.equals(gender)){
                if(rank == count){
                    return  rec.get(0);
                }

                count += 1;
            }
        }
        return "NO NAME";
    }

    public String  whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name,  gender);
        return  getName( newYear, rank, gender);

    }

    public int yearOfHighestRank(String name, String gender){
        int currentYear = -1;
        DirectoryResource dr = new DirectoryResource();
        double maxPerimeter = 0.0;
        double currentPerimeter;
        FileResource fr;
        int year;
        int rank ;
        int currentRank= 999999999;
        for(File  f : dr.selectedFiles()){
            year = Integer.parseInt(f.getName().replace("yob","").replace("short","").replace(".csv",""));
            rank =getRank(year, name, gender);
            if(currentRank > rank && rank!=-1){
                currentRank = rank;
                currentYear = year;
            }

        }
        return currentYear;
    }

    public double getAverageRank(String name, String gender){
        double avg =0;
        int count = 0;
        int rank;
        int year;

        DirectoryResource dr = new DirectoryResource();

        for(File  f : dr.selectedFiles()){
            year = Integer.parseInt(f.getName().replace("yob","").replace("short","").replace(".csv",""));
            rank =getRank(year, name, gender);
            if(rank >0 ){
                avg = avg + rank;
                count+=1;
            }

        }

        if(count == 0){
            return -1.0;
        }
        return avg/count;
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int totalNumber=0;
        int tempNumber;
        FileResource fr = new FileResource("data/yob"+year+".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {

            if (rec.get(1).equals(gender)) {
                if(rec.get(0).equals(name)){
                    return totalNumber;

                }
                totalNumber =totalNumber + Integer.parseInt(rec.get(2));
            }
        }
        return totalNumber;
    }
}
