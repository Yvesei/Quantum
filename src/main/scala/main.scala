import Vqs._
import Vqs.complex.QComplex
import Vqs.operators._

object Main {
  QReg.setDefaultUseColor()
  QReg.setDefaultUseASCII()

  def main(args: Array[String]): Unit = {
    QReg.setDefaultDrawAll()

    // Create quantum register with 3 qubits
    val rr = new QReg(3)
    rr.trace()
    // Get random state
    val phiState: (QComplex, QComplex) = QUtils.randomState()

    println(s"Alice (#1) and Bob (#0) share an EPR pair")
    println(f"Alice has the QBit phi (#2) with the unknown state of\n ${phiState}")

    // Set the state and continue with circuit
    rr.pokeQBitState(2, phiState)
    println(rr)

    println("Alice reads:")
    rr - H(0)
    rr - C(X(1), 0)
    rr - C(X(1), 2)
    rr - H(2)
    rr - <(1)- <(2)
    rr - Y(0)
    println(rr.render)


    println(rr)

    rr.pokeQBitState(0, phiState)


    println("\nBob State is : " + phiState)

  }
}