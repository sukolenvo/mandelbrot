package org.sukolenvo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ViewPoint {

  private double fromRe;
  private double fromIm;
  private double width;
  private double height;

  public ComplexNumber getNumber(double x, double y) {
    return new ComplexNumber(x * width + fromRe, y * height + fromIm);
  }
}
