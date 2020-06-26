Feature: Like a song on Frank Sinatra site

  Scenario: Like a song on Sinatra website
    Given I navigate to Sinatra song page
    When I click on like song
    Then song like counter is incremented