package tugasakhir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author reggi
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String dataset_path = System.getProperty("user.dir") + "\\dataset\\";
        String path_idx = System.getProperty("user.dir") + "\\inv_idx\\";
        File folder_idx = new File(path_idx);
        File cleaned_dataset = new File(System.getProperty("user.dir") + "\\cleaned_dataset\\");
        DataPreprocessing dp = new DataPreprocessing();
        InvertedIndex invIndex = new InvertedIndex();
        Map<String, List> invertedIndex = new TreeMap<String, List>();

        //cek apakah cleaned dataset sudah ada atau belum
        //*jika sudah ada maka langsung create inverted index
        if (cleaned_dataset.isDirectory()) {
            if (folder_idx.isDirectory()) {
                System.out.println("Inverted Index found"); //jika inv idx belum ada
                invIndex.loadMaps();
                invertedIndex = invIndex.getInvIndex();

            } else {
                System.out.println("Inverted Index not found, creating new.."); //jika inv idx sudah ada
                invIndex.createInvertedIndex(findFilesInDirectory(System.getProperty("user.dir") + "\\cleaned_dataset\\"));
                invIndex.saveMaps();
                invIndex.loadMaps();
                invertedIndex = invIndex.getInvIndex();
            }

            Scanner sc = new Scanner(System.in);
            System.out.print("Masukan kueri pertama:");
            String term1 = sc.next();
            System.out.print("Masukan kueri kedua:");
            String term2 = sc.next();
            System.out.print("ketik 0 untuk operasi AND dan ketik 1 untuk operasi OR:");
            int opt = sc.nextInt();
            System.out.println();
            if (opt == 0) {
                List<String> docContainer1 = invertedIndex.get(term1);
                List<String> docContainer2 = invertedIndex.get(term2);

                if (docContainer1.size() > docContainer2.size()) {
                    int idxKata1 = 0;
                    int idxKata2 = 0;

                    while (idxKata1 < docContainer1.size() && idxKata2 < docContainer2.size()) {
                        if (docContainer2.get(idxKata2).compareTo(docContainer1.get(idxKata1)) < 0) {
                            idxKata2++;
                        } else if (docContainer2.get(idxKata2).compareTo(docContainer1.get(idxKata1)) == 0) {
                            System.out.println(docContainer2.get(idxKata2));
                            idxKata2++;
                            idxKata1++;

                        } else {
                            idxKata1++;
                        }
                    }
                }
            } else if (opt == 1) {
                List<String> docContainer1 = invertedIndex.get(term1);
                List<String> docContainer2 = invertedIndex.get(term2);
                System.out.println("Kueri ditemukan pada dokumen:");
                if (docContainer1.size() > 0) {
                    for (int i = 0; i < docContainer1.size(); i++) {
                        System.out.println(docContainer1.get(i));
                    }
                }
                if (docContainer2.size() > 0) {
                    for (int i = 0; i < docContainer2.size(); i++) {
                        System.out.println(docContainer2.get(i));
                    }
                }
            }

        } //*jika belum ada maka lakukan data preprocessing dahulu dari raw dataset
        else {
            dp.data_processing(dataset_path); //lakukan data preprocessing
            invIndex.createInvertedIndex(findFilesInDirectory(System.getProperty("user.dir") + "\\cleaned_dataset\\")); //bikin inv index
            invIndex.saveMaps();
            invIndex.loadMaps();
        }
    }

    //    method untuk membaca file document yang ada di sebuah folder
    public static File[] findFilesInDirectory(String directoryPath) {
        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles();
        System.out.println("Jumlah documents : " + listOfFiles.length);
        return listOfFiles;
    }
}
