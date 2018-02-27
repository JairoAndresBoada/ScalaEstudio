package controllers

import java.util.ArrayList

import scala.collection.mutable
import play.api.mvc.{AbstractController, ControllerComponents}
import javax.inject.{Inject, Singleton}

@Singleton
class carroController @Inject() (cc: ControllerComponents) extends AbstractController(cc){
  var ListaCarros : mutable.Map[Int,mutable.Map[Int,String]] = mutable.Map(
    1 -> mutable.Map(1 -> "Carro",2 -> "15:20",3 -> "16:40",4 -> "7",5 -> "0"),
    2 -> mutable.Map(1 -> "Moto",2 -> "11:00",3 -> "12:00",4 -> "120",5 -> "0"),
    3 -> mutable.Map(1 -> "Moto",2 -> "16:12",3 -> "17:12",4 -> "300",5 -> "0"),
    4 -> mutable.Map(1 -> "Carro",2 -> "07:10",3 -> "08:10",4 -> "16",5 -> "0"),
  )

  def mostrarCarros = Action {
    calcularMonto()
    Ok(views.html.carros("Parqueadero Scala", ListaCarros))
  }

  def calcularMonto(): Unit = {

    for (item  <- ListaCarros) {
      val format = new java.text.SimpleDateFormat("HH:mm")
      val horaIngreso = format.parse(item._2.apply(2))
      val horaSalida = format.parse(item._2.apply(3))
      val diferencia : Double = (((horaSalida.getTime - horaIngreso.getTime)/60000D)/60D).ceil

      val horaCarro = 5000
      val horaMoto  = 2500

      def k(): Double = item._2.apply(1) match {
        case "Carro" => (horaCarro * diferencia)
        case "Moto"  => (horaMoto * diferencia)
        case _       => 0
      }

      item._2.update(5,k()+"")


    }
  }


}