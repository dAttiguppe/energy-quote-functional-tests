@EnergyQuoteTests
Feature: To provide functional tests on the energy comparison journey of CTM

  Background: User fills the required energy and provider details and proceeds to the summary page
    Given the user fills the supplier details
      |PostCode   |billPresent|EnergyToBeCompared|EnergyFromSameSupplier|CurrentSupplierSelection|
      |PE2 6YS    |Y          |    Electricity   |N                     |scottish-power          |
    When the user proceeds to the Your energy page
    And the user selects energy usage data
      |EnergyTariff        |Economy7MeterSelection|PaymentMode           |MainSourceOfHeating|CurrentElectricityUsage|
      |Random              |No                    |Monthly Direct Debit  |Yes                |kWH                    |
    When the user proceeds to the your preferences page
    And the user selects the preferences
      |TariffType   |PaymentMode           |EmailAddress   |
      |FixedTariff  |monthly               |test@test.com  |
    And the user confirms to the TermsAndConditions
    Then the user proceeds to the summary page

  @TariffsUserCantSwitchTo
    Scenario: To verify the available tariffs that the user can't switch to on the summary page
      Given the user checks the summary section on the Summary page
      When the user clicks on the More Tariffs button
      And the user clicks on the Include the providers we can’t switch you to button
      Then all the available tariffs should be displayed

    @FilterBySupplierRating
    Scenario: To verify the more details section on a tariff result after filtering by supplier rating
      Given the user checks the summary section on the Summary page
      When the user filters by four-star supplier rating filter
      And the user clicks on the checks more details about the quote
      Then all the more details section should be displayed

    @EditElectricitySupplier
    Scenario: To verify the user can edit the electricity supplier on the summary page
      Given the user checks the summary section on the Summary page
      When the user edits the electricity supplier
      Then the user lands on the Your Supplier page
