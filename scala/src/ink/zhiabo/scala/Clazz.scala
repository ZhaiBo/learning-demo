package ink.zhiabo.scala

/**
  * 实例化类
  *
  * @param xc
  * @param yc
  */
class Point(xc: Int, yc: Int) {
  var x: Int = xc
  var y: Int = yc

  def move(dx: Int, dy: Int) {
    x = x + dx
    y = y + dy
    println("x 的坐标点: " + x)
    println("y 的坐标点: " + y)
  }
}


class Location(val xc: Int, val yc: Int, val zc: Int) extends Point(xc, yc) {
  var z: Int = zc

  def move(dx: Int, dy: Int, dz: Int) {
    x = x + dx
    y = y + dy
    z = z + dz
    println("x 的坐标点 : " + x)
    println("y 的坐标点 : " + y)
    println("z 的坐标点 : " + z)
  }
}

class Person {
  var name = ""

  override def toString = getClass.getName + "[name=" + name + "]"
}

class Employee extends Person {
  var salary = 0.0

  override def toString = super.toString + "[salary=" + salary + "]"
}

object Clazz {
  def main(args: Array[String]) {
    val pt = new Point(10, 20)

    // 移到一个新的位置
    pt.move(10, 10);

    var location = new Location(10, 20, 5)

    location.move(10, 20, 5)

    val fred = new Employee
    fred.name = "Fred"
    fred.salary = 50000
    println(fred)
  }
}