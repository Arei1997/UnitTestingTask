import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class TaxCalculatorSpec extends AnyWordSpec {

  val taxCalculator: TaxCalculator = new TaxCalculator

  // I've done the first test for you!(Thank you)
  "TaxCalculator.calculateTax" should {
    "return the total amount of tax to pay" when {
      "the income is below the personal tax limit" in {
        val result: Double = taxCalculator.calculateTax(5000)
        assert(result == 0)
      }
    }
    "Income is equal to or below the basic rate limit" in {
      val result: Double = taxCalculator.calculateTax(30000)
      assert(result == 4000)
    }
    "Income is equal to or below the higher rate limit" in {
      val result: Double = taxCalculator.calculateTax((75000))
      assert(result == 18000)
    }
      //((50000-10000)*0.2)+((125000-50000)*0.4)+((150000-125000)*0.45)
    "income is above the higher rate limit" in {
      val result: Double = taxCalculator.calculateTax(150000)
      assert(result == 49250)
    }
  }

  "High rate tax payer" should {
    "Return true if the income is within the higher limit" in {
      val result: Boolean = taxCalculator.isHigherRateTaxpayer(85000)
      assert(result)
    }
    "return false if the income is below the higher limit" in {
      val result: Boolean = taxCalculator.isHigherRateTaxpayer(30000)
      assert(result)
    }
  }

  //CGT Testing

  "Tax from capital gain with income less than £50270" should {
    "return amount taxed for basic rate shares at 10%" in {
      val result: Double = taxCalculator.CapitalGainCalculate(2500,30000)
      assert(result==0)
    }
  }

  "Tax from capital gain with income less than £50270" should {
    "return amount taxed for higher rate shares at 20%" in {
      val result: Double = taxCalculator.CapitalGainCalculate(10000,75000)
      assert(result==1400)
    }
  }
  //Total Tax Testing

  "Total tax should calculate a sum of income tax and Capital gain tax" should{
    "return total tax for a High rate taxpayer" in {
      val result: Double = taxCalculator.TotalTax(30000, 2500)
      assert(result == 4000)
    }
    "return total tax for basic rate taxpayer" in{
      val result:Double = taxCalculator.TotalTax(450000,10000)
      assert(result==5050)
    }
  }




}