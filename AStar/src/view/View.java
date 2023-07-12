package view;

import java.util.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.ColorsController;

import static javax.swing.GroupLayout.Alignment.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Data;
import model.Cell;

public class View extends JFrame implements PropertyChangeListener {

    static final int COLOR_COUNT = 9;

    public int outputCost;

    private GeneralListener generalListener;
    private ChooseColorListener chooseColorListener;

    private GraphPanel graphPanel;
    JPanel buttonPanel;
    JPanel colorsPanel;
    JPanel textPanel;
    JScrollPane scrollPane;

    JMenuBar menuBar;
    JMenu menuFile;
    JButton toolSave;
    JButton toolLoad;

    JButton buttonChooseColor;
    JButton buttonReset;
    JButton buttonSetSize;
    JButton buttonLaunchNormal;
    JButton buttonLaunchStep;
    JButton buttonForward;
    JButton buttonClearSolver;

    JToggleButton button_WHITE_WITH_YELLOW;
    JToggleButton button_LIGHT_YELLOW;
    JToggleButton button_ORANGE;
    JToggleButton button_DARK_ORANGE;
    JToggleButton button_BROWN;
    JToggleButton button_GREY;
    JToggleButton buttonPosStart;
    JToggleButton buttonPosFinish;
    JToggleButton lastChoiceButton;

    OutTextArea outText;

    GroupLayout viewLayout;

    ArrayList<JButton> buttonPanelArray;
    ArrayList<JToggleButton> colorButtonPanelArray;
    ArrayList<JButton> menuBarArray;

    public void init(ActionListener aListener, ColorsController cListener, ActionListener fListener) {
        setTitle("Визуализация А*");
        graphPanel.setListener(cListener.getvListener());
        prepareButtonPanel(aListener);
        prepareColorsPanel(cListener.getcListener(), cListener);
        prepareMenuBar(fListener);
        prepareTextPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menuBar);
        setupLayout();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
    }


    private void setupLayout() {
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
    }

    public View() {
        generalListener = new GeneralListener();
        chooseColorListener = new ChooseColorListener();
        graphPanel = new GraphPanel();
    }

    public void start() {

    }

    public void prepareButtonPanel(ActionListener aListener) {
        buttonPanelArray = new ArrayList<JButton>();

        buttonChooseColor = new JButton("", new ImageIcon(this.getClass().getResource("/icons/pencil.png")));
        initPanelButton("Редактировать", buttonChooseColor);
        buttonChooseColor.addActionListener(chooseColorListener);

        buttonReset = new JButton("", new ImageIcon(this.getClass().getResource("/icons/reset.png")));
        initPanelButton("Сброс", buttonReset);

        buttonSetSize = new JButton("", new ImageIcon(this.getClass().getResource("/icons/resize.png")));
        initPanelButton("Задать размер графа", buttonSetSize);

        buttonLaunchNormal = new JButton("", new ImageIcon(this.getClass().getResource("/icons/play-and-pause-button.png")));
        initPanelButton("Запустить", buttonLaunchNormal);

        buttonLaunchStep = new JButton("", new ImageIcon(this.getClass().getResource("/icons/footsteps.png")));
        initPanelButton("Запустить пошагово", buttonLaunchStep);

        buttonForward = new JButton("", new ImageIcon(this.getClass().getResource("/icons/play.png")));
        initPanelButton("Шаг вперёд", buttonForward);
        buttonForward.setVisible(false);

        buttonClearSolver = new JButton("", new ImageIcon(this.getClass().getResource("/icons/broom.png")));
        initPanelButton("Очистить решение", buttonClearSolver);
        buttonClearSolver.setVisible(false);

        buttonPanel = new JPanel(new GridLayout(7, 1, 0, 20));

        buttonPanelArray.forEach((button) -> {
            buttonPanel.add(button);
            button.addActionListener(generalListener);
            button.addActionListener(aListener);
        });

    }

    private void initPanelButton(String tipText, JButton button){
        Dimension buttonSize = new Dimension(140, 80);
        button.setPreferredSize(buttonSize);
        button.setToolTipText(tipText);
        buttonPanelArray.add(button);
    }

    public GraphPanel getGraphPanel() {
        return graphPanel;
    }

    public void prepareColorsPanel(ActionListener cListener, ActionListener globalListener) {
        colorButtonPanelArray = new ArrayList<>();
        button_WHITE_WITH_YELLOW = new JToggleButton("1", false);
        initColorChoiceButton(Colors.WHITE_WITH_YELLOW.getColor(), button_WHITE_WITH_YELLOW);

        button_LIGHT_YELLOW = new JToggleButton("2", false);
        initColorChoiceButton(Colors.LIGHT_YELLOW.getColor(), button_LIGHT_YELLOW);

        button_ORANGE = new JToggleButton("3", false);
        initColorChoiceButton(Colors.ORANGE.getColor(), button_ORANGE);

        button_DARK_ORANGE = new JToggleButton("4", false);
        initColorChoiceButton(Colors.DARK_ORANGE.getColor(), button_DARK_ORANGE);

        button_BROWN = new JToggleButton("5", false);
        initColorChoiceButton(Colors.BROWN.getColor(), button_BROWN);

        button_GREY = new JToggleButton("Блок", false);
        initColorChoiceButton(Colors.GREY.getColor(), button_GREY);

        buttonPosStart = new JToggleButton("Старт", false);
        initColorChoiceButton(Color.GREEN, buttonPosStart);

        buttonPosFinish = new JToggleButton("Финиш", false);
        initColorChoiceButton(Color.RED, buttonPosFinish);

        colorsPanel = new JPanel(new GridLayout(4, 2, 20, 20));
        colorsPanel.setBorder ( new TitledBorder ( new EtchedBorder (), "Набор вершин" ) );

        colorButtonPanelArray.forEach((button) -> {
            colorsPanel.add(button);
            button.addActionListener(cListener);
            button.addActionListener(globalListener);
        });

        lastChoiceButton = button_WHITE_WITH_YELLOW;
    }

    private void initColorChoiceButton(Color color, JToggleButton button){
        button.setBackground(color);
        button.setBorder(BorderFactory.createLineBorder(color, 3, true));
        colorButtonPanelArray.add(button);
    }



    private void prepareMenuBar(ActionListener fListener) {

        menuBarArray = new ArrayList<>();
        toolSave = new JButton("Сохранить");
        toolLoad = new JButton("Загрузить");

        menuBarArray.add(toolSave);
        menuBarArray.add(toolLoad);

        menuBar = new JMenuBar();

        menuBarArray.forEach((button) -> {
            menuBar.add(button);
            button.addActionListener(fListener);
        });
    }

    private void prepareTextPanel() {
        Border grayline = BorderFactory.createLineBorder(Color.gray, 3);
        textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textPanel.setBorder ( BorderFactory.createTitledBorder( grayline, "Вывод"));
        
        outText = new OutTextArea(7, 95);
        

        outText.setFont(new Font("Dialog", Font.PLAIN, 18));
        outText.setTabSize(10);
        outText.setEditable(false);
        //outText.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3, false));
        outText.setLineWrap(true);

        scrollPane = new JScrollPane(outText);
        scrollPane.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        //textPanel.add(outText, BorderLayout.CENTER);
        textPanel.add(scrollPane);
        colorsPanel.setVisible(false);
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
                buttonChooseColor.setIcon(new ImageIcon(this.getClass().getResource("/icons/delete.png")));
                buttonPanelArray.forEach((button) -> {
                    if (button != buttonChooseColor)
                        button.setEnabled(false);
                });

                graphPanel.updateListener();
            } else {
                buttonChooseColor.setIcon(new ImageIcon(this.getClass().getResource("/icons/pencil.png")));
                colorsPanel.setVisible(false);
                buttonPanelArray.forEach((button) -> {
                    if (button != buttonChooseColor)
                        button.setEnabled(true);
                });
                graphPanel.setEnabledbuttons(false);

            }
            isPressed = !isPressed;
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        Data newData = (Data) e.getNewValue();
        if (e.getPropertyName().equals(new String("Size")) || e.getPropertyName().equals(new String("Field"))) {
            graphPanel.setupFromField(newData.getSizeX(), newData.getSizeY(), newData.getField());
            graphPanel.setEnabledbuttons(false);
            outText.setText("");
        } else if (e.getPropertyName().equals(new String("Path"))) {
            outText.setText(null);
            outText.append("Путь найден. Стоимость:" + Integer.toString(newData.getPathCost()) + "\n");
            outText.append(PathCoordinates(newData));
            postAnswerPrepare();
        } else if (e.getPropertyName().equals(new String("NoPath"))) {
            outText.setText(null);
            outText.append("Путь не найден.\n");
            postAnswerPrepare();
        } else if (e.getPropertyName().equals(new String("NoStart"))) {
            outText.setText(null);
            outText.append("Cтарт не задан.\n");
        } else if (e.getPropertyName().equals(new String("NoFinish"))) {
            outText.setText(null);
            outText.append("Финиш не задан.\n");
        }
    }

    public String PathCoordinates(Data fData) {
        ArrayList<Cell> path = new ArrayList<>();
        path = fData.getPath();
        Collections.reverse(path);
        StringBuilder resText = new StringBuilder();
        resText.append("Путь: ");
        path.forEach((cell) -> {
            resText.append("(" + (cell.getPosX() + 1) + "," + (cell.getPosY() + 1) + ")->");
        });
        resText.delete(resText.length() - 2, resText.length());
        return (resText.toString());
    }

    private void postAnswerPrepare() {
        outText.updateUI();
        buttonChooseColor.setEnabled(false);
        buttonReset.setEnabled(false);
        buttonSetSize.setEnabled(false);
        buttonForward.setEnabled(false);
        buttonLaunchNormal.setEnabled(false);
        buttonLaunchStep.setEnabled(false);
        buttonClearSolver.setVisible(true);
        buttonClearSolver.setEnabled(true);
    }

    public ArrayList<JButton> getButtonPanelArray() {
        return buttonPanelArray;
    }

    public ArrayList<JToggleButton> getColorButtonPanelArray() {
        return colorButtonPanelArray;
    }

    public ArrayList<JButton> getMenuBarArray() {
        return menuBarArray;
    }

    public OutTextArea getOutText() {
        return outText;
    }
}
