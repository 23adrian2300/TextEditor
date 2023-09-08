package textEditor;

import javax.swing.*;
import javax.swing.text.*;

public class MenuBar {

    public final TextEditor textEditor;

    public MenuBar(TextEditor textEditor) {
        this.textEditor = textEditor;
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem openMenuItem = new JMenuItem("Open");
        fileMenu.add(openMenuItem);

        JMenuItem saveMenuItem = new JMenuItem("Save");
        fileMenu.add(saveMenuItem);

        JMenuItem saveAsMenuItem = new JMenuItem("Save as");
        fileMenu.add(saveAsMenuItem);

        openMenuItem.addActionListener(e -> openFile());
        saveMenuItem.addActionListener(e -> saveFile());
        saveAsMenuItem.addActionListener(e -> saveFileAs());

        createFormatMenu(menuBar);
        return menuBar;
    }

    private void openFile() {
        FileManagement.openFile(textEditor);
    }

    private void saveFile() {
        FileManagement.saveFile(textEditor);
    }

    private void saveFileAs() {
        FileManagement.saveFileAs(textEditor);
    }


    private void createFormatMenu(JMenuBar menuBar) {
        JMenu formatMenu = new JMenu("Format");
        menuBar.add(formatMenu);

        JMenu fontSizeSubMenu = new JMenu("Font size");
        formatMenu.add(fontSizeSubMenu);

        ButtonGroup fontSizeGroup = new ButtonGroup();

        for (int size = 2; size <= 30; size += 2) {
            createFontSizeOption(fontSizeSubMenu, fontSizeGroup, String.valueOf(size), size);
        }

        JMenuItem customSizeMenuItem = new JMenuItem("Custom size");
        formatMenu.add(customSizeMenuItem);

        customSizeMenuItem.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Set font size:");
            try {
                int size = Integer.parseInt(input);
                createFontSizeOption(fontSizeSubMenu, fontSizeGroup, input, size);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Wrong font size", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    private void createFontSizeOption(JMenu parentMenu, ButtonGroup group, String label, int size) {
        JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(label);
        group.add(menuItem);
        parentMenu.add(menuItem);

        menuItem.addActionListener(e -> {
            setFontSize(size);
        });
    }

    public void setFontSize(int size) {
        StyledDocument doc = textEditor.getTextPane().getStyledDocument();
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setFontSize(attr, size);
        doc.setCharacterAttributes(0, doc.getLength(), attr, false);
    }

    public void increaseBold() {
        StyledDocument doc = textEditor.getTextPane().getStyledDocument();
        int start = textEditor.getTextPane().getSelectionStart();
        int end = textEditor.getTextPane().getSelectionEnd();
        if (start != end) {
            SimpleAttributeSet attr = new SimpleAttributeSet();
            StyleConstants.setBold(attr, true);
            doc.setCharacterAttributes(start, end - start, attr, false);
        }
    }

    public void decreaseBold() {
        StyledDocument doc = textEditor.getTextPane().getStyledDocument();
        int start = textEditor.getTextPane().getSelectionStart();
        int end = textEditor.getTextPane().getSelectionEnd();
        if (start != end) {
            SimpleAttributeSet attr = new SimpleAttributeSet();
            StyleConstants.setBold(attr, false);
            doc.setCharacterAttributes(start, end - start, attr, false);
        }
    }

    public void increaseUnderline() {
        StyledDocument doc = textEditor.getTextPane().getStyledDocument();
        int start = textEditor.getTextPane().getSelectionStart();
        int end = textEditor.getTextPane().getSelectionEnd();
        if (start != end) {
            SimpleAttributeSet attr = new SimpleAttributeSet();
            StyleConstants.setUnderline(attr, true);
            doc.setCharacterAttributes(start, end - start, attr, false);
        }
    }

    public void decreaseUnderline() {
        StyledDocument doc = textEditor.getTextPane().getStyledDocument();
        int start = textEditor.getTextPane().getSelectionStart();
        int end = textEditor.getTextPane().getSelectionEnd();
        if (start != end) {
            SimpleAttributeSet attr = new SimpleAttributeSet();
            StyleConstants.setUnderline(attr, false);
            doc.setCharacterAttributes(start, end - start, attr, false);
        }
    }
}