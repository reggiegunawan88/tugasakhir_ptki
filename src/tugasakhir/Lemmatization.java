/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author reggie
 */
public class Lemmatization {

    protected StanfordCoreNLP pipeline;

    public Lemmatization() {
        // Create StanfordCoreNLP object properties, with POS tagging
        // (required for lemmatization), and lemmatization
        Properties props;
        props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma");
        this.pipeline = new StanfordCoreNLP(props);
    }

    public String lemmatize(String text) {
        Annotation tokenAnnotation = new Annotation(text);
        pipeline.annotate(tokenAnnotation);  // necessary for the LemmaAnnotation to be set.
        List<CoreMap> list = tokenAnnotation.get(SentencesAnnotation.class);
        String tokenLemma = list
                .get(0).get(TokensAnnotation.class)
                .get(0).get(LemmaAnnotation.class);
        return tokenLemma;
    }

//    public static void main(String[] args) {
//        String text="I'd";
//        Lemmatization lem = new Lemmatization();
//        System.out.println(lem.lemmatize(text));
//    }
}
