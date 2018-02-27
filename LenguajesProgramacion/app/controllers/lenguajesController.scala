package controllers

import javax.inject.Inject
import javax.inject.Singleton
import play.api.mvc.{AbstractController, ControllerComponents}

@Singleton
class lenguajesController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  val lang = Map("Java" ->35 , "C++" -> 45 , "Scala" -> 15 , "Pascal" -> 60)

  def lenguaje = Action{

    Ok(views.html.Lenguajes("Prueba de Lenguajes con Scala y Play",lang))

  }

}