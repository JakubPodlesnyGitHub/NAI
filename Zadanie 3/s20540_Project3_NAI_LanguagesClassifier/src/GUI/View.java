package GUI;

import Trainer.Trainer;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private JPanel jPanelNorth, jPanelSouth;
    private JButton checkResultButton;
    private JTextArea jTextAreaInputText;
    private JTextField tFiled, numberOfTestsWhileLearning;
    /////Dane
    private Trainer trainer;

    public View(Trainer trainer) throws HeadlessException {
        this.trainer = trainer;
        components();
        addComponentsToJPanel();
        addListenerToButton();
        addComponentsToFrame();
        settingsWindow();
    }

    private void addComponentsToFrame() {
        this.add(new JScrollPane(jTextAreaInputText), BorderLayout.CENTER);
        this.add(jPanelNorth, BorderLayout.NORTH);
        this.add(checkResultButton, BorderLayout.SOUTH);
    }

    private void components() {
        jTextAreaInputText = new JTextArea();
        numberOfTestsWhileLearning = new JTextField();
        tFiled = new JTextField();
        checkResultButton = new JButton("SPRAWDZ WYNIK DLA TESKTU");
        jPanelNorth = new JPanel(new GridLayout(1, 2));
        jPanelSouth = new JPanel();
        setTextFiled(tFiled, "T ( PROCENT BLEDU ) : " + trainer.getT());
        setTextFiled(numberOfTestsWhileLearning, "LICZBA TESTOW PODCZAS UCZENIA : " + trainer.getNumberOfTests());
        setColor();
    }

    private void addComponentsToJPanel() {
        jPanelNorth.add(tFiled);
        jPanelNorth.add(numberOfTestsWhileLearning);
        jPanelSouth.add(checkResultButton);
    }

    private void setColor() {
        Color color = new Color(0x5555C6);
        tFiled.setForeground(Color.YELLOW);
        numberOfTestsWhileLearning.setForeground(Color.YELLOW);
        tFiled.setBackground(color);
        numberOfTestsWhileLearning.setBackground(color);
        checkResultButton.setBackground(color);
        checkResultButton.setForeground(Color.YELLOW);
    }

    private void setTextFiled(JTextField jTextField, String text) {
        jTextField.setEditable(false);
        jTextField.setText(text);
        jTextField.setHorizontalAlignment(JTextField.CENTER);
        jTextField.setSize(new Dimension(100, 80));
    }

    private void addListenerToButton() {
        checkResultButton.addActionListener(e -> {
            if (e.getActionCommand().equals("SPRAWDZ WYNIK DLA TESKTU")) {
                if (!jTextAreaInputText.getText().equals("")) {
                    String correctLang = JOptionPane.showInputDialog(null, "Podaj skrot tego jezyka: ");
                    trainer.matchLanguageToTextFromWindow(jTextAreaInputText.getText(), correctLang);
                    JOptionPane.showMessageDialog(null, trainer.matchLanguageToTextFromWindow(jTextAreaInputText.getText(), correctLang), "Kwalifikacja Jezyka", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "BRAK TEKSTU", "BRAK TESKTU", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void settingsWindow() {
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JEDNA WARSTWA NEURNOW");
        this.setVisible(true);
    }
}
