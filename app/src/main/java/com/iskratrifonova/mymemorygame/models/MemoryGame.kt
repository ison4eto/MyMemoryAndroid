package com.iskratrifonova.mymemorygame.models

import com.iskratrifonova.mymemorygame.utils.DEFAULT_ICONS

class MemoryGame (private val boardSize: BoardSize) {

    val cards: List<MemoryCard>
    var numPairsFound = 0

    private var indexOfSingleSelectedCard: Int? = null

    init {
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs());
        val randomizedImages = (chosenImages + chosenImages).shuffled()
        cards = randomizedImages.map { MemoryCard(it) }
    }

    fun flipCard(position: Int): Boolean {
        val card = cards[position]
        var foundMatch = false
        if(indexOfSingleSelectedCard == null) {
            restoreCards()
            indexOfSingleSelectedCard = position
        } else {
            // exactly one card is flipped over
            foundMatch = checkForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
        }
        card.isFaceUp = !card.isFaceUp
        return foundMatch
    }

    /**
     * Checks for a matches between two positions.
     * If match is found, update the isMatched field of these cards
     */
    private fun checkForMatch(flippedCardIndex: Int, position: Int): Boolean {
        if(cards[flippedCardIndex].identifier != cards[position].identifier) {
            return false
        }
        cards[flippedCardIndex].isMatched = true
        cards[position].isMatched = true
        numPairsFound++
        return true
    }

    /**
     * Turns down all cards that don't have matches.
     * This should happen when we turn the third card.
     */
    private fun restoreCards() {
        for (card in cards) {
            if (!card.isMatched) {
                card.isFaceUp = false
            }
        }
    }

    fun haveWonGame(): Boolean {
        return numPairsFound == boardSize.getNumPairs()
    }

    fun isCardFaceUp(position: Int): Boolean {
        return cards[position].isFaceUp
    }
}
