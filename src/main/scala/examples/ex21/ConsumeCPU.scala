package examples.ex21

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.infra.Blackhole

import java.util.concurrent.TimeUnit

@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class ConsumeCPU {


//  @Benchmark
  def consume_0000(): Unit = Blackhole.consumeCPU(0)

//  @Benchmark
  def consume_0001(): Unit = Blackhole.consumeCPU(1)

//  @Benchmark
  def consume_0002(): Unit = Blackhole.consumeCPU(2)

//  @Benchmark
  def consume_0004(): Unit = Blackhole.consumeCPU(4)

//  @Benchmark
  def consume_0008(): Unit = Blackhole.consumeCPU(8)

//  @Benchmark
  def consume_0016(): Unit = Blackhole.consumeCPU(16)

//  @Benchmark
  def consume_0032(): Unit = Blackhole.consumeCPU(32)

//  @Benchmark
  def consume_0064(): Unit = Blackhole.consumeCPU(64)

//  @Benchmark
  def consume_0128(): Unit = Blackhole.consumeCPU(128)

//  @Benchmark
  def consume_0256(): Unit = Blackhole.consumeCPU(256)

//  @Benchmark
  def consume_0512(): Unit = Blackhole.consumeCPU(512)

//  @Benchmark
  def consume_1024(): Unit = Blackhole.consumeCPU(1024)


}