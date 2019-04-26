/**
 * Created by dakshgargas (Apr/2019)
 */

class Box<T> {}

class A {
  static void empty(Box<? extends Number> b) {}
}

class B {
  public static void invoke() {
    A.empty(new Box());
  }
}

class WhenDoesTypeErasureOccur {
  public static void main(String[] args) {
    B.invoke();
  }
}