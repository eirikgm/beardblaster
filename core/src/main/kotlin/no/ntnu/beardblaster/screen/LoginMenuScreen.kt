package no.ntnu.beardblaster.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.Align
import ktx.actors.onClick
import ktx.log.debug
import ktx.log.logger
import no.ntnu.beardblaster.BeardBlasterGame
import no.ntnu.beardblaster.assets.Assets

private val LOG = logger<LoginMenuScreen>()

class LoginMenuScreen(game: BeardBlasterGame) : AbstractScreen(game) {
    private lateinit var skin: Skin
    private lateinit var table: Table
    private lateinit var heading: Label

    private lateinit var exitBtn: TextButton
    private lateinit var loginBtn: TextButton
    private lateinit var registerBtn: TextButton

    private val loginMenuStage: Stage by lazy {
        val result = BeardBlasterStage()
        Gdx.input.inputProcessor = result
        result
    }

    override fun show() {
        LOG.debug { "LOGIN MENU Screen" }

        skin = Skin(Assets.assetManager.get(Assets.atlas))
        table = Table(skin)
        table.setBounds(0f, 0f, viewport.worldWidth, viewport.worldHeight)

        // Fonts
        val standardFont = Assets.assetManager.get(Assets.standardFont)

        // Creating buttons
        val textButtonStyle = TextButton.TextButtonStyle()
        skin.getDrawable("button_default").also { textButtonStyle.up = it }
        skin.getDrawable("button_default_hover").also { textButtonStyle.over = it }
        skin.getDrawable("button_default_pressed").also { textButtonStyle.down = it }
        textButtonStyle.pressedOffsetX = 4f
        textButtonStyle.pressedOffsetY = -4f
        textButtonStyle.font = standardFont

        exitBtn = TextButton("EXIT GAME", textButtonStyle)
        loginBtn = TextButton("LOGIN", textButtonStyle)
        registerBtn = TextButton("REGISTER", textButtonStyle)
        setBtnEventListeners()

        // Creating heading
        val headingStyle = Label.LabelStyle(standardFont, Color.BLACK).also {
            heading = Label("BeardBlaster", it)
            heading.setFontScale(2f)
            it.background = skin.getDrawable("modal_fancy_header")
            heading.setAlignment(Align.center)
        }

        // Creating table
        table.apply {
            this.background = skin.getDrawable("modal_fancy")
            this.add(heading).pad(50f)
            this.row()
            this.add(loginBtn).pad(40f)
            this.row()
            this.add(registerBtn).pad(40f)
            this.row()
            this.add(exitBtn).pad(40f)
        }

        // Adding actors to the stage
        loginMenuStage.addActor(table)

        Gdx.input.inputProcessor = loginMenuStage

    }

    override fun update(delta: Float) {

    }

    override fun setBtnEventListeners() {
        loginBtn.onClick {
            game.setScreen<LoginScreen>()
        }
        registerBtn.onClick {
            game.setScreen<RegisterScreen>()
        }
        exitBtn.onClick {
            Gdx.app.exit()
        }
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        update(delta)

        loginMenuStage.act(delta)
        loginMenuStage.draw()
    }


}