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
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        //path dataset harus dibuild sendiri berdasarkan komputer/laptop masing-masing

//        Billy
//        String path = "D:\\Kuliah\\PTKI\\Data_Tugas_Akhir\\";
//        Winnie
//        String path = "F:/kuliah/Sem 7/PTKI/Data_Tugas_Akhir/";
//        Reggie
        String path = "C:\\Users\\reggi\\Documents\\INFORMATIKA 16 UNPAR\\Semester7\\PTKI\\Proyek Akhir\\tugasakhir_ptki\\dataset\\";
        File[] files = findFilesInDirectory(path);
        Map<String, ArrayList> invertedIndex = new TreeMap<String, ArrayList>();
        int jumlahWord = 0;
        Normalization norm = new Normalization();
        StopWords sw = new StopWords();
        Lemmatization lemmatization = new Lemmatization();

        for (int i = 0; i < files.length; i++) {
            System.out.println("===========================");
            System.out.println("Nama File : " + files[i]);
            String namaDoc = files[i].getName();
            int titik = namaDoc.indexOf(".");
            namaDoc = namaDoc.substring(0, titik);
            System.out.println("Nama Doc : " + namaDoc);
            
            Scanner input = new Scanner(files[i]);
            int count = 0;
            while (input.hasNext()) {
                String word = input.next();

                //case folding
                word = word.toLowerCase();

                //normalization
                word = norm.replaceWord(word);
                count = count + 1;

//                get dari map apakah word sudah ada atau belum
//                jika sudah ada, maka tambahkan, jika tidak, maka buat map baru
                if (!sw.checkStopWord(word)) {
                    //lemmatization
                    word = lemmatization.lemmatize(word);
                    if (word.length() > 0) {
                        ArrayList<String> valueWord = invertedIndex.get(word);
                        if (valueWord != null) {
                            if (!valueWord.contains(namaDoc)) {
                                valueWord.add(namaDoc);
                                invertedIndex.put(word, valueWord);
                            }
                        } else {
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
        System.out.println("Jumlah rata-rata words per dokumen : " + jumlahWord / files.length);
    }

//    method untuk membaca file document yang ada
    public static File[] findFilesInDirectory(String directoryPath) {

        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles();

        System.out.println("Jumlah documents : " + listOfFiles.length);
        return listOfFiles;
    }
}
