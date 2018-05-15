package examples.ex24

import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit

object Inheritance {

  @BenchmarkMode(Array(Mode.AverageTime))
  @Fork(1)
  @State(Scope.Thread)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  abstract class AbstractBenchmark {
    var x = 42

//    @Benchmark
    @Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    def bench: Double = doWork * doWork

    protected def doWork(): Double
  }

  class BenchmarkLog extends AbstractBenchmark {
    override protected def doWork(): Double = Math.log(x)
  }

  class BenchmarkSin extends AbstractBenchmark {
    override protected def doWork(): Double = Math.sin(x)
  }

  class BenchmarkCos extends AbstractBenchmark {
    override protected def doWork(): Double = Math.cos(x)
  }
}