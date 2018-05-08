package examples.ex9

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.Blackhole

@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
class Blackholes {

  val x1 = Math.PI
  val x2 = Math.PI * 2

//  @Benchmark
  def baseline: Double = Math.log(x1)

//  @Benchmark
  def measureWrong: Double  = {
    Math.log(x1)
    Math.log(x2)
  }


//  @Benchmark
  def measureRight_1: Double = Math.log(x1) + Math.log(x2)

//  @Benchmark
  def measureRight_2(bh: Blackhole): Unit = {
    bh.consume(Math.log(x1))
    bh.consume(Math.log(x2))
  }
}
