package examples.ex32

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

object BulkWarmup

sealed trait Counter {
  def inc(): Int
}

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class BulkWarmup {

  class Counter1 extends Counter {
    private var x: Int = 0
    override def inc(): Int = {
      x += 1
      x
    }
  }

  class Counter2 extends Counter {
    private var x: Int = 0
    override def inc(): Int = {
      x += 1
      x
    }
  }

  var c1: Counter = new Counter1()
  var c2: Counter = new Counter2()

  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  def measure(c: Counter): Int = {
    var s = 0
    for (i <- 0 until 10) {
      s += c.inc()
    }
    s
  }

//  @Benchmark
  def measure_c1(): Int = measure(c1)

//  @Benchmark
  def measure_c2(): Int = measure(c2)

}
