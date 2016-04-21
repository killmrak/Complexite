package Gui;
//encule
import DSatur.GrapheDSat;
import Graphe.Arret;
import Graphe.Sommet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.util.Iterator;
import javax.swing.JPanel;

 

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ruskiy
 */
public class Frame extends javax.swing.JFrame {
    public class GrapheGui extends GrapheDSat{
    private Graphics graphics;
    private  boolean cliqueSurSommet=false;
    private  SommetGui sommetClique;
   public  final Color[] couleurs={Color.gray,Color.red,Color.blue,Color.yellow,Color.PINK,Color.magenta,Color.black};
    private JFrame applet;
    
    public GrapheGui(Graphics g,JFrame jf){
        applet=jf;
        graphics=g;
    }
    public void dessine(){
        Iterator<Sommet> itsommets=this.getSommets().iterator();
        while(itsommets.hasNext()){
            SommetGui sommet=(SommetGui)itsommets.next();
            sommet.PanelForMethods.update(sommet.PanelForMethods.getGraphics());
        }
    }
  
    public class SommetGui extends Sommet implements MouseListener{
        private int x;
        private int y;
        private JPanel PanelForMethods=new JPanel(){
            @Override
            public void paint(Graphics g){
                g.setColor(couleurs[getCouleur()]);
                g.fillOval(0, 0, 40, 40);
                g.setColor(Color.WHITE);
                g.drawString(getNumero()+"", 0+20, 0+20);
            }

        };


  public     SommetGui(int nbr,int x,int y) {
            super(nbr,0);
            this.x=x;
            this.y=y;
        }
        public JPanel dessine(){
            applet.getContentPane().add(PanelForMethods);
            PanelForMethods.setBounds(x, y, 40, 40);
            PanelForMethods.setOpaque(false);
            PanelForMethods.setCursor(new Cursor(Cursor.HAND_CURSOR));
            PanelForMethods.addMouseListener(SommetGui.this);
            PanelForMethods.paint(PanelForMethods.getGraphics());
            return PanelForMethods;
        }
        public void mouseClicked(MouseEvent e) {
            System.out.println("click sur sommet");
            if(cliqueSurSommet){
                ArretGui a=new ArretGui(sommetClique, this);
                if(GrapheGui.this.ajouterArret(a))
                {
                    applet.getContentPane().add(a.dessine());
                }
                cliqueSurSommet=false;
                
            }else{
                sommetClique=this;
                cliqueSurSommet=true;
            }
        }

        public void mousePressed(MouseEvent e) {
        //    throw new UnsupportedOperationException("Not supported yet.");
        
        this.setCouleur(6);
            this.PanelForMethods.update(PanelForMethods.getGraphics());
        }

        public void mouseReleased(MouseEvent e) {
        //    throw new UnsupportedOperationException("Not supported yet.");
        
            this.setCouleur(0);
            this.PanelForMethods.update(PanelForMethods.getGraphics());
        }

        public void mouseEntered(MouseEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    class ArretGui extends Arret{
         private JPanel PanelForMethods=new JPanel(){
            @Override
            public void paint(Graphics g){
                g.setColor(couleurs[0]);
                SommetGui _1=(SommetGui) ArretGui.this.get_1();
                SommetGui _2=(SommetGui) ArretGui.this.get_2();
                int w=Math.abs(_2.x-_1.x);
                int h=Math.abs(_2.y-_1.y);
                if(_1.x<_2.x){
                    if(_1.y<_2.y)
                        g.drawLine(0, 0, w, h);
                    else
                        g.drawLine(0,h,w ,0 );
                }else
                    if(_1.y>_2.y)
                        g.drawLine(0, 0, w, h);
                    else
                        g.drawLine(0,h,w ,0 );


            }

        };

        ArretGui(SommetGui _1,SommetGui _2){
            super(_1,_2);
        }
        public JPanel dessine(){
            applet.getContentPane().add(PanelForMethods);
            PanelForMethods.setOpaque(false);
            PanelForMethods.paint(PanelForMethods.getGraphics());
            SommetGui _1=(SommetGui) ArretGui.this.get_1();
            SommetGui _2=(SommetGui) ArretGui.this.get_2();
            int x,y,w=Math.abs(_2.x-_1.x),h=Math.abs(_2.y-_1.y);

             if(_1.x<_2.x){
                 x=_1.x;
                 if(_1.y<_2.y)
                     y=_1.y;
                 else
                     y=_2.y;
                }else{
                    x=_2.x;
                    if(_1.y>_2.y)
                        y=_2.y;
                    else
                        y=_1.y;
                }
            PanelForMethods.setBounds(x+20, y+20,w, h);
            return PanelForMethods;
        }
    }
    
}

private Coloration2 coloration2 = new Coloration2();

class Coloration2 extends JFrame implements MouseListener {
private GrapheGui graphedsat;
    public void mouseClicked(MouseEvent e){

	if(e.getButton()==MouseEvent.BUTTON1){
            GrapheGui.SommetGui s=graphedsat.new SommetGui(graphedsat.size()+1,e.getPoint().x,e.getPoint().y);
	    graphedsat.ajouterSommet(s);
            coloration2.getContentPane().add(s.dessine());
	}else if(e.getButton()==MouseEvent.BUTTON2){
	}else if(e.getButton()==MouseEvent.BUTTON3){
            graphedsat.colorier();
            graphedsat.dessine();
	}
    }
   
    public void 	mouseEntered(MouseEvent e){}
    public void 	mouseExited(MouseEvent e){}
    public void 	mousePressed(MouseEvent e){}
    public void 	mouseReleased(MouseEvent e){}
 
    
  
}


private Coloration1 coloration1 = new Coloration1();

class Coloration1 extends JFrame implements MouseListener {
private GrapheGui grapheback;
    public void mouseClicked(MouseEvent e){

	if(e.getButton()==MouseEvent.BUTTON1){
            GrapheGui.SommetGui s=grapheback.new SommetGui(grapheback.size()+1,e.getPoint().x,e.getPoint().y);
	    grapheback.ajouterSommet(s);
            coloration1.getContentPane().add(s.dessine());
	}else if(e.getButton()==MouseEvent.BUTTON2){
	}else if(e.getButton()==MouseEvent.BUTTON3){
            grapheback.colorier();
            grapheback.dessine();
	}
    }
   
    public void 	mouseEntered(MouseEvent e){}
    public void 	mouseExited(MouseEvent e){}
    public void 	mousePressed(MouseEvent e){}
    public void 	mouseReleased(MouseEvent e){}
 
    
  
}

private Coloration3 coloration3 = new Coloration3();

class Coloration3 extends JFrame implements MouseListener {
private GrapheGui graphetabu;
    public void mouseClicked(MouseEvent e){

	if(e.getButton()==MouseEvent.BUTTON1){
            GrapheGui.SommetGui s=graphetabu.new SommetGui(graphetabu.size()+1,e.getPoint().x,e.getPoint().y);
	    graphetabu.ajouterSommet(s);
            coloration3.getContentPane().add(s.dessine());
	}else if(e.getButton()==MouseEvent.BUTTON2){
	}else if(e.getButton()==MouseEvent.BUTTON3){
            graphetabu.colorier();
            graphetabu.dessine();
	}
    }
   
    public void 	mouseEntered(MouseEvent e){}
    public void 	mouseExited(MouseEvent e){}
    public void 	mousePressed(MouseEvent e){}
    public void 	mouseReleased(MouseEvent e){}
 
    
  
}
    
private void delete1ActionPerformed(java.awt.event.ActionEvent evt){
      coloration1.grapheback.SuprimerTout();
      coloration1.getContentPane().removeAll();
      coloration1.getContentPane().repaint();
      coloration1.getContentPane().revalidate();
      coloration1.add(delete1,BorderLayout.SOUTH);   
      delete1.setPreferredSize(new Dimension(50,50));

 }
private void delete2ActionPerformed(java.awt.event.ActionEvent evt){
      coloration2.graphedsat.SuprimerTout();
      coloration2.getContentPane().removeAll();
      coloration2.getContentPane().repaint();
      coloration2.getContentPane().revalidate();
      coloration2.add(delete2,BorderLayout.SOUTH);   
      delete2.setPreferredSize(new Dimension(50,50));

 }
private void delete3ActionPerformed(java.awt.event.ActionEvent evt){
      coloration3.graphetabu.SuprimerTout();
      coloration3.getContentPane().removeAll();
      coloration3.getContentPane().repaint();
      coloration3.getContentPane().revalidate();
      coloration3.add(delete3,BorderLayout.SOUTH);   
      delete3.setPreferredSize(new Dimension(50,50));
     

 }
    



    
    
    /**
     * Creates new form НовыйJFrame
     */
    public Frame() {
        delete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete1ActionPerformed(evt);
            }
        });
        delete2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete2ActionPerformed(evt);
            }
        });
        delete3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete3ActionPerformed(evt);
            }
        });
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        PanelForMethods = new javax.swing.JPanel();
        methode_1 = new javax.swing.JButton();
        methode_2 = new javax.swing.JButton();
        methode_3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        methode_1.setText("Backtracking");
        methode_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                methode_1ActionPerformed(evt);
            }
        });

        methode_2.setText("Dsatur");
        methode_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                methode_2ActionPerformed(evt);
            }
        });

        methode_3.setText("Tabu Search");
        methode_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                methode_3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelForMethodsLayout = new javax.swing.GroupLayout(PanelForMethods);
        PanelForMethods.setLayout(PanelForMethodsLayout);
        PanelForMethodsLayout.setHorizontalGroup(
            PanelForMethodsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelForMethodsLayout.createSequentialGroup()
                .addGroup(PanelForMethodsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(methode_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(methode_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(methode_3, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE))
                .addGap(0, 153, Short.MAX_VALUE))
        );
        PanelForMethodsLayout.setVerticalGroup(
            PanelForMethodsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelForMethodsLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(methode_1)
                .addGap(74, 74, 74)
                .addComponent(methode_2)
                .addGap(61, 61, 61)
                .addComponent(methode_3)
                .addContainerGap(185, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(PanelForMethods, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelForMethods, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 143, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Methodes", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 977, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Courbe", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 982, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void methode_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_methode_1ActionPerformed
        // TODO add your handling code here:
    coloration1.add(delete1,BorderLayout.SOUTH);   
    coloration1.setTitle("Graphe Backtracking");
    delete1.setPreferredSize(new Dimension(50,50));
    coloration1.setResizable(false);
    coloration1.getContentPane().addMouseListener(coloration1);
    coloration1.setSize(new Dimension(700, 700));
    coloration1.show();
    coloration1.grapheback=new GrapheGui(coloration1.getGraphics(),coloration1);
    }//GEN-LAST:event_methode_1ActionPerformed

    private void methode_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_methode_2ActionPerformed
        // TODO add your handling code here: 
    coloration2.add(delete2,BorderLayout.SOUTH);
    coloration2.setTitle("Graphe Dsatur");
    delete2.setPreferredSize(new Dimension(50,50));
    coloration2.setResizable(false);
    coloration2.getContentPane().addMouseListener(coloration2);
    coloration2.setSize(new Dimension(700, 700));
    coloration2.show();
    coloration2.graphedsat=new GrapheGui(coloration2.getGraphics(),coloration2);
   
    }//GEN-LAST:event_methode_2ActionPerformed

    private void methode_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_methode_3ActionPerformed
    coloration3.add(delete3,BorderLayout.SOUTH);
    coloration3.setTitle("Graphe Tabu");
    delete3.setPreferredSize(new Dimension(50,50));
    coloration3.setResizable(false);
    coloration3.getContentPane().addMouseListener(coloration3);
    coloration3.setSize(new Dimension(700, 700));
    coloration3.show();
    coloration3.graphetabu=new GrapheGui(coloration3.getGraphics(),coloration3);
    }//GEN-LAST:event_methode_3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }
    private javax.swing.JButton delete1 = new javax.swing.JButton("Supprimer");
    private javax.swing.JButton delete2 = new javax.swing.JButton("Supprimer");
    private javax.swing.JButton delete3 = new javax.swing.JButton("Supprimer");
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelForMethods;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JButton methode_1;
    private javax.swing.JButton methode_2;
    private javax.swing.JButton methode_3;
    // End of variables declaration//GEN-END:variables

 
    
    
}
