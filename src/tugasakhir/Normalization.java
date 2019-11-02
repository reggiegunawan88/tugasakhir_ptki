/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;

/**
 *
 * @author reggi
 */
public class Normalization {
    public Normalization(){
    }
    
    public String replaceWord(String word){
        return word.replaceAll("[\\p{Punct}&&[^-]]+", "");
    }
}
