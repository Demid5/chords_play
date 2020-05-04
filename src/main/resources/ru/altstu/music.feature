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
    And then "Dm"
    Then I heard my music 3 * default time equals 15

  Scenario:
    Given I have tools for sound on Java
    When I entered chord "Am" with time equals 12
    Then I heard my music 12 time

  Scenario:
    Given I have tools for sound on Java
    When I entered chord "Am" with time equals 12
    And I add "Em" time equals 10
    And then "Dm" equals 32
    Then I heard my music 54 time