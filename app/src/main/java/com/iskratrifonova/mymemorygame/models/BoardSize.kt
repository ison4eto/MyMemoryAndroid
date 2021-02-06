package com.iskratrifonova.mymemorygame.models

enum class BoardSize(val numCards: Int) {
    EASY(8),
    MEDIUM(18),
    HARD(24);

    fun getWidth(): Int {
        return when(this) {
            EASY -> 2
            MEDIUM -> 3
            HARD -> 4
        }
    }

    fun getHeight(): Int {
        return numCards / getWidth()
    }

    fun getNumPairs(): Int {
        return numCards / 2
    }

    fun getInitialMovesText(boardSize: BoardSize): String {
        return "${getCapitalizedText(boardSize)}: ${boardSize.getWidth()} x ${boardSize.getHeight()}"
    }
    private fun getCapitalizedText(boardSize: BoardSize): String {
        return boardSize.toString().toLowerCase().capitalize();
    }
}