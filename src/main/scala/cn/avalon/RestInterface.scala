
package avalon

import spray.routing._

import scala.concurrent.duration._
import scala.language.postfixOps
import akka.actor._
import akka.util.Timeout
import avalon.routing.{ErrorFormat, IndexRoute, RouteDefinitions}

class RestInterface extends HttpServiceActor with BaseApi {
  def receive = runRoute(routes)
}

object RestInterface {
  def props = Props[RestInterface]
}

trait BaseApi extends HttpService with RouteDefinitions with ActorLogging { actor: Actor =>

  implicit val timeout = Timeout(5 seconds)
  implicit val dispatcher = context.dispatcher
  object IndexRoute extends IndexRoute(context)


  val routeDefinitions = Seq(
    IndexRoute
  )

  override def timeoutRoute: Route = {
    import spray.http.StatusCodes._
    import avalon.routing.ErrorResponseProtocol._
    complete(InternalServerError, ErrorFormat.TIMEOUT.response)
  }
}
