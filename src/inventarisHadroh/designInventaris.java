package inventarisHadroh;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.*;

public class designInventaris extends javax.swing.JFrame {
    penyimpananBarang pb = new penyimpananBarang();
    private int selectedRow = -1;
    
    public designInventaris() {
        initComponents();
        tblInventaris.setModel(pb);
        resizeListener();
    }

    private void resizeListener() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeColumns();
            }
        });
    }

    private void resizeColumns() {
        float[] columnWidthPercentage = {40.0f, 10.0f, 40.0f};
        int tW = tblInventaris.getWidth();
        TableColumn column;
        TableColumnModel jTableColumnModel = tblInventaris.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(columnWidthPercentage[i] * tW);
            column.setPreferredWidth(pWidth);
            tblInventaris.setRowHeight(27);
        }
    }
        
    private void showAddItemDialog() {
        selectedRow = -1;
        showItemDialog("Tambah Barang", "", "", 0);
    }

    private void showEditItemDialog() {
        int row = tblInventaris.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih barang yang akan diedit!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        barang b = pb.get(row);
        selectedRow = row;
        showItemDialog("Edit Barang", b.getNamaBarang(), String.valueOf(b.getJumlahBarang()), b.getKeadaanBarang().equals("Rusak") ? 1 : b.getKeadaanBarang().equals("Hilang") ? 2 : 0);
    }
        
         // Menampilkan dialog untuk tambah/edit barang
    private void showItemDialog(String title, String nama, String jumlah, int keadaanIndex) {
        JTextField txtNama = new JTextField(nama, 20);
        JTextField txtJumlah = new JTextField(jumlah, 5);
        JComboBox<String> cmbKeadaan = new JComboBox<>(new String[]{"Baik", "Rusak", "Hilang"});
        cmbKeadaan.setSelectedIndex(keadaanIndex);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Nama Barang:"));
        panel.add(txtNama);
        panel.add(new JLabel("Jumlah Barang:"));
        panel.add(txtJumlah);
        panel.add(new JLabel("Keadaan Barang:"));
        panel.add(cmbKeadaan);

        int result = JOptionPane.showConfirmDialog(this, panel, title, JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            saveItem(txtNama.getText(), txtJumlah.getText(), (String) cmbKeadaan.getSelectedItem());
        }
    }

    private void saveItem(String nama, String jumlahText, String keadaan) {
        if (nama.trim().isEmpty() || jumlahText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int jumlah;
        try {
            jumlah = Integer.parseInt(jumlahText);
            if (jumlah < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka positif!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        barang b = new barang(nama, jumlah, keadaan);
        if (selectedRow == -1) {
            pb.add(b);
        } else {
            pb.set(selectedRow, b);
        }
    }

    private void deleteItem() {
        int row = tblInventaris.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih barang yang akan dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus barang ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            pb.remove(row);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formAddItem = new javax.swing.JDialog();
        pnMenus = new javax.swing.JPanel();
        btnAddItem = new javax.swing.JButton();
        btnEditItem = new javax.swing.JButton();
        btnDeleteItem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInventaris = new javax.swing.JTable();

        javax.swing.GroupLayout formAddItemLayout = new javax.swing.GroupLayout(formAddItem.getContentPane());
        formAddItem.getContentPane().setLayout(formAddItemLayout);
        formAddItemLayout.setHorizontalGroup(
            formAddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        formAddItemLayout.setVerticalGroup(
            formAddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnMenus.setPreferredSize(new java.awt.Dimension(150, 458));

        btnAddItem.setText("Tambah Barang");
        btnAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemActionPerformed(evt);
            }
        });

        btnEditItem.setText("Edit Barang");
        btnEditItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditItemActionPerformed(evt);
            }
        });

        btnDeleteItem.setText("Hapus Barang");
        btnDeleteItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnMenusLayout = new javax.swing.GroupLayout(pnMenus);
        pnMenus.setLayout(pnMenusLayout);
        pnMenusLayout.setHorizontalGroup(
            pnMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMenusLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(pnMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDeleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditItem, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddItem))
                .addGap(19, 19, 19))
        );
        pnMenusLayout.setVerticalGroup(
            pnMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenusLayout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(btnAddItem)
                .addGap(18, 18, 18)
                .addComponent(btnEditItem)
                .addGap(18, 18, 18)
                .addComponent(btnDeleteItem)
                .addContainerGap(215, Short.MAX_VALUE))
        );

        getContentPane().add(pnMenus, java.awt.BorderLayout.LINE_START);

        tblInventaris.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblInventaris);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemActionPerformed
        showAddItemDialog();
    }//GEN-LAST:event_btnAddItemActionPerformed

    private void btnEditItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditItemActionPerformed
        showEditItemDialog();
    }//GEN-LAST:event_btnEditItemActionPerformed

    private void btnDeleteItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteItemActionPerformed
        deleteItem();
    }//GEN-LAST:event_btnDeleteItemActionPerformed

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
            java.util.logging.Logger.getLogger(designInventaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(designInventaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(designInventaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(designInventaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new designInventaris().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddItem;
    private javax.swing.JButton btnDeleteItem;
    private javax.swing.JButton btnEditItem;
    private javax.swing.JDialog formAddItem;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnMenus;
    private javax.swing.JTable tblInventaris;
    // End of variables declaration//GEN-END:variables
}
