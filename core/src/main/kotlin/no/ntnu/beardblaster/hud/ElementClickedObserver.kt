package no.ntnu.beardblaster.hud

import java.util.*

class ElementClickedObserver private constructor() : Observable() {
    fun notify(index: Int) {
        setChanged()
        notifyObservers(index)
    }

    companion object {
        val instance = ElementClickedObserver()
    }
}

