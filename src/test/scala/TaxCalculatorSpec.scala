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
    "Income is equal to or below the basic rate limit" in {
      val result: Double = taxCalculator.calculateTax(25000)
      assert(result == 3000)
    }
    "Income is equal to or below the higher rate limit" in {
      val result: Double = taxCalculator.calculateTax((80000))
      assert(result == 20000)
    }

    "income is above the higher rate limit" in {
      val result: Double = taxCalculator.calculateTax(150000)
      assert(result == 49250)
    }
  }

  "High rate tax payer" should{
    "Return true if the income is within the higher limit" in {
      val result: Boolean = taxCalculator.isHigherRateTaxpayer(85000)
      assert(result)
    }
    "return false if the income is below the higher limit" in {
      val result: Boolean = taxCalculator.isHigherRateTaxpayer(30000)
      assert((result))
    }
  }
}
