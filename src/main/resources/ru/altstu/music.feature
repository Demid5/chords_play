Feature: Play one chord
  Just to play on chord


  Scenario:
    Given I have tools for sound on Java
    When I entered chord "Am"
    Then I heard Am with default time equals 5

  Scenario:
    Given I have tools for sound on Java
    When I entered chord "Am"
    And I add "Em"
    And I add "Dm"
    Then I heard Am with default time equals 15

  Scenario:
    Given I have tools for sound on Java
    When I entered chord "Am" with time equals 12
    Then I heard Am with default time equals 12

  Scenario:
    Given I have tools for sound on Java
    When I entered chord "Am" with time equals 12
    And I add "Em" time equals 10
    And I add "Dm" time equals 4
    Then I heard Am with default time equals 26