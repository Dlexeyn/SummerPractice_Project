package controller.IOput;

import model.*;

import java.io.*;

public class Writer {

    // field info
    int sizeY;
    int sizeX;

    // file variables
    public File saveFile;
    public FileWriter fileWriter;
    String stringToWrite;

    // data to encode into file
    public Data fieldData;

    public void write() throws IOException {

        sizeY = fieldData.getSizeY();
        sizeX = fieldData.getSizeX();

        // field = new int[sizeY][sizeX];
        Cell[][] cells = fieldData.getField();

        try {

            // saveFile = new File("saved_graph.txt");

            fileWriter = new FileWriter(saveFile);

            fileWriter.write(Integer.toString(sizeY) + '\n');
            fileWriter.write(Integer.toString(sizeX) + '\n');

            for (int y = 0; y < sizeY; y++) {
                stringToWrite = "";
                for (int x = 0; x < sizeX; x++) {
                    stringToWrite += typeToChar(cells[y][x].getType());
                }
                stringToWrite += '\n';
                fileWriter.write(stringToWrite);
            }

            fileWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

    }

    public char typeToChar(CellType type) { // encoder
        switch (type) {
            case BLOCK_TYPE:
                return '0';
            case FIRST_TYPE:
                return '1';
            case SECOND_TYPE:
                return '2';
            case THIRD_TYPE:
                return '3';
            case FOURTH_TYPE:
                return '4';
            case FIFTH_TYPE:
                return '5';
            case SOURCE_TYPE:
                return 's';
            case STOCK_TYPE:
                return 'f';
        }
        return 0;
    }
}