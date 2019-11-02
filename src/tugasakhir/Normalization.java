package tugasakhir;

public class Normalization {
    public Normalization(){
    }
    
    public String replaceWord(String word){
        return word.replaceAll("[\\p{Punct}&&[^-]]+", "");
    }
}
