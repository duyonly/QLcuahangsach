package GUI;

import javax.swing.*;
import java.awt.*;

public class PanelNhapHang extends JPanel {
    public PanelNhapHang() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        JLabel lblTitle = new JLabel("GIAO DIỆN NHẬP HÀNG SẼ ĐƯỢC THIẾT KẾ Ở ĐÂY", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTitle, BorderLayout.CENTER);
    }
}