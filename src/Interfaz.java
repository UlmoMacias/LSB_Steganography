import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

/**
* Clase Interfaz que muestra los 4 botones y el area de texo
*
*/
public class Interfaz extends javax.swing.JFrame{

    BufferedImage foto;
    Imagen laImagen = new Imagen();
    Cifrador cifrador = new Cifrador();
    Texto text = new Texto();

    /** 
    *Constructor de una interfaz
     */
    public Interfaz() {
       initComponents();
       this.setTitle("Proyecto 2 : Esteganografia");
       this.setLocationRelativeTo(null);
       laImagen.updateUI();
       this.cmdGuardarImagen.setEnabled(false);
       this.cmdOcultarMensaje.setEnabled(false);
       this.cmdOcultarMensajeDeTexto.setEnabled(false);
       this.cmdVerMensaje.setEnabled(false);
    }


    /** 
    * Metodo que inicia la interfaz
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel = new javax.swing.JPanel();
        cmdAbrirImagen = new javax.swing.JButton();
        cmdOcultarMensaje = new javax.swing.JButton();
        cmdOcultarMensajeDeTexto = new javax.swing.JButton();
        cmdGuardarImagen = new javax.swing.JButton();
        cmdVerMensaje = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtMensaje = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel.setLayout(new java.awt.GridLayout(5, 1, 10, 10));

        cmdAbrirImagen.setText("Abrir Imagen");
        cmdAbrirImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAbrirImagenActionPerformed(evt);
            }
        });
        jPanel.add(cmdAbrirImagen);

        cmdOcultarMensaje.setText("Ocultar Mensaje");
        cmdOcultarMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOcultarMensajeActionPerformed(evt);
            }
        });
        jPanel.add(cmdOcultarMensaje);

        cmdOcultarMensajeDeTexto.setText("Ocultar Mensaje de Texto");
        cmdOcultarMensajeDeTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOcultarMensajeDeTextoActionPerformed(evt);
            }
        });
        jPanel.add(cmdOcultarMensajeDeTexto);

        cmdGuardarImagen.setText("Guardar Imagen");
        cmdGuardarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGuardarImagenActionPerformed(evt);
            }
        });
        jPanel.add(cmdGuardarImagen);

        cmdVerMensaje.setText("Ver Mensaje");
        cmdVerMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdVerMensajeActionPerformed(evt);
            }
        });
        jPanel.add(cmdVerMensaje);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jtMensaje.setColumns(20);
        jtMensaje.setRows(5);
        jScrollPane2.setViewportView(jtMensaje);

        
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        )
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                    )
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    /*
    *Metodo que abre una imagen y activa los botones necesarios para proseguir
    */
    private void cmdAbrirImagenActionPerformed(java.awt.event.ActionEvent evt) {
      if( laImagen.Abrir_Imagen() ){        
        this.cmdOcultarMensaje.setEnabled(true);
        this.cmdOcultarMensajeDeTexto.setEnabled(true);
        this.cmdVerMensaje.setEnabled(true);
        this.jtMensaje.setText("");
            laImagen.updateUI();
       }
    }

    /*
    *Metodo que oculta un mensaje y cambia el estado de los botones necesarios para proseguir
    */
    private void cmdOcultarMensajeActionPerformed(java.awt.event.ActionEvent evt) {
        cifrador.OcultarMensaje( laImagen.getFoto(),jtMensaje.getText() );
        cmdOcultarMensaje.setEnabled(false);
        cmdOcultarMensajeDeTexto.setEnabled(false);
        cmdGuardarImagen.setEnabled(true);
        JOptionPane.showMessageDialog(null, "El mensaje se oculto en la imagen");
    }

    /*
    *Metodo que oculta un mensaje desde un archivo y cambia el estado de los botones necesarios para proseguir
    */
    private void cmdOcultarMensajeDeTextoActionPerformed(java.awt.event.ActionEvent evt) {
        if(text.abrirArchivo() ){        
            laImagen.updateUI();
            cifrador.OcultarMensaje( laImagen.getFoto(), text.getTexto());    
            cmdOcultarMensajeDeTexto.setEnabled(false);
            cmdOcultarMensaje.setEnabled(false);
            cmdGuardarImagen.setEnabled(true);
            JOptionPane.showMessageDialog(null, "El mensaje se oculto en la imagen");
       }
    }

     /*
    *Metodo que guarda una imagen y cambia el estado de los botones necesarios para proseguir
    */
    private void cmdGuardarImagenActionPerformed(java.awt.event.ActionEvent evt) {
        laImagen.guardar_imagen( cifrador.getFoto() );
        cmdGuardarImagen.setEnabled(false);
    }

    /*
    *Metodo que revela un mensaje
    */
    private void cmdVerMensajeActionPerformed(java.awt.event.ActionEvent evt) {
        this.jtMensaje.setText("");
        this.jtMensaje.setText( cifrador.getMensajeToImage( laImagen.getFoto() ) );
    }

    /*
    *Metodo main de una interfaz
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    private javax.swing.JButton cmdAbrirImagen;
    private javax.swing.JButton cmdGuardarImagen;
    private javax.swing.JButton cmdOcultarMensaje;
    private javax.swing.JButton cmdOcultarMensajeDeTexto;
    private javax.swing.JButton cmdVerMensaje;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jtMensaje;

}
