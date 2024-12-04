package com.phycaresolutions.lib;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyClass {
    static ArrayList<String> val1 = new ArrayList<>(Arrays.asList("field1", "field1val2", "field1val3"));
    static ArrayList<String> val2 = new ArrayList<>(Arrays.asList("field2", "field2val2", "field2val3"));
    static int col1=15;
    static int col2=15;
    public static void main(String[] args){
       /* try {
            PrintWriter outputStream = new PrintWriter("C:\\Users\\rajuelaboina\\Desktop\\Resident\\myObjects.txt");
            outputStream.println(String.format("%-20s %-20s %-20s", "Name", "Age", "Gender"));
            outputStream.println(String.format("%-20s %-20s %-20s", "John", "30", "Male"));
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
       /* List<String> headersList = Arrays.asList("NAME", "GENDER", "MARRIED", "AGE", "SALARY($)");
        List<List<String>> rowsList = Arrays.asList(
                Arrays.asList("Eddy", "Male", "No", "23", "1200.27"),
                Arrays.asList("Libby", "Male", "No", "17", "800.50"),
                Arrays.asList("Rea", "Female", "No", "30", "10000.00"),
                Arrays.asList("Deandre", "Female", "No", "19", "18000.50"),
                Arrays.asList("Alice", "Male", "Yes", "29", "580.40"),
                Arrays.asList("Alyse", "Female", "No", "26", "7000.89"),
                Arrays.asList("Venessa", "Female", "No", "22", "100700.50")
        );
        Board board = new Board(75);
        String tableString = board.setInitialBlock(new Table(board, 75, headersList, rowsList).tableToBlocks()).build().getPreview();
        System.out.println(tableString);*/





            BufferedWriter writeTable = null;
            try {
                writeTable = new BufferedWriter(new FileWriter("C:\\Users\\rajuelaboina\\Desktop\\Resident\\testtable.txt"));

                //Anfang erste Zeile
                writeTable.write("+ ");
                for (int i = 0; i < col1; i++){
                    writeTable.write("-");
                }
                writeTable.write(" + ");
                for (int i = 0; i < col2; i++){
                    writeTable.write("-");
                }
                writeTable.write(" +");
                writeTable.newLine();
                //Ende erste Zeile

                for (int i = 0; i < val1.size(); i++){
                    writeTable.write("| " + val1.get(i) + "   "+ " + " +"   "+ val2.get(i) +  "   "+ " |");
                    writeTable.newLine();

                    writeTable.write("+ ");
                    for (int j = 0; j < col1; j++){
                        writeTable.write("-");
                    }
                    writeTable.write(" + ");
                    for (int m = 0; m < col2; m++){
                        writeTable.write("-");
                    }
                    writeTable.write(" +");
                    writeTable.newLine();

                }
            } catch (IOException e) {
                System.err.println(e);
            } finally {
                if (writeTable != null) {
                    try {
                        writeTable.close();
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                }
            }

    }
}