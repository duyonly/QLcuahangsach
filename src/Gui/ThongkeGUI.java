package Gui;

import Dto.*;
import Bus.ThongkeBus;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ThongkeGUI extends JPanel{
    private ThongkeBus bus = new ThongkeBus();
    private JLabel lblDoanhThu = new JLabel();
    private JLabel lblNhap = new JLabel();
    private JLabel lblLoiNhuan = new JLabel();
    private JTable tableTop = new JTable();
    private JTable tableNV = new JTable();
    private JTable tableTonKho = new JTable();
    public ThongkeGUI(){
        setLayout(new BorderLayout());
        // Panel tổng tiền
        JPanel pnlTop = new JPanel(new GridLayout(1,3));
        pnlTop.add(lblDoanhThu);
        pnlTop.add(lblNhap);
        pnlTop.add(lblLoiNhuan);
        add(pnlTop, BorderLayout.NORTH);
        // Center panel
        JTabbedPane tab = new JTabbedPane();
        tab.add("Top Sách", createTopSachPanel());
        tab.add("Nhân viên", createNhanVienPanel());
        tab.add("Biểu đồ", createChartPanel());
        tab.add("Tồn kho", createTonKhoPanel());
        add(tab, BorderLayout.CENTER);
        loadData();
    }
    private JPanel createTopSachPanel(){
        tableTop.setModel(new DefaultTableModel(
                new String[]{"Tên sách","Số lượng bán"},0));
        return new JPanel(new BorderLayout()) {{
            add(new JScrollPane(tableTop), BorderLayout.CENTER);
        }};
    }
    private JPanel createNhanVienPanel(){
        tableNV.setModel(new DefaultTableModel(
                new String[]{"Mã NV","Tên NV","Doanh thu"},0));
        return new JPanel(new BorderLayout()) {{
            add(new JScrollPane(tableNV), BorderLayout.CENTER);
        }};
    }
    private JPanel createChartPanel(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            List<DoanhthuthangModel> list = bus.getDoanhThuTheoThang();
            for(DoanhthuthangModel m : list) {
                dataset.addValue(
                        m.getDoanhThu(),
                        "Doanh thu",
                        "Tháng " + m.getThang()
                );
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "Doanh thu theo tháng",
                "Tháng",
                "Tiền",
                dataset,
                PlotOrientation.VERTICAL,
                false,true,false
        );
        return new ChartPanel(chart);
    }
    private JPanel createTonKhoPanel(){
        tableTonKho.setModel(new DefaultTableModel(
                new String[]{"Mã sách","Tên sách","Tồn kho"},0));
        return new JPanel(new BorderLayout()) {{
            add(new JScrollPane(tableTonKho), BorderLayout.CENTER);
        }};
    }
    private void loadData(){
        try {
            lblDoanhThu.setText("Doanh thu: " + String.format("%,.0f VNĐ", bus.getTongDoanhThu()));
            lblNhap.setText("Tổng nhập: " + String.format("%,.0f VNĐ", bus.getTongNhap()));
            lblLoiNhuan.setText("Lợi nhuận: " + String.format("%,.0f VNĐ", bus.getLoiNhuan()));
            // thongkesach
            DefaultTableModel modelTop =
                    (DefaultTableModel) tableTop.getModel();
            List<TopsachModel> topList = bus.getTopSach();
            for(TopsachModel m : topList){
                modelTop.addRow(new Object[]{
                        m.getTenSach(),
                        m.getSoLuongBan()
                });
            }
            tableTop.setDefaultRenderer(Object.class,
                    new javax.swing.table.DefaultTableCellRenderer(){
                        @Override
                        public Component getTableCellRendererComponent(
                                JTable table, Object value,
                                boolean isSelected, boolean hasFocus,
                                int row, int column) {
                            Component c = super.getTableCellRendererComponent(
                                    table, value, isSelected, hasFocus, row, column);
                            if(row < 5){
                                c.setBackground(new Color(255, 235, 150));
                                c.setFont(c.getFont().deriveFont(Font.BOLD));
                            } else{
                                c.setBackground(Color.WHITE);
                                c.setFont(table.getFont());
                            }
                            return c;
                        }
                    });
            // Nhanvien
            DefaultTableModel modelNV =
                    (DefaultTableModel) tableNV.getModel();
            List<ThongkenhanvienModel> nvList =
                    bus.getThongKeNhanVien();
            for(ThongkenhanvienModel m : nvList){
                modelNV.addRow(new Object[]{
                        m.getMaNV(),
                        m.getTenNV(),
                        m.getDoanhThu()
                });
            }
            DefaultTableModel modelTon = (DefaultTableModel) tableTonKho.getModel();
            List<TonkhoModel> tonList = bus.getTonKho();
            for(TonkhoModel m : tonList){
                modelTon.addRow(new Object[]{ m.getMaSach(), m.getTenSach(), m.getSoLuongTon() });
            }

            tableTonKho.setDefaultRenderer(Object.class,
                    new javax.swing.table.DefaultTableCellRenderer(){
@Override
                        public Component getTableCellRendererComponent(
                                JTable table, Object value,
                                boolean isSelected, boolean hasFocus,
                                int row, int column) {
                            Component c = super.getTableCellRendererComponent(
                                    table,value,isSelected,hasFocus,row,column);
                            int ton = (int)table.getValueAt(row,2);
                            if(ton == 0)
                                c.setBackground(Color.RED);
                            else if(ton <= 5)
                                c.setBackground(Color.ORANGE);
                            else
                                c.setBackground(Color.WHITE);
                            return c;
                        }
                    });
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
