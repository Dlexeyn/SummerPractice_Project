package graphics;

import java.util.*;

import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.CellType;
import model.Data;

public class View extends JFrame implements PropertyChangeListener {

    static final int MAX_ROW = 15;
    private static final int MAX_COLUMN = 25;
    static final int COLOR_COUNT = 9;

    private GeneralListener generalListener;
    private ChooseColorListener chooseColorListener;

    CellViewer[][] cellsViewers = new CellViewer[MAX_ROW][MAX_COLUMN];
    private JPanel graphPanel;
    JPanel buttonPanel;
    JPanel colorsPanel;
    JPanel textPanel;

    JMenuBar menuBar;
    JMenu menuFile;
    JButton toolSave;
    JButton toolLoad;

    JButton buttonChooseColor;
    JButton buttonReset;
    JButton buttonSetSize;
    JToggleButton buttonPosStart;
    JToggleButton buttonPosFinish;
    JButton buttonLaunch;
    JButton buttonForward;
    JToggleButton button_WHITE_WITH_YELLOW;
    JToggleButton button_LIGHT_YELLOW;
    JToggleButton button_ORANGE;
    JToggleButton button_DARK_ORANGE;
    JToggleButton button_BROWN;
    JToggleButton button_GREY;

    JTextArea outText;

    GroupLayout viewLayout;

    ArrayList<JButton> buttonPanelArray;
    ArrayList<JToggleButton> colorButtonPanelArray;

    private static EnumMap<CellType, Colors> colorTypeMap;

    public void init(ActionListener aListener) {
        setTitle("Визуализация А*");
        
        initGraphPanel();
        prepareButtonPanel(aListener);
        prepareColorsPanel();
        prepareMenuBar();
        prepareTextPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menuBar);

        viewLayout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(viewLayout);

        viewLayout.setHorizontalGroup(viewLayout.createSequentialGroup()
                .addGroup(viewLayout.createParallelGroup(LEADING)
                        .addComponent(graphPanel)
                        .addGap(20, 30, 40)
                        .addComponent(textPanel))
                .addGroup(viewLayout.createParallelGroup(LEADING)
                        .addComponent(buttonPanel)
                        .addGap(20, 30, 40)
                        .addComponent(colorsPanel)));

        viewLayout.setVerticalGroup(viewLayout.createSequentialGroup()
                .addGroup(viewLayout.createParallelGroup(LEADING)
                        .addComponent(graphPanel)
                        .addGap(20, 30, 40)
                        .addComponent(buttonPanel))
                .addGroup(viewLayout.createParallelGroup(LEADING)
                        .addComponent(textPanel)
                        .addGap(20, 30, 40)
                        .addComponent(colorsPanel)));
        viewLayout.setAutoCreateGaps(true);
        viewLayout.setAutoCreateContainerGaps(true);
        pack();
        //setVisible(true);
        setMinimumSize(new Dimension(this.getWidth(), this.getHeight()));
    }

    public View() {
        generalListener = new GeneralListener();
        chooseColorListener = new ChooseColorListener();
    }

    public void start()
    {

    }

    public void initGraphPanel() {
        ArrayList<CellType> types = new ArrayList<CellType>(EnumSet.allOf(CellType.class));
        colorTypeMap = new EnumMap<>(CellType.class);

        int index = 0;
        for (Colors color : Colors.values())
            colorTypeMap.put(types.get(index++), color);

        graphPanel = new JPanel(new GridLayout(MAX_ROW, MAX_COLUMN));
        graphPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5, false));

        for (int row = 0; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COLUMN; col++) {
                cellsViewers[row][col] = new CellViewer(row, col);
                graphPanel.add(cellsViewers[row][col]);
            }
        }
    }

    public void prepareButtonPanel(ActionListener aListener) {
        Dimension buttonSize = new Dimension(140, 80);

        buttonPanelArray = new ArrayList<JButton>();
        buttonReset = new JButton("Сброс");
        buttonReset.setPreferredSize(buttonSize);
        buttonSetSize = new JButton("Изменить размер");
        buttonSetSize.setPreferredSize(buttonSize);
        buttonChooseColor = new JButton("Редактировать");
        buttonChooseColor.addActionListener(chooseColorListener);
        buttonChooseColor.setPreferredSize(buttonSize);
        buttonLaunch = new JButton("Запустить");
        buttonLaunch.setPreferredSize(buttonSize);
        buttonForward = new JButton("Шаг вперёд");
        buttonForward.setPreferredSize(buttonSize);

        buttonPanel = new JPanel(new GridLayout(7, 1, 0, 20));

        buttonPanelArray.add(buttonChooseColor);
        buttonPanelArray.add(buttonReset);
        buttonPanelArray.add(buttonSetSize);
        buttonPanelArray.add(buttonLaunch);
        buttonPanelArray.add(buttonForward);

        buttonPanelArray.forEach((button) -> {
            buttonPanel.add(button);
            button.addActionListener(generalListener);
            button.addActionListener(aListener);
        });

    }

    public void prepareColorsPanel() {
        colorButtonPanelArray = new ArrayList<>();
        button_WHITE_WITH_YELLOW = new JToggleButton("1", false);
        button_WHITE_WITH_YELLOW.setBackground(Colors.WHITE_WITH_YELLOW.getColor());
        button_WHITE_WITH_YELLOW
                .setBorder(BorderFactory.createLineBorder(Colors.WHITE_WITH_YELLOW.getColor(), 3, true));
        // button_WHITE_WITH_YELLOW.setMnemonic(KeyEvent.VK_B);
        // button_WHITE_WITH_YELLOW.setSelected(true);
        button_LIGHT_YELLOW = new JToggleButton("2", false);
        button_LIGHT_YELLOW.setBackground(Colors.LIGHT_YELLOW.getColor());
        button_LIGHT_YELLOW.setBorder(BorderFactory.createLineBorder(Colors.LIGHT_YELLOW.getColor(), 3, true));
        // button_LIGHT_YELLOW.setSelected(true);
        button_ORANGE = new JToggleButton("3", false);
        button_ORANGE.setBackground(Colors.ORANGE.getColor());
        button_ORANGE.setBorder(BorderFactory.createLineBorder(Colors.ORANGE.getColor(), 3, true));
        ;
        // button_ORANGE.setSelected(true);
        button_DARK_ORANGE = new JToggleButton("4", false);
        button_DARK_ORANGE.setBackground(Colors.DARK_ORANGE.getColor());
        button_DARK_ORANGE.setBorder(BorderFactory.createLineBorder(Colors.DARK_ORANGE.getColor(), 3, true));
        ;
        // button_DARK_ORANGE.setSelected(true);
        button_BROWN = new JToggleButton("5", false);
        button_BROWN.setBackground(Colors.BROWN.getColor());
        button_BROWN.setBorder(BorderFactory.createLineBorder(Colors.BROWN.getColor(), 3, true));
        // button_BROWN.setSelected(true);
        button_GREY = new JToggleButton("Stone", false);
        button_GREY.setBackground(Colors.GREY.getColor());
        button_GREY.setBorder(BorderFactory.createLineBorder(Colors.GREY.getColor(), 3, true));

        buttonPosStart = new JToggleButton("Старт", false);
        buttonPosStart.setBackground(Color.GREEN);
        buttonPosStart.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3, true));

        buttonPosFinish = new JToggleButton("Финиш", false);
        buttonPosFinish.setBackground(Color.RED);
        buttonPosFinish.setBorder(BorderFactory.createLineBorder(Color.RED, 3, true));

        colorButtonPanelArray.add(button_WHITE_WITH_YELLOW);
        colorButtonPanelArray.add(button_LIGHT_YELLOW);
        colorButtonPanelArray.add(button_ORANGE);
        colorButtonPanelArray.add(button_DARK_ORANGE);
        colorButtonPanelArray.add(button_BROWN);
        colorButtonPanelArray.add(button_GREY);
        colorButtonPanelArray.add(buttonPosStart);
        colorButtonPanelArray.add(buttonPosFinish);

        colorsPanel = new JPanel(new GridLayout(2, 4, 20, 20));
        colorsPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3, false));

        colorButtonPanelArray.forEach((button) -> {
            colorsPanel.add(button);
            button.addActionListener(generalListener);
        });

    }

    private void prepareMenuBar() {

        menuBar = new JMenuBar();
        toolSave = new JButton("Сохранить");
        toolLoad = new JButton("Загрузить");
        menuBar.add(toolSave);
        menuBar.add(toolLoad);

    }

    private void prepareTextPanel() {
        // JTextArea area1 = new JTextArea("Многострочное поле", 8, 10);
        // // Шрифт и табуляция
        // area1.setFont(new Font("Dialog", Font.PLAIN, 14));
        // area1.setTabSize(10);

        textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        outText = new JTextArea("Вывод", 6, 70);
        outText.setFont(new Font("Dialog", Font.PLAIN, 18));
        outText.setTabSize(10);
        outText.setEditable(false);
        outText.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3, false));
        // textPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3, false));
        textPanel.add(outText, BorderLayout.CENTER);
        // outText.sh
        // outText.setBounds(50,100,200,30);
        colorsPanel.setVisible(false); // исчезновение
    }

    private class GeneralListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            colorButtonPanelArray.forEach((button) -> {
                if (button != e.getSource())
                    button.setSelected(false);
            });
        }

    }

    private class ChooseColorListener implements ActionListener {

        private boolean isPressed = false;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isPressed == false) {
                colorsPanel.setVisible(true);
                buttonChooseColor.setText("Закончить редактирование");
                buttonPanelArray.forEach((button) -> {
                    if (button != buttonChooseColor)
                        button.setEnabled(false);
                });
            } else {
                buttonChooseColor.setText("Редактировать");
                colorsPanel.setVisible(false);
                buttonPanelArray.forEach((button) -> {
                    if (button != buttonChooseColor)
                        button.setEnabled(true);
                });
            }
            isPressed = isPressed ^ true;
            pack();
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Data newData = (Data) evt.getNewValue();

    }

    public ArrayList<JButton> getButtonPanelArray() {
        return buttonPanelArray;
    }
}
