package textEditor;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileManagement {

    public static void openFile(TextEditor textEditor) {
        int returnValue = textEditor.getFileChooser().showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = textEditor.getFileChooser().getSelectedFile();
            try {
                FileInputStream fis = new FileInputStream(selectedFile);
                InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(reader);
                String line;
                StringBuilder content = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    content.append(line).append("\n");
                }
                br.close();
                reader.close();
                textEditor.getTextPane().setText(content.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error - opening file: " + e.getMessage());
            }
        }
    }

    public static void saveFile(TextEditor textEditor) {
        File currentFile = textEditor.getFileChooser().getSelectedFile();
        if (currentFile == null) {
            saveFileAs(textEditor);
        } else {
            try {
                FileOutputStream fos = new FileOutputStream(currentFile);
                OutputStreamWriter writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                writer.write(textEditor.getTextPane().getText());
                writer.close();
                JOptionPane.showMessageDialog(null, "File saved.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error - saving file: " + e.getMessage());
            }
        }
    }

    public static void saveFileAs(TextEditor textEditor) {
        int returnValue = textEditor.getFileChooser().showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = textEditor.getFileChooser().getSelectedFile();
            try {
                FileOutputStream fos = new FileOutputStream(selectedFile);
                OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");
                writer.write(textEditor.getTextPane().getText());
                writer.close();
                JOptionPane.showMessageDialog(null, "File saved.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error - saving file: " + e.getMessage());
            }
        }
    }
}