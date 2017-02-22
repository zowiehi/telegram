package com.telegram.app;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class GUI implements ActionListener {
  private Node node;
  private JFrame frame = new JFrame();
  private JTextField ipField = new JTextField(15);
  private JTextField portField = new JTextField(5);
  private JTextArea chatArea = new JTextArea(1, 20);
  private JTextField inputField;
  private JTextField textField;
  private SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");

  public GUI(Node node) {
    this.node = node;
    this.node.listener = this;

    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
    panel.setLayout(null);

    chatArea.setBounds(0, 0, 614, 522);
    chatArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(chatArea);
    scrollPane.setBounds(6, 6, 614, 525);
    panel.add(scrollPane);

    DefaultCaret caret = (DefaultCaret) chatArea.getCaret();
    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

    frame.getContentPane().add(panel, BorderLayout.CENTER);
    JButton connectButton = new JButton("Connect");
    connectButton.setBounds(6, 82, 148, 42);
    connectButton.addActionListener(this);
    JPanel connectionPanel = new JPanel();
    connectionPanel.setBounds(632, 48, 162, 132);
    connectionPanel.setLayout(null);
    ipField.setBounds(31, 6, 123, 26);
    connectionPanel.add(ipField);
    portField.setBounds(84, 44, 70, 26);
    connectionPanel.add(portField);
    connectionPanel.add(connectButton);

    panel.add(connectionPanel);

    JLabel lblIp = new JLabel("IP");
    lblIp.setBounds(6, 11, 61, 16);
    connectionPanel.add(lblIp);

    JLabel lblPort = new JLabel("Port");
    lblPort.setBounds(41, 49, 61, 16);
    connectionPanel.add(lblPort);

    JPanel sendPanel = new JPanel();
    sendPanel.setBounds(6, 533, 614, 39);


    panel.add(sendPanel);
    sendPanel.setLayout(null);

    inputField = new JTextField();
    inputField.setBounds(6, 6, 602, 26);
    sendPanel.add(inputField);
    inputField.setColumns(30);

    JButton btnSend = new JButton("Send");
    btnSend.setBounds(623, 533, 171, 39);
    btnSend.addActionListener(this);
    panel.add(btnSend);

    textField = new JTextField();
    textField.setBounds(675, 10, 119, 26);
    panel.add(textField);
    textField.setColumns(10);

    JLabel lblName = new JLabel("Name");
    lblName.setBounds(632, 15, 61, 16);
    panel.add(lblName);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.addWindowListener(new WindowListener() {
      @Override public void windowClosing(WindowEvent e) {
        this.node.leave();
      }

      @Override public void windowOpened(WindowEvent e) {}
      @Override public void windowIconified(WindowEvent e) {}
      @Override public void windowDeiconified(WindowEvent e) {}
      @Override public void windowDeactivated(WindowEvent e) {}
      @Override public void windowActivated(WindowEvent e) {}
      @Override public void windowClosed(WindowEvent e) {}
    });
    frame.setTitle("telegram");
    frame.setSize(800, 600);
    frame.setVisible(true);
  }

  public void messageReceived(Message message) {
    switch (message.type) {
      case "chat":
        chatArea.append(df.format(message.timestamp) + "     " + message.author + ": " + message.messageContent + '\n');
        break;
      case "err":
      case "connected":
        chatArea.append(message.messageContent + '\n');
        break;
    }
  }

  // process the button clicks
  public void actionPerformed(ActionEvent e) {
    if (textField.getText() != null && !textField.getText().isEmpty()) {
      this.node.setName(textField.getText());
    }
    switch (e.getActionCommand()) {
      case "Send":
        if (inputField.getText() != null && !inputField.getText().isEmpty()) {
          Message msg = this.node.generateMessage(inputField.getText());
          if (msg != null) {
            chatArea.append(df.format(msg.timestamp) + "     " + msg.author + ": " + msg.messageContent + '\n');
          }
          inputField.setText("");
        }
        break;
      case "Connect":
        this.node.connect(ipField.getText(),
                          Integer.parseInt(portField.getText()));
        break;
    }
  }

  // create one Frame
  public static void main(String[] args, Node node) {
      new GUI(node);
  }
}
