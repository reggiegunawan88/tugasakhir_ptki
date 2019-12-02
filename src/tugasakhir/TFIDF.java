/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hp
 */
public class TFIDF {
    private Map<String, Map<String, Integer>> invertedIndex;
    
    public TFIDF(Map<String, Map<String, Integer>> invertedIndex)
    {
        this.invertedIndex = invertedIndex;
    }
    
    public Map<String, Integer> df (Map<String, Integer> tfQuery, ArrayList<String> namaDoc)
    {
        Map<String, Integer> df = new HashMap<String, Integer> ();
        for (Map.Entry entry : tfQuery.entrySet()) {
            String word = (String)entry.getKey();
            int jumlahDF = (Integer)entry.getValue();
            Map<String, Integer> tfDoc = this.invertedIndex.get(word);
            for (int i = 0; i < namaDoc.size(); i++) {
                String namaDocNow = namaDoc.get(i);
                if (tfDoc.containsKey(namaDocNow)) {
                    jumlahDF += tfDoc.get(namaDocNow);
                }
            }
            df.put(word, jumlahDF);
        }
        return df;
    }
    
    public Map<String, Double> idf (Map<String, Integer> tfQuery, ArrayList<String> namaDoc)
    {
        Map<String, Double> idf = new HashMap<String, Double>();
        int jumlahDoc = namaDoc.size() + 1;
        Map<String, Integer> df = df(tfQuery, namaDoc);
        for (Map.Entry entry : df.entrySet()) {
            int jumlahDFWord = (Integer)entry.getValue();
            double idfWord = Math.log10(jumlahDoc/jumlahDFWord);
            idf.put((String)entry.getKey(), idfWord);
        }
        return idf;
    }
    
//    public Map<String, Double> tfIdf (Map<String, Integer> tfQuery, ArrayList<String> namaDoc)
//    {
        //cari nilai tf-nya
//        Map<String, Integer> tfDoc = this.invertedIndex.get(word);
//    }
    
//    public double idf(List<List<String>> docs, String term) {
//        double n = 0;
//        for (List<String> doc : docs) {
//            for (String word : doc) {
//                if (term.equalsIgnoreCase(word)) {
//                    n++;
//                    break;
//                }
//            }
//        }
//        return Math.log(docs.size() / n);
//    }
//    
//    public double tfIdf(List<String> doc, List<List<String>> docs, String term) {
//        return tf(doc, term) * idf(docs, term);
//    }
}


