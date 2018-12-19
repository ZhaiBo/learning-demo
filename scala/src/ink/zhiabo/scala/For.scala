package ink.zhiabo.scala

object For {
  def main(args: Array[String]) {
    /* var a = 0;
     for (a <- 1 to 10) {
       println("Value of a: " + a);
     }

     var b = 0;
     for (b <- 1 until 10) {
       println("Value of b: " + b)
     }*/

    /*var c = 0;
    var d = 0;
    // for 循环 输出区间所有的可能值
    for (c <- 1 to 3; d <- 1 to 3) {
      println("Value of c: " + c);
      println("Value of d: " + d);
    }*/
    /*
        var a = 0;
        var numList = List(1,2,3);

        // for 循环
        for( a <- numList ){
          println( "Value of a: " + a );
        }*/
    var a = 0;
    var x = 0;
    var numList = List(1, 2, 3);

    /**
      * 条件循环
      */
    /* for (a <- numList if a > 1; if a < 3) {
       println("Value of a: " + a);
     }*/
    print(for {x <- numList if x > 1; if x < 4} yield x)
  }
}