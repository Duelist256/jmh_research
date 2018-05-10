package examples.ex12

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class Forking {

  trait Counter {
    def inc: Int
  }

  class Counter1 extends Counter {
    private var x: Int = _

    override def inc: Int = {
      val a = x
      x += 1
      a
    }
  }

  class Counter2 extends Counter {
    private var x: Int = _

    override def inc: Int = {
      val a = x
      x += 1
      a
    }
  }

  def measure(c: Counter): Int = {
    var s = 0
    var i = 0
    while (i < 10) {
      s += c.inc
      i += 1
    }
    s
  }

  val c1 = new Counter1
  val c2 = new Counter2

  // Fork(0) helps to run in the same JVM.
//  @Benchmark
  @Fork(0)
  def measure_1_c1: Int = measure(c1)

//  @Benchmark
  @Fork(0)
  def measure_2_c2: Int = measure(c2)

//  @Benchmark
  @Fork(0)
  def measure_3_c1_again: Int = measure(c1)

//  @Benchmark
  @Fork(1)
  def measure_4_forked_c1: Int = measure(c1)

//  @Benchmark
  @Fork(1)
  def measure_5_forked_c2: Int = measure(c2)
}
