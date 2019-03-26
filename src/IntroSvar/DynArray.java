package IntroSvar;

import java.util.*;

public class DynArray<A> implements Iterable<A> {
  private A[] xs = (A[]) new Object[1];  // create empty array with size 1,
  private int size = 0;                  // we need to cast because Java does not allow creation of generic arrays (due to type erasure)
  private final int INC = 2;

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  // O(n), amortised O(1)
  private void resize(int n) {
    xs = Arrays.copyOf(xs, n);
  }

  // samma Ordo som add
  public void add(A x) {
    add(size, x);
  }

  public A get(int i) {
    if (i < size)
      return xs[i];
    else
      return null;
  }

  public void set(int i, A x) {
    if (i < size)
      xs[i] = x;
  }

  // O(n), amortised O(1) if att end
  public void add(int n, A x) {
    if (size == xs.length)
      resize(size * INC);

    for (int i = size++; i > n; i--)  // move all items above n one place
      xs[i] = xs[i - 1];

    xs[n] = x;
  }

  // O(n)
  public A remove(int n) {
    A x = xs[n];

    for (int i = n; i < size - 1; i ++)
      xs[i] = xs[i + 1];

    xs[--size] = null;  // decrement size and avoid loitering

    if (size > 0 && size == xs.length / 4)
      resize(xs.length/2);

    return x;
  }

  public void reverse() {
    int mid = size / 2;
    int end = size - 1;

    for (int i = 0; i < mid; i++) {
      A temp      = xs[i];
      xs[i]       = xs[end -i];
      xs[end - i] = temp;
    }
  }

  @Override
  public Iterator<A> iterator() {
    return new Iterator<A>() {
      private int i = size;
      @Override
      public boolean hasNext() {
        return i > 0;
      }

      @Override
      public A next() {
        return xs[--i];
      }
    };
  }

  @Override
  public String toString() {
    return Arrays.toString(xs);
  }
}
