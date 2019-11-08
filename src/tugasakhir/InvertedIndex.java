/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author reggi
 */
public class InvertedIndex {

    private static Map<String, List> invertedIndex = new TreeMap<String, List>();

    public InvertedIndex() {
    }

    public static void createInvertedIndex(File[] filesCleaned) throws FileNotFoundException {
        for (int i = 0; i < filesCleaned.length; i++) {
            Scanner input = new Scanner(filesCleaned[i]);
            String namaDoc = filesCleaned[i].getName();
            int titik = namaDoc.indexOf(".");
            namaDoc = namaDoc.substring(0, titik);
            while (input.hasNext()) {
                String word = input.next();
                if (!word.equalsIgnoreCase("'")) {
                    List<String> valueWord = invertedIndex.get(word);
                    //get dari map apakah word sudah ada atau belum jika sudah ada, maka tambahkan, jika tidak, maka buat map baru
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
    }

    public void saveMaps() {
        String path = System.getProperty("user.dir") + "\\inv_idx\\inv_idx.list";

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(this.getInvIndex());
            oos.close();
        } catch (Exception e) {
            // Catch exceptions
        }
    }

    public void loadMaps() {
        String path = System.getProperty("user.dir") + "\\inv_idx\\inv_idx.list";
        Map<String, List> invIndexLoad = new TreeMap<String, List>();

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            Object readMap = ois.readObject();
            if (readMap != null && readMap instanceof TreeMap) {
                invIndexLoad.putAll((TreeMap) readMap);
            }
            ois.close();
        } catch (Exception e) {
            // Catch exceptions
        }

        for (Map.Entry entry : invIndexLoad.entrySet()) {
//            invIndexLoad.put(entry.getKey().toString(), (List)entry.getValue());
            System.out.println("key: " + entry.getKey() + "; value: " + entry.getValue());
        }
    }

//    public void savingMapIntoFile() throws FileNotFoundException, IOException {
//        Properties properties = new Properties();
//        String path = System.getProperty("user.dir") + "\\inv_idx\\";
//        File file = new File(path);
//        file.mkdirs();
//        PrintWriter writer = new PrintWriter(path + "invertedIndex.txt");
//        for (Map.Entry<String, List> entry : invertedIndex.entrySet()) {
//            writer.println(entry.getKey() + " " + entry.getValue());
//            properties.put(entry.getKey(), entry.getValue());
//            System.out.println(entry.getKey() + " => " + entry.getValue());
//        }
//        properties.store(new FileOutputStream("invertedIndex.properties"), null);
//        writer.close();
//    }

//    public static Map<String, ArrayList> loadMapFromFile() throws FileNotFoundException, IOException {
//        Map<String, ArrayList> invIndex = new HashMap<String, ArrayList>();
//        Properties properties = new Properties();
//        properties.load(new FileInputStream("invertedIndex.properties"));

//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("invertedIndex.properties"));
//        out.writeObject(invIndex);
//        out.close();
//
//        for (String key : properties.stringPropertyNames()) {
//            System.out.println(key + " => " + properties.get(key).getClass());
////            invIndex.put(key, (ArrayList)properties.get(key));
//        }
//        return invIndex;
//    }

    public Map<String, List> getInvIndex() {
        return this.invertedIndex;
    }

//    public static void main(String[] args) throws IOException {
//        InvertedIndex invIndex = new InvertedIndex();
//        createInvertedIndex(findFilesInDirectory(System.getProperty("user.dir") + "\\cleaned_dataset\\"));
//        invIndex.saveMaps();
//        invIndex.loadMaps();

//        Map<String, ArrayList> hasil = loadMapFromFile();
//
//        for (Map.Entry<String, ArrayList> entry : hasil.entrySet()) {
////            writer.println(entry.getKey() + ", " + entry.getValue());
////            properties.put(entry.getKey(), entry.getValue());
//            System.out.println(entry.getKey() + " => " + entry.getValue());
//        }
//    }

    public static File[] findFilesInDirectory(String directoryPath) {
        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles();
        System.out.println("Jumlah documents : " + listOfFiles.length);
        return listOfFiles;
    }
}


