import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    // Declaring properties of TextEditor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    //File menu items
    JMenuItem newFile, openFile , saveFile;
    // Edit menu items
    JMenuItem cut, copy, paste, selectall, close;

    JTextArea textArea;
    TextEditor(){
   // Initialize a frame
        frame = new JFrame();
   // Initialize a menuBar
        menuBar = new JMenuBar();
    // Initialize textArea
       textArea = new JTextArea();
   // Initialize menu
        file =new JMenu("File");
        edit = new JMenu("Edit");
        //Initialize file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //Add action listener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        // Add file menu items to file
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectall = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        //Add action listener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        close.addActionListener(this);

        // Add edit menu items to edit
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        edit.add(close);
   // Add menus to menuBar
        menuBar.add(file);
        menuBar.add(edit);
   // Set menuBar to frame
       frame.setJMenuBar(menuBar);
    // Create Content Pane
        JPanel panel =new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
    // Add textarea to the panel
    panel.add(textArea,BorderLayout.CENTER);
    // create scroll panel
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED) ;
    // Add scrollpane to the panel
        panel.add(scrollPane);
     // Add panel to frame
     frame.add(panel);
   //Set Dimension of frame
        frame.setBounds(0 ,0 , 400 , 400);

        frame.setTitle("Text Editor");

        frame.setVisible(true);

        frame.setLayout(null);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
     if(actionEvent.getSource()==cut){
         textArea.cut();
     }
     if(actionEvent.getSource()==copy){
         textArea.copy();
     }
     if(actionEvent.getSource()==paste){
         textArea.paste();
     }
     if(actionEvent.getSource()==selectall){
         textArea.selectAll();
     }
     if(actionEvent.getSource()==close){
         System.exit(0);
     }
     if(actionEvent.getSource()==openFile){
         // Open a file chooser
         JFileChooser fileChooser = new JFileChooser("C");
         int chooseOption = fileChooser.showOpenDialog(null);
         // if we  click on open button
         if(chooseOption==JFileChooser.APPROVE_OPTION){
             // Getting selected file
             File file= fileChooser.getSelectedFile();
             // Get the path of selected file
             String filepath = file.getPath();
             try{
                 // Initialize file reader
                 FileReader fileReader= new FileReader(filepath);
                 // Initialise buffer reader
                 BufferedReader bufferedReader = new BufferedReader(fileReader);
                 String intermediate ="" , output ="";
                 while((intermediate= bufferedReader.readLine())!=null){
                     output+=intermediate+"\n";
                 }
                 //Set the Output to textArea
                 textArea.setText(output);
             }
             catch (FileNotFoundException fileNotFoundException){
                 fileNotFoundException.printStackTrace();
             }
             catch (IOException ioException){
                 ioException.printStackTrace();
             }
         }
     }
     if(actionEvent.getSource()==saveFile){
         // Open a filechooser
         JFileChooser fileChooser=new JFileChooser("C");
         int chooseOption = fileChooser.showSaveDialog(null);
         // if we click on open button
         if(chooseOption==JFileChooser.APPROVE_OPTION){
             //Create a new file with choosen directory path and file name
             File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
             try{
                 //initialize filewriter
                 FileWriter fileWriter = new FileWriter(file);
                 //initialise bufferwriter
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                 //Write content of text area to file
                 textArea.write(bufferedWriter);
                 bufferedWriter.close();
             }
             catch (IOException ioException){
                 ioException.printStackTrace();
             }
         }
     }
     if(actionEvent.getSource()==newFile){
         TextEditor newTextEditor= new TextEditor();
     }
    }
    public static void main(String[] args) {
         TextEditor textEditor = new TextEditor();
    }
}