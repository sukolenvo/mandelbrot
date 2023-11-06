package org.sukolenvo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.IntStream;
import javax.swing.JPanel;

public class MainJPanel extends JPanel {

  private final ViewPoint viewPoint;

  MainJPanel() {
    viewPoint = new ViewPoint(-2, -2, 3, 4);
    MouseHandler mouseHandler = new MouseHandler(viewPoint);
    addMouseListener(mouseHandler);
    addMouseMotionListener(mouseHandler);
    addMouseWheelListener(mouseHandler);
  }

  @Override
  protected void paintComponent(Graphics g) {
    BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    double scale = Math.sqrt(viewPoint.getWidth());
    int limit = (int) Math.sqrt(10000 / scale);
    List<Color> colors = IntStream.range(0, limit)
        .mapToObj(i -> Color.getHSBColor(1.0f / limit * i, 1, 0.7f))
        .toList();
    IntStream.range(0, getHeight())
        .parallel()
        .forEach(y -> {
          for (int x = 0; x < getWidth(); x++) {
            Color color = getColor(x, y, colors);
            int colorValue = color.getRGB();
            bi.setRGB(x, y, colorValue);
          }
        });
    g.drawImage(bi, 0, 0, null);
  }

  private Color getColor(int posX, int posY, List<Color> colors) {
    float x = (float) posX / getWidth();
    float y = (float) posY / getHeight();

    ComplexNumber complexNumber = viewPoint.getNumber(x, y);
    ComplexNumber accumulator = complexNumber;
    for (Color color : colors) {
      accumulator = accumulator.square().add(complexNumber);
      if (accumulator.getRe() * accumulator.getRe() + accumulator.getIm() * accumulator.getIm() > 1.4) {
        return color;
      }
    }
    return Color.BLACK;
  }
}
