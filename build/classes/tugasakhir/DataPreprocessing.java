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
 * @author Tim 7
 */
public class DataPreprocessing {

    public void data_processing(String dataset_path) throws FileNotFoundException, IOException {
        long startTime = System.currentTimeMillis();
        //dynamic path directory
        File[] files = findFilesInDirectory(dataset_path);
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
            jumlahWord += count;
        }
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.printf("Time elapsed: %dms\n", elapsedTime);
        System.out.println();
        System.out.println("Jumlah words dari seluruh dokumen : " + jumlahWord);
        System.out.println("Jumlah rata-rata words per dokumen : " + jumlahWord / files.length);
    }
    
    public String data_processing_query(String query) {
        Normalization norm = new Normalization();
        StopWords sw = new StopWords();
        Lemmatization lemmatization = new Lemmatization();
        Porter porter = new Porter();

            String temp = query;
            temp = temp.toLowerCase(); //case folding
            temp = norm.replaceWord(temp); //normalization
            if (!sw.checkStopWord(temp) && temp.length() > 0) { //check stop word
                temp = lemmatization.lemmatize(temp); //lemmatization
                temp = porter.stem(temp); //porter stemming
            }
            return temp;
    }

//    method untuk membaca file document yang ada di sebuah folder
    public static File[] findFilesInDirectory(String directoryPath) {
        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles();
        System.out.println("Jumlah documents : " + listOfFiles.length);
        return listOfFiles;
    }
}

