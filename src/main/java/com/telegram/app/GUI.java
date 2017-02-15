/**
* Domain classes used to produce .....
* <p>
* These classes contain the ......
* </p>
*
* @since 1.0
* @author somebody
* @version 1.0
*/
package com.telegram.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener {
    private Node node;
    private JFrame frame = new JFrame();
    private JTextField ipField = new JTextField(15);
    private JTextField portField = new JTextField(5);
    private JTextArea chatArea = new JTextArea(1, 80);
    private JTextField inputField;

    public GUI(Node node) {
        this.node = node;

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(null);

        JPanel chatPanel = new JPanel();
        chatPanel.setBounds(6, 6, 614, 525);
        chatPanel.setLayout(null);
        chatArea.setBounds(0, 0, 614, 522);
        chatArea.setEditable(false);
        chatPanel.add(chatArea);
        panel.add(chatPanel);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        JButton connectButton = new JButton("Connect");
        connectButton.setBounds(6, 82, 148, 42);
        connectButton.addActionListener(this);
        JPanel connectionPanel = new JPanel();
        connectionPanel.setBounds(632, 6, 162, 132);
        connectionPanel.setLayout(null);
        ipField.setText("IP");
        ipField.setBounds(6, 6, 148, 26);
        connectionPanel.add(ipField);
        portField.setText("PORT");
        portField.setBounds(84, 44, 70, 26);
        connectionPanel.add(portField);
        connectionPanel.add(connectButton);

        panel.add(connectionPanel);

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
        panel.add(btnSend);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("telegram");
        frame.setSize(800, 600);
        frame.setVisible(true);

        this.node.run();
    }

    // process the button clicks
    public void actionPerformed(ActionEvent e) {
      switch (e.getActionCommand()) {
        case "Send":
          this.node.generateMessage(inputField.getText());
          break;
        case "Connect":
          this.node.connect(ipField.getText(),
                            Integer.parseInt(portField.getText()));
          break;
        default:
          return;
      }
    }

    public Node getNode() {
      return this.node;
    }

    // create one Frame
    public static void main(String[] args, Node node) {
        new GUI(node);
    }
}
