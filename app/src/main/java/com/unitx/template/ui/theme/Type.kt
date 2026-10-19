package com.unitx.template.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.unitx.template.R

val Poppins = FontFamily(
    Font(
        resId = R.font.poppins_regular,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.poppins_medium,
        weight = FontWeight.Medium,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.poppins_semibold,
        weight = FontWeight.SemiBold,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.poppins_bold,
        weight = FontWeight.Bold,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.poppins_light,
        weight = FontWeight.Light,
        style = FontStyle.Normal
    )
)

val Lora = FontFamily(
    Font(
        resId = R.font.poppins_regular,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.poppins_medium,
        weight = FontWeight.Medium,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.poppins_semibold,
        weight = FontWeight.SemiBold,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.poppins_bold,
        weight = FontWeight.Bold,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.poppins_light,
        weight = FontWeight.Light,
        style = FontStyle.Normal
    )
)
val Typography = Typography(
    // H1
    displayLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 116.sp,
        fontSize = 96.sp
    ),

    // H2
    displayMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 60.sp,
        fontSize = 60.sp
    ),

    // H3
    displaySmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 48.sp,
        fontSize = 48.sp
    ),

    // H4
    headlineLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 40.sp,
        fontSize = 36.sp
    ),

    // H5
    headlineMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 32.sp,
        fontSize = 24.sp
    ),

    // H6
    headlineSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 28.sp,
        fontSize = 20.sp
    ),

    // Subtitle
    titleLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp,
        fontSize = 16.sp
    ),

    // Subtitle2
    titleMedium = TextStyle(
        fontFamily = Lora,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 20.sp,
        fontSize = 14.sp
    ),

    // Body
    bodyLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        fontSize = 16.sp
    ),

    //Body2
    bodyMedium = TextStyle(
        fontFamily = Lora,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
        fontSize = 14.sp
    ),

    // Button
    labelLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp,
        fontSize = 16.sp
    ),

    // Caption
    labelMedium = TextStyle(
        fontFamily = Lora,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 16.sp,
        fontSize = 12.sp
    ),

    // Caption2
    labelSmall = TextStyle(
        fontFamily = Lora,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
        fontSize = 12.sp
    )
)