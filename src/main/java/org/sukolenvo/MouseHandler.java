package org.sukolenvo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MouseHandler extends MouseAdapter {

  private final ViewPoint viewPoint;
  private int clickedX;
  private int clickedY;

  @Override
  public void mousePressed(MouseEvent e) {
    clickedX = e.getX();
    clickedY = e.getY();
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    viewPoint.setFromRe(viewPoint.getFromRe() + (float) (clickedX - e.getX()) / e.getComponent().getWidth() * viewPoint.getWidth());
    clickedX = e.getX();
    viewPoint.setFromIm(viewPoint.getFromIm() + (float) (clickedY - e.getY()) / e.getComponent().getWidth() * viewPoint.getHeight());
    clickedY = e.getY();
    e.getComponent().repaint();
  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    if (e.getWheelRotation() < 0) {
      viewPoint.setFromRe(viewPoint.getFromRe() + (float) e.getX() / e.getComponent().getWidth() * viewPoint.getWidth() / 2);
      viewPoint.setFromIm(viewPoint.getFromIm() + (float) e.getY() / e.getComponent().getHeight() * viewPoint.getHeight() / 2);
      viewPoint.setWidth(viewPoint.getWidth() / 2);
      viewPoint.setHeight(viewPoint.getHeight() / 2);
      e.getComponent().repaint();
    }
    if (e.getWheelRotation() > 0) {
      viewPoint.setFromRe(viewPoint.getFromRe() - (float) e.getX() / e.getComponent().getWidth() * viewPoint.getWidth());
      viewPoint.setFromIm(viewPoint.getFromIm() - (float) e.getY() / e.getComponent().getHeight() * viewPoint.getHeight());
      viewPoint.setWidth(viewPoint.getWidth() * 2);
      viewPoint.setHeight(viewPoint.getHeight() * 2);
      e.getComponent().repaint();
    }
  }
}
