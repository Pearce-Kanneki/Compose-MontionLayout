package com.kanneki.composemotionlayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.MotionLayout

@Composable
fun MotionLayoutHeader(
    progress: Float,
    scrollableBody: @Composable () -> Unit
) {
    MotionLayout(
        start = JsonConstraintSetStart(),
        end = JsonConstraintSetEnd(),
        progress = progress,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.jetpack),
            contentDescription = "poster",
            modifier = Modifier
                .layoutId("poster")
                .background(MaterialTheme.colors.primary)
        )

        Text(
            text = "Android Jetpack",
            modifier = Modifier
                .layoutId("title")
                .wrapContentHeight(),
            color = motionColor("title", "textColor"),
            fontSize = motionFontSize("title", "textSize"),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )

        Box(modifier = Modifier.layoutId("content")) {
            scrollableBody()
        }
    }
}

@Composable
private fun JsonConstraintSetStart() = ConstraintSet(
    """ {
	poster: { 
		width: "spread",
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['parent', 'top', 0],
	},
	title: {
		top: ['poster', 'bottom', 16],
		start: ['parent', 'start', 16],
		custom: {
			textColor: "#000000", 
			textSize: 40
		}
	},
	content: {
		width: "spread",
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['title', 'bottom', 16],
	}
} """
)

@Composable
private fun JsonConstraintSetEnd() = ConstraintSet(
    """ {
	poster: { 
		width: "spread",
		height: 56,
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['parent', 'top', 0],
	},
	title: {
		top: ['parent', 'top', 0],
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0], 
		bottom: ['poster', 'bottom', 0],
		custom: {
			textColor: "#ffffff",
			textSize: 20
        }
	},
	content: {
		width: "spread",
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['poster', 'bottom', 0],
	}
                  
} """
)
