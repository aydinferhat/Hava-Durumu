/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HavaDurumu;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Ferhat Aydin
 */
public class HavaDurumu extends javax.swing.JFrame {
String location;
String key;

   
    public HavaDurumu() {
        initComponents();
        location = "kocaeli";
        key="3e36b27feda8ba31c6ef0b63ebc303f8";
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("İlin ismini giriniz");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "bugün", "2.gün", "3.gün", "4.gün", "5.gün"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Hava Durumunu getir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 2129, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jTextField1.getText().isEmpty())
        {
          jTextField1.setText(location);   
        }
        else
        {
            location = jTextField1.getText();
        }
        String urlHavaDurumuGunluk = "http://api.openweathermap.org/data/2.5/forecast?q="+location+",tr&mode=xml&appid="+key+"&units=metric&lang=tr";
        HttpURLConnection baglantiGunluk = null;
        try {

            URL urlGunluk = new URL(urlHavaDurumuGunluk);

            baglantiGunluk = (HttpURLConnection) urlGunluk.openConnection();
                BufferedInputStream streamGunluk = new BufferedInputStream(baglantiGunluk.getInputStream());
                DocumentBuilderFactory documentBuilderFactoryGunluk = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilderGunluk = documentBuilderFactoryGunluk.newDocumentBuilder();
                Document documentGunluk = documentBuilderGunluk.parse(streamGunluk);
                Element gelecek = (Element) documentGunluk.getElementsByTagName("weatherdata").item(0);
                Element ikincigunel = (Element) gelecek.getElementsByTagName("forecast").item(0);
                for (int i = 0; i < 5; i++) {
                Element ikincigunel1=  ((Element) ikincigunel.getElementsByTagName("time").item(i*8));
                Element ikincigunel2=  ((Element) ikincigunel1.getElementsByTagName("temperature").item(0));
                jTable1.setValueAt(ikincigunel.getElementsByTagName("time").item(((i*8)+0)).getAttributes().item(0).getNodeValue(), 0, i);
                jTable1.setValueAt(ikincigunel.getElementsByTagName("time").item(((i*8)+0)).getAttributes().item(1).getNodeValue(), 1, i);
                jTable1.setValueAt(ikincigunel2.getAttribute("value"), 2, i);
                jTable1.setValueAt(((Element)ikincigunel1.getElementsByTagName("symbol").item(0)).getAttributes().item(0).getNodeValue(), 3, i);
                
                }
        } catch (Exception e) {
            System.out.println("HATA : Xml parse edemedi" + e.getMessage().toString());
        } finally {
           
        }
       
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HavaDurumu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
