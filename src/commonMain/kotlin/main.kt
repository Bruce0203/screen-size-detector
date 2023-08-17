import korlibs.korge.*
import korlibs.korge.scene.*
import korlibs.korge.view.*
import korlibs.image.color.*
import korlibs.image.text.*
import korlibs.korge.view.align.*
import korlibs.math.geom.*
import korlibs.math.geom.min
import kotlin.math.*

val color1 = Colors["#2b2b2b"]
val color2 = Colors.WHITESMOKE

suspend fun main() {
    Korge(
        title = "resolution test",
        windowSize = Size(512, 512), backgroundColor = color1,
        displayMode = KorgeDisplayMode(
            scaleMode = ScaleMode.NO_SCALE,
            scaleAnchor = Anchor.TOP_LEFT,
            clipBorders = false
        )
    ) {
        val sceneContainer = sceneContainer()
        sceneContainer.changeTo { MyScene() }
    }
}

class MyScene : Scene() {
	override suspend fun SContainer.sceneMain() {
        fixedSizeContainer(size) {
            val padding = 20f
            val container = fixedSizeContainer(size).centerOnStage()
            val originTextSize = 80f
            val text = container.text(
                "", textSize = originTextSize, alignment = TextAlignment.MIDDLE_CENTER, color = color2
            ).zIndex(5).centerOnStage()
            val top = container
                .solidRect(Size(width - padding, padding), color2)
                .positionY(padding)
            val bottom = container
                .solidRect(Size(width - padding, padding), color2)
                .positionY(height - padding * 2)
            val left = container
                .solidRect(Size(padding, height - padding), color2)
                .positionX(padding)
            val right = container
                .solidRect(Size(padding, height - padding), color2)
                .positionX(width - padding * 2)

            onStageResized { width, height ->
                container.size(width, height)
                top.width = width.toFloat() - padding * 2
                top.positionY(padding)
                top.positionX(padding)
                bottom.width = width.toFloat() - padding * 2
                bottom.positionY(height.toFloat() - padding * 2)
                bottom.positionX(padding)
                left.height = height.toFloat() - padding * 2
                left.positionX(padding)
                left.positionY(padding)
                right.height = height.toFloat() - padding * 2
                right.positionX(width.toFloat() - padding * 2)
                right.positionY(padding)

                text.text = "${width}x${height}"
                text.centerOn(container)
//                text.textSize = originTextSize.times(
//                    if (width == sceneWidth) height / width.toDouble() else height / width.toDouble()
//                        ).toFloat()
            }
        }

    }
}
