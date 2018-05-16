package examples.ex29

import java.util
import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

import scala.collection.JavaConverters._

object StatesDAG {

  class Counter {
    var x: Int = _

    def inc: Int = {
      val a = x
      x += 1
      a
    }

    def dispose(): Unit = () // pretend this is something really useful
  }

  @State(Scope.Benchmark)
  class Shared {
    var all: util.List[Counter] = _
    var available: util.Queue[Counter] = _

    @Setup
    def setup(): Unit = synchronized {
      all = new util.ArrayList[Counter]
      var c = 0
      while (c < 10) {
        all.add(new Counter)
        c += 1
      }

      available = new util.LinkedList[Counter]
      available.addAll(all)
    }

    @TearDown
    def tearDown(): Unit = synchronized {
      for (c <- all.asScala) c.dispose()
    }

    def getMine: Counter = available.poll

  }

  @State(Scope.Thread)
  class Local {
    var cnt: Counter = _

    @Setup
    def setup(shared: Shared): Unit = cnt = shared.getMine
  }
}

@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Thread)
class StatesDAG {

  import StatesDAG._

//  @Benchmark
  def test(local: Local): Int = local.cnt.inc
}
