import java.awt.Color;
import java.awt.image.BufferedImage;
/**
*Clase Cifrador que esconde el mensaje en una imagen.
*
*/
public class Cifrador {
    private String firma="UM";
    private int Longitud=0;
    private BufferedImage foto=null;
    private int r,g,b;
    private Color color;
    private String mensajeBinario;
    private String mensajeOriginal;
    private int contador = 0;

    /**
    *Constructor vacio sin parametros
    */
    public Cifrador(){}

    /**
    *Dado el mensaje que se quiere ocultar, lo une a la firma
    *@param String con el mensaje
    */
    private void SetMensaje(String mensaje){
        String bi="";
        Longitud = mensaje.length() + firma.length() * 2;
        for( int i = 15; i>=0; i--){
           bi = bi + ( ( ( Longitud & ( 1<<i ) ) > 0 ) ? "1" : "0" ) ;
        }
        mensajeBinario = getMensajeToBinary(firma) + bi + getMensajeToBinary(mensaje);
    }

    /**
    *Metodo que oculta el mensaje
    *@param BufferedImage una imagen.
    *@param String cadena con el mensaje
    */
    public void OcultarMensaje(BufferedImage f,String mensaje){
        int tmp_count=0;
        SetMensaje(mensaje);
        foto = new BufferedImage(f.getWidth(), f.getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int fila=0;fila<foto.getHeight();fila++){
          for(int columna=0;columna<foto.getWidth();columna++){
                color = new Color( f.getRGB(columna, fila) );
                if(tmp_count<=this.mensajeBinario.length()){
                    String red = toBinary( (byte) color.getRed() );
                    String verde = toBinary( (byte) color.getGreen() );
                    String azul = toBinary( (byte) color.getBlue() );
                    red = ReemplazarLSB(red);
                    verde = ReemplazarLSB(verde);
                    azul = ReemplazarLSB(azul);
                    r = Integer.parseInt(red ,2);
                    g = Integer.parseInt(verde ,2);
                    b = Integer.parseInt(azul ,2);
                }else{
                   r = color.getRed();
                   g = color.getGreen();
                   b = color.getBlue();
                }
                foto.setRGB(columna, fila, new Color(r,g,b).getRGB());
                tmp_count+=3;
          }
        }
    }

   /**
   *Metodo privado que lee que los primeros pixeles sean UM para verificar que hay un mensaje
   *@param BufferedImage la imagen a cargar
   *@return boolean 
   */
   private boolean leerfirma(BufferedImage f){
       boolean ok=false;
       String t = "";
        for(int j=0;j<6;j++){
            color = new Color(f.getRGB(j, 0));
            String red = toBinary( (byte) color.getRed() );
            String verde = toBinary( (byte) color.getGreen() );
            String azul = toBinary( (byte) color.getBlue() );
            red = getLSB(red);
            verde = getLSB(verde);
            azul = getLSB(azul);
            t = t + red + verde + azul;
        }          
        if( toChar(t.substring(0, 8)).equals("U") &&  toChar(t.substring(8, 16)).equals("M") ){
            ok=true;
        }
       return ok;
   }

   /**
   *Metodo que lee la longitud del mensaje
   *@return BufferedImage
   */
   private void LeerLongitudDelMensaje(BufferedImage f){
        String t = "";
        for(int j=5;j<12;j++){
            color = new Color(f.getRGB(j,0));
            String red = toBinary( (byte) color.getRed() );
            String verde = toBinary( (byte) color.getGreen() );
            String azul = toBinary( (byte) color.getBlue() );
            red = getLSB(red);
            verde = getLSB(verde);
            azul = getLSB(azul);
            t = t + red + verde + azul;
        }
        this.Longitud = toCharInt(t.substring(1, 17));        
    }

    /**
    *Metodo que convierte la imagen al mensaje
    *@return String con el mensaje
    */
    public String getMensajeToImage(BufferedImage f){
        mensajeOriginal="No existe ningún mensaje oculto";
        if( leerfirma(f) ){
            LeerLongitudDelMensaje(f);
            String[] s = new String[this.Longitud];
            String aux="";
            for(int fila=0;fila<f.getHeight();fila++){
                for(int columna=0;columna<f.getWidth();columna++){
                    color = new Color(f.getRGB(columna, fila));
                    String red = toBinary( (byte) color.getRed() );
                    String verde = toBinary( (byte) color.getGreen() );
                    String azul = toBinary( (byte) color.getBlue() );
                    red = getLSB(red);
                    verde = getLSB(verde);
                    azul = getLSB(azul);
                    if(aux.length()<=(this.Longitud*8)){
                        aux = aux + red + verde + azul;
                    }else{
                        break;
                    }
                }
            }
            int conta =0;
            for(int a=0; a<(this.Longitud*8);a = a + 8){
                s[conta]=aux.substring(a, a + 8);                
                conta++;
            }
            mensajeOriginal = getMensajeToString(s);
        }
        return mensajeOriginal;
    }

    /**
   *Metodo que regresa la foto
   *@return BufferedImage
   */
    public BufferedImage getFoto(){
        return this.foto;
    }

    /**
   *Metodo que convierte a binario
   *@return String
   */
   private String toBinary(byte caracter){
        byte byteDeCaracter = (byte)caracter;
        String binario="";
        for( int i = 7; i>=0; i--){
           binario = binario + ( ( ( byteDeCaracter & ( 1<<i ) ) > 0 ) ? "1" : "0" ) ;
        }
        return binario;
    }

    /**
   *Metodo que convierte un int a char
   *@return String
   */
    private String toChar(String binario){
        int i = Integer.parseInt(binario ,2);
        String aChar = new Character((char)i).toString();
        return aChar;        
    }

    /**
   *Metodo que convierte a binario
   *@return int
   */
    private int toCharInt(String binario){
        int i = Integer.parseInt(binario ,2);        
        return i;
    }
   /**
   *Metodo que convierte un mensaje a binario
   *@return String
   */
    private String getMensajeToBinary(String mensaje){
        String men = "";
        char[] mensaje_tmp = mensaje.toCharArray();
        for(int i=0; i<mensaje_tmp.length;i++){
            men = men + toBinary( (byte) mensaje_tmp[i]);
        }
        return men;
    }

    /**
   *Metodo que convierte de binario a String
   *@return String
   */
    private String getMensajeToString(String[] mensaje){
        String men ="";
        for(int i=4; i<mensaje.length;i++){
            men = men + toChar(mensaje[i]) ;
        }
        return men;
    }

    /**
   *Metodo que reemplaza el bite menos significativo con un bit del mensaje 
   *@return String
   */
    private String  ReemplazarLSB(String colorRGB){
        if(contador < mensajeBinario.length()){
            colorRGB = colorRGB.substring(0,7) + mensajeBinario.substring(contador, contador+1);
            contador++;    
        }
        return colorRGB;
    }

    /**
   *Metodo que regresa el LSB
   *@return String
   */
    private String getLSB(String binario){
        return binario.substring(7, 8);
    }

}
