/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;

import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author reggi
 */
public class Lemmatization {

    public Lemmatization() {
    }
    
    public String lemmatize(String text){
        String result = "";
        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma");
        
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation document = new Annotation(text);
        pipeline.annotate(document);
        
        List<CoreMap> sentenceList = document.get(SentencesAnnotation.class);
        for (CoreMap sentence : sentenceList){
            for (CoreLabel word : sentence.get(TokensAnnotation.class)){
                result = word.lemma();
//                System.out.println(result);
            }
        }
        return result;
    }
    
    public static void main(String[]args){
//        Lemmatization l = new Lemmatization();
//        System.out.println(l.lemmatize("walked"));
    }
}

