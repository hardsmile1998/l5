import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class GUI extends JFrame {
    private JButton openfile = new JButton("Открыть файл");
    private JButton count_symb = new JButton("Количество символов");
    private JLabel label_symbols = new JLabel("xxx");
    private JButton count_letters = new JButton("Количество букв");
    private JLabel label_letters = new JLabel("xxx");
    private JButton count_words = new JButton("Количество слов");
    private JLabel label_words = new JLabel("xxx");
    private JButton count_uwords = new JButton("Количество уникальных слов");
    private JButton help = new JButton("HELP");
    private JLabel label_uwords = new JLabel("xxx");
    private JButton plus = new JButton("+");
    private JScrollPane scrollPane = new JScrollPane();
    private JTextArea textArea = new JTextArea();
    private int click = 0;

    public void VisibleButtonsCount(boolean x) {
        count_symb.setVisible(x);
        count_letters.setVisible(x);
        count_words.setVisible(x);
        count_uwords.setVisible(x);
    }

    public void VisibleLabels(boolean x) {
        label_symbols.setVisible(x);
        label_letters.setVisible(x);
        label_words.setVisible(x);
        label_uwords.setVisible(x);
    }

    public void VisibleArea(boolean x) {
        scrollPane.setVisible(x);
        textArea.setVisible(x);
    }

    public GUI() {
        super("LABA 3");
        this.setBounds(500, 200, 330, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Statistik x = new Statistik();

        getContentPane().add(openfile, BorderLayout.NORTH);
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        SpringLayout sl_panel = new SpringLayout();
        sl_panel.putConstraint(SpringLayout.WEST, label_letters, 6, SpringLayout.EAST, count_letters);
        sl_panel.putConstraint(SpringLayout.NORTH, count_words, 0, SpringLayout.SOUTH, count_letters);
        sl_panel.putConstraint(SpringLayout.NORTH, label_letters, 11, SpringLayout.SOUTH, label_symbols);
        sl_panel.putConstraint(SpringLayout.NORTH, count_letters, -4, SpringLayout.NORTH, label_letters);
        sl_panel.putConstraint(SpringLayout.WEST, count_letters, 0, SpringLayout.WEST, count_symb);
        sl_panel.putConstraint(SpringLayout.EAST, count_letters, 0, SpringLayout.EAST, count_symb);
        sl_panel.putConstraint(SpringLayout.NORTH, plus, 0, SpringLayout.NORTH, count_uwords);
        sl_panel.putConstraint(SpringLayout.WEST, plus, 30, SpringLayout.EAST, label_uwords);
        sl_panel.putConstraint(SpringLayout.EAST, plus, 0, SpringLayout.EAST, scrollPane);
        sl_panel.putConstraint(SpringLayout.WEST, label_uwords, 6, SpringLayout.EAST, count_uwords);
        sl_panel.putConstraint(SpringLayout.WEST, label_words, 6, SpringLayout.EAST, count_words);
        sl_panel.putConstraint(SpringLayout.NORTH, label_symbols, 4, SpringLayout.NORTH, count_symb);
        sl_panel.putConstraint(SpringLayout.WEST, label_symbols, 6, SpringLayout.EAST, count_symb);
        sl_panel.putConstraint(SpringLayout.NORTH, label_uwords, 4, SpringLayout.NORTH, count_uwords);
        sl_panel.putConstraint(SpringLayout.NORTH, label_words, 4, SpringLayout.NORTH, count_words);
        sl_panel.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, panel);
        sl_panel.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, panel);
        sl_panel.putConstraint(SpringLayout.NORTH, count_uwords, 0, SpringLayout.SOUTH, count_words);
        sl_panel.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, count_uwords);
        sl_panel.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, panel);
        sl_panel.putConstraint(SpringLayout.EAST, count_uwords, 0, SpringLayout.EAST, count_symb);
        sl_panel.putConstraint(SpringLayout.WEST, count_uwords, 0, SpringLayout.WEST, count_symb);
        sl_panel.putConstraint(SpringLayout.EAST, count_words, 0, SpringLayout.EAST, count_symb);
        sl_panel.putConstraint(SpringLayout.NORTH, count_symb, 10, SpringLayout.NORTH, panel);
        sl_panel.putConstraint(SpringLayout.WEST, count_symb, 0, SpringLayout.WEST, panel);
        sl_panel.putConstraint(SpringLayout.EAST, count_symb, -100, SpringLayout.EAST, panel);
        sl_panel.putConstraint(SpringLayout.EAST, count_symb, -100, SpringLayout.EAST, panel);
        sl_panel.putConstraint(SpringLayout.WEST, count_words, 0, SpringLayout.WEST, panel);
        panel.setLayout(sl_panel);
        panel.add(count_symb);
        panel.add(help);
        panel.add(label_symbols);
        panel.add(count_letters);
        panel.add(label_letters);
        panel.add(count_words);
        panel.add(label_words);
        panel.add(count_uwords);
        panel.add(label_uwords);
        panel.add(plus);
        panel.add(scrollPane);

        textArea.setEnabled(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setViewportView(textArea);

        VisibleLabels(false);
        VisibleButtonsCount(false);
        VisibleArea(false);
        plus.setVisible(false);


        openfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VisibleLabels(false);
                VisibleButtonsCount(false);
                VisibleArea(false);
                plus.setText("+");
                click = 0;
                plus.setVisible(false);
                JFileChooser fileopen = new JFileChooser();
                fileopen.setFileFilter(new FileNameExtensionFilter(".txt","txt"));
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    x.filetostr(file.getAbsolutePath());
                    VisibleButtonsCount(true);
                }
            }
        });

        count_symb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label_symbols.setText(Integer.toString(x.count_symbols()));
                label_symbols.setVisible(true);
            }
        });

        count_letters.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label_letters.setText(Integer.toString(x.count_letters()));
                label_letters.setVisible(true);
            }
        });

        count_words.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label_words.setText(Integer.toString(x.count_words()));
                label_words.setVisible(true);
            }
        });

        count_uwords.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label_uwords.setText(Integer.toString(x.count_unic_words()));
                label_uwords.setVisible(true);
                plus.setVisible(true);
            }
        });

        plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(click == 0){
                    plus.setText("-");
                    VisibleArea(true);
                    textArea.setText(x.GetUnicWords());
                }
                if(click == 1){
                    plus.setText("+");
                    VisibleArea(false);
                    click = click -2;
                }
                click++;
            }
        });

    }
}
