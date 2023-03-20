Feature: A Simple Todo App

  Rule: The app should initally contain 5 items
    Scenario: Can see the default items
      When I open the app
      Then I should see 5 items

  Rule: Users can add more items to the list
    Scenario: Can add new items
      Given I have opened the app
      When I add new item "Complete LambdaTest Tutorial"
      Then I should see 6 items
