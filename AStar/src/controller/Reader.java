package controller;

import model.CellType; 

import java.io.*;

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
        int sizeY, sizeX;
        CellType [] [] field;                          
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(NAME_FILE));
        String line = "";
        while ((line = br.readLine()) != null){
            String[]items = line.split("");
            sizeY = Integer.parseInt(items[0]);
            sizeX = Integer.parseInt(items[1]);
            field = new CellType[sizeY][sizeX];
            for(int i=2;i<sizeY+2;i++){
                for(int j=0; j<sizeX;j++){
                    field[i-2][j] =inverse(Character.toLowerCase(items[i].charAt(j))); 
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