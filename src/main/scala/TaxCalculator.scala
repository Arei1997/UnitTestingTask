class TaxCalculator {

  // Tax bands (simplified to make testing a bit easier)
  private val personalAllowance: Int = 10000
  private val basicRateLimit: Int = 50000
  private val higherRateLimit: Int = 125000

  // Tax rates
  private val personalAllowanceRate: Double = 0
  private val basicRate: Double = 0.2
  private val higherRate: Double = 0.4
  private val additionalRate: Double = 0.45

  //CGT rates
  private val CapitalGainTaxAllowance:Double = 3000
  private val basicRateShare:Double = 0.1
  private val HigherRateShare:Double = 0.2
  //CGT Bands
  private val ThresholdCGT:Double = 50270

  // A method to calculate the total amount of tax to be paid, returned as a double
  def calculateTax(income: Double): Double = {
    if ( income <= personalAllowance){
      0
    } else if (income <= basicRateLimit){
      (income - personalAllowance) * basicRate
    } else if  ( income <= higherRateLimit){
      (basicRateLimit - personalAllowance) * basicRate + (income - basicRateLimit) * higherRate
    } else {
      (basicRateLimit - personalAllowance) * basicRate + (higherRateLimit - basicRateLimit)*higherRate + (income - higherRateLimit)*additionalRate
    }
  }

  // A method which can tell you if someone is a higher rate taxpayer
  def isHigherRateTaxpayer(income: Double): Boolean = {
    (income > basicRate)
  }

  // A method that will return a string with the income limit of their current tax band.
  // The return will also be formatted, E.g: "£12,500" or "No limit"
  def formattedCurrentTaxAllowance(income: Double): String = {
    if ( income <= personalAllowance){
      f"£$personalAllowance%,d"
    } else if (income <= basicRateLimit){
      f"£$basicRateLimit%,d"
    } else if ( income <= higherRateLimit){
      f"£$higherRateLimit,d"
    } else {
      "No Limit"
    }
  }

  // A method to calculate capital gains tax
  def CapitalGainCalculate(capGainProfit: Double, income: Double): Double = {
    if (capGainProfit <= CapitalGainTaxAllowance) {
      0
    } else {
      val taxableGain = capGainProfit - CapitalGainTaxAllowance
      if (income <= ThresholdCGT) {
        taxableGain * basicRateShare
      } else {
        taxableGain * HigherRateShare
      }
    }
  }

  //call existing calculateTax and CapitalGainCalculate and compute Total Tax
  def TotalTax (income:Double, CapGainProfit:Double):Double = {
   val  incomeTax = calculateTax(income)
   val  CapitalGainTax = CapitalGainCalculate(CapGainProfit, income)
    incomeTax+CapitalGainTax // Returning the total tax
  }

}
