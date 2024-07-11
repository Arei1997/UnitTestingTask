import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class TaxCalculatorSpec extends AnyWordSpec {

  val taxCalculator: TaxCalculator = new TaxCalculator

  // I've done the first test for you!
  "TaxCalculator.calculateTax" should {
    "return the total amount of tax to pay" when {
      "the income is below the personal tax limit" in {
        val result: Double = taxCalculator.calculateTax(5000)
        assert(result == 0)
      }
    }
  }

  "High rate tax payer" should{
    "Return true if the income is within the higher limit" in {
      val result: Boolean = taxCalculator.isHigherRateTaxpayer(85000)
      assert(result)
    }
  }
}
