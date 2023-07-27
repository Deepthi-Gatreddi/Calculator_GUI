package calculator_ui;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {    
 
	private static final long serialVersionUID = 1L;
	JPanel[] row = new JPanel[5];   //set of multiple components is JPanel
    JButton[] button = new JButton[19];    //total 19 buttons and JButton is a class in swing
    String[] buttonString = {"7", "8", "9", "+",
                             "4", "5", "6", "-",
                             "1", "2", "3", "*",
                             ".", "/", "C", "�",
                             "+/-", "=", "0"};
    int[] dimW = {430,70,150,140};
    int[] dimH = {50, 60};
    Dimension displayDimension = new Dimension(dimW[0], dimH[0]); //for text area
    Dimension regularDimension = new Dimension(dimW[1], dimH[1]); //for numbers
    Dimension rColumnDimension = new Dimension(dimW[2], dimH[1]); //for symbols
    Dimension zeroButDimension = new Dimension(dimW[3], dimH[1]); //for zero button
    boolean[] function = new boolean[4];
    double[] temporary = {0, 0};
    JTextArea display = new JTextArea(2,25);   //to display text 
    Font font = new Font("Times new Roman", Font.BOLD, 20);
    
    Calculator() {
        super("Calculator");  //calling the constructor of JFrame to set the title
        setDesign();
        setSize(500, 350);   //setting JFrame window size
        setResizable(false);    //not allowing user to resize window --> to look better
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        GridLayout grid = new GridLayout(5,5);
        setLayout(grid);
        
        for(int i = 0; i < 4; i++)
            function[i] = false;
        
        FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
        FlowLayout f2 = new FlowLayout(FlowLayout.CENTER,1,1);  //1->horizontal gap 1->vgap
        for(int i = 0; i < 5; i++)
            row[i] = new JPanel();  
        row[0].setLayout(f1);   //text area
        for(int i = 1; i < 5; i++)
            row[i].setLayout(f2);    //remaining rows with horizontal&vertical gap
        
        //setting text in button and adding action listener                       
        for(int i = 0; i < 19; i++) {    
            button[i] = new JButton();
            button[i].setText(buttonString[i]);
            button[i].setBackground(Color.white);
            button[i].setFont(font);
            button[i].addActionListener(this);
        }
        
        //setting properties to text area
        display.setFont(font);
        display.setEditable(false);   //not allowing user to enter values using keyboard
        display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        display.setPreferredSize(displayDimension);
        
        for(int i = 0; i < 14; i++)
            button[i].setPreferredSize(regularDimension);
        for(int i = 14; i < 18; i++)
            button[i].setPreferredSize(rColumnDimension);
        button[18].setPreferredSize(zeroButDimension);
        
        //adding first row to JFrame
        row[0].add(display); // Adds text view to JPanel
        add(row[0]); // Adds JPanel to Frame
        
       
        for(int i = 0; i < 4; i++) 
            row[1].add(button[i]);  //adding each button to JPanel for text view
        row[1].add(button[14]);     
        add(row[1]);                //adding second JPanel/row to JFrame
        
        for(int i = 4; i < 8; i++)
            row[2].add(button[i]);
        row[2].add(button[15]);
        add(row[2]);
        
        for(int i = 8; i < 12; i++)
            row[3].add(button[i]);
        row[3].add(button[16]);
        add(row[3]);
        
        row[4].add(button[18]);
        for(int i = 12; i < 14; i++)
            row[4].add(button[i]);
        row[4].add(button[17]);
        add(row[4]);
        
        setVisible(true);             //Making the JFrame to visible
        
    }
    
    public void clear() {
        try {
            display.setText("");
            for(int i = 0; i < 4; i++)
                function[i] = false;
            for(int i = 0; i < 2; i++)
                temporary[i] = 0;
        } catch(NullPointerException e) {  
        }
    }
    
    public void getSqrt() {
        try {
            double value = Math.sqrt(Double.parseDouble(display.getText()));
            display.setText(Double.toString(value));
        } catch(NumberFormatException e) {
        }
    }
    
    public void getPosNeg() {
        try {
            double value = Double.parseDouble(display.getText());
            if(value != 0) {
                value = value * (-1);
                display.setText(Double.toString(value));
            }
            else {
            }
        } catch(NumberFormatException e) {
        }
    }
    
    //to calculate the value
    public void getResult() {
        double result = 0;
        temporary[1] = Double.parseDouble(display.getText());
        try {
            if(function[2] == true)
                result = temporary[0] * temporary[1];
            else if(function[3] == true)
                result = temporary[0] / temporary[1];
            else if(function[0] == true)
                result = temporary[0] + temporary[1];
            else if(function[1] == true)
                result = temporary[0] - temporary[1];
            display.setText(Double.toString(result));
            for(int i = 0; i < 4; i++)
                function[i] = false;
        } catch(NumberFormatException e) {
        }
    }
    
    //it helps to look the interface more modern and pleasing using swing
    public final void setDesign() {
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception e) {   
        }
    }
    
    //when a button is clicked this function will be called from JButton class,addActionListener method
    @Override
    public void actionPerformed(ActionEvent ae) {
    	
        if(ae.getSource() == button[0])  //getSource will give which button is clicked
            display.append("7");         //making that to visible in text area
        if(ae.getSource() == button[1])
            display.append("8");
        if(ae.getSource() == button[2])
            display.append("9");
        if(ae.getSource() == button[3]) {
        	//add function[0]
            temporary[0] = Double.parseDouble(display.getText());
            function[0] = true;
            display.setText("");  //after entering operator we blank the screen
        }
        if(ae.getSource() == button[4])
            display.append("4");
        if(ae.getSource() == button[5])
            display.append("5");
        if(ae.getSource() == button[6])
            display.append("6");
        if(ae.getSource() == button[7]) {
            //subtract function[1]
            temporary[0] = Double.parseDouble(display.getText());
            function[1] = true;
            display.setText("");
        }
        if(ae.getSource() == button[8])
            display.append("1");
        if(ae.getSource() == button[9])
            display.append("2");
        if(ae.getSource() == button[10])
            display.append("3");
        if(ae.getSource() == button[11]) {
            //multiply function[2]
            temporary[0] = Double.parseDouble(display.getText());
            function[2] = true;
            display.setText("");
        }
        if(ae.getSource() == button[12])
            display.append(".");
        if(ae.getSource() == button[13]) {
            //divide function[3]
            temporary[0] = Double.parseDouble(display.getText());
            function[3] = true;
            display.setText("");
        }
        if(ae.getSource() == button[14])
            clear();
        if(ae.getSource() == button[15])
            getSqrt();
        if(ae.getSource() == button[16])
            getPosNeg();
        if(ae.getSource() == button[17])
            getResult();
        if(ae.getSource() == button[18])
            display.append("0");
    }
    
    public static void main(String[] arguments) {
        Calculator c = new Calculator();
    }
}