package examples.ex26

import java.util

import org.openjdk.jmh.annotations._

@State(Scope.Thread)
@Fork(1)
class BatchSize {

  var list: util.List[String] = new util.LinkedList[String]

//  @Benchmark
  @Warmup(iterations = 5, time = 1)
  @Measurement(iterations = 5, time = 1)
  @BenchmarkMode(Array(Mode.AverageTime))
  def measureWrong_1: util.List[String] = {
    list.add(list.size / 2, "something")
    list
  }

//  @Benchmark
  @Warmup(iterations = 5, time = 5)
  @Measurement(iterations = 5, time = 5)
  @BenchmarkMode(Array(Mode.AverageTime))
  def measureWrong_5(): util.List[String] = {
    list.add(list.size / 2, "something")
    list
  }

  /*
   * This is what you do with JMH.
   */
//  @Benchmark
  @Warmup(iterations = 5, batchSize = 5000)
  @Measurement(iterations = 5, batchSize = 5000)
  @BenchmarkMode(Array(Mode.SingleShotTime))
  def measureRight: util.List[String] = {
    list.add(list.size / 2, "something")
    list
  }

  @Setup(Level.Iteration)
  def setup: Unit = list.clear
}
