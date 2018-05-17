package examples.ex31

import java.util
import java.util.concurrent.{ConcurrentHashMap, TimeUnit}

import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.{BenchmarkParams, ThreadParams}

import scala.collection.JavaConverters._

object InfraParams {
  final val THREAD_SLICE = 1000

  /*
   * Here is another neat trick. Generate the distinct set of keys for all threads:
   */

  @State(Scope.Thread)
  class Ids {
    private[ex31] var ids: util.List[String] = _

    @Setup
    def setup(threads: ThreadParams) {
      ids = new util.ArrayList[String]
      var c = 0
      while (c < THREAD_SLICE) {
        ids.add("ID" + (THREAD_SLICE * threads.getThreadIndex() + c))
        c += 1
      }
    }
  }

}

@BenchmarkMode(Array(Mode.Throughput))
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
class InfraParams {

  import InfraParams._

  /*
   * There is a way to query JMH about the current running mode. This is
   * possible with three infrastructure objects we can request to be injected:
   *   - BenchmarkParams: covers the benchmark-global configuration
   *   - IterationParams: covers the current iteration configuration
   *   - ThreadParams: covers the specifics about threading
   *
   * Suppose we want to check how the ConcurrentHashMap scales under different
   * parallelism levels. We can put concurrencyLevel in @Param, but it sometimes
   * inconvenient if, say, we want it to follow the @Threads count. Here is
   * how we can query JMH about how many threads was requested for the current run,
   * and put that into concurrencyLevel argument for CHM constructor.
   */

  private var mapSingle: ConcurrentHashMap[String, String] = _
  private var mapFollowThreads: ConcurrentHashMap[String, String] = _

  @Setup
  def setup(params: BenchmarkParams) {
    val capacity = 16 * THREAD_SLICE * params.getThreads
    mapSingle = new ConcurrentHashMap[String, String](capacity, 0.75f, 1)
    mapFollowThreads = new ConcurrentHashMap[String, String](capacity, 0.75f, params.getThreads)
  }

//  @Benchmark
  def measureDefault(ids: Ids) {
    for (s <- ids.ids.asScala) {
      mapSingle.remove(s)
      mapSingle.put(s, s)
    }
  }

//  @Benchmark
  def measureFollowThreads(ids: Ids) {
    for (s <- ids.ids.asScala) {
      mapFollowThreads.remove(s)
      mapFollowThreads.put(s, s)
    }
  }

}