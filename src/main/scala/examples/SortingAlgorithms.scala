package examples

import org.openjdk.jmh.annotations.{Benchmark, Scope, State}

import scala.util.Random

object SortingAlgorithms {
  @State(Scope.Benchmark)
  class ArrayState {
    private val rnd = new Random()
    val arr = new Array[Int](10000)
    for (i <- arr.indices) arr(i) = rnd.nextInt()
  }
}

class SortingAlgorithms {

  import examples.SortingAlgorithms.ArrayState

//  @Benchmark
  def testSelectionSort(state: ArrayState): Unit = {
    selectionSort(state.arr)
  }

//  @Benchmark
  def testBubbleSort(state: ArrayState): Unit = {
    bubbleSort(state.arr)
  }

//  @Benchmark
  def testMergeSort(state: ArrayState): Unit = {
    mergeSort(state.arr)
  }

  def bubbleSort(arr: Array[Int]): Unit = {
    for (i <- arr.indices) {
      for (j <- i until arr.length - 1) {
        if(arr(j) > arr(j + 1)) {
          val temp = arr(j)
          arr(j) = arr(j + 1)
          arr(j + 1) = temp
        }
      }
    }
  }

  def selectionSort(arr: Array[Int]): Unit = {
    for (i <- arr.indices) {
      var min = arr(i)
      var idx = i
      for (j <- i + 1 until arr.length) {
        if (min > arr(j)) min = arr(j); idx = j
      }
      val temp  = arr(i)
      arr(i) = min
      arr(idx) = temp
    }
  }

  def mergeSort(array: Array[Int]): Unit = {

    def sort(a: Array[Int], aux: Array[Int], lo: Int, hi: Int): Unit = {
      if (hi <= lo) return

      val mid = lo + (hi - lo) / 2

      sort(a, aux, lo, mid)
      sort(a, aux, mid + 1, hi)
      merge(a, aux, lo, mid, hi)
    }

    def merge(a: Array[Int], aux: Array[Int], lo: Int, mid: Int, hi: Int): Unit = {
      for (i <- a.indices) {
        aux(i) = a(i)
      }

      var i = lo
      var j = mid + 1

      for (k <- lo to hi) {
        if (i > mid) {
          a(k) = aux(j); j += 1
        } else if (j > hi) {
          a(k) = aux(i); i += 1
        } else if (aux(j) < aux(i)) {
          a(k) = aux(j); j += 1
        } else {
          a(k) = aux(i); i += 1
        }
      }
    }

    val aux = new Array[Int](array.length)
    sort(array, aux, 0, array.length - 1)
  }

}