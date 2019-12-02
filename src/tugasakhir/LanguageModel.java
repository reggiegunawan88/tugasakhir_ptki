/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author tim 7
 */
public class LanguageModel {

    private static double lamda = 0.25;
//    private static Map<String, Map<String, Integer>> invertedIndex;
    private static Map<String, Map<String, Integer>> invertedIndex = new TreeMap<String, Map<String, Integer>>();
    private static InvertedIndex invIndex;

    public LanguageModel() {
        invIndex = new InvertedIndex();
        invIndex.loadMaps();
        invertedIndex = invIndex.getInvIndex();
    }

//    public Map<String, Double> getResult(ArrayList namaDoc, ArrayList words) throws IOException {
    public static Map<Double, String> getResult(ArrayList namaDoc, ArrayList words, Map<String, Map<String, Integer>> invertedIndex) throws IOException {

        String dataset_path = System.getProperty("user.dir") + "\\cleaned_dataset\\";
        File folder = new File(dataset_path);
        File[] listTxt = folder.listFiles();
//        for (int i = 0; i < listTxt.length; i++) {
//            File file = listTxt[i];
//            if (file.isFile() && file.getName().endsWith(".txt")) {
//                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(listTxt[i].getPath())));
//                String content = br.readLine();
//                List<String> listWords = Arrays.asList(content.split(" "));
//                System.out.println(content);
//            }
//        }

        ArrayList<Double> hasil = new ArrayList<>();
        
        Map<String, Integer> jumlahTotalWord = new HashMap<>();
        int jumlahWordSemuaDoc = 0;
//        hitung banyaknya word di doc
        for (int i = 0; i < namaDoc.size(); i++) {
            String doc = (String) namaDoc.get(i);
            int noDoc = Integer.valueOf(doc.substring(3));
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(listTxt[noDoc-1].getPath())));
            String text = br.readLine();
            String[] arrayWords = text.split(" ");
            int length = arrayWords.length;
            jumlahWordSemuaDoc += length;
            jumlahTotalWord.put(doc, length);
        }
        
        Map<String, Integer> jumlahWord = new HashMap<>();
//        hitung jumlah kata di seluruh doc
        for (int i = 0; i < words.size(); i++) {
            String word = (String)words.get(i);
            Map<String, Integer> jumlahWordInOneDoc = invertedIndex.get(word);
            int jumlah = 0;
            for (int j = 0; j < namaDoc.size(); j++) {
                String doc = (String)namaDoc.get(j);
                if (jumlahWordInOneDoc.containsKey(doc))
                {
                    jumlah += jumlahWordInOneDoc.get(doc);
                }
            }
            jumlahWord.put(word, jumlah);
        }
        
        
        Map<Double, String> nilaiOneDoc = new TreeMap<Double, String>();
        for (int i = 0; i < namaDoc.size(); i++) {
            String doc = (String) namaDoc.get(i);
            double res = 0;
            double jumlah = 0;
            for (int j = 0; j < words.size(); j++) {
                String word = (String) words.get(j);
                int jumlahWordInOneDoc = 0;
                if (invertedIndex.get(word).get(doc) != null)
                {
                    jumlahWordInOneDoc = invertedIndex.get(word).get(doc);
                }
                
                int totalWordInDoc = jumlahTotalWord.get(doc);
                res = ((jumlahWordInOneDoc*1.0/totalWordInDoc*1.0) + (jumlahWord.get(word)*1.0/jumlahWordSemuaDoc*1.0))*lamda*1.0;
                
                if (jumlah == 0)
                {
                    jumlah = res;
                }
                else
                {
                    jumlah = jumlah * res;
                }
            }
            nilaiOneDoc.put(jumlah, doc);
        }
        return nilaiOneDoc;
    }

    public static File[] findFilesInDirectory(String directoryPath) {
        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles();
        System.out.println("Jumlah documents : " + listOfFiles.length);
        return listOfFiles;
    }

    public static void main(String[] args) throws IOException {
        LanguageModel lm = new LanguageModel();
        Map<String, Map<String, Integer>> invertedIndex = new TreeMap<String, Map<String, Integer>>();
        InvertedIndex invIndex = new InvertedIndex();
        invIndex.loadMaps();
        invertedIndex = invIndex.getInvIndex();

        ArrayList namaDoc = new ArrayList();
        namaDoc.add("Doc001");
        namaDoc.add("Doc005");
        namaDoc.add("Doc016");
        namaDoc.add("Doc021");
        namaDoc.add("Doc065");
        namaDoc.add("Doc068");
        namaDoc.add("Doc069");
        namaDoc.add("Doc098");
        namaDoc.add("Doc099");
        namaDoc.add("Doc124");

        ArrayList words = new ArrayList();
        words.add("flower");
        words.add("famin");
        words.add("barren");

        Map<Double, String> res = getResult(namaDoc, words, invertedIndex);
        
        for (Map.Entry entry : res.entrySet()) {
            System.out.println("key: " + entry.getKey() + "; value: " + entry.getValue());
        }
    }
}

