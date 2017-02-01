package controllers

import models.ContactForm
import play.api.Logger
import play.api.mvc._

import scala.concurrent.Future
import play.api.libs.mailer._
import javax.inject._

import akka.stream.scaladsl.Source

import scala.concurrent.ExecutionContext.Implicits.global
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.ws.WSClient
import play.api.mvc.MultipartFormData.DataPart


class Application @Inject()(mailerClient: MailerClient, ws: WSClient, configuration: play.api.Configuration,
                            val messagesApi: MessagesApi)
  extends Controller with I18nSupport {
  def index(contactSuccess: Option[Boolean]) = Action {
    Ok(views.html.index(ContactForm.form, contactSuccess))
  }

  def contact = Action.async { implicit request =>
    ContactForm.form.bindFromRequest.fold(
      errorForm => {
        Logger.warn("error : " + errorForm.errors)
        Future.successful(Ok(views.html.index(errorForm, None)))
      },
      successData => {
        val email = Email(
          "Message from " + successData.name + " on agoetschm.com",
          successData.email,
          Seq(configuration.underlying.getString("play.mailer.admin.email")),
          bodyText = Some(successData.message)
        )

        //Logger.warn("captcha response  " + successData.recaptcha_response)

        // send the reCaptcha response to google
        ws.url("https://www.google.com/recaptcha/api/siteverify")
          .post(Source(
            DataPart("secret", configuration.underlying.getString("recaptcha.secret"))
              :: DataPart("response", successData.recaptcha_response)
              :: List()))
          .map(response => {
            val success = (response.json \ "success").as[Boolean]
            //            Logger.warn("captcha result : " + success)
            if (success) {
              val res = mailerClient.send(email)
              //              Logger.warn("mail id : " + res)
            }
            Redirect(routes.Application.index(Some(success)))
          })
      }
    )
  }

}
