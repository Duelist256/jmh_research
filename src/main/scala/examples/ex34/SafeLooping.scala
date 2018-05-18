package examples.ex34

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations.{Benchmark, BenchmarkMode, CompilerControl, Fork, Measurement, Mode, OutputTimeUnit, Param, Scope, Setup, State, Warmup}
import org.openjdk.jmh.infra.Blackhole

object SafeLooping

@State(Scope.Thread)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(2)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class SafeLooping {

  @Param(Array("1", "10", "100", "1000"))
  var size: Int = _

  var xs: Array[Int] = _

  val BASE = 42

  def work(x: Int): Int = BASE + x

  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  def sink(v: Int) {
  }

  @Setup
  def setup() {
    xs = Array.ofDim[Int](size)
    for (c <- 0 until size) {
      xs(c) = c
    }
  }

//  @Benchmark
  def measureWrong_1(): Int = {
    var acc = 0
    for (x <- xs) {
      acc = work(x)
    }
    acc
  }

//  @Benchmark
  def measureWrong_2(): Int = {
    var acc = 0
    for (x <- xs) {
      acc += work(x)
    }
    acc
  }

//  @Benchmark
  def measureRight_1(bh: Blackhole) {
    for (x <- xs) {
      bh.consume(work(x))
    }
  }

//  @Benchmark
  def measureRight_2() {
    for (x <- xs) {
      sink(work(x))
    }
  }

}
