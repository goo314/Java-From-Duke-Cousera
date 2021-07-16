/**
 * Find all possible genes in given DNA
 * 
 * @author goo314
 * @version Jul 3, 2021
 */

import edu.duke.*;

public class AllCodons {
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon, startIndex+3);
        while(currIndex != -1){
            int diff = currIndex - startIndex;
            if(diff%3 == 0){
                return currIndex;
            }
            else{
                currIndex = dnaStr.indexOf(stopCodon, currIndex+1);
            }
        }
        return -1;
    }
    
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1){
            return"";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        //int minIndex = Math.min(taaIndex, tagIndex);
        //minIndex = Math.min(minIndex, tgaIndex);
        //minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        int minIndex = 0;
        if(taaIndex == -1 ||
            (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;
        }
        else{
            minIndex = taaIndex;
        }
        if(minIndex == -1 ||
            (tagIndex != -1 && tagIndex < minIndex)){
                minIndex = tagIndex;
        }
        
        if(minIndex == -1){
            return"";
        }
        
        /*
        if(minIndex == dna.length()){
            return"";
        }
        */
       
        return dna.substring(startIndex, minIndex+3);        
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        while( true ){
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()){
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        
    }
    
    public void testFindStop(){
        //            01234567890123456789012345
        String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        int dex = findStopCodon(dna, 0, "TAA");
        System.out.println(dex);        
    }
    
    public void testOn(String dna){
        System.out.println("Testing printAllGenes on " + dna);
        printAllGenes(dna);
    }
    
    public void test(){
        //      ATGv  TAA   ATGv        TGA  
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
        //      ATGv  v  v  v  TAA          ATGTAA
        testOn("ATGATCATAAGAAGATAATAGGAGGGCCATGTAA");
    }
}
