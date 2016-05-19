
package avalon

object BootApi extends App with Bootable {
  val serviceBootstrap = Api

  startup()
}
