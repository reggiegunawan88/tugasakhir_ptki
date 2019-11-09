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
    private double precision, recall, f1;
    private int x,y,z;
    
    //@param:
    //x : relevant items retrieved
    //y : retrieved items
    //z : relevant items
    public CalculateRelevance(int x, int y, int z){
        this.x=x;
        this.y=y;
        this.z=z;
        this.precision = 0;
        this.recall = 0;
        this.f1 = 0;
    }
    
    public void calc_Precision(){
        this.precision = x/y;
    }
    
    public void calc_Recall(){
        this.recall = x/z;
    }
    
    public double getPrecision(){
        return this.precision;
    }
    
    public double getRecall(){
        return this.recall;
    }
    
    //method ini dipanggil setelah calcPrecision dan calcRecall dipanggil
    public double findF1(){
        return (2*this.getPrecision()*this.getRecall()) / this.getPrecision() + this.getRecall();
    }
}
