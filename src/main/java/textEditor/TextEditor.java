package textEditor;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextEditor extends JFrame{

    private final JTextPane textPane;
    private final JFileChooser fileChooser;
    private final JToolBar toolBar;
    private final MenuBar menuBar;


    public TextEditor() {
        super("Text Editor");
        textPane = new JTextPane();
        fileChooser = new JFileChooser();
        toolBar = new JToolBar();
        menuBar = new MenuBar(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int windowWidth = getWidth();
        int windowHeight = getHeight();
        setLocation((screenWidth - windowWidth) / 4, (screenHeight - windowHeight) / 8);
        setupUI();
    }

    private void setupUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JScrollPane scrollPane = new JScrollPane(textPane);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setJMenuBar(menuBar.createMenuBar());;
        textPane.setBackground(Color.WHITE);
        textPane.setForeground(Color.BLACK);

        JButton increaseBoldButton = new JButton("Bold");
        JButton decreaseBoldButton = new JButton("Unbold");
        JButton increaseUnderlineButton = new JButton("Underline");
        JButton decreaseUnderlineButton = new JButton("Ununderline");

        increaseBoldButton.setBackground(Color.GREEN);
        increaseBoldButton.setForeground(Color.BLACK);

        decreaseBoldButton.setBackground(Color.PINK);
        decreaseBoldButton.setForeground(Color.BLACK);

        increaseUnderlineButton.setBackground(Color.GREEN);
        increaseUnderlineButton.setForeground(Color.BLACK);

        decreaseUnderlineButton.setBackground(Color.PINK);
        decreaseUnderlineButton.setForeground(Color.BLACK);

        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(increaseBoldButton);
        toolBar.add(decreaseBoldButton);
        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(increaseUnderlineButton);
        toolBar.add(decreaseUnderlineButton);
        toolBar.add(Box.createHorizontalGlue());


        increaseBoldButton.addActionListener(e -> menuBar.increaseBold());
        decreaseBoldButton.addActionListener(e -> menuBar.decreaseBold());
        increaseUnderlineButton.addActionListener(e -> menuBar.increaseUnderline());
        decreaseUnderlineButton.addActionListener(e -> menuBar.decreaseUnderline());
        getContentPane().add(toolBar, BorderLayout.NORTH);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to save changes before closing?", "Save Changes", JOptionPane.YES_NO_CANCEL_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    FileManagement.saveFile(TextEditor.this);
                    dispose();
                } else if (option == JOptionPane.NO_OPTION) {
                    dispose();
                }

            }
        });
    }

    public JTextPane getTextPane() {
        return textPane;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

}
