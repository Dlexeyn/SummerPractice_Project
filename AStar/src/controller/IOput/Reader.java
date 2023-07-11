package controller.IOput;

import model.CellType;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.ArrayList;

public class Reader {
    private int sizeX, sizeY;
    private CellType[][] field;
    private int numStart = 0; 
    private int numFinish = 0;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public CellType[][] getField() {
        return field;
    }

    public void addPropertyChangeListener(PropertyChangeListener pListener) {
        support.addPropertyChangeListener(pListener);
    }

    public void removePropertyChangeListener(PropertyChangeListener pListener) {
        support.removePropertyChangeListener(pListener);
    }

    public void notify(String message, String error){
        support.firePropertyChange(message, null, error);
    }

    public void read(String fileName) throws IOException {
        numStart = 0; numFinish = 0;
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
            if(sizeX<2 || sizeY<2){
                notify("ErrorRead","Нарушение минимального размера: (минимум 2)");
                throw new IOException();
            }
            if(sizeX > 14 || sizeY > 22){
                notify("ErrorRead","Нарушение максимального размера: (максX 14, максY 22)");
                throw new IOException();
            }
            field = new CellType[sizeY][sizeX];
            if (text.size() == sizeY + 2) {
                for (int i = 2; i < sizeY + 2; i++) {
                    if (text.get(i).length() == sizeX) {
                        for (int j = 0; j < sizeX; j++) {
                            field[i - 2][j] = charToType(Character.toLowerCase(text.get(i).charAt(j)));
                        }
                    } else {
                        notify("ErrorRead","Проблема с позицией столбцов");
                        //System.out.println("Не хватает столбцов");
                        throw new IOException();
                    }
                }
                if(numStart<1 || numStart>1){
                    notify("ErrorRead","Проблема с позицией старта");
                    //System.out.println("Проблема с позицией старта");
                    throw new IOException();
                }
                if(numFinish<1 || numFinish>1){
                    notify("ErrorRead","Проблема с позицией финиша");
                    //System.out.println("Проблема с позицией финиша");
                    throw new IOException();
                }
            } else {
                //System.out.println("Не хватает строчек");
                notify("ErrorRead","Проблема с количеством строчек");
                throw new IOException();
            }
        } else {
            notify("ErrorRead","Проблема с количеством строчек");
            //System.out.println("Не хватает строчек");
            throw new IOException();
        }

    }

    public void CheckCharInt(String s) throws IOException {
        for (int i = 0; i < s.length(); i++) {
            if (!(s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
                notify("ErrorRead","Число " + s + " введено не правильно");
                //System.out.println("Число " + s + " введено не правильно");
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
                numStart++;
                return CellType.SOURCE_TYPE;
            case 'f':
                numFinish++;
                return CellType.STOCK_TYPE;
        }
        notify("ErrorRead","Неправильный символ " + c);
        //System.out.println("Неправильный символ " + c);
        throw new IOException();
    }

}