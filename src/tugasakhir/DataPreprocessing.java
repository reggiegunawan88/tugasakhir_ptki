package tugasakhir;

import tugasakhir.processing_method.Normalization;
import tugasakhir.processing_method.Porter;
import tugasakhir.processing_method.StopWords;
import tugasakhir.processing_method.Lemmatization;
import tugasakhir.processing_method.WriteToFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class DataPreprocessing {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        long startTime = System.currentTimeMillis();
        //dynamic path directory
        String dir = System.getProperty("user.dir") + "\\dataset\\";
        File[] files = findFilesInDirectory(dir);
        int jumlahWord = 0;
        Normalization norm = new Normalization();
        StopWords sw = new StopWords();
        Lemmatization lemmatization = new Lemmatization();
        Porter porter = new Porter();
        WriteToFile write = new WriteToFile();

        for (int i = 0; i < files.length; i++) {
            System.out.println("===========================");
            System.out.println("Nama File : " + files[i]);
            String namaDoc = files[i].getName();
            System.out.println("Nama Doc : " + namaDoc);
            Scanner input = new Scanner(files[i]);
            int count = 0;
            String text = "";

            while (input.hasNext()) {
                String word = input.next();
                word = word.toLowerCase(); //case folding
                word = norm.replaceWord(word); //normalization
                if (!sw.checkStopWord(word) && word.length() > 0) { //check stop word
                    word = lemmatization.lemmatize(word); //lemmatization
                    word = porter.stem(word); //porter stemming
                    if (text.length() > 0) {
                        text += " ";
                    }
                    text += word;
                }
                count++;
            }
            write.writeUsingFileWriter(namaDoc, text);
//            System.out.println(text);
            jumlahWord += count;
        }
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

