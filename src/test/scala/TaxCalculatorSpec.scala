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
      val result: Double = taxCalculator.calculateTax(75000)
      assert(result == 18000)
    }
    "Income is above the higher rate limit" in {
      val result: Double = taxCalculator.calculateTax(150000)
      assert(result == 49250)
    }
    "Income is exactly at the personal allowance" in {
      val result: Double = taxCalculator.calculateTax(10000)
      assert(result == 0)
    }
    "Income is exactly at the basic rate limit" in {
      val result: Double = taxCalculator.calculateTax(50000)
      assert(result == 8000)
    }
    "Income is exactly at the higher rate limit" in {
      val result: Double = taxCalculator.calculateTax(125000)
      assert(result == 38000)
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

  "TaxCalculator.formattedCurrentTaxAllowance" should {
    "return the formatted allowance for the personal tax limit" in {
      val result: String = taxCalculator.formattedCurrentTaxAllowance(5000)
      assert(result == "£10,000")
    }
    "return the formatted allowance for the basic rate limit" in {
      val result: String = taxCalculator.formattedCurrentTaxAllowance(30000)
      assert(result == "£50,000")
    }
    "return the formatted allowance for the higher rate limit" in {
      val result: String = taxCalculator.formattedCurrentTaxAllowance(80000)
      assert(result == "£125,000")
    }
    "return 'No limit' for incomes above the higher rate limit" in {
      val result: String = taxCalculator.formattedCurrentTaxAllowance(150000)
      assert(result == "No Limit")
    }
  }




}