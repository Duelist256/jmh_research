package examples.ex8

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class DeadCode {

  private val x = Math.PI

//  @Benchmark
  def baseline(): Unit = {}

//  @Benchmark
  def measureWrong(): Unit =
    Math.log(x)

//  @Benchmark
  def measureRight(): Double =
    Math.log(x)
}
