package org.apache.spark.mllib.linalg

import breeze.linalg.{Vector ⇒ BV}
import org.apache.spark.mllib.linalg.{Vector ⇒ MLLibVector}

/**
  * This shim reaches into Spark's private linear algebra
  * code, in order to take advantage of optimized dot products.
  * While the dot product implementation in question is part of
  * MLlib's BLAS module, BLAS itself only supports dot products
  * between dense vectors, and MLlib implements sparse vector
  * dot products. Using a shim here avoids copy/pasting that
  * implementation.
  */
object LinalgShim {

  /**
    * Convert a Spark vector to a Breeze vector to access
    * vector operations that Spark doesn't provide.
    */
  def toBreeze(x: MLLibVector): BV[Double] = {
    x.asBreeze
  }

}