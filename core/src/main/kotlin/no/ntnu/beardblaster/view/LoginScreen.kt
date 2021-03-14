package no.ntnu.beardblaster.view

import com.badlogic.gdx.Gdx
import ktx.log.debug
import ktx.log.logger
import no.ntnu.beardblaster.AbstractScreen
import no.ntnu.beardblaster.BeardBlasterGame

private val LOG = logger<LoginScreen>()

class LoginScreen(game: BeardBlasterGame) : AbstractScreen(game) {
    override fun show() {
        LOG.debug { "LOGIN SCREEN SHOWN" }

    }

    override fun update(delta: Float) {
        if (Gdx.input.justTouched()) {
            //Change screen
            game.setScreen<LoginMenuScreenScreen>()
        }
    }

    override fun render(delta: Float) {
        update(delta)
    }

    override fun resize(width: Int, height: Int) {
        super.resize(width, height)
    }

    override fun dispose() {
        super.dispose()
    }
}
