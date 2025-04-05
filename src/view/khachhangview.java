/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;
import service.KhachHangService;
import util.connectdb;
import java.sql.Connection;
import java.sql.CallableStatement;
/**
 *
 * @author LINH
 */
public class khachhangview extends javax.swing.JFrame {
    Connection conn;
    KhachHangService khs = new KhachHangService();
    DefaultTableModel dtm = new DefaultTableModel();
    ArrayList<KhachHang> listKH = new ArrayList<>();
    private String maNV;

    /**
     * Creates new form test
     */
    public khachhangview(){
        initComponents();
    }
    public khachhangview(String maNV) {
        initComponents();
        dtm = (DefaultTableModel) tabData.getModel();
        listKH = khs.findAll();
        loadTable(listKH);
        this.maNV=maNV;
        vh();
        fen();
    }
    public void vh(){
        btnThem.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
    }

    public void loadTable(ArrayList<KhachHang> list) {

        ArrayList<KhachHang> kh = khs.findAll();
        dtm.setRowCount(0);
        for (KhachHang kH : kh) {
            dtm.addRow(new Object[]{
                kH.getMaKH(),
                kH.getTenKH(),
                kH.getTuoiKH(),
                kH.getEmailKH(),
                kH.getDiaChiKH(),
                kH.getSdtKH(),
                kH.getTreEm(),
                kH.getNguoiLon(),
                kH.getGhiChu()});
        }
    }

    public void detailTable(int index) {
        if (index >= 0 && index < listKH.size()) {
            KhachHang kh = listKH.get(index);
            txtMa.setText(String.valueOf(kh.getMaKH()));
            txtTen.setText(kh.getTenKH());
            txtTuoi.setText(String.valueOf(kh.getTuoiKH()));
            txtEmail.setText(kh.getEmailKH());
            txtDiaChi.setText(kh.getDiaChiKH());
            txtSdt.setText(String.valueOf(kh.getSdtKH()));
            txtTreEm.setText(String.valueOf(kh.getTreEm()));
            txtNguoiLon.setText(String.valueOf(kh.getNguoiLon()));
            txtGhiChu.setText(kh.getGhiChu());
        }
    }

    public KhachHang getDataForm() {
        String ma = txtMa.getText();
        String ten = txtTen.getText();
        String tuoi = txtTuoi.getText();
        String email = txtEmail.getText();
        String diaChi = txtDiaChi.getText();
        String sdt = txtSdt.getText();
        String treEm = txtTreEm.getText();
        String nguoiLon = txtNguoiLon.getText();
        String ghiChu = txtGhiChu.getText();
        return new KhachHang(ma, ten, Integer.parseInt(tuoi), email, diaChi, sdt, Integer.parseInt(treEm), Integer.parseInt(nguoiLon), ghiChu);
    }
    public boolean checkNullandRegex() {
        if (txtMa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã khách hàng!");
            txtMa.requestFocus();
            return false;
        }
        if (txtTen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống tên khách hàng!");
            txtTen.requestFocus();
            return false;
        }
        if (txtTuoi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống tuổi khách hàng!");
            txtTuoi.requestFocus();
            return false;
        }
        if (txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống email khách hàng!");
            txtEmail.requestFocus();
            return false;
        }
        if (txtDiaChi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống địa chỉ khách hàng!");
            txtDiaChi.requestFocus();
            return false;
        }
        if (txtSdt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống sdt khách hàng!");
            txtSdt.requestFocus();
            return false;
        }
        if (txtTreEm.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống số lượng trẻ em, nếu không có thì nhập là 0");
            txtTreEm.requestFocus();
            return false;
        }
        if (txtNguoiLon.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống số lượng người lớn!");
            txtNguoiLon.requestFocus();
            return false;
        }
        if (Integer.valueOf(txtTuoi.getText())<18){
            JOptionPane.showMessageDialog(this, "Tuổi phải lớn hơn 18!");
            txtTuoi.requestFocus();
            return false;
        }
        String sdtregex= "^0\\d{9}$";
        String emailregex= "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        String soregex="^[1-9]\\d*(\\.\\d+)?$";
        String makhregex="^KH\\d{3}$";
        if(!txtMa.getText().matches(makhregex)){
            JOptionPane.showMessageDialog(this, "Lỗi: Sai định dạng mã khách hàng, ví dụ đúng: KH001");
            txtMa.requestFocus();
            return false;
        }
        if(!txtSdt.getText().matches(sdtregex)){
            JOptionPane.showMessageDialog(this, "Lỗi: Sai định dạng sdt!");
            txtSdt.requestFocus();
            return false;
        }
        if(!txtEmail.getText().matches(emailregex)){
            JOptionPane.showMessageDialog(this, "Lỗi: Sai định dạng Email!");
            txtEmail.requestFocus();
            return false;
        }
        if(!txtNguoiLon.getText().matches(soregex)){
            JOptionPane.showMessageDialog(this, "Lỗi: Giá trị vừa được nhập vào không phải là só!");
            txtNguoiLon.requestFocus();
            return false;
        }
        if(!txtTreEm.getText().matches(soregex)){
            JOptionPane.showMessageDialog(this, "Lỗi: Giá trị vừa được nhập vào không phải là số!");
            txtTreEm.requestFocus();
            return false;
        }
        if(!txtTuoi.getText().matches(soregex)){
            JOptionPane.showMessageDialog(this, "Lỗi: Giá trị vừa được nhập vào không phải là só!");
            txtTuoi.requestFocus();
            return false;
        }
        return true;
    }
    public void fen (){
        txtMa.setEditable(false);
        txtTen.setEditable(false);
        txtTuoi.setEditable(false);
        txtEmail.setEditable(false);
        txtDiaChi.setEditable(false);
        txtSdt.setEditable(false);
        txtTreEm.setEditable(false);
        txtNguoiLon.setEditable(false);
        txtGhiChu.setEditable(false);
    }
    public void ten(){
        txtMa.setEditable(true);
        txtTen.setEditable(true);
        txtTuoi.setEditable(true);
        txtEmail.setEditable(true);
        txtDiaChi.setEditable(true);
        txtSdt.setEditable(true);
        txtTreEm.setEditable(true);
        txtNguoiLon.setEditable(true);
        txtGhiChu.setEditable(true);
    }
    public void teen(){
        txtTen.setEditable(true);
        txtTuoi.setEditable(true);
        txtEmail.setEditable(true);
        txtDiaChi.setEditable(true);
        txtSdt.setEditable(true);
        txtTreEm.setEditable(true);
        txtNguoiLon.setEditable(true);
        txtGhiChu.setEditable(true);
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
        tabData = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txtGhiChu = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNguoiLon = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTreEm = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtTuoi = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        txtSearch = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnTaomoi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Khách hàng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        tabData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã KH", "Tên KH", "Tuổi ", "Email ", "Địa chỉ ", "SDT ", "Trẻ em", "Người lớn", "Ghi chú"
            }
        ));
        tabData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabData);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, 780, 270));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        txtGhiChu.setColumns(20);

        jLabel10.setText("Ghi chú");

        jLabel9.setText("Người lớn");

        jLabel8.setText("Trẻ em");

        jLabel7.setText("SDT");

        jLabel3.setText("Tên KH");

        jLabel4.setText("Tuổi ");

        jLabel5.setText("Email ");

        jLabel6.setText("Địa chỉ");

        txtTuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTuoiActionPerformed(evt);
            }
        });

        jLabel11.setText("Mã KH");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtTreEm, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(txtTuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addComponent(jLabel9)
                        .addGap(6, 6, 6)
                        .addComponent(txtNguoiLon, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(13, 13, 13)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(53, 53, 53)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtTreEm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(txtNguoiLon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel10)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 780, 220));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        txtSearch.setText("Search");
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch)
                    .addComponent(btnRefresh))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 390, 80));

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
                .addContainerGap()
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(btnXoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTaomoi)
                .addGap(10, 10, 10))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnTaomoi))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, 360, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
            String searchText = txtSearch.getText().trim();
            if (!searchText.isEmpty()) {
                String maKH = searchText;
                KhachHang kh = khs.findById(maKH);
                if (kh != null) {
                    // Clear existing data in table
                    JOptionPane.showMessageDialog(this, "Đã tìm thấy thông tin cho "+txtSearch.getText());
                    listKH.clear();
                    listKH.add(kh);
                    loadTable(listKH);
                    detailTable(0); // Hiển thị chi tiết của dịch vụ tìm thấy
                    dtm.setRowCount(0);
                    // Add the found item to table
                    dtm.addRow(new Object[]{
                        kh.getMaKH(),
                        kh.getTenKH(),
                        kh.getTuoiKH(),
                        kh.getEmailKH(),
                        kh.getDiaChiKH(),
                        kh.getSdtKH(),
                        kh.getTreEm(),
                        kh.getNguoiLon(),
                        kh.getGhiChu()});
                // Update details in the text fields
                detailTable(0); // Since there's only one item, use index 0
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả cho "+txtSearch.getText());
                // Clear table if no item found
                dtm.setRowCount(0);
                // Clear detail fields
                txtMa.setText("");
                txtTen.setText("");
                txtTuoi.setText("");
                txtEmail.setText("");
                txtDiaChi.setText("");
                txtSdt.setText("");
                txtTreEm.setText("");
                txtNguoiLon.setText("");
                txtGhiChu.setText("");
            }
        }else if (!txtSearch.getText().matches("^KH\\d{3}$")){
            JOptionPane.showMessageDialog(this, "Lỗi: Sai định dạng mã khách hàng, ví dụ đúng: KH001");
        }else{
            JOptionPane.showMessageDialog(this, "Không để trống ô tìm kiếm.");
        }
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        teen();
        txtSearch.setText("");
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (checkNullandRegex()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Sửa thông tin khách hàng "+txtTen.getText()+"", "Xác nhận sửa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
            int selectedRow = tabData.getSelectedRow();
                KhachHang kh = getDataForm();
                try{
                    khs.change(kh);
                    listKH.set(selectedRow, kh);
                    loadTable(listKH);
                } catch(SQLException e) {
                    JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                JOptionPane.showMessageDialog(this, "Sửa thành công");
            vh();
            fen();
            }
        }
        
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

        int selectedRow = tabData.getSelectedRow();
            String maKH = (String) dtm.getValueAt(selectedRow, 0); // Đọc mã khách hàng là String
            int confirm = JOptionPane.showConfirmDialog(this, "Xóa khách hàng"+txtTen.getText()+" ?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    // Xóa khách hàng từ cơ sở dữ liệu
                    khs.delete(maKH);

                    // Xóa khách hàng từ danh sách và cập nhật bảng
                    listKH.remove(selectedRow);
                    loadTable(listKH);

                    JOptionPane.showMessageDialog(this, "Xóa khách hàng thành công");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                 vh();
                 fen();
            }
        
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // Làm sạch các ô nhập liệu
        dtm.setRowCount(0);
        // Tải lại tất cả dịch vụ
        listKH = khs.findAll();
        loadTable(listKH);
        vh();
        fen();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txtTuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTuoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTuoiActionPerformed

    private void tabDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabDataMouseClicked
        int index = tabData.getSelectedRow();
        detailTable(index);
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        teen();
    }//GEN-LAST:event_tabDataMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
    if (checkNullandRegex()) {
        int confirm = JOptionPane.showConfirmDialog(this, "Thêm khách hàng "+txtTen.getText()+" ?", "Xác nhận thêm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
    KhachHang kh = getDataForm();
    try {
        khs.add(kh);
        listKH.add(kh);
        loadTable(listKH);
        JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công");
         try (Connection conn = connectdb.getConnection()) {
                    CallableStatement st = conn.prepareCall("{call sp_themhoadon(?, ?)}");
                    st.setString(1, txtMa.getText());
                    st.setString(2, maNV);
                    st.execute();

                    JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    vh();
    fen();
            }
    }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnTaomoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaomoiActionPerformed
        // TODO add your handling code here:
        vh();
        txtMa.setText("");
        txtTen.setText("");
        txtTuoi.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        txtSdt.setText("");
        txtTreEm.setText("");
        txtNguoiLon.setText("");
        txtGhiChu.setText("");
        btnThem.setEnabled(true);
        ten();
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
            java.util.logging.Logger.getLogger(khachhangview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(khachhangview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(khachhangview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(khachhangview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new khachhangview().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTaomoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tabData;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNguoiLon;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JButton txtSearch;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTreEm;
    private javax.swing.JTextField txtTuoi;
    // End of variables declaration//GEN-END:variables
}

