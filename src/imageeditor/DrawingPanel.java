package imageeditor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * This is a class represents a panel holds the image for 
 * which we can modify
 * @author Chang Zhao
 * @version 1.0 November 2017
 */
public class DrawingPanel extends JPanel 
{
    private String path; //initial image path is null
    private double degree = 0.0;
    private int imgWidth;
    private int imgHeight;
    private ImageIcon imageIcon;
    private Image myImage;
    private BufferedImage myBufferedImage;
   
    //constructor of the Drawing Panel
    public DrawingPanel() throws IOException {
        // create an ImageIcon
        imageIcon = new ImageIcon(path);
        // return image icon's image
        myImage = imageIcon.getImage();       
    }
       
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    	Graphics2D g2 = (Graphics2D) g;
    	double x = getWidth()/2;
    	double y = getHeight()/2;
        g2.rotate(Math.toRadians(this.degree), x, y);
        g2.drawImage(myImage, getWidth()/8, getHeight()/8,imageIcon.getImageObserver());
        g2.dispose();   
        
        //TODO: add more functionalities here, for example, resize, draw, add shape...
        
    }
    
    //set number of degrees to rotate
    public void setDegree(double degree)
    {
    	this.degree = degree;
    	repaint(); // refresh drawing panel
    }
    
    //set image file path to display image
    public void setPath(String path)
    {
        this.path = path;
        imageIcon = new ImageIcon(this.path);// reset path
        myImage = imageIcon.getImage();
        repaint(); // refresh drawing panel
    }
    
    // TODO: need to modify in order to make it work...
    public void setImageSize(int width, int height){
        //TODO code here
        myImage = myImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        repaint();
    }
    
//    public BufferedImage getBufferedImage()
//    {
//       BufferedImage BI = new BufferedImage(myImage.getWidth(null),myImage.getHeight(null),BufferedImage.TYPE_INT_ARGB);
//       return BI;
//    }

//    BufferedImage LoadBufferedImage(String FileName){
//        BufferedImage img = null;
//        try{
//            img = ImageIO.read(new File(FileName));
//        } catch (IOException e){
//            System.out.println("error");
//        }
//        return img;
//    }
} // end class DrawingPanel
