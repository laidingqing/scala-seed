package avalon
package routing

import akka.actor.ActorContext
import skarn.push.PushServiceInfo
import spray.routing._
import Directives._
import spray.http.MediaTypes._

class IndexRoute(context: ActorContext) extends BasicRoute {
  implicit val system = context.system
  val resource = ""

  val route = (pathSingleSlash | path(version)) {
    get {
      complete {
        <html>
          <body>
            <h1>Seed</h1>
            <p>seed server build on Akka Actor with Scala</p>
            <h2>Services</h2>
          </body>
        </html>
      }
    }
  }
}
