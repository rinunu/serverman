package serverman

import org.specs2.mutable.Specification


class ProjectTest extends Specification {

  trait Role0 extends Role

  trait Role1 extends Role

  object SampleEnv extends Environment("sample")

  "get node" >> {
    import serverman.Implicits._
    val nodes = Seq(
      new Node("a", SampleEnv) with Role0,
      new Node("b", SampleEnv) with Role1,
      new Node("c", SampleEnv) with Role0)

    val role0s = nodes.withRole[Role0]

    role0s === Seq(nodes(0), nodes(2))
  }


}
