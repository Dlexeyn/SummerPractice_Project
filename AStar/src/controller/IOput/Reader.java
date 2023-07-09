package controller.IOput;

import model.CellType;

import java.io.*;
import java.util.ArrayList;

public class Reader {
    private int sizeX, sizeY;
    private CellType[][] field;

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public CellType[][] getField() {
        return field;
    }

    public void read(String fileName) throws IOException {
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(fileName));
        String line = "";
        ArrayList<String> text = new ArrayList<String>();
        while ((line = br.readLine()) != null) {
            text.add(line);
        }
        br.close();
        if (text.size() > 1) {
            CheckCharInt(text.get(0));
            CheckCharInt(text.get(1));
            sizeY = Integer.parseInt(text.get(0));
            sizeX = Integer.parseInt(text.get(1));
            field = new CellType[sizeY][sizeX];
            if (text.size() == sizeY + 2) {
                for (int i = 2; i < sizeY + 2; i++) {
                    if (text.get(i).length() == sizeX) {
                        for (int j = 0; j < sizeX; j++) {
                            field[i - 2][j] = charToType(Character.toLowerCase(text.get(i).charAt(j)));
                        }
                    } else {
                        System.out.println("Не хватает столбцов");
                        throw new IOException();
                    }
                }
            } else {
                System.out.println("Не хватает строчек");
                throw new IOException();
            }
        } else {
            System.out.println("Не хватает строчек");
            throw new IOException();
        }

    }

    public void CheckCharInt(String s) throws IOException {
        for (int i = 0; i < s.length(); i++) {
            if (!(s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
                System.out.println("Число " + s + " введено не правильно");
                throw new IOException();
            }
        }
    }

    public CellType charToType(char c) throws IOException {
        switch (c) {
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
        System.out.println("Неправильный символ" + c);
        throw new IOException();
    }

}