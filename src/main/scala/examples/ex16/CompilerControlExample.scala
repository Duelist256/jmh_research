package examples.ex16

import org.openjdk.jmh.annotations.{CompilerControl, _}
import java.util.concurrent.TimeUnit

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class CompilerControlExample {

  def target_blank(): Unit = ()

  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  def target_dontInline(): Unit = ()

  @CompilerControl(CompilerControl.Mode.INLINE)
  def target_inline(): Unit = ()

  @CompilerControl(CompilerControl.Mode.EXCLUDE)
  def target_exclude(): Unit = ()


//  @Benchmark
  def baseline(): Unit = ()

//  @Benchmark
  def blank(): Unit = target_blank()

//  @Benchmark
  def dontInline(): Unit = target_dontInline()

//  @Benchmark
  def inline(): Unit = target_inline()

//  @Benchmark
  def exclude(): Unit = target_exclude()
}
