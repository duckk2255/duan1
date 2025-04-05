/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Color;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.chitiethoadon;
import model.hoadon;
import model.tour;
import model.DichVu;
import model.KhuyenMai;
import service.chitiethoadonservice;
import service.hoadonservice;
import service.tourservice;
import service.DichVuService;
import service.KhuyenMaiService;
import view.tourview;
import java.sql.Connection;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import util.connectdb;

/**
 *
 * @author LINH
 */
public class hoadonview extends javax.swing.JFrame {

    private ArrayList<hoadon> lsthd;
    private ArrayList<chitiethoadon> lstcthd;
    private ArrayList<tour> lsttour;
    private ArrayList<DichVu> lstdv;
    private ArrayList<KhuyenMai> lstkm;
    private hoadonservice hdsc;
    private chitiethoadonservice ctsc;
    private tourservice toursc;
    private DichVuService dvsc;
    private KhuyenMaiService kmsc;
    private Connection conn;

    /**
     * Creates new form test
     */
    public hoadonview() throws SQLException {
        initComponents();
        hdsc = new hoadonservice();
        ctsc = new chitiethoadonservice();
        toursc = new tourservice();
        dvsc = new DichVuService();
        kmsc = new KhuyenMaiService();
        this.getDataHoaDon();
        this.getDataChiTiet();
        this.getDataTour();
        this.getDataDV();
        this.getDataKM();
        this.displayToTableHD();
        this.displayToControlHD(indexhd);
        this.displayToTableCTHD();
        this.displayToControlCTHD(indexct);
        this.loadcombo();
        btnXoa1.setEnabled(false);
        btnSuaCT.setEnabled(false);
        btnSua1.setEnabled(false);
        txtSLNL.setEditable(false);
        txtSLTE.setEditable(false);
        txtSLDV.setEditable(false);
        txtMaKH.setEditable(false);
    }

    public void getDataHoaDon() throws SQLException {
        lsthd = hdsc.getData();
    }

    public void getDataChiTiet() throws SQLException {
        lstcthd = ctsc.getData();
    }

    public void getDataTour() throws SQLException {
        lsttour = toursc.getData();
    }

    public void getDataDV() {
        lstdv = dvsc.findAll();
    }

    public void getDataKM() {
        lstkm = kmsc.getAll();
    }

    public void displayToTableHD() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setNumRows(0);
        for (hoadon hd : lsthd) {
            Object[] row = {hd.getMaHD(), hd.getMaKH(), hd.getMaNV(), hd.getNgayTaoHD()};
            model.addRow(row);
        }
    }

    public void displayToTableCTHD() {
        DefaultTableModel model = (DefaultTableModel) tblCTHD.getModel();
        model.setNumRows(0);
        for (chitiethoadon cthd : lstcthd) {
            Object[] row = {cthd.getMaHD(), cthd.getMaTour(), cthd.getTenTour(), cthd.getMaDV(), cthd.getTenDV(), cthd.getMaKM(), cthd.getNoiDungKM(), cthd.getPhanTramKM(), cthd.getTongTien()};
            model.addRow(row);
        }
    }

    public void check() {
        if (cbMaTour.getSelectedItem().equals("Mã tour") && cbMaDV.getSelectedItem().equals("Mã DV")) {
            JOptionPane.showMessageDialog(this, "Chọn sản phẩm trước, sau đó mới nhập số lượng");
        } else {
            txtSLNL.setEditable(true);
            txtSLTE.setEditable(true);
            txtSLDV.setEditable(true);
        }
    }
    int indexhd = 0;

    public void displayToControlHD(int indexhd) {
        hoadon hd = lsthd.get(indexhd);
        txtMaHD.setText(hd.getMaHD());
        txtMaKH.setText(hd.getMaKH());
        txtMaNV.setText(hd.getMaNV());
        jDateChooser1.setDate(hd.getNgayTaoHD());
    }
    int indexct = 0;

    public void displayToControlCTHD(int indexct) {
        chitiethoadon cthd = lstcthd.get(indexct);
        txtMaHDCT.setText(cthd.getMaHD());
        cbMaTour.setSelectedItem(cthd.getMaTour());
        txtTenTour.setText(cthd.getTenTour());
        txtGiaNL.setText(String.valueOf(cthd.getGiaNL()));
        txtGiaTE.setText(String.valueOf(cthd.getGiaTE()));
        txtSLNL.setText(String.valueOf(cthd.getSLNL()));
        txtSLTE.setText(String.valueOf(cthd.getSLTE()));
        cbMaDV.setSelectedItem(cthd.getMaDV());
        txtTenDV.setText(cthd.getTenDV());
        txtGiaDV.setText(String.valueOf(cthd.getGiaDV()));
        txtSLDV.setText(String.valueOf(cthd.getSLDV()));
        cbMaKM.setSelectedItem(cthd.getMaKM());
        txtTenKM.setText(cthd.getNoiDungKM());
        txtKM.setText(String.valueOf(cthd.getPhanTramKM()));
        txtTongCong.setText(String.valueOf(cthd.getTongTien()));
        txtTongNL.setText(String.valueOf(cthd.getGiaNL() * cthd.getSLNL()));
        txtTongTE.setText(String.valueOf(cthd.getGiaTE() * cthd.getSLTE()));
        txtTongDV.setText(String.valueOf(cthd.getGiaDV() * cthd.getSLDV()));
    }

    public void loadcombo() {
        cbMaTour.removeAllItems();
        cbMaDV.removeAllItems();
        cbMaKM.removeAllItems();
        cbMaTour.addItem("Mã tour");
        cbMaDV.addItem("Mã DV");
        cbMaKM.addItem("Mã KM");
        for (tour tr : lsttour) {
            cbMaTour.addItem(tr.getMaTour());
        }
        for (DichVu dv : lstdv) {
            cbMaDV.addItem(dv.getMaDV());
        }
        for (KhuyenMai km : lstkm) {
            cbMaKM.addItem(km.getMaKM());
        }
    }

    public void displaycb() {
        for (tour tr : lsttour) {
            if (tr.getMaTour() == cbMaTour.getSelectedItem()) {
                txtTenTour.setText(tr.getTenTour());
                txtGiaNL.setText(String.valueOf(tr.getGiaNguoiLon()));
                txtGiaTE.setText(String.valueOf(tr.getGiaTreEm()));
            }
        }
        for (DichVu dv : lstdv) {
            if (dv.getMaDV() == cbMaDV.getSelectedItem()) {
                txtTenDV.setText(dv.getTenDV());
                txtGiaDV.setText(String.valueOf(dv.getGiaDV()));
            }
        }
        for (KhuyenMai km : lstkm) {
            if (km.getMaKM() == cbMaKM.getSelectedItem()) {
                txtTenKM.setText(km.getNoiDungKM());
                txtKM.setText(String.valueOf(km.getGiamGia()));
            }
        }
    }

    public void cbdefaut() {
        if (cbMaTour.getSelectedItem().equals("Mã tour")) {
            txtTenTour.setText("");
            txtGiaNL.setText("");
            txtGiaTE.setText("");
        }
        if (cbMaDV.getSelectedItem().equals("Mã DV")) {
            txtTenDV.setText("");
            txtGiaDV.setText("");
        }
        if (cbMaKM.getSelectedItem().equals("Mã KM")) {
            txtTenKM.setText("");
            txtKM.setText("");
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

        jButton1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        txtTimKiemMa = new javax.swing.JTextField();
        btnTimKiem1 = new javax.swing.JButton();
        btnRefresh1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel10 = new javax.swing.JPanel();
        btnChiTiet = new javax.swing.JButton();
        btnSua1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtMaHDCT = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        cbMaDV = new javax.swing.JComboBox<>();
        cbMaTour = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbMaKM = new javax.swing.JComboBox<>();
        txtTenKM = new javax.swing.JTextField();
        txtTenDV = new javax.swing.JTextField();
        txtTenTour = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtGiaNL = new javax.swing.JTextField();
        txtGiaTE = new javax.swing.JTextField();
        txtGiaDV = new javax.swing.JTextField();
        txtSLDV = new javax.swing.JTextField();
        txtSLTE = new javax.swing.JTextField();
        txtSLNL = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTongNL = new javax.swing.JTextField();
        txtTongTE = new javax.swing.JTextField();
        txtKM = new javax.swing.JTextField();
        txtTongDV = new javax.swing.JTextField();
        txtTongCong = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTHD = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        btnRefresh = new javax.swing.JButton();
        btnXoa1 = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnSuaCT = new javax.swing.JButton();
        btnXuatCT = new javax.swing.JButton();

        jButton1.setText("jButton1");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(51, 153, 255));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Hóa đơn");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(395, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Mã khách hàng", "Mã nhân viên", "Ngày tạo"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDon);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        btnTimKiem1.setText("Tìm kiếm");
        btnTimKiem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem1ActionPerformed(evt);
            }
        });

        btnRefresh1.setText("Refresh");
        btnRefresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefresh1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemMa, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimKiem1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRefresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem1)
                    .addComponent(btnRefresh1))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        jLabel26.setText("Mã hóa đơn:");

        txtMaHD.setEditable(false);

        jLabel27.setText("Mã khách hàng:");

        txtMaKH.setEditable(false);

        jLabel28.setText("Mã nhân viên:");

        txtMaNV.setEditable(false);

        jLabel29.setText("Ngày tạo:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaHD)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnChiTiet.setText("Chi tiết hóa đơn");
        btnChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietActionPerformed(evt);
            }
        });

        btnSua1.setText("Sửa");
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnChiTiet)
                .addGap(62, 62, 62)
                .addComponent(btnSua1)
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChiTiet)
                    .addComponent(btnSua1))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel4);

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Chi tiết hóa đơn");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(401, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jLabel2.setText("Mã hóa đơn");

        txtMaHDCT.setEditable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn chi tiết"));

        cbMaDV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbMaDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMaDVActionPerformed(evt);
            }
        });

        cbMaTour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbMaTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMaTourActionPerformed(evt);
            }
        });

        jLabel3.setText("Mã");

        cbMaKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbMaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMaKMActionPerformed(evt);
            }
        });

        txtTenKM.setEditable(false);

        txtTenDV.setEditable(false);

        txtTenTour.setEditable(false);

        jLabel4.setText("Tên");

        jLabel5.setText("Giá");

        txtGiaNL.setEditable(false);
        txtGiaNL.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                txtGiaNLCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtGiaNLInputMethodTextChanged(evt);
            }
        });

        txtGiaTE.setEditable(false);
        txtGiaTE.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                txtGiaTECaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtGiaTEInputMethodTextChanged(evt);
            }
        });

        txtGiaDV.setEditable(false);
        txtGiaDV.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                txtGiaDVCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtGiaDVInputMethodTextChanged(evt);
            }
        });

        txtSLDV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSLDVMouseClicked(evt);
            }
        });
        txtSLDV.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                txtSLDVCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtSLDVInputMethodTextChanged(evt);
            }
        });

        txtSLTE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSLTEMouseClicked(evt);
            }
        });
        txtSLTE.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                txtSLTECaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtSLTEInputMethodTextChanged(evt);
            }
        });

        txtSLNL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSLNLMouseClicked(evt);
            }
        });
        txtSLNL.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                txtSLNLCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtSLNLInputMethodTextChanged(evt);
            }
        });

        jLabel6.setText("Số lượng");

        jLabel7.setText("Tổng");

        txtTongNL.setEditable(false);
        txtTongNL.setText("0");

        txtTongTE.setEditable(false);
        txtTongTE.setText("0");

        txtKM.setEditable(false);
        txtKM.setText("0");
        txtKM.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                txtKMCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtKMInputMethodTextChanged(evt);
            }
        });

        txtTongDV.setEditable(false);
        txtTongDV.setText("0");

        txtTongCong.setEditable(false);
        txtTongCong.setText("0");

        jLabel8.setText("Tổng cộng:");

        jLabel1.setText("%");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtTongCong, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbMaTour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbMaDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiaNL, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiaTE, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiaDV, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSLNL, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtSLDV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                        .addComponent(txtSLTE, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(txtTenDV, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenTour, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTongNL, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKM, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTongDV, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTongTE, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenTour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaNL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSLNL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongNL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMaTour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSLTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSLDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMaDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTongCong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        tblCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Mã tour", "Tên tour", "Mã DV", "Tên DV", "Mã KM", "Nội dung KM", "Khuyến mãi", "Tổng tiền"
            }
        ));
        tblCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTHDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCTHD);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnXoa1.setText("Xóa");
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });

        btnBack.setText("Quay lại ");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnSuaCT.setText("Thanh toán");
        btnSuaCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTActionPerformed(evt);
            }
        });

        btnXuatCT.setText("Xuất HD");
        btnXuatCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatCTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuaCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXuatCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRefresh)
                .addGap(18, 18, 18)
                .addComponent(btnXoa1)
                .addGap(27, 27, 27)
                .addComponent(btnBack)
                .addGap(31, 31, 31)
                .addComponent(btnSuaCT)
                .addGap(18, 18, 18)
                .addComponent(btnXuatCT)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel23)
                    .addGap(0, 890, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel24)
                    .addGap(0, 890, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 1, Short.MAX_VALUE)
                    .addComponent(jLabel23)
                    .addGap(0, 774, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 1, Short.MAX_VALUE)
                    .addComponent(jLabel24)
                    .addGap(0, 774, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab1", jPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -36, -1, 810));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        int indexhd = tblHoaDon.getSelectedRow();
        this.displayToControlHD(indexhd);
        btnSua1.setEnabled(true);
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Sửa thông tin hóa đơn " + txtMaHD.getText() + " ?", "Xác nhận sửa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int indexhd = tblHoaDon.getSelectedRow();
            hoadon hd = lsthd.get(indexhd);
            java.util.Date utilNgayTao = jDateChooser1.getDate();
            java.sql.Date sqlNgayTao = new java.sql.Date(utilNgayTao.getTime());
            hd.setNgayTaoHD(sqlNgayTao);
            try {
                hdsc.sua(hd);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(this, "Sửa thành công");
            lsthd.clear();
            try {
                this.getDataHoaDon();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            this.displayToTableHD();
            btnSua1.setEnabled(false);
        }
    }//GEN-LAST:event_btnSua1ActionPerformed

    private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnChiTietActionPerformed

    private void cbMaTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMaTourActionPerformed
        // TODO add your handling code here:
        this.displaycb();
    }//GEN-LAST:event_cbMaTourActionPerformed

    private void cbMaDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMaDVActionPerformed
        // TODO add your handling code here:
        this.displaycb();
    }//GEN-LAST:event_cbMaDVActionPerformed

    private void cbMaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMaKMActionPerformed
        // TODO add your handling code here:
        this.displaycb();
    }//GEN-LAST:event_cbMaKMActionPerformed

    private void txtSLNLCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtSLNLCaretPositionChanged
        // TODO add your handling code here:
        tinhtong();
    }//GEN-LAST:event_txtSLNLCaretPositionChanged

    private void txtSLNLInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtSLNLInputMethodTextChanged
        // TODO add your handling code here:
        tinhtong();
    }//GEN-LAST:event_txtSLNLInputMethodTextChanged

    private void txtSLTECaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtSLTECaretPositionChanged
        // TODO add your handling code here:
        tinhtong();
    }//GEN-LAST:event_txtSLTECaretPositionChanged

    private void txtSLTEInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtSLTEInputMethodTextChanged
        // TODO add your handling code here:
        tinhtong();
    }//GEN-LAST:event_txtSLTEInputMethodTextChanged

    private void txtSLDVCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtSLDVCaretPositionChanged
        // TODO add your handling code here:
        tinhtong();
    }//GEN-LAST:event_txtSLDVCaretPositionChanged

    private void txtSLDVInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtSLDVInputMethodTextChanged
        // TODO add your handling code here:
        tinhtong();
    }//GEN-LAST:event_txtSLDVInputMethodTextChanged

    private void txtGiaNLCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtGiaNLCaretPositionChanged
        // TODO add your handling code here:
        tinhtong();
    }//GEN-LAST:event_txtGiaNLCaretPositionChanged

    private void txtGiaNLInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtGiaNLInputMethodTextChanged
        // TODO add your handling code here:
        tinhtong();
    }//GEN-LAST:event_txtGiaNLInputMethodTextChanged

    private void txtGiaTECaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtGiaTECaretPositionChanged
        // TODO add your handling code here:
        tinhtong();
    }//GEN-LAST:event_txtGiaTECaretPositionChanged

    private void txtGiaTEInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtGiaTEInputMethodTextChanged
        // TODO add your handling code here:
        tinhtong();
    }//GEN-LAST:event_txtGiaTEInputMethodTextChanged

    private void txtGiaDVCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtGiaDVCaretPositionChanged
        // TODO add your handling code here:
        tinhtong();
    }//GEN-LAST:event_txtGiaDVCaretPositionChanged

    private void txtGiaDVInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtGiaDVInputMethodTextChanged
        // TODO add your handling code here:
        tinhtong();
    }//GEN-LAST:event_txtGiaDVInputMethodTextChanged

    private void txtKMInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtKMInputMethodTextChanged
        // TODO add your handling code here:
        tinhtong();
    }//GEN-LAST:event_txtKMInputMethodTextChanged

    private void txtKMCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtKMCaretPositionChanged
        // TODO add your handling code here:
        tinhtong();
    }//GEN-LAST:event_txtKMCaretPositionChanged

    private void btnXuatCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatCTActionPerformed
        // TODO add your handling code here
        int confirm = JOptionPane.showConfirmDialog(this, "Xuất thông tin hóa đơn " + txtMaHD.getText() + " ?", "Xác nhận xuất", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
        String directory = "C:\\Users\\LINH";
        String maKH = txtMaKH.getText();
        File file = new File(directory, "hoadon_" + maKH + ".txt");
        try (PrintWriter p = new PrintWriter(file)) {
            p.println("Công ty Cổ phần Lữ hành Quốc tế Kim Liên");
            p.println("Mã số thuế / Giấy phép kinh doanh: 0102683524");
            p.println("Giấy phép lữ hành quốc tế: 01-098/2016/TCDL-GP LHQT");
            p.println("Địa chỉ trụ sở: Tầng 3, Tòa nhà Udic Riverside 1, 122 Vĩnh Tuy, Hai Bà Trưng, Hà Nội");
            p.println("Điện thoại : 0903 230 230");
            p.println("Email : booking@kimlientravel.com.vn");
            p.println("---------------------------------------------------------------");

            // Đề mục
            p.printf("%-40s %-10s %-10s %-10s%n", "Tên", "Giá", "Số lượng", "Tổng");
            p.println("---------------------------------------------------------------");

            // Thông tin hóa đơn
            p.printf("%-40s %-10.2f %-10d %-10.2f%n",
                    txtTenTour.getText() + " (Adult)",
                    Float.parseFloat(txtGiaNL.getText()),
                    Integer.parseInt(txtSLNL.getText()),
                    Float.parseFloat(txtTongNL.getText()));

            p.printf("%-40s %-10.2f %-10d %-10.2f%n",
                    txtTenTour.getText() + " (Kid)",
                    Float.parseFloat(txtGiaTE.getText()),
                    Integer.parseInt(txtSLTE.getText()),
                    Float.parseFloat(txtTongTE.getText()));

            p.printf("%-40s %-10.2f %-10d %-10.2f%n",
                    txtTenDV.getText(),
                    Float.parseFloat(txtGiaDV.getText()),
                    Integer.parseInt(txtSLDV.getText()),
                    Float.parseFloat(txtTongDV.getText()));

            p.println("---------------------------------------------------------------");
            p.printf("%-50s %-10.2f%%%n",
                    txtTenKM.getText(),
                    Float.parseFloat(txtKM.getText()));

            p.printf("%-50s %-10.2f%n",
                    "Tổng cộng",
                    Float.parseFloat(txtTongCong.getText()));
            p.println("---------------------------------------------------------------");

            // Ngày, giờ và lời cảm ơn
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDateTime = now.format(formatter);

            p.println("Ngày giờ: " + formattedDateTime);
            p.println("Cảm ơn quý khách đã sử dụng dịch vụ của chúng tôi!");
            JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công1");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        }

    }//GEN-LAST:event_btnXuatCTActionPerformed

    private void btnSuaCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Thanh toán hóa đơn " + txtMaHD.getText() + " ?", "Xác nhận thanh toán", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int indexct = tblCTHD.getSelectedRow();
            if (indexct >= 0) {
                chitiethoadon cthd = lstcthd.get(indexct);
                cthd.setMaTour((String) cbMaTour.getSelectedItem());
                cthd.setTenTour(txtTenTour.getText());
                cthd.setGiaNL(Float.valueOf(txtGiaNL.getText()));
                cthd.setSLNL(Integer.valueOf(txtSLNL.getText()));
                cthd.setGiaTE(Float.valueOf(txtGiaTE.getText()));
                cthd.setSLTE(Integer.valueOf(txtSLTE.getText()));
                cthd.setMaDV((String) cbMaDV.getSelectedItem());
                cthd.setTenDV(txtTenDV.getText());
                cthd.setGiaDV(Float.valueOf(txtGiaDV.getText()));
                cthd.setSLDV(Integer.valueOf(txtSLDV.getText()));
                cthd.setMaKM((String) cbMaKM.getSelectedItem());
                cthd.setNoiDungKM(txtTenKM.getText());
                cthd.setPhanTramKM(Float.valueOf(txtKM.getText()));
                cthd.setTongTien(Float.valueOf(txtTongCong.getText()));
                try {
                    ctsc.sua(cthd);
                    displayToTableCTHD();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                JOptionPane.showMessageDialog(this, "Đã thanh toán thành công!");
            }
            btnXoa1.setEnabled(false);
            btnSuaCT.setEnabled(false);
            txtSLNL.setEditable(false);
            txtSLTE.setEditable(false);
            txtSLDV.setEditable(false);
        }
    }//GEN-LAST:event_btnSuaCTActionPerformed

    private void tblCTHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTHDMouseClicked
        // TODO add your handling code here:
        int indexct = tblCTHD.getSelectedRow();
        this.displayToControlCTHD(indexct);
        btnXoa1.setEnabled(true);
        btnSuaCT.setEnabled(true);
    }//GEN-LAST:event_tblCTHDMouseClicked

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnTimKiem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem1ActionPerformed
        // TODO add your handling code here:
        if (txtTimKiemMa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống ô tìm kiếm!");
        } else if (txtTimKiemMa.getText().matches("^HD\\\\d{3}$")) {
            JOptionPane.showMessageDialog(this, "Lỗi: Sai định dạng mã hóa đơn, ví dụ đúng: HD001");
        } else {
            JOptionPane.showMessageDialog(this, "Đã tìm thấy thông tin cho mã hoa don " + txtTimKiemMa.getText());
            conn = connectdb.getConnection();
            try {
                CallableStatement st = conn.prepareCall("{call sp_timhd(?)}");
                st.setString(1, txtTimKiemMa.getText());
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    txtMaHD.setText(rs.getString("MaHD").trim());
                    txtMaKH.setText(rs.getString("MaKH").trim());
                    txtMaNV.setText(rs.getString("MaNV").trim());
                    jDateChooser1.setDate(rs.getDate("NgayTaoHD"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả cho " + txtTimKiemMa.getText());
            }
        }

    }//GEN-LAST:event_btnTimKiem1ActionPerformed

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Xóa hóa đơn " + txtMaHD.getText() + " ?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int index = tblHoaDon.getSelectedRow();
            try {
                ctsc.xoa(txtMaHD.getText());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            lsthd.clear();
            lstcthd.clear();
            try {
                this.getDataHoaDon();
                this.getDataChiTiet();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            this.displayToTableHD();
            this.displayToTableCTHD();
            btnXoa1.setEnabled(false);
            btnSuaCT.setEnabled(false);
        }
    }//GEN-LAST:event_btnXoa1ActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        lsthd.clear();
        lstcthd.clear();
        try {
            this.getDataHoaDon();
            this.getDataChiTiet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        this.displayToTableHD();
        this.displayToTableCTHD();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnRefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh1ActionPerformed
        // TODO add your handling code here:
        lsthd.clear();
        lstcthd.clear();
        try {
            this.getDataHoaDon();
            this.getDataChiTiet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        this.displayToTableHD();
        this.displayToTableCTHD();
    }//GEN-LAST:event_btnRefresh1ActionPerformed

    private void txtSLNLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSLNLMouseClicked
        // TODO add your handling code here:
        check();
    }//GEN-LAST:event_txtSLNLMouseClicked

    private void txtSLTEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSLTEMouseClicked
        // TODO add your handling code here:
        check();
    }//GEN-LAST:event_txtSLTEMouseClicked

    private void txtSLDVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSLDVMouseClicked
        // TODO add your handling code here:
        check();
    }//GEN-LAST:event_txtSLDVMouseClicked

    private void tinhtong() {
        try {
            float nl = (Float.valueOf(txtGiaNL.getText().trim().isEmpty() ? "0" : txtGiaNL.getText().trim()))
                    * (Float.valueOf(txtSLNL.getText().trim().isEmpty() ? "0" : txtSLNL.getText().trim()));
            float te = (Float.valueOf(txtGiaTE.getText().trim().isEmpty() ? "0" : txtGiaTE.getText().trim()))
                    * (Float.valueOf(txtSLTE.getText().trim().isEmpty() ? "0" : txtSLTE.getText().trim()));
            float dv = (Float.valueOf(txtGiaDV.getText().trim().isEmpty() ? "0" : txtGiaDV.getText().trim()))
                    * (Float.valueOf(txtSLDV.getText().trim().isEmpty() ? "0" : txtSLDV.getText().trim()));
            float km = Float.valueOf(txtKM.getText().trim().isEmpty() ? "1" : txtKM.getText().trim()); // km mặc định là 1 nếu rỗng

            txtTongNL.setText(String.valueOf(nl));
            txtTongTE.setText(String.valueOf(te));
            txtTongDV.setText(String.valueOf(dv));
            txtTongCong.setText(String.valueOf((nl + te + dv) - (nl + te + dv) * (km / 100)));
        } catch (NumberFormatException e) {
            // In ra console lỗi để debug, hoặc hiển thị thông báo lỗi cho người dùng
            System.out.println("Lỗi: " + e.getMessage());
        }
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
            java.util.logging.Logger.getLogger(hoadonview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(hoadonview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(hoadonview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(hoadonview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                try {
                    new hoadonview().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(hoadonview.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnChiTiet;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRefresh1;
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnSuaCT;
    private javax.swing.JButton btnTimKiem1;
    private javax.swing.JButton btnXoa1;
    private javax.swing.JButton btnXuatCT;
    private javax.swing.JComboBox<String> cbMaDV;
    private javax.swing.JComboBox<String> cbMaKM;
    private javax.swing.JComboBox<String> cbMaTour;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblCTHD;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtGiaDV;
    private javax.swing.JTextField txtGiaNL;
    private javax.swing.JTextField txtGiaTE;
    private javax.swing.JTextField txtKM;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaHDCT;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtSLDV;
    private javax.swing.JTextField txtSLNL;
    private javax.swing.JTextField txtSLTE;
    private javax.swing.JTextField txtTenDV;
    private javax.swing.JTextField txtTenKM;
    private javax.swing.JTextField txtTenTour;
    private javax.swing.JTextField txtTimKiemMa;
    private javax.swing.JTextField txtTongCong;
    private javax.swing.JTextField txtTongDV;
    private javax.swing.JTextField txtTongNL;
    private javax.swing.JTextField txtTongTE;
    // End of variables declaration//GEN-END:variables
}
