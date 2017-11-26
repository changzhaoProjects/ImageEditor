/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageeditor;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.image.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;

/**
 *
 * @author changzhao
 */
public class ImageEditor extends JFrame {

    /**
     * Creates new form ImageViewerFrame
     */
    // Variables declaration
    private JPanel controlPanel;
    private DrawingPanel drawingPanel; 
    private JFileChooser jfcOpen;
    private JFileChooser jfcSave;
    
    private JButton openButton;
    private JButton clearButton;
    private JButton saveButton;
    private JButton rotateButton;
    
    private JButton resizeButton;
    private JButton cropButton;
    private JComboBox addShapeComboBox;
    private JButton addTextButton;
    
    private JButton drawButton;
    private JComboBox colorComboBox;
    
    
    private JTextField imageWidth;
    private JTextField imageHeight;
        
    private double degree = 0.0;

    // End of variables declaration 
            
    public ImageEditor() {
        initComponents();  
    }
                 
    private void initComponents() {
       
        // initialization of variables
        controlPanel = new JPanel(new GridLayout(2,6));
        
        try {
            drawingPanel = new DrawingPanel();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        
        imageWidth = new JTextField(3);
        imageHeight = new JTextField(3);
        
        openButton = new JButton("Open");
        clearButton = new JButton("Clear");
        saveButton = new JButton("Save");
        rotateButton = new JButton("Rotate");
        resizeButton = new JButton("Resize");
        
        cropButton = new JButton("Crop");
        String[] shapeStrings = { "Circle", "Square", "Triangle"};
        addShapeComboBox = new JComboBox(shapeStrings);
        addShapeComboBox.setSelectedIndex(0);
        addTextButton = new JButton("Add Text");
        
        drawButton = new JButton("Draw");
        String[] colorStrings = { "Red","Bule","Green","Black"};
        colorComboBox = new JComboBox(colorStrings);
        colorComboBox.setSelectedIndex(0);

        jfcOpen = new JFileChooser();
        jfcOpen.setCurrentDirectory(new File("C:\\Users\\changzhao\\Pictures"));
        jfcOpen.setFileFilter(new FileNameExtensionFilter("PNG images","png"));
        
        jfcSave = new JFileChooser();
        jfcSave.setCurrentDirectory(new File("C:\\Users\\changzhao\\Pictures"));
        
        // file browser only shows png files
        jfcSave.setFileFilter(new FileNameExtensionFilter("PNG images","png"));
        
        this.add(controlPanel,BorderLayout.PAGE_START);
        this.add(drawingPanel,BorderLayout.CENTER);

        // add actionListerner to each component and add the component to the J
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                OpenActionPerformed(evt);
            }
        });
        controlPanel.add(openButton);

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });
        controlPanel.add(clearButton);


        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });
        controlPanel.add(saveButton);
        
        rotateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                RotateActionPerformed(evt);
            }
        });
        controlPanel.add(rotateButton);
        
        resizeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ResizeActionPerformed(evt);
            }
        });
        controlPanel.add(resizeButton);
        controlPanel.add(imageWidth);
        controlPanel.add(imageHeight);
                
        // TODO: add action listeners of the rest of the panel components here

        
        controlPanel.add(cropButton);
        controlPanel.add(addShapeComboBox);
        controlPanel.add(addTextButton);
        
        controlPanel.add(drawButton);
        controlPanel.add(colorComboBox);
        

    }                    
    
    // define the ActionPerformed method for each component here
    public void OpenActionPerformed(ActionEvent evt) {                                         

        //open file from file browser and display the image in the drawing panel
        if (jfcOpen.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            //get selected image file
            File fileOpen = jfcOpen.getSelectedFile();
            
            //get image file path
            String fileName = fileOpen.toString();
            
            //set image path and display image
            drawingPanel.setPath(fileName);   
        }
    }                                        

    public void ClearActionPerformed(java.awt.event.ActionEvent evt) {
        //set image path to null and display the empty image
        drawingPanel.setPath("null");   
    }                                         

    public void SaveActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // select file and display
        if (jfcSave.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
            //get selected file name
            File fileSave = jfcSave.getSelectedFile();
            String fileSaveName = fileSave.toString();

            try {
                SaveScreenShot(drawingPanel,fileSaveName);
            } catch (Exception e){
                System.out.println("image failed to save!");
            }
        }
    } 
    
    // rotate image 90 degrees clockwise
    public void RotateActionPerformed(java.awt.event.ActionEvent evt) {                                          
        degree = degree + 90;
        drawingPanel.setDegree(degree); 
    }  
    
    // TODO: need to fix some bugs...
    public void ResizeActionPerformed(ActionEvent evt){
        int w = Integer.parseInt(imageWidth.getText());
        int h = Integer.parseInt(imageHeight.getText());
        drawingPanel.setImageSize(w, h);
    }
    
    // get the screenshot of a specified component
    public static BufferedImage getScreenShot (Component component){
        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(),BufferedImage.TYPE_INT_RGB);
        component.paint(image.getGraphics());
        return image;
    }
    
    // save the screenshot of a specified component
    public void SaveScreenShot(Component component, String filename) throws Exception{
        BufferedImage imag = getScreenShot(component);
        ImageIO.write(imag, "png", new File(filename));
    }

    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ImageEditor imageEditor = new ImageEditor(); // create a JFrame object
                imageEditor.setVisible(true);
                imageEditor.setSize(1000, 1200); // set JFrame size
                imageEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });
    }  
}

  


