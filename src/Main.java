//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.File;

public class Main implements ActionListener {
    //decorating the properties of TextEditor
    JFrame frame;

    JMenuBar menuBar;

    JMenu file,edit;

    //File menu items
    JMenuItem newFile,openFile,saveFile;
    //edit menu items
    JMenuItem cut,copy,paste,selectAll,close;

    JTextArea textArea;

    //create constructor
    Main(){
        //initialize a frame
        frame = new JFrame();


        //initialize menubar
        menuBar = new JMenuBar();

        //Intitialize textArea
        textArea = new JTextArea();

        //Initialize menus
        file = new JMenu("file");
        edit = new JMenu("edit");

        //Initializfile menu items
        newFile = new JMenuItem("new window");
        openFile = new JMenuItem("open file");
        saveFile = new JMenuItem("save file");

        // add action listeners to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        //add menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Intitialize menu items
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        close = new JMenuItem("close");

        //adding action listner to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //add menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);



        //Add menus to menubar
        menuBar.add(file);
        menuBar.add(edit);



        //set menubar to frame
        frame.setJMenuBar(menuBar); // it has spl we use set j ,but for all other we uswe add

        //create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //add text area to panel
        panel.add(textArea,BorderLayout.CENTER);
        //create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scrollpane to panel
        panel.add(scrollPane);

        //add panel to frame
        frame.add(panel);

//        //add text area to frame
//        frame.add(textArea);

        //set Dimensions of frame
        frame.setBounds(30,30,400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);//not hidden
        frame.setLayout(null);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            //perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            //perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            //perform close editor operation
            System.exit(0);
        }
        if(actionEvent.getSource()==openFile){
            //open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //if we click on open button
            if(chooseOption ==JFileChooser.APPROVE_OPTION){
                //getting the selected file
                File file = fileChooser.getSelectedFile();
                //get the path of selected file
                String filePath = file.getPath();
                try{
                    //Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialize buffer reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output =  "";
                    //read contents of file line by line
                    while((intermediate=bufferedReader.readLine())!= null){
                        output+=intermediate+"\n";
                    }
                    //set the output string to text area
                    textArea.setText(output);

                }

                catch (IOException ioException){
                    ioException.printStackTrace();
                }

            }
        }
        if(actionEvent.getSource()==saveFile){
            //initilaize file picker
            JFileChooser fileChooser = new JFileChooser("c:");
            //get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //check if we clicked on save button
            if(chooseOption ==JFileChooser.APPROVE_OPTION){
                //craete a new file with choosen directory path and file name
                File file  = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    FileWriter fileWriter = new FileWriter(file);
                    //initialize bufferdWriter
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==newFile){
            Main newTextEditor = new Main();
        }

    }
    public static void main(String[] args) {
        Main textEditor = new Main();
        // when this func trig main application will be created
    }
}