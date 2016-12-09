package controllers

import models.ContactForm
import play.api.Logger
import play.api.mvc._

import scala.concurrent.Future
import play.api.libs.mailer._
import javax.inject._
import scala.concurrent.ExecutionContext.Implicits.global

import play.api.i18n.{I18nSupport, MessagesApi}

class Application @Inject()(mailerClient: MailerClient, val messagesApi: MessagesApi)
  extends Controller with I18nSupport {
  def index = Action {
    Ok(views.html.index(ContactForm.form))
  }

  def contact = Action.async { implicit request =>
    ContactForm.form.bindFromRequest.fold(
      errorForm => {
        Logger.warn("error : " + errorForm.errors)
        Future.successful(Ok(views.html.index(errorForm)))
      },
      successData => {
        Future {
          val email = Email(
            "Message from " + successData.name + " on agoetschm.com",
            successData.email,
            Seq("agoetschm@agoetschm.com"),
            bodyText = Some(successData.message)
          )
          mailerClient.send(email)
          Redirect(routes.Application.index())
        }
      }
    )
  }

}
