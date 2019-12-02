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
import static tugasakhir.LanguageModel.getResult;

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
    
    public BM25 (ArrayList namaDoc, ArrayList words, Map<String, Map<String, Integer>> invertedIndex)
    {
        this.n = namaDoc.size();
        this.k1 = 1.2;
        this.b = 0.75;
        this.namaDoc = namaDoc;
        this.jumlahWordSemuaDoc = 0;
        this.Lave = 0;
        this.words = words;
        this.invertedIndex = invertedIndex;
        this.ld = new HashMap<String, Integer> ();
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
    
    public Map<String, Double> getBM25 () throws IOException
    {
        getLd();
        Map<String, Double> res = new TreeMap<String, Double>();
        Map<String, Integer> df = df(words, namaDoc);
        
        for (int i = 0; i < namaDoc.size(); i++) {
            String namaDocNow = (String)namaDoc.get(i);
            double hasilDoc = 0;
            for (int j = 0; j < words.size(); j++) {
                String word = (String)words.get(j);
                int dft = df.get(word);
                double tftd = 0;
                if (this.invertedIndex.get(word).get(namaDocNow) != null)
                {
                    tftd = this.invertedIndex.get(word).get(namaDocNow);
                } 
                double hasil = Math.log10(n/dft) * (((k1+1)*tftd)/((k1 * ((1-b)+b *(ld.get(namaDocNow)/Lave)))+(tftd*1.0)));
                hasilDoc += hasil;
            }
            res.put(namaDocNow, hasilDoc);
        }        
        return res;
    }
    
    public Map<String, Integer> df (ArrayList<String> wordQuery, ArrayList<String> namaDoc)
    {
        Map<String, Integer> df = new HashMap<String, Integer> ();
        for (int i = 0; i < wordQuery.size(); i++) {
            String word = wordQuery.get(i);
            int jumlahDF = 0;
            Map<String, Integer> tfDoc = this.invertedIndex.get(word);
            for (int j = 0; j < namaDoc.size(); j++) {
                String namaDocNow = namaDoc.get(j);
                if (tfDoc.containsKey(namaDocNow)) {
                    jumlahDF += tfDoc.get(namaDocNow);
                }
            }
            df.put(word, jumlahDF);
        }
        return df;
    }
    
    public static void main (String[] args) throws IOException
    {
        
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
        
        BM25 bm = new BM25(namaDoc, words, invertedIndex);
        Map<String, Double> res = bm.getBM25();
        
        for (Map.Entry entry : res.entrySet()) {
            System.out.println("key: " + entry.getKey() + "; value: " + entry.getValue());
        }
    }
}





