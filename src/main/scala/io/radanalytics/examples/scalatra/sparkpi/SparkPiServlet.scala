package io.radanalytics.examples.scalatra.sparkpi

import org.apache.spark.{SparkConf, SparkContext}
import org.scalatra.{Ok, ScalatraServlet}

class SparkPiServlet extends ScalatraServlet {

    get("/"){
        Ok( "Scala Scalatra SparkPi server running. Add the 'sparkpi' route to this URL to invoke the app." )
    }

    get( "/sparkpi" ) {
        val scale = Integer.parseInt( params.getOrElse( "scale", "2" ) )

        val spark = new SparkContext( new SparkConf().setAppName( "Radanalytics IO SparkPI Scalatra Tutorial" ) )
        val sparkPi = new SparkPI( spark, scale ).calculate()
        spark.stop()

        Ok( "Pi is roughly " + sparkPi )
    }
}