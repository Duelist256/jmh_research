package examples.ex22

import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit


object FalseSharing {

  @State(Scope.Group)
  class StateBaseline {
    var readOnly: Int = _
    var writeOnly: Int = _
  }


  @State(Scope.Group)
  class StatePadded {
    var readOnly: Int = _
    var p01, p02, p03, p04, p05, p06, p07, p08  = 0
    var p11, p12, p13, p14, p15, p16, p17, p18 = 0
    var writeOnly: Int = _
    var q01, q02, q03, q04, q05, q06, q07, q08 = 0
    var q11, q12, q13, q14, q15, q16, q17, q18 = 0
  }

  class StateHierarchy_1 {
    var readOnly: Int = _
  }

  class StateHierarchy_2 extends StateHierarchy_1 {
    var p01, p02, p03, p04, p05, p06, p07, p08 = 0
    var p11, p12, p13, p14, p15, p16, p17, p18 = 0
  }

  class StateHierarchy_3 extends StateHierarchy_2 {
    var writeOnly: Int = _
  }

  class StateHierarchy_4 extends StateHierarchy_3 {
    var q01, q02, q03, q04, q05, q06, q07, q08 = 0
    var q11, q12, q13, q14, q15, q16, q17, q18 = 0
  }

  @State(Scope.Group)
  class StateHierarchy extends StateHierarchy_4


  @State(Scope.Group)
  class StateArray {
    var arr = new Array[Int](128)
  }


  @State(Scope.Group)
  class StateContended {
    var readOnly: Int = _

    @sun.misc.Contended
    var writeOnly: Int = _
  }
}

@BenchmarkMode(Array(Mode.Throughput))
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
class FalseSharing {
  import FalseSharing._

//  @Benchmark
  @Group("baseline")
  def reader(s: StateBaseline): Int = s.readOnly

//  @Benchmark
  @Group("baseline")
  def writer(s: StateBaseline): Unit = s.writeOnly += 1

//  @Benchmark
  @Group("padded")
  def reader(s: StatePadded): Int = s.readOnly

//  @Benchmark
  @Group("padded")
  def writer(s: StatePadded): Unit = s.writeOnly += 1

//  @Benchmark
  @Group("hierarchy")
  def reader(s: StateHierarchy): Int = s.readOnly

//  @Benchmark
  @Group("hierarchy")
  def writer(s: StateHierarchy): Unit = s.writeOnly += 1

//  @Benchmark
  @Group("sparse")
  def reader(s: StateArray): Int = s.arr(0)

//  @Benchmark
  @Group("sparse")
  def writer(s: StateArray): Unit = s.arr(64) += 1

//  @Benchmark
  @Group("contended")
  def reader(s: StateContended): Int = s.readOnly

//  @Benchmark
  @Group("contended")
  def writer(s: StateContended): Unit = s.writeOnly += 1
}
