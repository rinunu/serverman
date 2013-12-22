package serverman

import scala.reflect.ClassTag

trait Role

object Role {
}

case class Environment(name: String)

trait Project {
}

/**
 * 1つの役割をもつ1つの host
 *
 * 1つの host が複数の role を保つ場合、その分だけ node を作ります
 */
case class Node(ip: String,
                environment: Environment) {
  def is[ROLE <: Role : ClassTag]: Boolean = {
    val roleClass = implicitly[ClassTag[ROLE]].runtimeClass

    roleClass.isAssignableFrom(getClass)
  }
}

class RichNodeSeq[NODE <: Node, COL[A] <: Traversable[A]](val impl: COL[NODE]) {
  def withRole[ROLE <: Role : ClassTag]: COL[Node with ROLE] = {
    impl.filter(_.is[ROLE]).asInstanceOf[COL[Node with ROLE]]
  }
}

trait Implicits {
  implicit def richNodeCol[NODE <: Node, COL[A] <: Traversable[A]](a: COL[NODE]) =
    new RichNodeSeq[NODE, COL](a)
}

object Implicits extends Implicits
