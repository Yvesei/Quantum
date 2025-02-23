import Vqs._
import Vqs.complex.QComplex
import Vqs.operators.{$, <, C, H, Label, X, Y, Z}

object Main {
  QReg.setDefaultUseColor()
  QReg.setDefaultUseASCII()

  def main(args: Array[String]): Unit = {
    val rr: QReg = QReg(3)
    rr - $(0, "Bob") - $(1, "Alice") - $(2, "Phi")
    val phiState: (QComplex, QComplex) = QUtils.randomState()
    rr.pokeQBitState(2, phiState)
    rr - H(0)
    rr - C(X(1), 0)
    rr - C(X(1), 2)
    rr - H(2)
    rr - Y(0)
    println(rr.render)
    println(rr)
  }
}