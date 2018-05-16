package examples.ex30

import org.openjdk.jmh.annotations._

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import java.util.concurrent.TimeUnit

@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Group)
@Fork(1)
@Warmup(iterations = 3)
class Interrupts {

  private var q: BlockingQueue[Integer] = _

  @Setup
  def setup(): Unit = q = new ArrayBlockingQueue(1)

  @Group("Q")
//  @Benchmark
  def take: Integer = q.take

  @Group("Q")
//  @Benchmark
  def put(): Unit = q.put(42)
}

// idk, but there is deadlock