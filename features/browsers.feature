#Author: sogeti
@TESTS
Feature: Test capgemini web on Firefox browser.
 Esta feature realiza pruebas sobre navegadores.

@TestCapgeminiWeb
Scenario Outline: F001 - Búsqueda en capgemini.com.
Given I navigate to page "https://www.capgemini.com"
 When I search for "<busqueda>"
 Then I verify the presence of link with text "<nombre>"
  And I close the browser
	
 Examples:
   | busqueda  | nombre           | 
   | seguridad | Security Manager | 
   | scrum     | Scrum Master     | 
   
