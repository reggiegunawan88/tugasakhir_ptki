/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;

import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class DataDoc {

    private String namaDoc;
    private double tf;
    private ArrayList posisiWord;

    public DataDoc(String namaDoc, int posisiWord) {
        this.namaDoc = namaDoc;
        this.posisiWord.add(posisiWord);
    }

    public void addPosisiWord(int posisiWord) {
        this.posisiWord.add(posisiWord);
    }

    public int getJumlahWord() {
        return this.posisiWord.size();
    }

    public String getNamaDoc() {
        return namaDoc;
    }

    public void setNamaDoc(String namaDoc) {
        this.namaDoc = namaDoc;
    }

    public double getTf() {
        return tf;
    }

    public void setTf(double tf) {
        this.tf = tf;
    }

    public ArrayList getPosisiWord() {
        return posisiWord;
    }

    public void setPosisiWord(ArrayList posisiWord) {
        this.posisiWord = posisiWord;
    }
}

