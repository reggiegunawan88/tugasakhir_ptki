package tugasakhir;

import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author reggi
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String path_idx = System.getProperty("user.dir") + "\\inv_idx\\";
        File folder_idx = new File(path_idx);
        File[] contents = folder_idx.listFiles();
        if (contents.length == 0) {
            System.out.println("false"); //jika inv idx belum ada
            InvertedIndex invIndex = new InvertedIndex();
            invIndex.createInvertedIndex(findFilesInDirectory(System.getProperty("user.dir") + "\\cleaned_dataset\\"));
            invIndex.saveMaps();
            invIndex.loadMaps();
        } else {
            System.out.println("true"); //jika inv idx sudah ada
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

