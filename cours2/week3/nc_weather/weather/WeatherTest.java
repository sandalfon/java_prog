
/**
 * Décrivez votre classe WeatherTest ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class WeatherTest {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

    }

    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord  currentRecord=null;
        double currentTemperature = 9999;
        double artifact = -9999;
        double temperature;

        for(CSVRecord record : parser){
            temperature = Double.parseDouble(record.get("TemperatureF"));
            if(temperature < currentTemperature && temperature !=artifact){
                currentTemperature = temperature;
                currentRecord = record;
            }

        }
        return currentRecord;
    }

    public String fileWithColdestTemperature(){
        String currentFile = "";
        DirectoryResource dr = new DirectoryResource();
        double currentTemperature = 9999;
        double temperature;
        CSVParser parser; 
        for(File  f : dr.selectedFiles()){
            parser = new FileResource(f).getCSVParser();
            temperature = Double.parseDouble(this.coldestHourInFile(parser).get("TemperatureF"));
            System.out.println(f.getAbsolutePath().replaceAll("D:.*weather","weather")+" :"+temperature);
            if(temperature < currentTemperature){
                currentTemperature = temperature;
                currentFile = f.getAbsolutePath();
                
            }
        }

        return currentFile;  
    }

    public void testFileWithColdestTemperature(){
        String file = this.fileWithColdestTemperature();
        System.out.println("Coldest day was in file "+file.replaceAll("D:.*weather","weather"));
        CSVParser parser = new FileResource(file).getCSVParser();

        CSVRecord record =coldestHourInFile(parser);
        double temperature = Double.parseDouble(record.get("TemperatureF"));
        System.out.println("Coldest temperature on that day was "+temperature);
        parser = new FileResource(file).getCSVParser();
        for(CSVRecord rd : parser){
            System.out.println(rd.get("DateUTC")+": "+rd.get("TemperatureF"));
        }
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord  currentRecord=null;
        double currentHumidity = 9999;
        String tmpHumidity;
        double humidity;

        for(CSVRecord record : parser){
            tmpHumidity = record.get("Humidity");
            if(!tmpHumidity.equals("N/A")){
                humidity = Double.parseDouble(tmpHumidity);
                if(humidity < currentHumidity){
                    currentHumidity = humidity;
                    currentRecord = record;
                }
            }
        }
        return currentRecord;
    }

    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);  
        System.out.println(csv.get("DateUTC")+": "+csv.get("Humidity"));
    }

    public double averageTemperatureInFile(CSVParser parser){
        double avg =0.0;
        int count = 0;
        double temperature;
        double artifact = -9999;

        for(CSVRecord record : parser){
            temperature = Double.parseDouble(record.get("TemperatureF"));
            if(temperature !=artifact){
                avg = avg + temperature;
                count +=1;
            }

        }

        return avg/(count*1.0);  
    }

    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature in file is "+this.averageTemperatureInFile(parser));
    }

    public double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value){
        double avg =0.0;
        int count = 0;
        double artifact = -9999;
        String tmpHumidity;
        double humidity;
        double temperature;
        for(CSVRecord record : parser){
            tmpHumidity = record.get("Humidity");
            if(!tmpHumidity.equals("N/A")){
                humidity = Double.parseDouble(tmpHumidity);
                if(humidity >=value ){
                    temperature = Double.parseDouble(record.get("TemperatureF"));
                    if(temperature !=artifact){
                        avg = avg + temperature;
                        count +=1;
                    }  
                }
            }
        }
        if(count == 0){
            return 0.0;
        }

        return avg/(count*1.0);  
    }

    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = this.averageTemperatureWithHighHumidityInFile(parser, 80);
        if(avg == 0.0){
            System.out.println("No temperatures with that humidity");
        }else{
            System.out.println("Average temperatures with that humidity :" + avg);
        }

    }

}
