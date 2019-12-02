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
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author hp
 */
public class BM25 {
    private int n;
    private double k1;
    private double b;
    private ArrayList namaDoc;
    private Map<String, Integer> ld;
    private int jumlahWordSemuaDoc;
    private double Lave;
    private ArrayList words;
    private Map<String, Map<String, Integer>> invertedIndex;
    
    public BM25 (Map<String, Map<String, Integer>> invertedIndex)
    {
        this.k1 = 1.2;
        this.b = 0.75;
        this.jumlahWordSemuaDoc = 0;
        this.Lave = 0;
        this.invertedIndex = invertedIndex;
    }
    
    public void getLd () throws IOException
    {
        String dataset_path = System.getProperty("user.dir") + "\\cleaned_dataset\\";
        File folder = new File(dataset_path);
        File[] listTxt = folder.listFiles();
//        Map<String, Integer> jumlahTotalWord = new HashMap<>();
//        int jumlahWordSemuaDoc = 0;
//        hitung banyaknya word di doc
        for (int i = 0; i < namaDoc.size(); i++) {
            String doc = (String) namaDoc.get(i);
            int noDoc = Integer.valueOf(doc.substring(3));
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(listTxt[noDoc-1].getPath())));
            String text = br.readLine();
            String[] arrayWords = text.split(" ");
            int length = arrayWords.length;
            jumlahWordSemuaDoc += length;
            ld.put(doc, length);
        }
        
        this.Lave = this.jumlahWordSemuaDoc*1.0/ld.size()*1.0;
    }
    
    public Map<String, Double> getBM25 (ArrayList namaDoc, ArrayList words)
    {
        this.n = namaDoc.size();
        this.namaDoc = namaDoc;
        this.words = words;
        
        Map<String, Double> res = new TreeMap<String, Double>();
//        Map<String, Integer> df = df(words, namaDoc);
        
        for (int i = 0; i < namaDoc.size(); i++) {
            double hasilDoc = 0;
            for (int j = 0; j < words.size(); j++) {
                double hasil = Math.log10(n/1);
            }
        }
        
//        for (Map.Entry entry : invIndexLoad.entrySet()) {
//            System.out.println("key: " + entry.getKey() + "; value: " + entry.getValue());
//        }
        
        return res;
    }
    
//    public Map<String, Integer> df (ArrayList<String> wordQuery, ArrayList<String> namaDoc)
//    {
//        Map<String, Integer> df = new HashMap<String, Integer> ();
//        for (int i = 0; i < wordQuery.size(); i++) {
//            
//            String word = wordQuery.get(i);
//            Map<String, Integer> tfDoc = this.invertedIndex.get(word);
//            for (int i = 0; i < namaDoc.size(); i++) {
//                String namaDocNow = namaDoc.get(i);
//                if (tfDoc.containsKey(namaDocNow)) {
//                    jumlahDF += tfDoc.get(namaDocNow);
//                }
//            }
//            df.put(word, jumlahDF);
//        }
//        return df;
//    }
}



