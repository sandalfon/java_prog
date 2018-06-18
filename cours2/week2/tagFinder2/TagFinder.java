/**
 * Finds a protein within a strand of DNA represented as a string of c,g,t,a letters.
 * A protein is a part of the DNA strand marked by start and stop codons (DNA triples)
 * that is a multiple of 3 letters long.
 *
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;


// 69 ok
//25 >60   à tester 23
//longest 489 ok
//gc ratio 64  à tester 40
//CTG 102 à tester 224


public class TagFinder {
    public String findProtein(String dna) {
        int start = dna.indexOf("atg");
        if (start == -1) {
            return "";
        }
        int stop = dna.indexOf("tag", start+3);
        if ((stop - start) % 3 == 0) {
            return dna.substring(start, stop+3);
        }
        else {
            return "";
        }
    }

    public Boolean isDnaGreaterThan(String dna, int size){
        return dna.length() > size;

    }

    public StorageResource findGenes(String dna) {
        StorageResource store = new StorageResource();
        int startIndex = dna.indexOf("atg", 0);
        String subDna;
        while (startIndex!= -1) {
            subDna=findGene(dna,startIndex);
            if(subDna.length()>0)
                store.add(subDna);
            startIndex=dna.indexOf("atg",startIndex+subDna.length()+1);

        }
        return store;
    }

    public String findGene(String dna, int startPos){
        int startIndex = dna.indexOf("atg",startPos);
        int stopIndex; 

        if(startIndex ==-1){
            return "";
        }
        stopIndex = this.findStop(dna, startIndex);
        if(stopIndex == dna.length())
            return "";

        else{
            //System.out.println("Start at "+startIndex+" end at"+stopIndex+" :"+ dna.substring(startIndex, stopIndex+3));
            return dna.substring(startIndex, stopIndex+3);
        }

    }

    public int findStopCodon(String dna, int startIndex, String codon){
        // taa tag tga
        int currIndex = dna.indexOf(codon, startIndex+3);
        int diff;
        while(currIndex !=-1){
            diff = currIndex - startIndex;
            if(diff %3 ==0){
                return currIndex   ;
            }
            else{
                currIndex = dna.indexOf(codon,currIndex+1);   
            }

        }
        return dna.length(); 
    }

    public int findStop(String dna, int startIndex){

        int taaEnd = findStopCodon(dna,startIndex, "taa");
        int tagEnd = findStopCodon(dna,startIndex, "tag");
        int tgaEnd = findStopCodon(dna,startIndex, "tga");

        return  Math.min(Math.min(taaEnd,tagEnd),tgaEnd);
        //return tagMin;

    }

    public int findNbGreaterThan(StorageResource store, int size){
        int count = 0;
        for(String dna : store.data()){
            if(isDnaGreaterThan(dna, size))
                count +=1;
        }
        return count;

    }

    public int findLongest(StorageResource store){
        int tmpLen = 0;
        int currentLen;
        for(String dna : store.data()){
            currentLen = dna.length();
            if(currentLen >= tmpLen){
                tmpLen = currentLen;
            }
        }
        return tmpLen;

    }

    public double gcRatio(String dna){
        int maxSize= dna.length();
        dna =dna.replaceAll("c","").replaceAll("g","");
        //System.out.println("m size "+maxSize+" /current "+dna.length()+" = "+(dna.length()*1.0)/(maxSize*1.0));
        return (maxSize*1.0-dna.length()*1.0)/(maxSize*1.0);

    }

    public Boolean isRatioGreaterThan(String dna, double ratio){
        return this.gcRatio(dna) > ratio;

    }

    public int findNbGcRatioAbove(StorageResource store, double ratio){
        int count = 0;
        for(String dna : store.data()){
            if(isRatioGreaterThan(dna, ratio))
                count +=1;
        }
        return count;

    }

    public  int findCTGCodon(String dna) {
        dna = dna.toLowerCase();
        int count = dna.length() - dna.replaceAll("ctg", "").length();
        System.out.println("DNA : "+dna+"  \n ctg: "+count/3);
        return count/3;
    }

    public int numberOfCTGCodon(StorageResource store){
        int count =  0;
        for(String dna : store.data()){
            count = count +  findCTGCodon( dna);
        }

        return count;

    }

    public void testing() {
        String a = "cccatggggtttaaataataataggagagagagagagagttt";
        String ap = "atggggtttaaataataatag";
        //String a = "atgcctag";
        //String ap = "";
        //String a = "ATGCCCTAG";
        //String ap = "ATGCCCTAG";
        String result = findProtein(a);
        if (ap.equals(result)) {
            System.out.println("success for " + ap + " length " + ap.length());
        }
        else {
            System.out.println("mistake for input: " + a);
            System.out.println("got: " + result);
            System.out.println("not: " + ap);
        }
    }

    public void realTesting() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String s = fr.asString().toLowerCase();
            System.out.println("");
            System.out.println("");
            System.out.println("*********NEW***********");
            System.out.println("read " + s.length() + " characters");
            StorageResource store = this.findGenes(s);
            System.out.println("size = " + store.size());
            System.out.println("Nb greater than 60 = " + this.findNbGreaterThan(store,60));
            System.out.println("Longest = " + this.findLongest(store));
            System.out.println("Nb gcRatio greater than 0.35= " + this.findNbGcRatioAbove(store,0.35));
            System.out.println("Nb CTG codon = " + this.findCTGCodon(s));
            
            System.out.println(mystery("AAATCCCTGGGTAAATCCCT"));
            
            System.out.println(findCTGCodon("cctgtgctga"));
            //String result = findProtein(s);
            //System.out.println("found " + result);
        }
    }
    
    
    public String mystery(String dna) {
  int pos = dna.indexOf("T");
  int count = 0;
  int startPos = 0;
  String newDna = "";
  if (pos == -1) {
    return dna;
  }
  while (count < 3) {
    count += 1;
    newDna = newDna + dna.substring(startPos,pos);
    startPos = pos+1;
    pos = dna.indexOf("T", startPos);
    if (pos == -1) {
      break;
    }
  }
  newDna = newDna + dna.substring(startPos);
  return newDna;
}
}
