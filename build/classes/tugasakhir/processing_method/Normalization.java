package tugasakhir.processing_method;

public class Normalization {
    public Normalization(){
    }
    
    public String replaceWord(String word){
        return word.replaceAll("[\\p{Punct}&&[^-]]+", "");
    }
}
