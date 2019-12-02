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
public class CalculateRelevance {
    private double precision, recall;
    
    public CalculateRelevance(){
        this.precision = 0;
        this.recall = 0;
    }
    
    public double calc_Precision(int TP, int FP){
        precision = (double)TP/(TP+FP);
        return precision;
    }
    
    public double calc_Recall(int TP, int FN){
        recall = (double)TP/(TP+FN);
        return recall;
    }
    
    //method ini dipanggil setelah calcPrecision dan calcRecall dipanggil
    public double findF1(){
        return (2*precision*this.recall) / (precision + recall);
    }
}