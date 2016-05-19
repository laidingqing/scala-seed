package avalon

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import kamon.Kamon
import java.io.File
import scala.collection.JavaConversions._

trait Bootable {
  val serviceBootstrap: ServiceBootstrap

  def startup() = {
    val config = serviceBootstrap.config
    println(s"Config file: ${System.getProperty("CONFIG_PATH")}")
    println("Loaded services: ")
    val system = ActorSystem(ProjectInfo.name, config)
    serviceBootstrap.run(system)
  }
}
