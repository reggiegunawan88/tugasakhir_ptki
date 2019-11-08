package tugasakhir;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Dell
 */
public class DataPreprocessing {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //dynamic path directory
        String dir = System.getProperty("user.dir") + "\\dataset\\";
        File[] files = findFilesInDirectory(dir);
        Map<String, ArrayList> invertedIndex = new TreeMap<String, ArrayList>();
        int jumlahWord = 0;
        Normalization norm = new Normalization();
        StopWords sw = new StopWords();
        Lemmatization lemmatization = new Lemmatization();
        Porter porter = new Porter();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < files.length; i++) {
            System.out.println("===========================");
            System.out.println("Nama File : " + files[i]);
            String namaDoc = files[i].getName();
            int titik = namaDoc.indexOf(".");
            namaDoc = namaDoc.substring(0, titik);
            System.out.println("Nama Doc : " + namaDoc);
            Scanner input = new Scanner(files[i]);
            int count = 0;
            String text = "";
            
            while(input.hasNext()){
                String word = input.next();
                word = word.toLowerCase(); //case folding
                word = norm.replaceWord(word); //normalization
                if(!sw.checkStopWord(word) && word.length() > 0){ //check stop word
                    word = lemmatization.lemmatize(word); //lemmatization
                    word = porter.stem(word); //porter stemming
                    if (text.length() > 0){
                        text += " ";
                    }
                    text += word;
                }
                count++;
            }
//            for (int j = 0; j < token.size(); j++) {
//                String kata = porter.stem(token.get(j)); //porter stemming
//                text += kata + " ";
//            }
            
//            for (int j = 0; j < word.length; j++) {
//                if (!sw.checkStopWord(word[j])) {
//                    text += word[j] + " "; 
//                }
//            }
            
//                    if (word.length() > 0) {
//                        ArrayList<String> valueWord = invertedIndex.get(word);
//                        
//                        //get dari map apakah word sudah ada atau belum
//                        //jika sudah ada, maka tambahkan, jika tidak, maka buat map baru
//                        if (valueWord != null) {
//                            if (!valueWord.contains(namaDoc)) {
//                                valueWord.add(namaDoc);
//                                invertedIndex.put(word, valueWord);
//                            }
//                        } else {
//                            ArrayList<String> postingList = new ArrayList<String>();
//                            postingList.add(namaDoc);
//                            invertedIndex.put(word, postingList);
//                        }
//                    }
//                }
//            }
            
            System.out.println(text);
//          System.out.println("Word count: " + panjang1.length);
            jumlahWord += count;
        }

//        invertedIndex.forEach((key, value) -> System.out.println(key + ":" + value));

        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.printf("Time elapsed: %dms\n", elapsedTime);
        
        System.out.println();
        System.out.println("Jumlah words dari seluruh dokumen : " + jumlahWord);
        System.out.println("Jumlah rata-rata words per dokumen : " + jumlahWord / files.length);
    }

//    method untuk membaca file document yang ada di sebuah folder
    public static File[] findFilesInDirectory(String directoryPath) {
        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles();
        System.out.println("Jumlah documents : " + listOfFiles.length);
        return listOfFiles;
    }
}






















