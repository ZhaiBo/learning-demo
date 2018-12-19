package ink.zhiabo.scala

object Function {
  def main(args: Array[String]): Unit = {
    println(m(3))
    println(f(3))
    println(appendStr("hello,", "world"))
  }

  def m(x: Int) = x + 3

  val f = (x: Int) => x + 3

  def appendStr(y: String, z: String): String = {
    return y + z
  }
}