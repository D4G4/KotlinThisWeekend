import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dakshgargas (Apr/2019)
 */

class Animal {
  void animalize() {
    System.out.println("Animalizing");
  }
}

class Cat extends Animal {
  @Override void animalize() {
    super.animalize();
    System.out.println("Catize");
  }

  void meow() {
    System.out.println("meow");
  }
}

interface Source<T> {
  T nextT();
}

@SuppressWarnings("DanglingJavadoc") class Test {
  static Object getObject() {
    return null;
  }

  static void setObject(Object obj) {
  }

  static String getString() {
    return "";
  }

  static void setString(String str) {
  }

  void demo(Source<String> strs) {
    //Source<Object> objectSource = strs;
    Source<? extends Object> objSource = strs;
  }

  public static void main(String[] args) {
    //\u000d System.out.println("Daksh");
    arraysAreCovariant();
  }

  private static void arraysAreCovariant() {
    Number[] numbers = new Number[3];
    numbers[0] = new Integer(0);
    numbers[1] = new Double(3.14);
    numbers[2] = new Byte((byte) 2);

    Integer[] myInts = {1, 2, 3, 4};
    Number[] myNumber = myInts;
    /*
    Because, according to the subtyping rules in Java,
    an array Integer[] is a subtype of an array Number[]
    because Integer is a subtype of Number.
    */
    //But this subtyping rule can lead to an interesting que:
    //What would happen if we try to do this
    myNumber[0] = 3.14; //attempt of heap pollution
    //The above line would compile just fine, but if we run
    //this code, we would get an ArrayStoreException,
    //because we are trying to put Double in and Integer array
  }

  static long sum(Number[] numbers) {
    long summation = 0;
    for (Number number : numbers) {
      summation += number.longValue();
    }
    return summation;
  }

  private static void proveArraysAreCovariant() {
    Integer[] myInts = {1, 2, 3, 4, 5};
    Long[] myLongs = {1L, 2L, 3L, 4L, 5L};
    Double[] myDoubles = {1.0, 2.0, 3.0, 4.0, 5.0};

    System.out.println(sum(myInts));
    System.out.println(sum(myLongs));
    System.out.println(sum(myDoubles));
  }

  //But the following attempt will fail with generic collections
  static long sum(List<Number> numbers) {
    long summation = 0;
    for (Number number : numbers) {
      summation += number.longValue();
    }
    return summation;
  }

  /**
     private static void proveArraysAreCovariant_equivalent() {
   List<Integer> myInts = Arrays.asList(1, 2, 3, 4);
   List<Long> myLongs = Arrays.asList(1L, 2L, 3L, 4L, 5L);
   List<Double> myDoubles = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
   System.out.println(sum(myInts)); //compiler error
   System.out.println(sum(myLongs)); //compiler error
   System.out.println(sum(myDoubles)); //compiler error
   }
   */
  //COVARIANCE to the rescue!!
  /*
  For this case, instead of using a type 'T' as the argument
  of a given generic type, we use a wildcard declared as
  ` ? extends T `, where T is the known base type
   */
  //So with convariance, we can read items from a structure
  private static void proveArraysAreCovariant_equivalent() {
    List<? extends Number> myNumsI = new ArrayList<Integer>();
    //myNumsI.add(21);
    List<? extends Number> myNumsF = new ArrayList<Float>();
    List<? extends Number> myNumsD = new ArrayList<Double>();

    Number n = myNumsI.get(0);
    //Because we are sure whatever the actual list contains,
    //we can upcast it to a Number
    //However we are not allowed to put anything into covariant structure
    /*myNumsI.add(45L);*/

    //Therefore we can read, but not write
  }

}













