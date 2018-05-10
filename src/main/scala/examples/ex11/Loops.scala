package examples.ex11

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class Loops {

  val x = 1
  val y = 2

  @Benchmark
  def measureRight: Int = x + y

  private def reps(reps: Int): Int = {
    var s = 0
    var i = 0
    while(i < reps) {
      s += (x + y)
      i += 1
    }
    s
  }

//  @Benchmark
  @OperationsPerInvocation(1)
  def measureWrong_1: Int = reps(1)

//  @Benchmark
  @OperationsPerInvocation(10)
  def measureWrong_10: Int = reps(10)

//  @Benchmark
  @OperationsPerInvocation(100)
  def measureWrong_100: Int = reps(100)

//  @Benchmark
  @OperationsPerInvocation(1000)
  def measureWrong_1000: Int = reps(1000)

//  @Benchmark
  @OperationsPerInvocation(10000)
  def measureWrong_10000: Int = reps(10000)

//  @Benchmark
  @OperationsPerInvocation(100000)
  def measureWrong_100000: Int = reps(100000)
}
