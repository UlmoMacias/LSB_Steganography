     import javax.swing.JFileChooser;
     import javax.swing.filechooser.FileNameExtensionFilter;
     import java.io.IOException;
     import java.io.BufferedReader;
     import java.io.FileReader;
     import java.io.File;
     import javax.swing.JOptionPane;
     import javax.swing.JPanel; 

     /**
     *Clase Texto que trabaja con archivos de texto.
     */
     public class Texto{

       private static String txt;
       private static JFileChooser fileChooser = new JFileChooser();
       private static File Directorio = fileChooser.getCurrentDirectory();
       private static FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Texto","txt");
       private static String PathFile = ""; 

     /**
     * Constructor Clase Texto vacio
     */
     public Texto(){

     }
     
      /**
      * Metodo que abre un archivo 
      *@return boolean 'true' si se logro abrir 'false' si no 
      */
     public boolean abrirArchivo() {
       String aux="";   
       txt="";
       try
       {
        JFileChooser file=new JFileChooser();
        file.showOpenDialog(file);
        File abre=file.getSelectedFile();

        if(abre!=null)
        {     
         FileReader archivos=new FileReader(abre);
         BufferedReader lee=new BufferedReader(archivos);
         while((aux=lee.readLine())!=null)
         {
          txt+= aux+ "\n";
        }
        lee.close();
      }    
      return true;
    }
    catch(IOException ex)
    {
      JOptionPane.showMessageDialog(null,ex+"" +
        "\nNo se ha encontrado el archivo",
        "Error",JOptionPane.WARNING_MESSAGE);
      return false;
    }
  }

  /**
  * Metodo que regresa el texto
  * @return String
  */
  public static String getTexto(){
   return txt;
  }
  }