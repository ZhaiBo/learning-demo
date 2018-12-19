package ink.zhiabo.scala

/*
Unit	表示无值，和其他语言中void等同。用作不返回任何结果的方法的结果类型。Unit只有一个实例值，写成()。
Null	null或空引用
Nothing	Nothing类型在Scala的类层级的最低端；它是任何其他类型的子类型。
Any	Any是所有其他类的超类
AnyRef	AnyRef类是Scala里所有引用类(reference class)的基类
 */

object DataType {

  final case class Symbol private(name: String) {
    override def toString: String = "'" + name
  }

  def main(args: Array[String]): Unit = {
    val str =
      """
           *****   *****
         ******** ********
        *******************
         *****************
          ***************
            ***********
              *******
                ***
      """
    var str1 : String = "i am a variable"
    var var1 : Int = 0
    var var2 : Long = 0
    var var3 : Float = 0
    var var4 : Double = 0
    var var5 = 0D

    val xmax, ymax = 100

    println(str)
    println(var1)
    println(var2)
    println(var3)
    println(var4)
    println(var5)
    println(xmax)
    println(ymax)

    val pa = (40,"Foo")
    println(pa._1)
    println(pa._2)

  }
}
