package examples.ex10

import org.openjdk.jmh.annotations._
import java.util.concurrent.TimeUnit

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class ConstantFold {

  private val x = Math.PI

//  @Benchmark
  def baseline: Double = Math.PI

//  @Benchmark
  def measureWrong: Double = Math.log(Math.PI)

//  @Benchmark
  def measureRight: Double = Math.log(x)
}
