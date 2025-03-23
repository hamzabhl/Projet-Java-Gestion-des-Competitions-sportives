/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.*;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import services.*;
//import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hamza
 */
public class CompetitionForm extends javax.swing.JInternalFrame {

    private CompetitionService cs;
    private DefaultTableModel model;
    private static int id;

    /**
     * Creates new form CompetitionForm
     */
    public CompetitionForm() {
        initComponents();
        setFrameIcon(new ImageIcon(getClass().getResource("/icons/balls.png")));
        cs = new CompetitionService();
        model = (DefaultTableModel) listeCompetitions.getModel();
        load();
    }

    public void load() {
        model.setRowCount(0);
        for (Competition c : cs.findAll()) {
            model.addRow(new Object[]{c.getId(), c.getNom(), c.getDate(), c.getLieu(), c.getType()});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        txtLieu = new javax.swing.JTextField();
        bnAdd = new javax.swing.JButton();
        collectif = new javax.swing.JRadioButton();
        individuel = new javax.swing.JRadioButton();
        bnModify = new javax.swing.JButton();
        bnDelete = new javax.swing.JButton();
        txtCompetitionDate = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listeCompetitions = new javax.swing.JTable();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Gestion des Compétitions"));

        jLabel5.setText("Nom");

        jLabel6.setText("Date");

        jLabel7.setText("Lieu");

        jLabel8.setText("Type");

        txtNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomActionPerformed(evt);
            }
        });

        txtLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLieuActionPerformed(evt);
            }
        });

        bnAdd.setText("Ajouter");
        bnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnAddActionPerformed(evt);
            }
        });

        buttonGroup1.add(collectif);
        collectif.setText("Collectif");
        collectif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collectifActionPerformed(evt);
            }
        });

        buttonGroup1.add(individuel);
        individuel.setText("Individuel");

        bnModify.setText("Modifier");
        bnModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnModifyActionPerformed(evt);
            }
        });

        bnDelete.setText("Supprimer");
        bnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNom, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txtCompetitionDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(42, 42, 42)
                                .addComponent(txtLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(26, 26, 26)
                                .addComponent(collectif)
                                .addGap(18, 18, 18)
                                .addComponent(individuel))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(bnModify, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(bnDelete)))
                .addContainerGap(149, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(collectif)
                        .addComponent(individuel)
                        .addComponent(jLabel8))
                    .addComponent(jLabel6)
                    .addComponent(txtCompetitionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnModify)
                    .addComponent(bnDelete)
                    .addComponent(bnAdd))
                .addGap(25, 25, 25))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Liste des Compétitions"));

        listeCompetitions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nom", "Date", "Lieu", "Type"
            }
        ));
        listeCompetitions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listeCompetitionsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listeCompetitions);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(24, 24, 24))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listeCompetitionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listeCompetitionsMouseClicked
        // TODO add your handling code here:
        try {
            int selectedRow = listeCompetitions.getSelectedRow();
            if (selectedRow == -1) {
                return;
            }

            id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());

            txtNom.setText(model.getValueAt(selectedRow, 1).toString());

            Object dateObject = model.getValueAt(selectedRow, 2);
            if (dateObject instanceof java.sql.Date) {
                java.sql.Date sqlDate = (java.sql.Date) dateObject;
                txtCompetitionDate.setDate(new java.util.Date(sqlDate.getTime()));
            } else if (dateObject instanceof String) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsedDate = sdf.parse(dateObject.toString());
                txtCompetitionDate.setDate(parsedDate);
            }

            txtLieu.setText(model.getValueAt(selectedRow, 3).toString());

            String type = model.getValueAt(selectedRow, 4).toString();
            if ("Collectif".equalsIgnoreCase(type)) {
                collectif.setSelected(true);
            } else if ("Individuel".equalsIgnoreCase(type)) {
                individuel.setSelected(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la sélection de la compétition!", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_listeCompetitionsMouseClicked

    private void bnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnDeleteActionPerformed
        // TODO add your handling code here:
        int selectedRow = listeCompetitions.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une compétition à supprimer!", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int choice = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment Supprimer la compétition " + model.getValueAt(listeCompetitions.getSelectedRow(), 1) + " ?");
        if (choice == 0) {

            cs.delete(cs.findById(id));
            load();
            txtNom.setText("");
            txtCompetitionDate.setDate(null);
            txtLieu.setText("");
            buttonGroup1.clearSelection();  // Clears radio button selection
            JOptionPane.showMessageDialog(this, "Compétition bien Supprimé!");
        }
    }//GEN-LAST:event_bnDeleteActionPerformed

    private void bnModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnModifyActionPerformed
        // TODO add your handling code here:
        int selectedRow = listeCompetitions.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une compétition à modifier!", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nom = txtNom.getText();
        String lieu = txtLieu.getText();
        java.util.Date selectedDate = txtCompetitionDate.getDate();

        if (nom.isEmpty() || lieu.isEmpty() || selectedDate == null) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs!", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());

        int competitionId = (int) model.getValueAt(selectedRow, 0);

        String type = null;
        if (collectif.isSelected()) {
            type = "Collectif";
        } else if (individuel.isSelected()) {
            type = "Individuel";
        }

        if (type == null) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un type de compétition!", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Competition updatedCompetition = new Competition(competitionId, nom, sqlDate, lieu, type);

        if (cs.update(updatedCompetition)) {
            load();
            JOptionPane.showMessageDialog(this, "Compétition mise à jour avec succès!");
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de la modification de la compétition!", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bnModifyActionPerformed

    private void collectifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_collectifActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_collectifActionPerformed

    private void bnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnAddActionPerformed
        String nom = txtNom.getText().trim();
        String lieu = txtLieu.getText().trim();

        // Get the selected date from JDateChooser
        java.util.Date selectedDate = txtCompetitionDate.getDate();

        // Validate before using selectedDate
        if (selectedDate == null) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs!", "Erreur", JOptionPane.ERROR_MESSAGE);
            return; // Stop execution
        }

        // Convert to java.sql.Date
        java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());

        // Get the selected type from radio buttons
        String type = null;
        if (collectif.isSelected()) {
            type = "Collectif";
        } else if (individuel.isSelected()) {
            type = "Individuel";
        }

        // Validation: Check if all fields are filled
        if (nom.isEmpty() || lieu.isEmpty() || type == null) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs!", "Erreur", JOptionPane.ERROR_MESSAGE);
            return; // Stop execution
        }

        // Create competition and save in database
        if (cs.create(new Competition(nom, sqlDate, lieu, type))) {
            // Reset form fields
            txtNom.setText("");
            txtCompetitionDate.setDate(null);
            txtLieu.setText("");
            buttonGroup1.clearSelection();  // Clears radio button selection

            load(); // Reload table data
            JOptionPane.showMessageDialog(this, "Compétition bien enregistrée!");
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de l’enregistrement de la compétition!", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bnAddActionPerformed

    private void txtLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLieuActionPerformed

    private void txtNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomActionPerformed

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
            java.util.logging.Logger.getLogger(CompetitionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CompetitionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CompetitionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CompetitionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CompetitionForm().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnAdd;
    private javax.swing.JButton bnDelete;
    private javax.swing.JButton bnModify;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JRadioButton collectif;
    private javax.swing.JRadioButton individuel;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable listeCompetitions;
    private com.toedter.calendar.JDateChooser txtCompetitionDate;
    private javax.swing.JTextField txtLieu;
    private javax.swing.JTextField txtNom;
    // End of variables declaration//GEN-END:variables
}
