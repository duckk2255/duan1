/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import model.KhuyenMai;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import static java.awt.image.ImageObserver.WIDTH;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.connectdb;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Connection;
/**
 *
 * @author LINH
 */
public class khuyenmaiview extends javax.swing.JFrame {
    private Connection conn;
    service.KhuyenMaiService repo = new service.KhuyenMaiService();
    DefaultTableModel dtm = new DefaultTableModel();
    ArrayList<KhuyenMai> KM = new ArrayList<>();
    int i = 0;
    /**
     * Creates new form test
     */
    public khuyenmaiview() {
        initComponents();
        fillTable(repo.getAll());
        vh();
        fen();
    }
    public void vh(){
        btnThem.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
    }
    public void fen(){
        txtMa.setEditable(false);
        txtNoidung.setEditable(false);
        txtGiamGia.setEditable(false);
        dcNgayBD.setEnabled(false);
        dcNgayKT.setEnabled(false);
    }
    public void ten(){
        txtMa.setEditable(true);
        txtNoidung.setEditable(true);
        txtGiamGia.setEditable(true);
        dcNgayBD.setEnabled(true);
        dcNgayKT.setEnabled(true);
    }
    public void teen(){
        txtNoidung.setEditable(true);
        txtGiamGia.setEditable(true);
        dcNgayBD.setEnabled(true);
        dcNgayKT.setEnabled(true);
    }
    void fillTable(ArrayList<KhuyenMai> KM) {
        dtm = (DefaultTableModel) tblQlKhuyenMai.getModel();
        dtm.setRowCount(0);       
        for (KhuyenMai khuyenMai : KM) {
            dtm.addRow(new Object[]{
                khuyenMai.getMaKM(),
                khuyenMai.getNoiDungKM(),
                khuyenMai.getGiamGia(),
                khuyenMai.getNgayBatDau(),
                khuyenMai.getNgayKetThuc()
            });

        }

    }

    public boolean checkNullandRegex() {
        if (txtMa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã Khuyến mãi");
            txtMa.requestFocus();
            return false;
        }
        if (txtNoidung.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống nội dung Khuyến mãi");
            txtNoidung.requestFocus();
            return false;
        }
        if (txtGiamGia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã giảm giá");
            txtGiamGia.requestFocus();
            return false;
        }
        if (dcNgayBD.getDate().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống ngày bắt đầu");
            dcNgayBD.requestFocus();
            return false;
        }
        if (dcNgayKT.getDate().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống ngày kết thúc");
            dcNgayKT.requestFocus();
            return false;
        }
        Date BD= dcNgayBD.getDate();
        Date KT= dcNgayKT.getDate();
        if(BD.before(KT)){
            JOptionPane.showMessageDialog(this, "Lỗi: Ngày bắt đầu phải nằm trước ngày kết thúc/Ngày kết thúc phải nằm sau ngày bắt đầu!");
            return false;
        }
        String soregex="^[1-9]\\d*(\\.\\d+)?$";
        String makmregex="^KM\\d{3}$";
        //if(!txtMa.getText().matches(makmregex)){
           // JOptionPane.showMessageDialog(this, "Lỗi: Sai định dạng mã tour, ví dụ đúng: KM001");
           // txtMa.requestFocus();
           // return false;
        //}
        if(!txtGiamGia.getText().matches(soregex)){
            JOptionPane.showMessageDialog(this, "Lỗi: Giá trị vừa được nhập vào không phải là số!");
            txtGiamGia.requestFocus();
            return false;
        }
        return true;
    }

    public KhuyenMai getDataForm() {
        String maKM = txtMa.getText();
        String noiDungKM = txtNoidung.getText();
        String giamGia = txtGiamGia.getText();
        Date ngayBatDau = new java.sql.Date(dcNgayBD.getDate().getTime());
        Date ngayketThuc = new java.sql.Date(dcNgayKT.getDate().getTime());
        KhuyenMai KKM = new KhuyenMai(maKM, noiDungKM, Integer.parseInt(giamGia), ngayBatDau, ngayketThuc);
        return KKM;
    }
    void clearfrom() {
        txtMa.setText("");
        txtNoidung.setText("");
        txtGiamGia.setText("");
        fillTable(repo.getAll());
    }

    public void displayClick(int index) {
        KhuyenMai km = KM.get(index);
        txtMa.setText(String.valueOf(km.getMaKM()));
        txtNoidung.setText(String.valueOf(km.getNoiDungKM()));
        txtGiamGia.setText(String.valueOf(km.getGiamGia()));
        dcNgayBD.setDate(km.getNgayBatDau());
        dcNgayKT.setDate(km.getNgayKetThuc());
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQlKhuyenMai = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtNoidung = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dcNgayBD = new com.toedter.calendar.JDateChooser();
        dcNgayKT = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnTaomoi = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Khuyến mãi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        tblQlKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã", "Nội dung", "Giảm giá", "Ngày bất đầu", "Ngày kết thúc"
            }
        ));
        tblQlKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQlKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQlKhuyenMai);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        jLabel5.setText("Mã");

        jLabel1.setText("Nội dung");

        jLabel2.setText("Giảm giá");

        jLabel3.setText("Ngày bắt đầu");

        jLabel4.setText("Ngày kết thúc");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(465, 465, 465))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtNoidung, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(153, 153, 153))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(dcNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(dcNgayKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(153, 153, 153)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNoidung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(dcNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(dcNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

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

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnTaomoi.setText("Tạo mới");
        btnTaomoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaomoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTaomoi)
                    .addComponent(btnRefresh)
                    .addComponent(btnXoa)
                    .addComponent(btnSua)
                    .addComponent(btnThem))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addComponent(btnSua)
                .addGap(18, 18, 18)
                .addComponent(btnXoa)
                .addGap(18, 18, 18)
                .addComponent(btnRefresh)
                .addGap(18, 18, 18)
                .addComponent(btnTaomoi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch)
                .addGap(18, 18, 18)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        if(txtSearch.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Không để trống ô tìm kiếm!");
        }else if(!txtSearch.getText().matches("^KM\\d{3}$")){
             JOptionPane.showMessageDialog(this, "Lỗi: Sai định dạng mã khuyến mãi, ví dụ đúng: KM001");
        }else{
        conn=connectdb.getConnection();
        try {
                        JOptionPane.showMessageDialog(this, "Đã tìm thấy thông tin cho "+txtSearch.getText());
            CallableStatement st=conn.prepareCall("{call sp_timkm(?)}");
            st.setString(1, txtSearch.getText());
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                txtMa.setText(rs.getString("MaKM").trim());
                txtNoidung.setText(rs.getString("NoiDungKM").trim());
                txtGiamGia.setText(String.valueOf(rs.getFloat("GiamGia")));
                dcNgayBD.setDate(rs.getDate("NgayBatDau"));
                dcNgayKT.setDate(rs.getDate("NgayKetThuc"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả cho "+txtSearch.getText());
        }
        }
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        teen();
        txtSearch.setText("");
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tblQlKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQlKhuyenMaiMouseClicked

        i = tblQlKhuyenMai.getSelectedRow();
        txtMa.setText(tblQlKhuyenMai.getValueAt(i, 0).toString());
        txtNoidung.setText(tblQlKhuyenMai.getValueAt(i, 1).toString());
        txtGiamGia.setText(tblQlKhuyenMai.getValueAt(i, 2).toString());
        dcNgayBD.setDate((Date) tblQlKhuyenMai.getValueAt(i, 3));
        dcNgayKT.setDate((Date) tblQlKhuyenMai.getValueAt(i, 3));
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        teen();
    }//GEN-LAST:event_tblQlKhuyenMaiMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (checkNullandRegex()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Thêm khuyến mãi "+txtNoidung.getText()+" ?", "Xác nhận thêm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
            KhuyenMai km = getDataForm();
            try {
                repo.add(km);
                KM.add(km);
                fillTable(KM);
                fillTable(repo.getAll());
                JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thành công");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi:" + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);

            }
            clearfrom();
            vh();
            fen();
            }
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
     if (checkNullandRegex()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Sửa thông tin khuyến mãi "+txtNoidung.getText()+"", "Xác nhận sửa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
            KhuyenMai km = getDataForm();
            try {
                repo.update(km);
                KM.add(km);
                fillTable(KM);
                fillTable(repo.getAll());
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi:" + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);

            }
            clearfrom();
            vh();
            fen();
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

          int selectedRow = tblQlKhuyenMai.getSelectedRow();
        String maKM = tblQlKhuyenMai.getValueAt(selectedRow, 0).toString(); // Giả sử mã khuyến mãi ở cột 0
        int confirm = JOptionPane.showConfirmDialog(this, "Xóa khuyến mãi "+txtNoidung.getText()+" ?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Xóa khỏi cơ sở dữ liệu
            repo.delete(maKM);
            // Cập nhật danh sách sau khi xóa
            fillTable(repo.getAll()); // Giả sử fillTable sẽ cập nhật bảng
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        vh();
        fen();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        KM = repo.getAll();
        fillTable(repo.getAll());
        vh();
        fen();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnTaomoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaomoiActionPerformed
        // TODO add your handling code here:
         vh();
         clearfrom();
         ten();
         btnThem.setEnabled(true);
    }//GEN-LAST:event_btnTaomoiActionPerformed
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
            java.util.logging.Logger.getLogger(khuyenmaiview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(khuyenmaiview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(khuyenmaiview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(khuyenmaiview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new khuyenmaiview().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTaomoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private com.toedter.calendar.JDateChooser dcNgayBD;
    private com.toedter.calendar.JDateChooser dcNgayKT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tblQlKhuyenMai;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNoidung;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}

