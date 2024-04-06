# A Basic Deck of Cards Game

Your assignment is to code, a set of classes and a REST API that represent a deck of poker-style playing cards along 
with the services for a very basic game between multiple players holding cards.

A deck is defined as follows: Fifty-two playing cards in four suits: hearts, spades, clubs, and diamonds, 
with face values of Ace, 2-10, Jack, Queen, and King.

The game API is a very basic game in which one or more decks are added to create a ‘game deck’, commonly referred to as a shoe, 
along with a group of players getting cards from the game deck.

### You must provide the following operations:

* Create and delete a game
* Create a deck 
* Add a deck to a game deck 
  * Please note that once a deck has been added to a game deck it cannot be removed. 
* Add and remove players from a game
* Deal cards to a player in a game from the game deck 
  * Specifically, for a game deck containing only one deck of cards, a call to shuffle
    followed by 52 calls to dealCards(1) for the same player should result in the
    caller being provided all 52 cards of the deck in a random order. If the caller then
    makes a 53rd call to dealCard(1), no card is dealt. This approach is to be
    followed if the game deck contains more than one deck.
* Get the list of cards for a player 
  * Get the list of players in a game along with the total added value of all the cards each
    player holds; use face values of cards only. Then sort the list in descending order, from
    the player with the highest value hand to the player with the lowest value hand:
    For instance if player ‘A’ holds a 10 + King then her total value is 23 and player
    ‘B’ holds a 7 + Queen then his total value is 19, so player ‘A’ will be listed first
    followed by player ‘B’. 
* Get the count of how many cards per suit are left undealt in the game deck (example: 5
hearts, 3 spades, etc.)
* The Datastore should be In-Memory and support concurrency access for the purpose of
this exercise **DO NOT USE** existing Java ConcurrentHashMap, use a standard
HashMap and build concurrency support around it. 
* An event should be triggered when an action is performed, include the change that
occurred. The listener should store an history of changes which will be exposed via an
endpoint that list out the Event in chronological order, for any specified entity (Game,
Player, Deck)

### Guides

The following guides illustrate how to use some features concretely:

* Java 17 and spring boot 3.x
* `mvn clean install`
* `mvn spring-boot:run`
* All the http request are into resources folder _`generated-requests.http`_

