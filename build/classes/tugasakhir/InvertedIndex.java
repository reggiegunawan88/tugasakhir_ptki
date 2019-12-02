/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
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

//    private static Map<String, List> invertedIndex = new TreeMap<String, List>();
    private static Map<String, Map<String, Integer>> invertedIndex = new TreeMap<String, Map<String, Integer>>();


    public InvertedIndex() {
    }

    public static void createInvertedIndex(File[] filesCleaned) throws FileNotFoundException, IOException {
        for (int i = 0; i < filesCleaned.length; i++) {
            Scanner input = new Scanner(filesCleaned[i]);
            String namaDoc = filesCleaned[i].getName();
            int titik = namaDoc.indexOf(".");
            namaDoc = namaDoc.substring(0, titik);
//            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filesCleaned[i].getPath())));
//            String text = br.readLine();
//            List<String> isiDoc = Arrays.asList(text.split(" "));
//            TFIDF tfIdf = new TFIDF();
            while (input.hasNext()) {
                String word = input.next();
                if (!word.equalsIgnoreCase("'")) {
//                    List<String> valueWord = invertedIndex.get(word);
                    Map<String, Integer> valueWord = invertedIndex.get(word);

                    //get dari map apakah word sudah ada atau belum jika sudah ada, maka tambahkan, jika tidak, maka buat map baru
                    if (valueWord != null) {
                        if (!valueWord.containsKey(namaDoc)) {
//                            valueWord.add(namaDoc);
//                            double tf = tfIdf.tf(isiDoc, word);
                            valueWord.put(namaDoc, 1);
                            invertedIndex.put(word, valueWord);
                        }
                        else{
                            int tf = valueWord.get(namaDoc) + 1;
                            valueWord.put(namaDoc, tf);
                            invertedIndex.put(word, valueWord);
                        }
                    } else {
//                        ArrayList<String> postingList = new ArrayList<String>();
//                        postingList.add(namaDoc);
                        Map<String, Integer> postingList = new TreeMap<String, Integer>();
//                        double tf = tfIdf.tf(isiDoc, word);
                        postingList.put(namaDoc, 1);
                        invertedIndex.put(word, postingList);
                    }
                }
            }
            
            
        }
    }

    public void saveMaps() {
        String folder_path = System.getProperty("user.dir") + "\\inv_idx\\";
        File file = new File(folder_path);
        file.mkdirs();
        String file_path = System.getProperty("user.dir") + "\\inv_idx\\inv_idx.list";
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file_path));
            oos.writeObject(this.getInvIndex());
            oos.close();
        } catch (Exception e) {
            // Catch exceptions
        }
    }

    public void loadMaps() {
        String path = System.getProperty("user.dir") + "\\inv_idx\\inv_idx.list";
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            Object readMap = ois.readObject();
            if (readMap != null && readMap instanceof TreeMap) {
                this.invertedIndex.putAll((TreeMap) readMap);
            }
            ois.close();
        } catch (Exception e) {
            // Catch exceptions
        }

//        for (Map.Entry entry : invIndexLoad.entrySet()) {
//            System.out.println("key: " + entry.getKey() + "; value: " + entry.getValue());
//        }
    }

    public Map<String, Map<String, Integer>> getInvIndex() {
        return this.invertedIndex;
    }

    public static File[] findFilesInDirectory(String directoryPath) {
        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles();
        System.out.println("Jumlah documents : " + listOfFiles.length);
        return listOfFiles;
    }
}

