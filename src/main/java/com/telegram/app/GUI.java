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
    private JTextField field = new JTextField(255);
    private JTextField ipField = new JTextField(20);
    private JTextField portField = new JTextField(5);

    public GUI(Node node) {
        this.node = node;

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(this);
        JButton connectButton = new JButton("Connect");
        connectButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(sendButton);
        panel.add(ipField);
        panel.add(portField);
        panel.add(connectButton);
        panel.add(field);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("telegram");
        frame.pack();
        frame.setVisible(true);
    }

    // process the button clicks
    public void actionPerformed(ActionEvent e) {
      switch (e.getActionCommand()) {
        case "Send":
          this.node.generateMessage(field.getText());
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
