package examples.ex23

import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit

object AuxCountersExample {

  @AuxCounters
  @State(Scope.Thread)
  class AdditionalCounters {
    var case1: Int = _
    var case2: Int = _

    @Setup(Level.Iteration)
    def clean() {
      case1 = 0
      case2 = 0
    }

    def total: Int = case1 + case2

  }

}

@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
class AuxCountersExample {

  import AuxCountersExample._

//  @Benchmark
  def measure(counters: AdditionalCounters): Unit =
    if (Math.random() < 0.1) counters.case1 += 1
    else counters.case2 += 1
}
