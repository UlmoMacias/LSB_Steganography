import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Clase Imagen que trabaja con las imagenes
 *
 */
public class Imagen extends JPanel {
    private BufferedImage foto;
    private int ancho=0;
    private int alto=0;
    private BufferedImage imagen_en_memoria;
    private JFileChooser fileChooser = new JFileChooser();
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Imagen","jpg","png","bmp");
    private File Directorio = fileChooser.getCurrentDirectory();
    private String PathFile = "";

    /**
    *Constructor de una imagen
    */
    public Imagen(){
            this.setVisible(true);
            this.repaint();
    }

    /**
    *Se sobreescribe el metodo paint de JPanel
    *@Override paintComponent
    */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        //se crea una imagen en memoria
        imagen_en_memoria = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = imagen_en_memoria.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //se dibuja las imagenes en el contenedor
        g2d.drawImage(foto,0,0,this);
        //se dibuja toda la imagen
        g2.drawImage(imagen_en_memoria, 0, 0, this);
    }


    /**
    *Metodo que regresa una foto
    *@return una imagen
    */
    public BufferedImage getFoto(){
        return this.foto;
    }

    /**
    * Metodo que abre una imagen.
    *@return boolean 'true' si se logro abrir 'false' si no 
    */
    public boolean Abrir_Imagen(){
       boolean ok=false;
       fileChooser = new JFileChooser();
       fileChooser.setFileFilter(filter);
       fileChooser.setCurrentDirectory(new java.io.File("e:/"));
       fileChooser.setCurrentDirectory( Directorio );
       int result = fileChooser.showOpenDialog(null);
       if ( result == JFileChooser.APPROVE_OPTION ){
            try {                
                foto = ImageIO.read( fileChooser.getSelectedFile() );
                PathFile = fileChooser.getSelectedFile().getPath();
                ancho =foto.getWidth();
                alto = foto.getHeight();
                this.setSize(new Dimension(ancho, alto));
                this.repaint();
                this.Directorio = fileChooser.getCurrentDirectory();
                ok=true;
            } catch (IOException ex) {

            }
        }
       return ok;
    }

    /**
    *Metodo que escribe una imagen nueva
    */
    public void guardar_imagen( BufferedImage foto ){        
        try {
            String tmp_file = this.PathFile.substring(0, this.PathFile.length()-4) + "(ConMensajeCifrado).bmp";
            //se escribe en disco            
            ImageIO.write(foto, "bmp", new File( tmp_file ));
            //System.out.println( tmp_file );
            JOptionPane.showMessageDialog(null, "La imagen se guardo en:\n"
                    + "File: " + tmp_file);
	} catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: no se pudo guardar la imagen...");
	}
   }

}
