package examples.ex7

import java.util.concurrent.{Callable, ExecutorService, Executors, TimeUnit}

import org.openjdk.jmh.annotations._

object FixtureLevelInvocation {

  @State(Scope.Benchmark)
  class NormalState {
    var service: ExecutorService = _

    @Setup(Level.Trial)
    def up(): Unit = service = Executors.newCachedThreadPool()

    @TearDown(Level.Trial)
    def down(): Unit = service.shutdown()
  }

  object LaggingState {
    final val SLEEP_TIME = Integer.getInteger("sleepTime", 10).intValue()
  }

  class LaggingState extends NormalState {
    import LaggingState._
    @Setup(Level.Invocation)
    def lag(): Unit = TimeUnit.MILLISECONDS.sleep(SLEEP_TIME)
  }

  @State(Scope.Thread)
  class Scratch {
    private var p: Double = _
    def doWork(): Double = {
      p = Math.log(p)
      p
    }
  }

  class Task(s: Scratch) extends Callable[Double] {
    override def call(): Double = s.doWork()
  }
}

@OutputTimeUnit(TimeUnit.MICROSECONDS)
class FixtureLevelInvocation {

  import FixtureLevelInvocation._


//  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  def measureHot(e: NormalState, s: Scratch): Double = e.service.submit(new Task(s)).get

//  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  def measureCold(e: LaggingState, s: Scratch): Double = e.service.submit(new Task(s)).get

}