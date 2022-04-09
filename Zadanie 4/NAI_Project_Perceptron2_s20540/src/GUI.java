import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public JPanel jPanelCentere,jPanelNorth;
    private JTable measuresTable;
    private String[] columnNames;
    private Object[][] data;
    private JTextField precisionFiled,recallFiled,fMeFiled;
    public GUI(Trainer trainer) {
        columnNames = new String[]{"", "Benign", "Malignant"};
        data = new Object[][]{{"Benign", trainer.getMeasuresTab()[2], trainer.getMeasuresTab()[0]},
                {"Malignant", trainer.getMeasuresTab()[4], trainer.getMeasuresTab()[3]}
        };
        components(trainer);
        editJtable();
        addToWindow();
        settingsWindow();
    }

    private void editJtable() {
        measuresTable.setDefaultEditor(Object.class,null);
        measuresTable.getTableHeader().setReorderingAllowed(false);
    }

    private void components(Trainer trainer) {
        precisionFiled = new JTextField("P: " + trainer.getPrecision());
        precisionFiled.setEditable(false);
        recallFiled = new JTextField("R: " + trainer.getRecall());
        recallFiled.setEditable(false);
        fMeFiled = new JTextField("F: " + trainer.getFmeasure());
        fMeFiled.setEditable(false);
        jPanelNorth = new JPanel();
        jPanelNorth.add(precisionFiled);
        jPanelNorth.add(recallFiled);
        jPanelNorth.add(fMeFiled);
        jPanelCentere = new JPanel();
        measuresTable = new JTable(data, columnNames);
        jPanelCentere.add(new JScrollPane(measuresTable));
    }

    private void addToWindow() {
        this.add(jPanelCentere, BorderLayout.CENTER);
        this.add(jPanelNorth,BorderLayout.NORTH);
    }

    private void settingsWindow() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Perceptron Z Miarami");
        this.setVisible(true);
    }
}
