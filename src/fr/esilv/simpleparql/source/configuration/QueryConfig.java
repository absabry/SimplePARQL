package fr.esilv.simpleparql.source.configuration;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Strcuture of the configuration file of the ignored proprieties
 */
public class QueryConfig {
    private ArrayList<String> ignoredProprieties;
    private ArrayList<String> predifinedPrefixes;

    public QueryConfig(FileInputStream file) throws IOException {
        ignoredProprieties = new ArrayList<>();
        predifinedPrefixes = new ArrayList<>();
        readProprieties(file);
    }

    public ArrayList<String> getIgnoredProprieties() {
        return ignoredProprieties;
    }

    public ArrayList<String> getPredifinedPrefixes() {
        return predifinedPrefixes;
    }

    private void readProprieties(InputStream file) throws IOException {
        ArrayList<String> linesOfFile = new ArrayList<>();
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            linesOfFile.add(input.nextLine());
        }
        for (int i = 0; i < linesOfFile.size(); i++) {
            String[] words = linesOfFile.get(i).split("\\s+");
            switch (words[0].toLowerCase().trim()) {
                case "#":
                    break;
                case "prefixes":
                    i++;
                    String prefixe = linesOfFile.get(i).split("\\s+")[0].toLowerCase().trim();
                    while (!prefixe.equals("end")) { // the end of the prefixes's list
                        if (!prefixe.equals("")) { // the line can be empty!
                            predifinedPrefixes.add("PREFIX " + prefixe);
                        }
                        i++;
                        prefixe = linesOfFile.get(i).split("\\s+")[0].toLowerCase().trim();
                    }
                    break;
                case "ignored":
                    i++;
                    String proprety = linesOfFile.get(i).split("\\s+")[0].toLowerCase().trim();
                    while (!proprety.equals("end")) { // the end of the proprety's list
                        if (!proprety.equals("")) { // the line can be empty!
                            ignoredProprieties.add(proprety);
                        }
                        i++;
                        proprety = linesOfFile.get(i).split("\\s+")[0].toLowerCase().trim();
                    }
                    break;
                default:
                    break;
            }
        }
    }
}

