package examples.ex18

import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.Control

import java.util.concurrent.atomic.AtomicBoolean

@State(Scope.Group)
class ControlExample {

  val flag = new AtomicBoolean

//  @Benchmark
  @Group("pingpong")
  def ping(cnt: Control): Unit = {
    while (!cnt.stopMeasurement && !flag.compareAndSet(false, true)) {
      // this body is intentionally left blank
    }
  }

//  @Benchmark
  @Group("pingpong")
  def pong(cnt: Control): Unit = {
    while (!cnt.stopMeasurement && !flag.compareAndSet(false, true)) {
      // this body is intentionally left blank
    }
  }
}
