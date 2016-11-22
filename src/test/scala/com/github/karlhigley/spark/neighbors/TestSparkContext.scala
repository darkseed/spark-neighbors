package com.github.karlhigley.spark.neighbors

import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{BeforeAndAfterAll, Suite}

trait TestSparkContext extends BeforeAndAfterAll {
  self: Suite ⇒
  @transient var sc: SparkContext = _

  override def beforeAll() {
    super.beforeAll()
    val conf = new SparkConf()
      .setMaster("local[2]")
      .setAppName("LshUnitTest")
    sc = new SparkContext(conf)
  }

  override def afterAll() {
    if (sc != null) {
      sc.stop()
    }
    sc = null
    super.afterAll()
  }

}