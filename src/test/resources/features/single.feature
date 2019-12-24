Feature: Google's Search Functionality

    Scenario Outline: Can find search results <keyword>
        When I type query as <keyword>
        And I submit
        Then I should see title <keyword> - Google Search
    Scenarios: 
    | keyword |
    | LambdaTest |
    | Google |
    | DuckDuckgo |