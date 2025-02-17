import Vqs._
import Vqs.operators.{$, <, C, H, Label, X, Y, Z}

object Main {

  QReg.setDefaultUseColor()
  QReg.setDefaultUseASCII()
  def superDense(vcode: Int): Unit = {
    val rr: QReg = QReg(2)
    rr - $(0, "Bob") - $(1, "Alice")
    // Apply Hadamard on qubit 0 (Bob)
    rr - H(0)
    // Apply CNOT between qubits 0 and 1 (Bob and Alice)
    rr - C(X(1), 0)

    // Apply conditional NOT gates based on vcode
    vcode match {
      case 0 =>
        rr - Label("Alice -> Bob")
      case 1 =>
        rr - X(1)  // Apply NOT on qubit 1 (Alice's qubit)
        rr - Label("Alice -> Bob")

      case 2 =>
        rr - Z(1)  // Apply Z on qubit 1
        rr - Label("Alice -> Bob")

      case 3 =>
        rr - Y(1)  // Apply NOT on qubit 1
        rr - Label("Alice -> Bob")
    }
    rr - C(X(0), 1)
    rr - H(1);
    rr.render()  // Render the quantum circuit visually

    // Measure the qubits and output the result
    rr - <(0)
    rr - <(1)
    println(rr.render())
    println(s"Measurement result: ${rr.?()}")

    rr.end()
  }

  def main(args: Array[String]): Unit = {


    for (vcode <- 0 to 3) {
      println("Alice (#1) and Bob (#0) share an EPR pair.")
      println("Alice encodes a value and sends its OBit to Bob.")
      println("Bob decodes the OBit from Alice, performs a measurement, and reads the result.")
      println(s"Bob reads $vcode:")
      superDense(vcode)
    }
  }
}