package tugasakhir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
        
        //cek apakah cleaned dataset sudah ada atau belum
        //*jika sudah ada maka langsung create inverted index
        if (cleaned_dataset.isDirectory()) {
            if (folder_idx.isDirectory()) {
                System.out.println("Folder exist"); //jika inv idx belum ada
                invIndex.loadMaps();

            } else {
                System.out.println("Folder doesn't exist, creating the folder..."); //jika inv idx sudah ada
                invIndex.createInvertedIndex(findFilesInDirectory(System.getProperty("user.dir") + "\\cleaned_dataset\\"));
                invIndex.saveMaps();
                invIndex.loadMaps();
            }
        } 
        //*jika belum ada maka lakukan data preprocessing dahulu dari raw dataset
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
