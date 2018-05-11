package examples.ex13

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

object RunToRun {

  @State(Scope.Thread)
  class SleepyState {
    var sleepTime: Long = _

    @Setup
    def setup(): Unit = sleepTime = (Math.random() * 1000).toLong
  }

}

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
class RunToRun {

  import RunToRun._

//  @Benchmark
  @Fork(1)
  def baseline(s: SleepyState): Unit = TimeUnit.MILLISECONDS.sleep(s.sleepTime)

//  @Benchmark
  @Fork(5)
  def fork_1(s: SleepyState): Unit = TimeUnit.MILLISECONDS.sleep(s.sleepTime)

//  @Benchmark
  @Fork(20)
  def fork_2(s: SleepyState): Unit = TimeUnit.MILLISECONDS.sleep(s.sleepTime)
}
