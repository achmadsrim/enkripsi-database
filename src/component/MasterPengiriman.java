/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skripa3l;

import skripa3l.Koneksi;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author KAMPRTO
 */
public class MasterPengiriman extends javax.swing.JFrame {
    DefaultTableModel tabModel;
    Object[] kolom;
    Dimension OD = Toolkit.getDefaultToolkit().getScreenSize();
    MasterPengiriman.SearchRenderer renderer = new MasterPengiriman.SearchRenderer();
    private JFrame frame = new JFrame();
    /**
     * Creates new form MasterPengiriman
     */
    public MasterPengiriman() {
        initComponents();
        setTitle("Master Pengiriman");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(OD.width / 2 - getWidth() / 2, OD.height / 2 - getHeight() / 2);
        kolom = new Object[]{"Kode Pengiriman", "Tujuan Pengiriman", "Harga Pengiriman"};
        tabModel = new DefaultTableModel(null, kolom);
        tabelpengiriman.setModel(tabModel);
      
        setAwalForm();
        txtCari.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                fireDocumentChangeEvent();
            }

            public void removeUpdate(DocumentEvent e) {
                fireDocumentChangeEvent();
            }

            public void changedUpdate(DocumentEvent e) {
            }
        });
        fireDocumentChangeEvent();
        tabelpengiriman.setDefaultRenderer(Object.class, renderer);
    }
    
    void setAwalForm() {
        txtKodepengiriman.setEnabled(false);
        txtTujuan.setText("");
        txtHrgKirim.setText("");
        txtTujuan.requestFocus();
        btnBersih.setEnabled(true);
        btnHapus.setEnabled(false);
        btnSimpan.setEnabled(true);
        btnEdit.setEnabled(false);

        
        hapusTabel();
        tampilTabel();
    }
    void hapusTabel() {
        int brs = tabModel.getRowCount();
        for (int i = 0; i < brs; i++) {
            tabModel.removeRow(0);
        }
    }

    void tampilTabel() {
        try {
            Koneksi objK = new Koneksi();
            Connection con = objK.getConnection();
            Statement st = con.createStatement();
            String sql = "Select * from master_pengiriman order by Kode_Pengiriman";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String Kode_Pengiriman = rs.getString("Kode_Pengiriman");
                String Tujuan_Pengiriman = rs.getString("Tujuan_Pengiriman");
                String Harga_Pengiriman = rs.getString("HrgPengiriman");
                String[] data = {Kode_Pengiriman, Tujuan_Pengiriman, Harga_Pengiriman};
                tabModel.addRow(data);
                TableColumn column;
                tabelpengiriman.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF); 
                column = tabelpengiriman.getColumnModel().getColumn(0); 
                column.setPreferredWidth(55);
                column = tabelpengiriman.getColumnModel().getColumn(1); 
                column.setPreferredWidth(150); 
                column = tabelpengiriman.getColumnModel().getColumn(2); 
                column.setPreferredWidth(320); 
                //System.out.println("data customer : "+data);
            }
            rs.close();
            con.close();
        } catch (SQLException | HeadlessException e) {

        }
    }
     
     void tampildaritable() {
        int row = tabelpengiriman.getSelectedRow();
        txtKodepengiriman.setText(tabelpengiriman.getValueAt(row, 0).toString());
        txtTujuan.setText(tabelpengiriman.getValueAt(row, 1).toString());
        txtHrgKirim.setText(tabelpengiriman.getValueAt(row, 2).toString());
        btnSimpan.setEnabled(false);
        btnBersih.setEnabled(true);
        btnHapus.setEnabled(true);
        btnEdit.setEnabled(true);
        txtTujuan.requestFocus();
     }
     
     void autoNumber() {
        try {
            Koneksi objK = new Koneksi();
            Connection con = objK.getConnection();
            Statement st = con.createStatement();
            String sql = "select * from master_pengiriman order by Kode_Pengiriman DESC";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                String kd = rs.getString("Kode_Pengiriman");
                int kd_customer = Integer.parseInt(kd) + 1;
                txtKodepengiriman.setText(String.valueOf(kd_customer));
                rs.close();
                con.close();
            } else {
                txtKodepengiriman.setText("1");
            }
        } catch (Exception e) {
       } 
     }
     
     private void fireDocumentChangeEvent() {
        String pattern = txtCari.getText().trim();
        renderer.setPattern(pattern);
        tabelpengiriman.repaint();
    }
     
     class SearchRenderer implements TableCellRenderer {

        private final Color BACKGROUND_SELECTION_COLOR = new Color(220, 240,
                255);
        private final transient Highlighter.HighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(
                Color.BLUE);
        private final JTextField txtCari = new JTextField();
        private String pattern = "";
        private String prev;

        public boolean setPattern(String str) {
            if (str == null || str.equals(pattern)) {
                return false;
            } else {
                prev = pattern;
                pattern = str;
                return true;
            }
        }
      public SearchRenderer() {
            super();
            txtCari.setOpaque(true);
            txtCari.setBorder(BorderFactory.createEmptyBorder());
            txtCari.setForeground(Color.BLACK);
            txtCari.setBackground(Color.WHITE);
            txtCari.setEditable(false);
        }

        @Override
        public Component getTableCellRendererComponent(JTable tabel,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {
            String txt = Objects.toString(value, "");
            Highlighter highlighter = txtCari.getHighlighter();
            highlighter.removeAllHighlights();
            txtCari.setText(txt);
            txtCari.setBackground(isSelected ? BACKGROUND_SELECTION_COLOR
                    : Color.WHITE);
            if (pattern != null && !pattern.isEmpty() && !pattern.equals(prev)) {
                Matcher matcher = Pattern.compile(pattern).matcher(txt);
                if (matcher.find()) {
                    int start = matcher.start();
                    int end = matcher.end();
                    try {
                        highlighter.addHighlight(start, end, highlightPainter);
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                }
            }
            return txtCari;
        }
    }
    
//   public void lebarKolom(){ 
//        TableColumn column;
//        tabelpengiriman.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF); 
//        column = tabelpengiriman.getColumnModel().getColumn(0); 
//        column.setPreferredWidth(100);
//        column = tabelpengiriman.getColumnModel().getColumn(1); 
//        column.setPreferredWidth(200); 
 
//    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTujuan = new javax.swing.JTextField();
        txtHrgKirim = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnBersih = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelpengiriman = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        txtKodepengiriman = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setText("Master Pengiriman");

        jLabel2.setText("Tujuan Pengiriman");

        jLabel3.setText("Harga Pengiriman");

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnBersih.setText("Bersih");
        btnBersih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBersihActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnKembali.setText("Kembali");

        tabelpengiriman.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Kode Pengiriman", "Tujuan Pengiriman", "Harga Pengiriman"
            }
        ));
        tabelpengiriman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelpengirimanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelpengiriman);

        jLabel4.setText("Cari");

        jLabel5.setText("Kode Pengiriman");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(btnSimpan)
                            .addGap(32, 32, 32)
                            .addComponent(btnEdit)
                            .addGap(29, 29, 29)
                            .addComponent(btnBersih)
                            .addGap(35, 35, 35)
                            .addComponent(btnHapus)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnKembali))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtKodepengiriman)
                            .addComponent(txtHrgKirim, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(txtTujuan))))
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(224, 224, 224))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKodepengiriman, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHrgKirim, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnEdit)
                    .addComponent(btnBersih)
                    .addComponent(btnHapus)
                    .addComponent(btnKembali))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if (!txtKodepengiriman.getText().equals("") && !txtTujuan.getText().equals("") && !txtHrgKirim.getText().equals("")) {
            try {
                Koneksi objK = new Koneksi();
                Connection con = objK.getConnection();
                Statement st = con.createStatement();
                String sql = "Insert into master_pengiriman values('"
                        + txtKodepengiriman.getText() + "','"
                        + txtTujuan.getText() + "','"
                        + txtHrgKirim.getText() + "')";
                int vHasil = st.executeUpdate(sql);
                if (vHasil > 0) {
                    JOptionPane.showMessageDialog(null, "Data Berhasil Ditambah");
                }
                con.close();
                setAwalForm();
            } catch (SQLException | HeadlessException e) {

            }
        } else {
            JOptionPane.showMessageDialog(null, "Isi Data Secara Lengkap");
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showOptionDialog(frame, 
                        "Apakah Anda Ingin Mengubah Data Ini ?", 
                        "Confirmation", JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == JOptionPane.YES_OPTION){
            try {
                Koneksi objK = new Koneksi();
                Connection con = objK.getConnection();
                Statement st = con.createStatement();//menulis statement harus melalui objek koneksi// langkah 3
                String sql = "Update master_pengiriman set Tujuan_Pengiriman='" + txtTujuan.getText()+ "', HrgPengiriman='" + txtHrgKirim.getText()+ "' Where Kode_Pengiriman='" + txtKodepengiriman.getText() + "';";
                int vHasil = st.executeUpdate(sql);//langkah 5, sql dari string yg dipakai untuk insert barang tadi
                if (vHasil > 0) {//langkah 6
                    JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
                }
                setAwalForm();
                con.close();
            } catch (SQLException | HeadlessException e) {
                System.out.println(e.toString());
            }
        }
        else if (confirm == JOptionPane.NO_OPTION){
            setAwalForm();
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showOptionDialog(frame, 
                        "Apakah Anda Ingin Menghapus Data Ini ?", 
                        "Confirmation", JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == JOptionPane.YES_OPTION){
            try {
                Koneksi objK = new Koneksi();
                Connection con = objK.getConnection();
                Statement st = con.createStatement();//menulis statement harus melalui objek koneksi// langkah 3
                String sql = "Delete from master_pengiriman Where " + "Kode_Pengiriman='" + txtKodepengiriman.getText() + "'";
                int vHasil = st.executeUpdate(sql);//langkah 5, sql dari string yg dipakai untuk insert barang tadi
                if (vHasil > 0) {//langkah 6
                    JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                }
                setAwalForm();
                con.close();
            } catch (SQLException | HeadlessException e) {

            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void tabelpengirimanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelpengirimanMouseClicked
        // TODO add your handling code here:
         tampildaritable();
    }//GEN-LAST:event_tabelpengirimanMouseClicked

    private void btnBersihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBersihActionPerformed
        // TODO add your handling code here:
         setAwalForm();
    }//GEN-LAST:event_btnBersihActionPerformed

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
            java.util.logging.Logger.getLogger(MasterPengiriman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MasterPengiriman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MasterPengiriman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MasterPengiriman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MasterPengiriman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBersih;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelpengiriman;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHrgKirim;
    private javax.swing.JTextField txtKodepengiriman;
    private javax.swing.JTextField txtTujuan;
    // End of variables declaration//GEN-END:variables
}
