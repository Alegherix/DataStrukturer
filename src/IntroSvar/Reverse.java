package IntroSvar;

import java.util.Arrays;

public class Reverse {
  // O(n)
  public static <A> A[] reverse(A[] xs) {
    A[] ys = Arrays.copyOf(xs, xs.length);

    for (int i = 0; i < xs.length; i++)
      ys[i] = xs[xs.length - i - 1];

    return ys;
  }

  // O(n)
  public static <A> void reverseInPlace(A[] xs) {
    for (int i = 0; i < xs.length / 2; i++) {
      A temp = xs[i];
      xs[i] = xs[xs.length - i - 1];
      xs[xs.length - i - 1] = temp;
    }
  }
}
