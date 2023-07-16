package com.canerkaseler.threadscard

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.canerkaseler.threadscard.cards.CardBackSide
import com.canerkaseler.threadscard.cards.CardFrontSide
import com.canerkaseler.threadscard.entity.User
import kotlin.math.abs

@Composable
fun ThreadsInviteCard() {

    // This is our custom value of Axis-Y on coordinate system.
    var axisY by remember { mutableStateOf(0f) }

    ThreadsInviteCardHolder (
        frontSide = {
            CardFrontSide(user = User())
        },
        backSide = {
            CardBackSide()
        },
        positionAxisY = axisY,
        modifier = Modifier
            .padding(
                horizontal = 48.dp,
                vertical = 210.dp
            )
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragStart = { offset ->

                    },
                    onDragEnd = {

                    },
                    onDragCancel = {

                    },
                    onHorizontalDrag = { change, dragAmount ->

                        // Decide to turn card in which side.
                        axisY = if (dragAmount < 0) {
                            (axisY - abs(dragAmount)) % 360 // Turn left for negative numbers.
                        } else {
                            (axisY + abs(dragAmount)) % 360 // Turn right for positive numbers.
                        }
                    }
                )
            },
    )
}

/**
 * This holder manages logic to show correct side of the card.
 */
@Composable
fun ThreadsInviteCardHolder(
    modifier: Modifier = Modifier,
    positionAxisY: Float,
    frontSide: @Composable () -> Unit = {},
    backSide: @Composable () -> Unit = {},
) {
    Card(
        modifier = modifier
            .graphicsLayer {
                rotationY = positionAxisY // Move card according to value of customY.
                cameraDistance = 14f * density
            },
    ) {

        // Here, logic is about coordinate system such as [0..90], [91..270], [270..360].
        if (abs(positionAxisY.toInt()) % 360 <= 90) {
            Box(
                Modifier.fillMaxSize()
            ) {
                frontSide()
            }
        } else if (abs(positionAxisY.toInt()) % 360 in 91..270) {
            Box(
                Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        rotationY = 180f // Important to avoid mirror effect.
                    },
            ) {
                backSide()
            }
        } else {
            Box(
                Modifier.fillMaxSize()
            ) {
                frontSide()
            }
        }
    }
}
