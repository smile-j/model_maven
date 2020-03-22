package com.dong.base.test.nlp;

import opennlp.tools.doccat.*;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.ObjectStreamUtils;
import opennlp.tools.util.TrainingParameters;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.SortedMap;

public class TestNlp {

    /* http://opennlp.apache.org/documentation/1.5.3/manual/opennlp.html   官方教程Apache OpenNLP Developer Documentation
     * openNLP 中的各种模型可以在   http://opennlp.sourceforge.net/models-1.5/   下载
     * http://www.programcreek.com/2012/05/opennlp-tutorial/    this is good tutorial about openNLP tools
     *
     * */

    @Test
    public void testSimpleTraining() throws IOException {

        ObjectStream<DocumentSample> samples = ObjectStreamUtils.createObjectStream(
                new DocumentSample("1", new String[]{"a", "b", "c"}),
                new DocumentSample("1", new String[]{"a", "b", "c", "1", "2"}),
                new DocumentSample("1", new String[]{"a", "b", "c", "3", "4"}),
                new DocumentSample("0", new String[]{"x", "y", "z"}),
                new DocumentSample("0", new String[]{"x", "y", "z", "5", "6"}),
                new DocumentSample("0", new String[]{"x", "y", "z", "7", "8"}));

        TrainingParameters params = new TrainingParameters();
        params.put(TrainingParameters.ITERATIONS_PARAM, 100);
        params.put(TrainingParameters.CUTOFF_PARAM, 0);

        DoccatModel model = DocumentCategorizerME.train("x-unspecified", samples,
                params, new DoccatFactory());

        DocumentCategorizer doccat = new DocumentCategorizerME(model);

        double[] aProbs = doccat.categorize(new String[]{"a"});
        Assert.assertEquals("1", doccat.getBestCategory(aProbs));

        double[] bProbs = doccat.categorize(new String[]{"x"});
        Assert.assertEquals("0", doccat.getBestCategory(bProbs));

        //test to make sure sorted map's last key is cat 1 because it has the highest score.
        SortedMap<Double, Set<String>> sortedScoreMap = doccat.sortedScoreMap(new String[]{"a"});
        Set<String> cat = sortedScoreMap.get(sortedScoreMap.lastKey());
        Assert.assertEquals(1, cat.size());
    }

    public static void main(String[] args) {
//		String testString = "This isn't the greatest example sentence in the world because I've seen better.  Neither is this one.  This one's not bad, though.";
        String testString = "Hi. How are you?  This is      &3 $444 Mike."	;

        String tokens[] = Token(testString);
        String sentences[] = sentenceSegmentation(testString);
        System.out.println(tokens);
        System.out.println(sentences);


    }
    //分句
    public static String[] sentenceSegmentation(String str){
        try {
            InputStream modelIn = new FileInputStream("en-sent.bin");
            SentenceModel model = null;
            try {
                model = new SentenceModel(modelIn);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (modelIn != null) {
                    try {
                        modelIn.close();
                    }
                    catch (IOException e) {
                    }
                }
            }

            SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
            String sentences[] = sentenceDetector.sentDetect(str);
            return sentences;

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return null;
        }
    }


    //分词
    public static String[] Token(String str){
        try{
            InputStream modelIn = new FileInputStream("en-token.bin");
            TokenizerModel model = null;
            try {
                model = new TokenizerModel(modelIn);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (modelIn != null) {
                    try {
                        modelIn.close();
                    }
                    catch (IOException e) {
                    }
                }
            }

            TokenizerME tokenizer = new TokenizerME(model);
            String tokens[] = tokenizer.tokenize(str);
//		double tokenProbs[] = tokenizer.getTokenProbabilities();//must be called directly after one of the tokenize methods was called.
            return tokens;
        }
        catch(FileNotFoundException e){return null;}
    }

}
