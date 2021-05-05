package until;

import javax.swing.*;
import java.awt.*;

public class Column_chart extends JFrame {
    private String[] x_arr;
    private int[] y_arr;

    //绘制柱形统计图
    class V {
        int x, y;

        V(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public Column_chart() {
        super();
        x_arr = new String[]{"100-90", "89-70", "79-70", "69-60", "60-"};
        y_arr = new int[]{50, 12, 31, 16, 15};
        setTitle("成绩分析图");
        setBounds(100, 100, 600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        int Width = getWidth();
        int Height = getHeight();
        int left_margin = 80;//不少于40
        int top_margin = 80;//不少于80
        int y_length = 450;
        int x_length = 450;
        int y_divisor = 20;
        int x_divisor = x_arr.length + 1;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(2));
        V x_top = new V(left_margin + x_length, top_margin + y_length);
        V y_top = new V(left_margin, top_margin);
        V origin = new V(left_margin, top_margin + y_length);
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, Width, Height);
        //画箭头
        g2d.setColor(Color.black);
        int arrow_size = 10;
        int[] x_top_x_arr = {x_top.x - arrow_size, x_top.x, x_top.x - arrow_size};
        int[] x_top_y_arr = {x_top.y + arrow_size, x_top.y, x_top.y - arrow_size};
        int[] y_top_x_arr = {y_top.x + arrow_size, y_top.x, y_top.x - arrow_size};
        int[] y_top_y_arr = {y_top.y + arrow_size, y_top.y, y_top.y + arrow_size};
        g2d.drawPolyline(y_top_x_arr, y_top_y_arr, 3);
        g2d.drawPolyline(x_top_x_arr, x_top_y_arr, 3);
        g2d.drawLine(y_top.x, y_top.y, origin.x, origin.y);
        g2d.drawLine(origin.x, origin.y, x_top.x, x_top.y);
        g2d.setFont(new Font("华文楷体", 0, 20));
        g2d.drawString("x轴", x_top.x, x_top.y + arrow_size + 10);
        g2d.drawString("y轴", y_top.x - 10, y_top.y - arrow_size - 10);
        g2d.drawString("0", origin.x - 20, origin.y);
        //画虚线
        //Y轴
        for (int i = 1; i <= y_divisor; i++) {
            g2d.setStroke(new BasicStroke(1));
            g2d.setColor(Color.black);
            g2d.setColor(Color.gray);
            g2d.drawLine(origin.x, origin.y - i * (y_length / y_divisor), x_top.x, origin.y - i * (y_length / y_divisor));
        }

        for (int i = 1; i < x_divisor; i++) {
            g2d.setStroke(new BasicStroke(1));
            g2d.setColor(Color.black);
            g2d.setColor(Color.gray);
            int step = (x_length / x_divisor + 1);
            g2d.drawLine(origin.x + i * step, origin.y, origin.x + i * step, y_top.y);
        }

        //绘制柱形

        g2d.setFont(new Font("微软黑体", 1, 20));
        for (int i = 1; i < x_divisor; i++) {
            int step = x_length / x_divisor;
            int value = y_arr[i - 1] * (y_length / y_divisor) / 5;
            g2d.setColor(Color.BLACK);
            g2d.drawString(x_arr[i - 1], origin.x + i * (x_length / x_divisor) - x_length / x_divisor / 4, origin.y + 20);
            g2d.setColor(new Color(0x2DA127));
            g2d.fillRect(origin.x + i * (x_length / x_divisor) - step / 4, origin.y - 2 * value + 1,
                    step / 2, 2 * value);
            g2d.setColor(new Color(0x0CE505));
            g2d.fillRect(origin.x + i * (x_length / x_divisor) - step / 4 + 10, origin.y - 2 * value + 1,
                    step / 2, 2 * value);

            g2d.setColor(Color.BLUE);
            g2d.drawString(String.valueOf(y_arr[i - 1]),
                    origin.x + i * (x_length / x_divisor) - step / 4 + 10, origin.y - 15 - 2 * value);
            g2d.setColor(new Color(0x92FC99));
            g2d.fillOval(origin.x + i * (x_length / x_divisor) - step / 4, origin.y - 10 - 2 * value, step / 2 + 10, 20);
        }
    }
}




