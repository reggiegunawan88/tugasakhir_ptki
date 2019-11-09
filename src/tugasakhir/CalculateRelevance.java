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
    private int TP,FP,FN;
    
    public CalculateRelevance(int TP, int FP, int FN){
        this.TP=TP;
        this.FP=FP;
        this.FN=FN;
        this.precision = 0;
        this.recall = 0;
    }
    
    public void calc_Precision(){
        this.precision = TP/(TP+FP);
    }
    
    public void calc_Recall(){
        this.recall = TP/(TP+FN);
    }
    
    public double getPrecision(){
        return this.precision;
    }
    
    public double getRecall(){
        return this.recall;
    }
    
    //method ini dipanggil setelah calcPrecision dan calcRecall dipanggil
    public double findF1(){
        return (2*this.getPrecision()*this.getRecall()) / (this.getPrecision() + this.getRecall());
    }
}