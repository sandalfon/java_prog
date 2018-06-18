/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }

    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(this.countryInfo(parser,"Nauru"));
        parser = fr.getCSVParser();
        System.out.println(this.countryInfo(parser,"bourgondie"));
        parser = fr.getCSVParser();
        this.listExportersTwoProducts(parser,"cotton", "flowers");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser,"cocoa"));
        parser = fr.getCSVParser();
        this.bigExporter(parser,"$999,999,999,999");
    }

    public String countryInfo(CSVParser parser, String countryOfInterest){
        String country;
        for(CSVRecord record : parser){
            country = record.get("Country");  
            if(country.contains(countryOfInterest)){
                return countryOfInterest+": "+record.get("Exports")+": "+record.get("Value (dollars)");
            }
        }
        return countryOfInterest+": NOT FOUND"; 

    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
          String export;
           System.out.println("***********");
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
             export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                
                    System.out.println(record.get("Country"));
                
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportItem)) {
                    count +=1;
            }
        }
        
        return count;
    }
    
    public void bigExporter(CSVParser parser, String valueOfInterest){
         for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String value = record.get("Value (dollars)");
            //Check if it contains exportOfInterest
            if (value.length() > valueOfInterest.length() ) {
                    System.out.println(record.get("Country")+": "+value);
            }
        }
        
    }
}
