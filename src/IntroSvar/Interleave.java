package IntroSvar;

import static java.lang.Integer.max;

public class Interleave {
  public static String interleave(String a, String b) {
    int n = a.length();
    int m = b.length();
    char[] res = new char[n + m];
    int i = 0;
    int j = 0;

    while (j < max(n, m)) {
      if (j < n) res[i++] = a.charAt(j);
      if (j < m) res[i++] = b.charAt(j++);
    }

    return new String(res);
  }

  public static void main(String[] args) {
    String a = "anna";
    String p = "patrik";
    String res = interleave(a, p);

    System.out.println(res);
  }
}
