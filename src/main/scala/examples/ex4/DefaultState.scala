package examples.ex4

import org.openjdk.jmh.annotations.{Benchmark, Scope, State}

@State(Scope.Thread)
class DefaultState {

  var x: Double = Math.PI

  //@Benchmark
  def measure(): Unit = x += 1

}
