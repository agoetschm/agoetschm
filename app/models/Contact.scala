package models

import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.{Constraint, Invalid, Valid}

/**
  * Created by agoetschm on 12/9/16.
  */

case class ContactMessage(name: String, email: String, message: String, recaptcha_response: String)

object ContactForm {
  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "email" -> email,
      "message" -> nonEmptyText,
      "g-recaptcha-response" -> text
    )(ContactMessage.apply)(ContactMessage.unapply)
  )
}