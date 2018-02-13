package lv.r80vs;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.util.InvalidFormatException;

import java.io.*;

/**
 * Created by Artjom Travkov on 1/16/2018.
 */
public class OpenNLPChecker {
    DoccatModel model;

    public static void main(String args[]) {
        String modelPath = args[0];
        String testDataFile = args[1];
        String outputFile = args[2];
        String amount = args[3];

        System.out.println("Model Path: " + modelPath);
        System.out.println("Test Data File: " + testDataFile);
        System.out.println("Output File: " + outputFile);
        System.out.println("Amount: " + amount);

        OpenNLPChecker categorizer = new OpenNLPChecker();
        categorizer.model = categorizer.loadModel(modelPath);
        categorizer.testData(testDataFile, outputFile, amount);
    }

    void testData(String input, String output, String amount) {
        BufferedReader br = null;
        PrintWriter writer = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(input));
            writer = new PrintWriter(output, "UTF-8");

            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                String mark = this.classifyNewReview(data[1]);
                writer.println(data[0] + "\t" + mark + "\t" + data[1] + "\t" + data[1].length() + "\t" + amount);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (writer != null) writer.close();
        }
    }

    DoccatModel loadModel(String modelFile) {
        try {
            InputStream is = new FileInputStream(modelFile);
            return new DoccatModel(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String classifyNewReview(String review) {
        DocumentCategorizerME myCategorizer = new DocumentCategorizerME(model);
        double[] outcomes = myCategorizer.categorize(review);
        String category = myCategorizer.getBestCategory(outcomes);

        return category;
    }

}
