#Author: sogeti
@TESTS
Feature: Test calculator on Android
 Esta feature realiza pruebas sobre Android.

@TestAndroidCalculator
Scenario Outline: F001 - Busqueda google.
Given I open Calculator app on Android
 When I sum numbers "<num1>" and "<num2>"
 Then I get the result "<result>"
  And I close the app
	
 Examples:
   | num1 | num2 | result | 
   | 2    | 3    | 5      | 
   
