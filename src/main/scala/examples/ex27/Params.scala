package examples.ex27

import org.openjdk.jmh.annotations._

import java.math.BigInteger
import java.util.concurrent.TimeUnit

@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
class Params {

  @Param(Array("1", "31", "65", "101", "103"))
  var arg: Int = _

  @Param(Array("0", "1", "2", "4", "8", "16", "32"))
  var certainty: Int = _

//  @Benchmark
  def bench: Boolean = BigInteger.valueOf(arg).isProbablePrime(certainty)

}
