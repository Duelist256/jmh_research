package examples.ex28

import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.Blackhole

import java.util.concurrent.TimeUnit

@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Thread)
class BlackholeHelpers {

  trait Worker {
    def work(): Unit
  }

  private var workerBaseline: Worker = _
  private var workerRight: Worker = _
  private var workerWrong: Worker = _

  @Setup
  def setup(bh: Blackhole) {
    workerBaseline = new Worker() {
      var x: Double = _
      def work: Unit = () // do nothing
    }

    workerWrong = new Worker() {
      var x: Double = _
      def work: Unit = Math.log(x)
    }

    workerRight = new Worker() {
      var x: Double = _
      def work: Unit = bh.consume(Math.log(x))
    }
  }

//  @Benchmark
  def baseline(): Unit = workerBaseline.work

//  @Benchmark
  def measureWrong(): Unit = workerWrong.work

//  @Benchmark
  def measureRight(): Unit = workerRight.work

}
