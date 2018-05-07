package examples

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations.{Benchmark, BenchmarkMode, Mode, OutputTimeUnit}

class BenchmarkModes {

  // @Benchmark
  // @BenchmarkMode(Array(Mode.Throughput))
  // @OutputTimeUnit(TimeUnit.SECONDS)
  // def measureThroughput(): Unit = TimeUnit.MILLISECONDS.sleep(100)


  // @Benchmark
  // @BenchmarkMode(Array(Mode.AverageTime))
  // @OutputTimeUnit(TimeUnit.MICROSECONDS)
  // def measureAvgTime(): Unit = TimeUnit.MILLISECONDS.sleep(100)

  // @Benchmark
  // @BenchmarkMode(Array(Mode.SampleTime))
  // @OutputTimeUnit(TimeUnit.MICROSECONDS)
  // def measureSamples(): Unit = TimeUnit.MILLISECONDS.sleep(100)

  // @Benchmark
  // @BenchmarkMode(Array(Mode.SingleShotTime))
  // @OutputTimeUnit(TimeUnit.MICROSECONDS)
  // def measureSingleShot(): Unit = TimeUnit.MILLISECONDS.sleep(100)

  //@Benchmark
  @BenchmarkMode(Array(Mode.Throughput, Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime))
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  def measureMultiple(): Unit = TimeUnit.MILLISECONDS.sleep(100)

  //@Benchmark
  @BenchmarkMode(Array(Mode.All))
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  def measureAll(): Unit = TimeUnit.MILLISECONDS.sleep(100)
}
