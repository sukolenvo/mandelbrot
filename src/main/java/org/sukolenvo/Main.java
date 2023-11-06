package org.sukolenvo;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main {

  private static void createAndShowGUI() {
    JFrame frame = new JFrame("Mandelbrot");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new MainJPanel());
    frame.setLocationByPlatform(true);
    frame.setSize(600, 600);
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(Main::createAndShowGUI);
  }
}
