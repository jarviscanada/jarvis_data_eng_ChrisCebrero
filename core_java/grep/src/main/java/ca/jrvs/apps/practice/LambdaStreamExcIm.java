package ca.jrvs.apps.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcIm implements LambdaStreamExc {

  public static void main(String[] args) {

  }
  @Override
  public Stream<String> createStrStream(String... strings) {
    return Stream.of(strings);
  }

  @Override
  public Stream<String> toUpperCase(String... strings) {
    return createStrStream(strings)
        .map(String::toUpperCase);
  }

  @Override
  public Stream<String> filter(Stream<String> stringStream, String pattern) {
    return stringStream.filter(s -> !s.contains(pattern));
  }

  @Override
  public IntStream createIntStream(int[] arr) {
    return IntStream.of(arr);
  }

  @Override
  public <E> List<E> toList(Stream<E> stream) {
    List<E> result = new ArrayList<>();
    stream.forEach(result::add);
    return result;
  }

  @Override
  public List<Integer> toList(IntStream intStream) {
    List<Integer> result = new ArrayList<>();
    intStream.forEach(result::add);
    return result;
  }

  @Override
  public IntStream createIntStream(int start, int end) {
    return IntStream.rangeClosed(start, end);
  }

  @Override
  public DoubleStream squareRootIntStream(IntStream intStream) {
    return intStream.mapToDouble(Math::sqrt);
  }

  @Override
  public IntStream getOdd(IntStream intStream) {
    return intStream.filter(n -> n % 2 == 1);
  }

  @Override
  public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
    return str -> System.out.println(prefix + str + suffix);
  }

  @Override
  public void printMessages(String[] messages, Consumer<String> printer) {
    for (String msg : messages) {
      printer.accept(msg);
    }
  }

  @Override
  public void printOdd(IntStream intStream, Consumer<String> printer) {
    intStream.filter(n -> n % 2 == 1)
        .forEach(n -> printer.accept(Integer.toString(n)));
  }

  @Override
  public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
    return ints.map(list -> list.stream().map(n -> n * n))
        .reduce(Stream::concat)
        .orElseGet(Stream::empty);
  }
}
