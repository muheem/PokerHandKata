#### README.md

## Poker Hands Kata

### Introduction
The aim of the project is to compare several pairs of poker hands and to indicate which, if either, has a higher rank.

### Technologies
Project is created with:
Java 17
IntelliJ IDEA 2022.3.1
Maven Apache 4.0

### How to Launch
From an IDE run Main. The terminal will display command line:
Either enter a hand manually (in string format indicated)
or press RETURN, a 'random' hand will be dealt.
***************************
Please Enter BLACK poker hand in the following format:

-------  JD TD 3H 3D 5H   (enter 'bye' to exit)  -------
***************************

The result will be displayed:
***************************
Black: 2D 8S 3D 4H 3D ,White: 8C TC 9C TC 5C

White wins. - with a flush
***************************


### Design
The design evolved. Initially there were more classes.
The second stage I focussed on TDD, there appeared redundencies in the initial design,
and complexity in the (spagetti) code. The refactoring and final design is a partial solution.

### Diagram

![](/Users/ibrahim/Downloads/PokerHandsKataUML.jpg)


### Improvements
- Add more testing coverage.
- Improve the message detailing the winning hand.