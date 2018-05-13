package examples.ex17

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

import java.util.concurrent.TimeUnit


@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
class SyncIterations {

  private var src: Double = _

//  @Benchmark
  def test: Double = {
    var s = src
    var i = 0
    while (i < 1000) {
      s = Math.sin(s)
      i += 1
    }
    s
  }
}
