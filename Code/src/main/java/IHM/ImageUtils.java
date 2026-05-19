package IHM;

import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageUtils {
    
    public static void setIcon(JLabel label, String imagePath, int width, int height){
        URL resource = ImageUtils.class.getResource(imagePath);
        ImageIcon icon = new ImageIcon(resource);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(img));
        label.setPreferredSize(new Dimension(width, height));
        label.setSize(new Dimension(width, height));
    }
    
    public static void setIcon(JLabel label, String imagePath){
        setIcon(label, imagePath, 60, 60);
    }   
}
