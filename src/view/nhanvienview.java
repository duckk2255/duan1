/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import service.NhanVienService;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import util.connectdb;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
/**
 *
 * @author LINH
 */
public class nhanvienview extends javax.swing.JFrame {
    private Connection conn;
    NhanVienService repo = new NhanVienService();
    ArrayList<model.NhanVien> list = new ArrayList<>();
    DefaultTableModel dtm = new DefaultTableModel();
    /**
     * Creates new form test
     */
    public nhanvienview() {
        initComponents();
        init();
        fillTable();
    }
    public void vh(){
        btnThem.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
    }
    
    public void init(){
        lblHinh.setText("");
    }
    public void fillTable(){
        list=repo.getAll();
        dtm =(DefaultTableModel)tblQuanLiNhanVien.getModel();
        dtm.setRowCount(0);
        for (model.NhanVien nhanVien : list) {
            Object[] row={
                nhanVien.getMaNV(),
                nhanVien.getTenNV(),
                nhanVien.getUser(),
                nhanVien.getPass(),
                nhanVien.getHinh(),
                nhanVien.getSoDienThoai(),
                nhanVien.getEmailNV()};
            dtm.addRow(row);
        }   
    }
    
    public model.NhanVien readForm(){
           model.NhanVien nhanVien = new model.NhanVien();
        nhanVien.setMaNV(txtMa.getText());
        nhanVien.setTenNV(txtTen.getText());
        nhanVien.setHinh(lblHinh.getText());
        nhanVien.setUser(txtUser.getText());
        nhanVien.setPass(txtPass.getText());
        nhanVien.setSoDienThoai(txtSDT.getText());
        nhanVien.setEmailNV(txtEmail.getText());
        return nhanVien;
    }

    void clearForm() {
        txtMa.setText("");
        txtTen.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        txtUser.setText("");
        txtPass.setText("");
        lblHinh.setText("Thêm hình ảnh");
    }

    void fillForm(int index) {
        model.NhanVien nv=list.get(index);
        txtMa.setText(nv.getMaNV());  
        txtTen.setText(nv.getTenNV()); 
        txtUser.setText(nv.getUser());
        txtPass.setText(nv.getPass());
        uphinh(nv.getHinh());
        txtSDT.setText(nv.getSoDienThoai());
        txtEmail.setText(nv.getEmailNV());
    }
    public void fen(){
        txtMa.setEditable(false);
        txtTen.setEditable(false);
        txtSDT.setEditable(false);
        txtEmail.setEditable(false);
        txtUser.setEditable(false);
        txtPass.setEditable(false);
    }
    public void ten(){
        txtMa.setEditable(true);
        txtTen.setEditable(true);
        txtSDT.setEditable(true);
        txtEmail.setEditable(true);
        txtUser.setEditable(true);
        txtPass.setEditable(true);
    }
    public void teen(){
        txtTen.setEditable(true);
        txtSDT.setEditable(true);
        txtEmail.setEditable(true);
        txtUser.setEditable(true);
        txtPass.setEditable(true);
    }
    public boolean checkNullandRegex(){
        if (txtMa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã nhân viên!");
            txtMa.requestFocus();
            return false;
        }
        if (txtTen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống tên nhân viên!");
            txtTen.requestFocus();
            return false;
        }
        if (txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống email nhân viên!");
            txtEmail.requestFocus();
            return false;
        }
        if (txtSDT.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống sdt nhân viên!");
            txtSDT.requestFocus();
            return false;
        }
        if(txtUser.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Không được để trống tên đăng nhập nhân viên!");
            txtUser.requestFocus();
            return false;
        }
        if(txtPass.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Không được để trống mật khẩu nhân viên!");
            txtPass.requestFocus();
            return false;
        }
        String sdtregex= "^0\\d{9}$";
        String emailregex= "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        String manvregex="^NV\\d{3}$";
        if(!txtEmail.getText().matches(emailregex)){
            JOptionPane.showMessageDialog(this, "Lỗi: Sai định dạng Email!");
            txtEmail.requestFocus();
            return false;
        }
        if(!txtSDT.getText().matches(sdtregex)){
            JOptionPane.showMessageDialog(this, "Lỗi: Sai định dạng sdt!");
            txtSDT.requestFocus();
            return false;
        }
        if(!txtMa.getText().matches(manvregex)){
            JOptionPane.showMessageDialog(this, "Lỗi: Sai định dạng mã nhân viên, ví dụ đúng: NV001");
            txtMa.requestFocus();
            return false;
        }
        return true;
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
        jLabel15 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblHinh = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblQuanLiNhanVien = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPass = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Nhân viên");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(370, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 860, 96));
        jPanel1.getAccessibleContext().setAccessibleName("");

        lblHinh.setBackground(new java.awt.Color(51, 255, 255));
        lblHinh.setText("Thêm hình ảnh");
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });
        getContentPane().add(lblHinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 320, 120, 140));

        tblQuanLiNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Họ tên", "Tên đăng nhập", "Mật khẩu", "Hình", "SDT", "Email"
            }
        ));
        tblQuanLiNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQuanLiNhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblQuanLiNhanVien);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 820, 180));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch)
                .addGap(26, 26, 26))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 530, 90));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        jLabel2.setText("Mã");

        jLabel3.setText("Tên");

        jLabel4.setText("Số điện thoai");

        jLabel5.setText("Email");

        jLabel7.setText("Username");

        jLabel6.setText("Mật khẩu");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                    .addComponent(txtTen)
                    .addComponent(txtSDT)
                    .addComponent(txtEmail)
                    .addComponent(txtUser)
                    .addComponent(txtPass))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 530, 210));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnClear.setText("Tạo mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem)
                .addGap(32, 32, 32)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(btnXoa)
                .addGap(32, 32, 32)
                .addComponent(btnClear)
                .addGap(37, 37, 37)
                .addComponent(btnRefresh)
                .addGap(17, 17, 17))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnClear)
                    .addComponent(btnRefresh))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 530, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        if(txtSearch.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Không để trống ô tìm kiếm!");
        }else if(!txtSearch.getText().matches("^NV\\d{3}$")){
            JOptionPane.showMessageDialog(this, "Lỗi: Sai định dạng mã nhân viên, ví dụ đúng: NV001");
        }else{
            JOptionPane.showMessageDialog(this, "Đã tìm thấy thông tin cho "+txtSearch.getText());
            conn=connectdb.getConnection();
        try{
            CallableStatement st=conn.prepareCall("{call sp_timnv(?)}");
            st.setString(1, txtSearch.getText());
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                txtMa.setText(rs.getString("MaNV").trim());
                txtTen.setText(rs.getString("TenNV").trim());
                txtUser.setText(rs.getString("TenDangNhap").trim());
                txtPass.setText(rs.getString("MatKhau").trim());
                txtSDT.setText(rs.getString("SdtNV").trim());
                txtEmail.setText(rs.getString("EmailNV").trim());
                uphinh(rs.getString("HinhAnhNV"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả cho "+txtSearch.getText());
        }
        }
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        lblHinh.setEnabled(true);
        teen();
        txtSearch.setText("");
    }//GEN-LAST:event_btnSearchActionPerformed

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        String hinh=chinh();
        lblHinh.setText(hinh);
        uphinh(hinh);
    }//GEN-LAST:event_lblHinhMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if(checkNullandRegex()){
            int confirm = JOptionPane.showConfirmDialog(this, "Thêm nhân viên "+txtTen.getText()+" ?", "Xác nhận thêm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
        if(repo.Them(readForm())> 0){
            JOptionPane.showMessageDialog(this,"Thêm thất bại");
        }else{
            JOptionPane.showMessageDialog(this,"Thêm thành công");
        }
        fillTable();
        clearForm();
        vh();
        fen();
        }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if(checkNullandRegex()){
            int confirm = JOptionPane.showConfirmDialog(this, "Sửa thông tin nhân viên "+txtTen.getText()+"", "Xác nhận sửa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
        if(repo.Sua(readForm())> 0){
            JOptionPane.showMessageDialog(this,"Sửa thất bại");
        }else{
            JOptionPane.showMessageDialog(this,"Sửa thành công");
        }
        fillTable();
        clearForm();
        vh();
        fen();
        }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xóa nhân viên "+txtTen.getText()+" ?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
        if(repo.Xoa(readForm())> 0){
            JOptionPane.showMessageDialog(this,"Xóa thất bại");
        }else{
            JOptionPane.showMessageDialog(this,"Xóa thành công");
        }
        fillTable();
        clearForm();
        vh();
        fen();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        vh();
        clearForm();
        btnThem.setEnabled(true);
        ten();
    }//GEN-LAST:event_btnClearActionPerformed

    private void tblQuanLiNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQuanLiNhanVienMouseClicked
        // TODO add your handling code here:
        int index=tblQuanLiNhanVien.getSelectedRow();
        fillForm(index);
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        teen();
    }//GEN-LAST:event_tblQuanLiNhanVienMouseClicked
    void setColor(JPanel panel){
        panel.setBackground(new Color(29,90,151));
    }
    void resetColor(JPanel panel){
        panel.setBackground(new Color(33,81,129));
    }
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
            java.util.logging.Logger.getLogger(nhanvienview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nhanvienview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nhanvienview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nhanvienview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new nhanvienview().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JTable tblQuanLiNhanVien;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
void uphinh(String hinh){
        ImageIcon image1=new ImageIcon("src\\HinhAnh\\"+hinh);
        Image im=image1.getImage();
        ImageIcon icon = new ImageIcon(im.getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), im.SCALE_SMOOTH));
        lblHinh.setIcon(icon);
    }
private String chinh(){
        String hinh="";
        JFileChooser open=new JFileChooser();
        File defaultDirectory=new File("src\\HinhAnh\\"+hinh);
        open.setCurrentDirectory(defaultDirectory);
        int rs=open.showOpenDialog(null);
        if(rs==open.APPROVE_OPTION){
            File f=open.getSelectedFile();
            String filename=f.getAbsolutePath();
            String [] chuoi=filename.split("\\\\");
            hinh=chuoi[chuoi.length-1];
        }
        return hinh;
    }

}

