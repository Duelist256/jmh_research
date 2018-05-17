package examples.ex33

import java.security.{Policy, URIParameter}
import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

object SecurityManagerExample {

  @State(Scope.Benchmark)
  class SecurityManagerInstalled {

    @Setup
    def setup() {
      val policyFile = classOf[SecurityManagerExample].getResource("/jmh-security.policy").toURI()
      Policy.setPolicy(Policy.getInstance("JavaPolicy", new URIParameter(policyFile)))
      System.setSecurityManager(new SecurityManager())
    }

    @TearDown
    def tearDown() {
      System.setSecurityManager(null)
    }
  }

  @State(Scope.Benchmark)
  class SecurityManagerEmpty {
    @Setup
    def setup() {
      System.setSecurityManager(null)
    }
  }


}

@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class SecurityManagerExample {

  import SecurityManagerExample._

//  @Benchmark
  def testWithSM(s: SecurityManagerInstalled): String = System.getProperty("java.home")

//  @Benchmark
  def testWithoutSM(s: SecurityManagerEmpty): String = System.getProperty("java.home")

}
