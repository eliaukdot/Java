package ScenicSpotTickets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketBox extends JFrame {
    public TicketBox() {
        initComponents();
    }
    private JFrame jFrame = new JFrame("景区门票");
    private JPanel jPanel = new JPanel();

    //标签
    private JLabel jLabel1 = new JLabel("门票标准单价：");
    private JTextField TF_Ticket = new JTextField("100");
    private JLabel jLabel2 = new JLabel("元");

    private JLabel jLabel3 = new JLabel("成人票：");
    private JTextField TF_Young = new JTextField("0");
    private JLabel jLabel4 = new JLabel("张");

    private JLabel jLabel5 = new JLabel("老年票：");
    private JTextField TF_Old = new JTextField("0");
    private JLabel jLabel6 = new JLabel("张");

    private JLabel jLabel7 = new JLabel("儿童票：");
    private JTextField TF_Child = new JTextField("0");
    private JLabel jLabel8 = new JLabel("张");

    private JLabel jLabel9 = new JLabel("总价：");
    private JLabel JL_TotalPrice = new JLabel("0元");

    private JButton jButton = new JButton("计算");

    private void initComponents() {

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButtonActionPerformed(e);
            }
        });
        jFrame.setResizable(false); //是否可以更改窗口大小
        Font font = new Font("宋体", Font.BOLD, 18);
        jFrame.setBounds(525, 150, 525, 425);

        jLabel1.setFont(font);
        TF_Ticket.setFont(font);
        jLabel2.setFont(font);
        jLabel1.setBounds(70, 30, 150, 30);
        TF_Ticket.setBounds(220, 30, 200, 30);
        jLabel2.setBounds(425, 30, 30, 30);
        jPanel.add(jLabel1);
        jPanel.add(TF_Ticket);
        jPanel.add(jLabel2);

        jLabel3.setFont(font);
        TF_Young.setFont(font);
        jLabel4.setFont(font);
        jLabel3.setBounds(70, 100, 150, 30);
        TF_Young.setBounds(220, 100, 150, 30);
        jLabel4.setBounds(375, 100, 30, 30);
        jPanel.add(jLabel3);
        jPanel.add(TF_Young);
        jPanel.add(jLabel4);

        jLabel5.setFont(font);
        TF_Old.setFont(font);
        jLabel6.setFont(font);
        jLabel5.setBounds(70, 150, 150, 30);
        TF_Old.setBounds(220, 150, 150, 30);
        jLabel6.setBounds(375, 150, 30, 30);
        jPanel.add(jLabel5);
        jPanel.add(TF_Old);
        jPanel.add(jLabel6);

        jLabel7.setFont(font);
        TF_Child.setFont(font);
        jLabel8.setFont(font);
        jLabel7.setBounds(70, 200, 150, 30);
        TF_Child.setBounds(220, 200, 150, 30);
        jLabel8.setBounds(375, 200, 30, 30);
        jPanel.add(jLabel7);
        jPanel.add(TF_Child);
        jPanel.add(jLabel8);

        jLabel9.setFont(font);
        JL_TotalPrice.setFont(font);
        jLabel9.setBounds(70, 250, 150, 30);
        JL_TotalPrice.setBounds(220, 250, 150, 30);
        jPanel.add(jLabel9);
        jPanel.add(JL_TotalPrice);

        jButton.setFont(font);
        jButton.setBounds(70, 300, 80, 30);
        jPanel.add(jButton);

        jPanel.setLayout(null);
        jFrame.add(jPanel);
        jFrame.setVisible(true);

    }
    private void jButtonActionPerformed(ActionEvent e) {
        float standardPrice = Float.parseFloat(TF_Ticket.getText().trim());
        YoungTicket youngTicket = new YoungTicket(standardPrice);
        OldTicket oldTicket = new OldTicket(standardPrice);
        ChildTicket childTicket = new ChildTicket(standardPrice);
        Ticket ticket  = youngTicket;
        int youngNum = Integer.parseInt(TF_Young.getText().trim());
        int oldNum = Integer.parseInt(TF_Old.getText().trim());
        int childNum = Integer.parseInt(TF_Child.getText().trim());
        float  total = youngNum * youngTicket.singlePrice() + oldNum * oldTicket.singlePrice() + childNum * childTicket.singlePrice();
        JL_TotalPrice.setText(Float.toString(total) + "元");
    }
}