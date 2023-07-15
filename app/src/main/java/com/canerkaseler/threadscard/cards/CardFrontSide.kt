package com.canerkaseler.threadscard.cards

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.canerkaseler.threadscard.R
import com.canerkaseler.threadscard.entity.User


private val spaceBetweenItems = 28.dp
private val framePadding = 24.dp

@Composable
fun CardFrontSide(
    user: User
) {

    // All card surface.
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {

        // Top black half circle.
        CardBlackHalfCircles(modifier = Modifier.align(alignment = Alignment.TopCenter))


        // Card content.
        CardContent(
            date = user.date,
            time = user.time,
            instagram = user.instagram,
            userId = user.userId,
            username = user.username,
            userImage = user.userImage,
            userQrCode = user.userQrCode,
        )

        // Bottom black half circle.
        CardBlackHalfCircles(modifier = Modifier.align(alignment = Alignment.BottomCenter))
    }
}

@Composable
fun CardBlackHalfCircles(
    modifier: Modifier
) {
    Canvas(
        modifier = modifier
            .border(color = Color.Magenta, width = 2.dp)
    ) {
        drawCircle(
            color = Color.Black,
            radius = 24.dp.toPx()
        )
    }
}

@Composable
fun CardContent(
    date: String,
    time: String,
    instagram: String,
    userId: String,
    username: String,
    userImage: Int,
    userQrCode: Int,
) {
    Column (
        modifier = Modifier
    ) {

        Spacer(modifier = Modifier.height(spaceBetweenItems))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            CardTitleText(title = "DATE", info = date)

            CardBrand(modifier = Modifier.align(alignment = Alignment.Bottom))
        }

        Spacer(modifier = Modifier.height(spaceBetweenItems))

        CardTitleText(title = "TIME", info = time)

        Spacer(modifier = Modifier.height(spaceBetweenItems))

        CardTitleText(title = "USERNAME", info = username)

        Spacer(modifier = Modifier.height(spaceBetweenItems))

        CardUserQrCode(userQrCode = userQrCode, modifier = Modifier.align(alignment = Alignment.Start))

        Spacer(modifier = Modifier.height(spaceBetweenItems))

        CardDashDivider()

        Spacer(modifier = Modifier.height(spaceBetweenItems))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {

                CardUserImage(userImage = userImage)

                CardInstagram(text = instagram)
            }

            CardUserId(text = userId)
        }
    }
}

@Composable
fun CardTitleText(title: String, info: String) {
    Column {
        Text(
            modifier = Modifier.padding(
                horizontal = framePadding
            ),
            text = title,
            color = Color.Black,
            fontWeight = FontWeight.ExtraBold,
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily.Monospace,
            )
        )

        Text(
            modifier = Modifier.padding(
                horizontal = framePadding
            ),
            text = info,
            color = Color.Black,
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace
            )
        )
    }
}

@Composable
fun CardBrand(
    modifier: Modifier
) {
    Image(
        modifier = modifier
            .padding(
                end = framePadding
            )
            .size(size = 42.dp),
        painter = painterResource(id = R.drawable.ic_threads_black),
        contentDescription =""
    )
}

@Composable
fun CardDashDivider() {
    Canvas(
        Modifier
            .fillMaxWidth()
            .height(1.dp)) {
        drawLine(
            color = Color.DarkGray,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 14f), 0f)
        )
    }
}

@Composable
fun CardUserQrCode(userQrCode: Int, modifier: Modifier) {
    Image(
        modifier = modifier
            .padding(horizontal = framePadding)
            .size(size = 56.dp),
        painter = painterResource(id = userQrCode),
        contentDescription =""
    )
}

@Composable
fun CardUserImage(userImage: Int) {
    Image(
        modifier = Modifier
            .padding(start = framePadding)
            .size(size = 42.dp)
            .clip(CircleShape) ,
        contentScale = ContentScale.Crop,
        painter = painterResource(id = userImage),
        contentDescription = ""
    )
}

@Composable
fun CardInstagram(text: String) {
    Text(
        modifier = Modifier.padding(start = 16.dp),
        text = text,
        color = Color.Black,
        fontWeight = FontWeight.SemiBold,
        style = TextStyle(
            fontSize = 12.sp,
            fontFamily = FontFamily.Default,
            letterSpacing = 0.7.sp,
        )
    )
}

@Composable
fun CardUserId(text: String) {
    Text(
        modifier = Modifier.padding(end = framePadding),
        text = text,
        color = Color.Black,
        fontWeight = FontWeight.Light,
        style = TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily.Default,
            letterSpacing = 1.sp,
        )
    )
}

@Composable
@Preview
fun CardFrontSidePreview() {
    CardFrontSide(
        User(
            username = "CANERKASELER",
            instagram = "canerkaseler",
            userId = "071030501",
            date = "WED JUL 7",
            time = "03:24 P.M.",
            userImage = R.drawable.ic_user_avatar,
            userQrCode = R.drawable.ic_qr_code,
        )
    )
}