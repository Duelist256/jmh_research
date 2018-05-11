package examples.ex15

import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

@State(Scope.Group)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class Asymmetric {

  private var counter: AtomicInteger = _

  @Setup
  def up(): Unit = {
    counter = new AtomicInteger()
  }

//  @Benchmark
  @Group("g")
  @GroupThreads(3)
  def inc: Int = counter.incrementAndGet()

//  @Benchmark
  @Group("g")
  @GroupThreads(1)
  def get: Int = counter.get()
}
