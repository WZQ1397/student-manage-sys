package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
;
import stuio.stuio;

//主类，程序的入口
public class Main {
    public static void main(String[] args) {
        new begindemo("这是我的管理系统");
        stuio stu = new stuio();
        //stu.record();

    }
}

class managedemo extends JFrame {

    //声明屏幕的宽高，程序窗口的宽高
    private int windowWidth;
    private int windowHeight;
    private int screenSizeWidth;
    private int screenSizeHeight;

    public managedemo(String title) {
        super(title);   //设置标题
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置能关闭窗口
        this.setSize(600, 400);     //设置窗口的大小
        this.setLayout(null);       //设置程序默认布局格式为空，以便于后期自己简单的设置布局
        this.setResizable(false);   //设置不可缩放
        init();                     //执行初始化函数（将用户名密码等组件加入到面板中）
        this.setVisible(true);      //使程序可见
    }

    public void init() {
        //给屏幕的宽度高度，程序窗口的宽度高度赋值
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        screenSizeWidth = (int) dimension.getWidth();
        screenSizeHeight = (int) dimension.getHeight();
        windowWidth = this.getWidth();
        windowHeight = this.getHeight();
        //设置程序窗口的位置为屏幕的正中央
        this.setLocation(screenSizeWidth / 2 - windowWidth / 2,
                screenSizeHeight / 2 - windowHeight / 2);

        //声明登录按钮
        JButton login_btn = new JButton("login");

        login_btn.setBounds(280, 260, 100, 50);

        this.add(login_btn);
}
}

class begindemo extends JFrame {
    //登录的用户名和密码
    private final String userName = "1";
    private final String password = "1";
    //声明屏幕的宽高，程序窗口的宽高
    private int windowWidth;
    private int windowHeight;
    private int screenSizeWidth;
    private int screenSizeHeight;

    //构造函数，
    public begindemo(String title) {
        super(title);   //设置标题
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置能关闭窗口
        this.setSize(600, 400);     //设置窗口的大小
        this.setLayout(null);       //设置程序默认布局格式为空，以便于后期自己简单的设置布局
        this.setResizable(false);   //设置不可缩放
        init();                     //执行初始化函数（将用户名密码等组件加入到面板中）
        this.setVisible(true);      //使程序可见
    }

    public void init() {
        //给屏幕的宽度高度，程序窗口的宽度高度赋值
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        screenSizeWidth = (int) dimension.getWidth();
        screenSizeHeight = (int) dimension.getHeight();
        windowWidth = this.getWidth();
        windowHeight = this.getHeight();
        //设置程序窗口的位置为屏幕的正中央
        this.setLocation(screenSizeWidth / 2 - windowWidth / 2,
                screenSizeHeight / 2 - windowHeight / 2);

        // 声明姓名，密码的标签
        JLabel username_label = new JLabel("Name");
        JLabel password_label = new JLabel("Password");
        JLabel Title_label = new JLabel("student information system");
        Title_label.setFont(new java.awt.Font("verdana", 1, 24));
        Title_label.setForeground(Color.ORANGE);
        // 声明姓名输入框和密码输入框
        final JTextField user_field = new JTextField();
        final JPasswordField password_field = new JPasswordField();
        //声明登录按钮
        JButton login_btn = new JButton("login");

        //设置各个标签和输入框的大小和位置
        Title_label.setBounds(80, 30, 500, 30);
        username_label.setBounds(100, 100, 100, 50);
        password_label.setBounds(100, 150, 100, 50);
        user_field.setBounds(200, 120, 300, 30);
        password_field.setBounds(200, 160, 300, 30);
        login_btn.setBounds(280, 260, 100, 50);

        this.add(Title_label);
        this.add(username_label);
        this.add(password_label);
        this.add(user_field);
        this.add(password_field);
        this.add(login_btn);

        //登录按钮的监听器
        login_btn.addActionListener(new ActionListener() {
            public void mainmanage() {
                JOptionPane.showMessageDialog(null, "login success", "Login",
                        JOptionPane.INFORMATION_MESSAGE);

                //声明主页
                /*JFrame home_page = new JFrame("student manage system");
                //给主页设置位置
                home_page.setLocation(screenSizeWidth / 2 - windowWidth / 2
                        + 50, screenSizeHeight / 2 - windowHeight / 2 + 50);
                //给主页设置大小
                home_page.setSize(windowWidth, windowHeight);
                //设置主页能够关闭，并且登录成功后将登录页面隐藏
                home_page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                home_page.setVisible(true);*/
                //new managedemo("这是我的管理系统");
                //new BrowseStudentsFrame("ok");
                //new SetStatusBarFrame("Grade Management");
                new MainFrame("mange");
                setVisible(false);//登录页面隐藏
            }

            @SuppressWarnings("deprecation")
            @Override
            //当按钮被单击时自动调动这个方法
            public void actionPerformed(ActionEvent event) {
                //如果用户名和密码都是123，那么弹出对话框显示登录成功，并且开启另一个主框架（主页）
                if (user_field.getText().equals(userName)
                        && password_field.getText().equals(password)) {
                    mainmanage();
                } else      //反之，登录不成功，重新登录
                {
                    JOptionPane.showMessageDialog(null, "login failed", "Login",
                            JOptionPane.INFORMATION_MESSAGE);
                    //设置输入框的内容为空，让用户重新输入
                    user_field.setText("");
                    password_field.setText("");
                }
            }
        });

    }
}
