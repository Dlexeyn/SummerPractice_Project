package graphics;

import java.util.*;

import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.*; 

import java.awt.*;
import java.lang.reflect.Array;

import model.CellType;


public class View {

    static final int MAX_ROW = 15;
    private static final int MAX_COLUMN = 25;
    static final int COLOR_COUNT = 9;

    CellViewer [][] cellsViewers = new CellViewer[MAX_ROW][MAX_COLUMN];
    private JFrame frame;   // окно
    private JPanel graphPanel; // 
    // private JPanel eastPanel;
    JPanel buttonPanel;
    JPanel colorsPanel;
    JPanel textPanel;
    
    JMenuBar menuBar;
    JMenu menuFile;
    JMenuItem menuFileSave;
    JMenuItem menuFileLoad;
    
    JButton buttonChooseColor;
    JButton buttonReset;
    JButton buttonSetSize;
    JButton buttonPosStart;
    JButton buttonPosFinish;
    JButton buttonLaunch;
    JButton buttonForward;
    JButton button_WHITE_WITH_YELLOW;
    JButton button_LIGHT_YELLOW;
    JButton button_ORANGE;
    JButton button_DARK_ORANGE;
    JButton button_BROWN;
    JButton button_GREY;

    JTextArea outText;

    GroupLayout layout;

    
    private static EnumMap<CellType, Colors> colorTypeMap;
    
    public View() {
        frame = new JFrame("Визуализация А*");
        // layout = new GridLayout(2, 2, 10, 10);


        //frame.setLayout(new GroupLayout(frame.getContentPane()));
        
        initGraphPanel();
        prepareGUI();

    
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setJMenuBar(menuBar);

        // frame.add(buttonPanel, java.awt.BorderLayout.EAST);b
        // frame.add(graphPanel, BorderLayout.CENTER);
        // frame.getContentPane().add(graphPanel);

        layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);


        layout.setHorizontalGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(LEADING)
                                                        .addComponent(graphPanel)
                                                        .addGap(20, 30, 40)
                                                        .addComponent(textPanel))
                                        .addGroup(layout.createParallelGroup(LEADING)
                                                        .addComponent(buttonPanel)
                                                        .addGap(20, 30, 40)
                                                        .addComponent(colorsPanel))                                                        
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                                      .addGroup(layout.createParallelGroup(LEADING)
                                                      .addComponent(graphPanel)
                                                      .addGap(20, 30, 40)
                                                      .addComponent(buttonPanel))
                                      .addGroup(layout.createParallelGroup(LEADING)
                                                      .addComponent(textPanel)
                                                      .addGap(20, 30, 40)
                                                      .addComponent(colorsPanel))
        );
        layout.setAutoCreateGaps(true); 
        layout.setAutoCreateContainerGaps(true);

        frame.pack();
        frame.setVisible(true);
        //frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
        
    }

    public void initGraphPanel() {
        ArrayList<CellType> types = new ArrayList<CellType>(EnumSet.allOf(CellType.class));
        colorTypeMap = new EnumMap<>(CellType.class);
        int index = 0;
        for(Colors color : Colors.values())
        {
            colorTypeMap.put(types.get(index++), color);
        }

        graphPanel = new JPanel(new GridLayout(MAX_ROW, MAX_COLUMN));
        graphPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, false));

        for(int row = 0; row < MAX_ROW; row++){
            for(int col = 0; col < MAX_COLUMN; col++){
                cellsViewers[row][col] = new CellViewer(row, col);
                graphPanel.add(cellsViewers[row][col]);
            }
        }
    }

    private void prepareGUI(){

        menuBar = new JMenuBar();
        menuFile = new JMenu("Файл");
        menuBar.add(menuFile);
        menuFileSave = new JMenuItem("Сохранить");
        menuFileLoad = new JMenuItem("Загрузить");
        menuFile.add(menuFileSave);
        menuFile.add(menuFileLoad);
        // JTextArea area1 = new JTextArea("Многострочное поле", 8, 10);
        // // Шрифт и табуляция
        // area1.setFont(new Font("Dialog", Font.PLAIN, 14));
        // area1.setTabSize(10);

        textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        outText = new JTextArea("Вывод", 8, 100);
        outText.setFont(new Font("Dialog", Font.PLAIN, 14));
        outText.setTabSize(10);
        outText.setEditable(false);
        //outText.sh
        // outText.setBounds(50,100,200,30);
        
        Dimension buttonSize = new Dimension(140, 80);
        button_WHITE_WITH_YELLOW = new JButton("1");
        button_WHITE_WITH_YELLOW.setBackground(Colors.WHITE_WITH_YELLOW.getColor());
        button_WHITE_WITH_YELLOW.setBorder(BorderFactory.createLineBorder(Colors.WHITE_WITH_YELLOW.getColor(), 3, true));
        //button_WHITE_WITH_YELLOW.setMnemonic(KeyEvent.VK_B);
        //button_WHITE_WITH_YELLOW.setSelected(true);
        button_LIGHT_YELLOW = new JButton("2");
        button_LIGHT_YELLOW.setBackground(Colors.LIGHT_YELLOW.getColor());
        button_LIGHT_YELLOW.setBorder(BorderFactory.createLineBorder(Colors.LIGHT_YELLOW.getColor(), 3, true));
        //button_LIGHT_YELLOW.setSelected(true);
        button_ORANGE = new JButton("3");
        button_ORANGE.setBackground(Colors.ORANGE.getColor());
        button_ORANGE.setBorder(BorderFactory.createLineBorder(Colors.ORANGE.getColor(), 3, true));;
        //button_ORANGE.setSelected(true);
        button_DARK_ORANGE = new JButton("4");
        button_DARK_ORANGE.setBackground(Colors.DARK_ORANGE.getColor());
        button_DARK_ORANGE.setBorder(BorderFactory.createLineBorder(Colors.DARK_ORANGE.getColor(), 3, true));;
        //button_DARK_ORANGE.setSelected(true);
        button_BROWN = new JButton("5");
        button_BROWN.setBackground(Colors.BROWN.getColor());
        button_BROWN.setBorder(BorderFactory.createLineBorder(Colors.BROWN.getColor(), 3, true));
        //button_BROWN.setSelected(true);
        button_GREY = new JButton("Stone");
        button_GREY.setBackground(Colors.GREY.getColor());
        button_GREY.setBorder(BorderFactory.createLineBorder(Colors.GREY.getColor(), 3, true));;
        textPanel.add(outText, BorderLayout.CENTER);
        //button_GREY.setSelected(true);

        colorsPanel = new JPanel(new GridLayout(1, 1, 20, 20));
        colorsPanel.add(button_WHITE_WITH_YELLOW);
        colorsPanel.add(button_LIGHT_YELLOW);
        colorsPanel.add(button_ORANGE);
        colorsPanel.add(button_DARK_ORANGE);
        colorsPanel.add(button_BROWN);
        colorsPanel.add(button_GREY);

        buttonReset = new JButton("Сброс");
        buttonReset.setPreferredSize(buttonSize);
        buttonSetSize = new JButton("Задать размер");
        buttonSetSize.setPreferredSize(buttonSize);
        buttonChooseColor = new JButton("Изменить цвет");
        buttonChooseColor.setPreferredSize(buttonSize);
        buttonPosStart = new JButton("Задать старт");
        buttonPosStart.setPreferredSize(buttonSize);
        buttonPosFinish = new JButton("Задать финиш");
        buttonPosFinish.setPreferredSize(buttonSize);
        buttonLaunch = new JButton("Запустить");
        buttonLaunch.setPreferredSize(buttonSize); 
        buttonForward = new JButton("Шаг вперёд");
        buttonForward.setPreferredSize(buttonSize);

        
        buttonPanel =  new JPanel(new GridLayout(7, 1, 0, 20));
        //eastPanel = new JPanel(new GridBagLayout());
        //eastPanel.add(buttonPanel);

        buttonPanel.add(buttonChooseColor);
        buttonPanel.add(buttonReset);
        buttonPanel.add(buttonPosStart);
        buttonPanel.add(buttonPosFinish);
        buttonPanel.add(buttonSetSize);
        buttonPanel.add(buttonLaunch);
        buttonPanel.add(buttonForward);
        //colorsPanel.setVisible(false); //исчезновение

        // frame.getContentPane().add(buttonPanel, BorderLayout.EAST);
        // frame.getContentPane().add(colorsPanel, BorderLayout.EAST);

    }
}
