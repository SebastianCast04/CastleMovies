package com.example.movieapp.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.core.domain.model.FilterType

@Composable
fun FilterBoxes(
    type: FilterType,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit

) {

    val backgrounColor = if (isSelected) Color.White else Color.Black
    val textColor = if (isSelected) Color.Black else Color.White


    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                onClick()
            }
            .background(backgrounColor).then(
                if (!isSelected) {
                    Modifier.border(0.5.dp, color = Color.LightGray, RoundedCornerShape(20.dp))
                }else{
                    Modifier
                }

            )

            .padding(vertical = 8.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = type.text, color = textColor, fontSize = 14.sp)
    }


}