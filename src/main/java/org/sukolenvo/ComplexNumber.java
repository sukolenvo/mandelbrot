package org.sukolenvo;

import lombok.Data;

@Data
public class ComplexNumber {

  private final double re;
  private final double im;

  public ComplexNumber square() {
    double newRe = re * re - im * im;
    double newIm = 2 * re * im;
    return new ComplexNumber(newRe, newIm);
  }

  public ComplexNumber add(ComplexNumber other) {
    return new ComplexNumber(this.re + other.re, this.im + other.im);
  }

}
