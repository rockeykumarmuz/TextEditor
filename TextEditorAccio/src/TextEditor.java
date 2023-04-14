import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;

    JMenuBar menuBar;
    JMenu file, edit;

    // file menu items
    JMenuItem newFile, openFile, saveFile;

    //edit menu items
    JMenuItem cut, copy, paste, selectAll, close;

    // Text Area
    JTextArea textArea;

    TextEditor() {

        // Initialize a frame
        frame= new JFrame();

        //Initialize menubar
        menuBar= new JMenuBar();
        // adding menu to menubar

        // Initializing text Area
        textArea= new JTextArea();

        // initialize menu
        file= new JMenu("File");
        edit= new JMenu("Edit");

        // intializing file items
        newFile=new JMenuItem("New Window");
        openFile= new JMenuItem("Open File");
        saveFile= new JMenuItem("Save File");

        // add action listener to file menu it basically adding object of texteditor every times
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // Add menu items to file Menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);


        // Now initializing Edit items
        cut= new JMenuItem("Cut");
        copy= new JMenuItem("Copy");
        paste= new JMenuItem("Paste");
        selectAll= new JMenuItem("SelectAll");
        close= new JMenuItem("Close");

        // add action listener to file menu it basically adding object of texteditor every times
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // Adding Edit items into the Edit Menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // Adding edit menu to the MenuBar

        menuBar.add(file);
        menuBar.add(edit);

        // adding menubar into the frame its a special function where we need setmenubar insted of add
        frame.setJMenuBar(menuBar);
        // create content panel
        JPanel panel= new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        // add text pane to panel
        panel.add(textArea, BorderLayout.CENTER);
        // create scroll pane
        JScrollPane scrollPane= new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // add scroll pane to panel
        panel.add(scrollPane);
        // add panel to frame
        frame.add(panel);

        // setting dimension of the window
        frame.setBounds(100, 100, 400, 400);
        // setting title of project as a name
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==cut) {
            // we will perform cut operation
            // because particular area we need to cut so we will select text area
            textArea.cut();
        }
        if(actionEvent.getSource()==copy) {
            // we will perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource()==paste) {
            // we will perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll) {
            // we will perform selectAll operation
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close) {
            // we will perform close operation
            // status we need to provide 0 so that it can understand its closes
            System.exit(0);
        }
        if(actionEvent.getSource()==openFile) {
            JFileChooser fileChooser= new JFileChooser("f:");
            int chooseOption = fileChooser.showOpenDialog(null);
            // if we have clicked on open button
            if(chooseOption == JFileChooser.APPROVE_OPTION) {
                //getting selected file
                File file= fileChooser.getSelectedFile();
                String filePath= file.getPath();

                try{
                    // initialize file chooser
                    FileReader fileReader= new FileReader(filePath);
                    // initialize buffered Reader
                    BufferedReader bufferedReader= new BufferedReader(fileReader);
                    String intermediate= "", output= "";
                    // record contests of file by line
                    while((intermediate = bufferedReader.readLine())!=null) {
                        output+= intermediate+"\n";
                    }
                    // see the output string to text Area
                    textArea.setText(output);

                } catch(IOException fileNotFoundExceptions) {
                    fileNotFoundExceptions.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()== saveFile) {
            JFileChooser fileChooser= new JFileChooser("f");
            // set chooso option for file choooser
            int chooseOption= fileChooser.showSaveDialog(null);
            // check if we clicked on save button
            if(chooseOption== JFileChooser.APPROVE_OPTION) {
                // create a new file with choosen directory path and file name
                File file= new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");

                try{
                    // Initializer file writer
                    FileWriter fileWriter = new FileWriter(file);
                    // Initialize buffered writer
                    BufferedWriter bufferedWritter= new BufferedWriter(fileWriter);
                    // Write contents if text area to file
                    textArea.write(bufferedWritter);
                    bufferedWritter.close();
                }
                catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()== newFile) {
            TextEditor newTextEditor= new TextEditor();
        }
    }

    public static void main(String args[]) {
         TextEditor textEditor= new TextEditor();
    }
}
