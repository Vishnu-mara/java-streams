# Java 8 streams

A stream is a sequence of elements on which different kinds of sequential and parallel operations are performed. Stream functionality was introduced in Java 8 java.util.stream which contains classes for processing sequences of elements.

A Collection is in-memory data structure that holds all the data structure’s values. Every element in the Collection has to be computed before it can be added to the Collection. While a Stream is conceptually a pipeline in which elements are computed on demand. The java.util.stream package contains the interfaces and classes to support functional-style operations on streams of elements. In addition to the Stream interface, which is a stream of object references, there are primitive specializations like IntStream, LongStream, and DoubleStream.

IntStream intStream = IntStream.of(1, 2, 3, 4, 10);

LongStream longStream = LongStream.of(1234567891, 22, 33, 44);

DoubleStream doubleStream = DoubleStream.of(1.0, 1.1, 2.2, 5);

# Types of streams -

1. Sequential Streams — processes elements in a collection or data source in a sequential, non-parallel manner(default behavior) in a single thread, and in the order they appear in the stream pipeline.
2. Parallel Streams — process elements in a collection or data source concurrently using multiple threads. They are designed to take advantage of multi-core processors and are useful for computationally intensive tasks.

#Stream Operations - 
The operations that we can perform on a stream are broadly categorized into two types:

Intermediate — are a set of operations that can be applied to a stream to transform, filter, or manipulate its elements. Intermediate operations are called “intermediate” because they do not produce a final result or trigger the actual processing of the stream. Instead, they return a new stream that represents the result of the intermediate operation.
Terminal-operation is applied to a stream to produce a final result or trigger the processing of the stream. Terminal operations are the actions that actually consume the elements of the stream and produce an outcome, such as a value, a collection, or a side effect. Once a terminal operation is invoked on a stream, the stream cannot be used again.
Note : Operations on stream don’t change the source.

#Common Intermediate Operations -

1. filter(Predicate<T> predicate) — accepts a predicate to filter all elements of the stream.
2. map(Function<T,R> mapper)-Transforms stream to a stream consisting of the results of applying the given function to the elements of the stream.
3. sorted(), sorted(Comparator<T> comparator) — elements in the stream are sorted in natural order unless we pass a custom Comparator.
4. flatMap(Function<T, Stream <R>> mapper)-flatten the objects from all the collections in the original Stream into a single collection.
5. distinct()-returns a stream consisting of the distinct elements.
6. peek(Consumer<T> action)-returns a new Stream consisting of all the elements from the original Stream after applying a given Consumer action. As per javadoc this method exists mainly to support debugging, where you want to see the elements as they flow past a certain point in a pipeline.
7. limit(long maxSize)-is short-circuiting intermediate operation, returns a new stream consisting of the elements of the given stream, truncated to be no longer than maxSize in length.
8. skip(long n) — returns a stream consisting of the remaining elements of the stream after discarding the first n elements of the stream.

# Terminal Operations-

1. forEach(Consumer<T> action) — performs specified action for each element of this stream.
2. forEachOrdered(Consumer<T> action) — similar to forEach, but it guarantees that elements are processed in the order of the original stream, even for parallel streams.
3. toArray() — returns an array containing the elements of the given stream.
4. collect(Collector<T, A, R> collector) —collects the elements of the stream into a collection or a custom aggregation.
5. reduce(T identity, BinaryOperator<T> accumulator) — produce a single result by repeatedly applying a function(binary operator) to a sequence of elements from a stream.
6. count() -returns stream’s size.
7. min(Comparator<T> comparator) — returns the minimum element of this stream according to the provided Comparator.
8. max(Comparator<T> comparator) — returns the maximum element of this stream according to the provided Comparator.
9. anyMatch(Predicate<T> predicate) — checks if any element matches a predicate.
10. allMatch(Predicate<T> predicate) — checks if all elements match a predicate.
11. noneMatch(Predicate<T> predicate) — checks if no element matches a predicate.
12. findAny() — finds any element in the stream.
13. findFirst() — finds the first element in the stream.