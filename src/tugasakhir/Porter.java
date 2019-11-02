package tugasakhir;
import org.tartarus.snowball.ext.PorterStemmer;

public class Porter {

    public Porter() {
    }

    //Porter Stemming method using Snowball library
    public String stem(String word) {
        PorterStemmer stemmer = new PorterStemmer();
        stemmer.setCurrent(word);
        stemmer.stem();
        return stemmer.getCurrent();
    }
    
//    public static void main(String[]args){
//        PorterStemmer stemmer = new PorterStemmer();
//        stemmer.setCurrent("alumnae");
//        stemmer.stem();
//        System.out.println(stemmer.getCurrent());
//    }
}
