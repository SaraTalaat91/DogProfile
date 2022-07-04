package com.example.dogprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

@Composable
fun ProfilePageConstraints() {
    Card(
        elevation = 6.dp,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp, bottom = 60.dp, start = 16.dp, end = 16.dp)
            .border(width = 4.dp, color = Color.White, shape = RoundedCornerShape(30.dp))
    ) {
        BoxWithConstraints {
            val constraints = if (minWidth < 600.dp) getPortraitConstraints(margin = 16.dp)
            else getLandscapeConstraints(margin = 16.dp)

            ConstraintLayout(
                constraintSet = constraints,
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Image(
                    painter = painterResource(id = R.drawable.husky),
                    contentDescription = "Husky",
                    Modifier
                        .layoutId("image")
                        .clip(CircleShape)
                        .size(100.dp)
                        .border(width = 1.dp, Color.Blue, CircleShape),
                    contentScale = ContentScale.Crop
                )
                Text(text = "Siberian Husky", Modifier.layoutId("nameText"))
                Text(text = "Germany", Modifier.layoutId("countryText"))

                Row(
                    Modifier
                        .layoutId("profileStatsRow")
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ProfileStats(title = "Followers", value = "150")
                    ProfileStats(title = "Following", value = "100")
                    ProfileStats(title = "Posts", value = "30")
                }

                Button(
                    onClick = { /*TODO*/ }, modifier = Modifier
                        .layoutId("leftButton")
                        .padding(top = 16.dp, bottom = 16.dp)
                ) {
                    Text(text = "Follow User")
                }

                Button(
                    onClick = { /*TODO*/ }, modifier = Modifier
                        .layoutId("rightButton")
                        .padding(top = 16.dp, bottom = 16.dp)
                ) {
                    Text(text = "Direct Message")
                }
            }
        }
    }
}

@Composable
private fun getPortraitConstraints(margin: Dp) = ConstraintSet {
    val image = createRefFor("image")
    val nameText = createRefFor("nameText")
    val countryText = createRefFor("countryText")
    val profileStatsRow = createRefFor("profileStatsRow")
    val leftButton = createRefFor("leftButton")
    val rightButton = createRefFor("rightButton")
    val guideline = createGuidelineFromTop(0.2f)

    constrain(image) {
        top.linkTo(guideline)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }

    constrain(nameText) {
        top.linkTo(image.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }

    constrain(countryText) {
        top.linkTo(nameText.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }

    constrain(profileStatsRow) {
        top.linkTo(countryText.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }

    constrain(leftButton) {
        start.linkTo(parent.start)
        top.linkTo(profileStatsRow.bottom, margin)
        end.linkTo(rightButton.start)
    }

    constrain(rightButton) {
        start.linkTo(leftButton.end)
        top.linkTo(profileStatsRow.bottom, margin)
        end.linkTo(parent.end)
    }
}

@Composable
private fun getLandscapeConstraints(margin: Dp) = ConstraintSet {
    val image = createRefFor("image")
    val nameText = createRefFor("nameText")
    val countryText = createRefFor("countryText")
    val profileStatsRow = createRefFor("profileStatsRow")
    val leftButton = createRefFor("leftButton")
    val rightButton = createRefFor("rightButton")
    val guideline = createGuidelineFromTop(0.1f)

    constrain(image) {
        top.linkTo(guideline)
        start.linkTo(parent.start, margin)
    }

    constrain(nameText) {
        top.linkTo(image.bottom)
        start.linkTo(parent.start, margin)
    }

    constrain(countryText) {
        top.linkTo(nameText.bottom)
        start.linkTo(nameText.start, margin)
    }

    constrain(profileStatsRow) {
        top.linkTo(guideline)
        start.linkTo(image.end, margin)
        end.linkTo(parent.end)
    }

    constrain(leftButton) {
        start.linkTo(profileStatsRow.start)
        top.linkTo(profileStatsRow.bottom, margin)
        end.linkTo(rightButton.start)
    }

    constrain(rightButton) {
        start.linkTo(leftButton.end)
        top.linkTo(profileStatsRow.bottom, margin)
        end.linkTo(profileStatsRow.end)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePageConstraintsPreview() {
    ProfilePageConstraints()
}