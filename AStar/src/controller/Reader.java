package controller;

import model.CellType; 

import java.io.*;
import java.util.ArrayList;

public class Reader{
    private int sizeX, sizeY;
    private CellType [][] field;
    private static final String NAME_FILE = "save.txt";

    public int getSizeX(){
        return sizeX;
    }

    public int getSizeY(){
        return sizeY;
    }

    public CellType [][] getField(){
        return field;
    }
    
    public void read() throws IOException{
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(NAME_FILE));
        String line = "";
        ArrayList<String> text = new ArrayList<String>();
        while ((line = br.readLine()) != null) {
            text.add(line);
        }
        if(text.size()>1){
            sizeY = Integer.parseInt(text.get(0));
            sizeX = Integer.parseInt(text.get(1));
            field = new CellType[sizeY][sizeX];
            if(text.size() == sizeY + 2){
                for(int i=2;i<sizeY+2;i++){
                    if(text.get(i).length() == sizeX){
                        for(int j=0; j<sizeX;j++){
                            field[i-2][j] =inverse(Character.toLowerCase(text.get(i).charAt(j)));
                        }
                    }
                }
            }
        }

    }
    public CellType inverse (char c){
        switch(c){
            case '0':
                return CellType.BLOCK_TYPE;
            case '1':
                return CellType.FIRST_TYPE;
            case '2':
                return CellType.SECOND_TYPE;
            case '3':
                return CellType.THIRD_TYPE;
            case '4':
                return CellType.FOURTH_TYPE;
            case '5':
                return CellType.FIFTH_TYPE;
            case 's':
                return CellType.SOURCE_TYPE;
            case 'f':
                return CellType.STOCK_TYPE;
        }
        return CellType.FIRST_TYPE;
    }

}