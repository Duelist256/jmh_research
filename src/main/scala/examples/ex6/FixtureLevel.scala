package examples.ex6

import org.openjdk.jmh.annotations._

@State(Scope.Thread)
class FixtureLevel {

  var x: Double = _

  @Setup(Level.Invocation)
  def prepare(): Unit = x = Math.PI

  @TearDown(Level.Iteration)
  def check(): Unit = assert(x > Math.PI, "Nothing changed?")

//  @Benchmark
  def measureRight(): Unit = x += 1

//  @Benchmark
  def measureWrong(): Unit = x -= 1
}
