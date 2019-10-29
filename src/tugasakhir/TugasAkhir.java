/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Dell
 */
public class TugasAkhir {
    
    private static ArrayList<String> stopWordList;

    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        //path dataset harus dibuild sendiri berdasarkan komputer/laptop masing-masing
        
//        Billy
//        String path = "D:\\Kuliah\\PTKI\\Data_Tugas_Akhir\\";
        
//        Winnie
//        String path = "F:/kuliah/Sem 7/PTKI/Data_Tugas_Akhir/";
        
//        Reggie
        String path = "C:\\Users\\reggi\\Documents\\INFORMATIKA 16 UNPAR\\Semester7\\PTKI\\Proyek Akhir\\tugasakhir_ptki\\dataset\\";
        
//        add arrayList stop word
        
        
        File[] files = findFilesInDirectory(path);
        Map<String, ArrayList> invertedIndex = new TreeMap<String, ArrayList> ();
        int jumlahWord = 0;
        ArrayList<String> stopWord = addStopWord();
        Lemmatization lemmatization = new Lemmatization();
        
        for (int i = 0; i < files.length; i++) {
            
            System.out.println("===========================");
            System.out.println("nama file : " + files[i]);
            String namaDoc = files[i].getName();
            int titik = namaDoc.indexOf(".");
            namaDoc = namaDoc.substring(0, titik);
            System.out.println("nama doc : " + namaDoc);

            Scanner input = new Scanner(files[i]); 
            int count = 0;
            while (input.hasNext()) 
            {
                String word = input.next();
                
//                case folding
                word = word.toLowerCase(); 
                
//                normalization
                word = word.replaceAll("[\\p{Punct}&&[^-]]+", "");
                count = count + 1;
                
//                get dari map apakah word sudah ada atau belum
//                jika sudah ada, maka tambahkan, jika tidak, maka buat map baru
                if (!stopWord.contains(word))
                {
                    //lemmatization
                    word = lemmatization.lemmatize(word);
                    if (word.length() > 0)
                    {
                        ArrayList<String> valueWord = invertedIndex.get(word);
                        if (valueWord != null)
                        {
                            if (!valueWord.contains(namaDoc))
                            {
                                valueWord.add(namaDoc);
                                invertedIndex.put(word, valueWord);
                            }
                        }
                        else
                        {
                            ArrayList<String> postingList = new ArrayList<String>();
                            postingList.add(namaDoc);
                            invertedIndex.put(word, postingList);
                        }
                    }
                }
            }
            System.out.println("Word count: " + count);
            jumlahWord += count;
        }
        
        invertedIndex.forEach((key, value) -> System.out.println(key + ":" + value));
        
        System.out.println();
        System.out.println("Jumlah words dari seluruh dokumen : " + jumlahWord);
        System.out.println("Jumlah rata-rata words per dokumen : " + jumlahWord/files.length);
    }
    
//    method untuk membaca file document yang ada
    public static File[] findFilesInDirectory(String directoryPath) {

        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles();

        System.out.println("Jumlah documents : " + listOfFiles.length);
        return listOfFiles;
    } 
    
    public static ArrayList<String> addStopWord () 
    {
        String[] sw = new String[] {"a", "about", "above", "above", "across", "after", "afterwards", "again", "against", "all", "almost", "alone", "along", "already", "also","although",
        "always","am","among", "amongst", "amoungst", "amount",  "an", "and", "another", "any","anyhow","anyone","anything","anyway", "anywhere", "are", "around", "as",  "at", "back","be",
        "became", "because","become","becomes", "becoming", "been", "before", "beforehand", "behind", "being", "below", "beside", "besides", "between", "beyond", "bill", "both", "bottom",
        "but", "by", "call", "can", "cannot", "cant", "co", "con", "could", "couldnt", "cry", "de", "describe", "detail", "do", "done", "down", "due", "during", "each", "eg", "eight", "either",
        "eleven","else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every", "everyone", "everything", "everywhere", "except", "few", "fifteen", "fify", "fill", "find", "fire", "first",
        "five", "for", "former", "formerly", "forty", "found", "four", "from", "front", "full", "further", "get", "give", "go", "had", "has", "hasnt", "have", "he", "hence", "her", "here", "hereafter", 
        "hereby", "herein", "hereupon", "hers", "herself", "him", "himself", "his", "how", "however", "hundred", "ie", "if", "in", "inc", "indeed", "interest", "into", "is", "it", "its", "itself", "keep", 
        "last", "latter", "latterly", "least", "less", "ltd", "made", "many", "may", "me", "meanwhile", "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much", "must", "my", "myself", 
        "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none", "noone", "nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one", "only", 
        "onto", "or", "other", "others", "otherwise", "our", "ours", "ourselves", "out", "over", "own","part", "per", "perhaps", "please", "put", "rather", "re", "same", "see", "seem", "seemed", "seeming", 
        "seems", "serious", "several", "she", "should", "show", "side", "since", "sincere", "six", "sixty", "so", "some", "somehow", "someone", "something", "sometime", "sometimes", "somewhere", "still", 
        "such", "system", "take", "ten", "than", "that", "the", "their", "them", "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein", "thereupon", "these", "they", 
        "thick", "thin", "third", "this", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "top", "toward", "towards", "twelve", "twenty", "two", "un", "under",
        "until", "up", "upon", "us", "very", "via", "was", "we", "well", "were", "what", "whatever", "when", "whence", "whenever", "where", "whereafter", "whereas", "whereby", "wherein", "whereupon", 
        "wherever", "whether", "which", "while", "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would", "yet", "you", "your", "yours", "yourself", 
        "yourselves", "the"};
        
        ArrayList<String> stopWord = new ArrayList<String>(Arrays.asList(sw));
        return stopWord;
    }
}

