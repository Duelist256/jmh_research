package examples.ex5

import org.openjdk.jmh.annotations._

@State(Scope.Thread)
class StateFixtures {

  var x: Double = _

  @Setup
  def prepare(): Unit = x = Math.PI

  @TearDown
  def check(): Unit = assert(x > Math.PI, "Nothing changed?")

  //@Benchmark
  def measureRight(): Unit = x += 1

  //@Benchmark
  def measureWrong(): Unit = x -= 1
}
