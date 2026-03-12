package Main; 

import javax.swing.SwingUtilities;
import GUI.TrangChu;

public class Main {
    public static void main(String[] args) {
        try {
            // (Tùy chọn) Lệnh này giúp giao diện Swing sử dụng giao diện mặc định của hệ điều hành (Windows/Mac)
            // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TrangChu();
            }
        });
    }
}