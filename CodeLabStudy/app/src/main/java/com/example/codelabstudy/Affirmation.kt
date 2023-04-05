package com.example.codelabstudy

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(
    //@StringRes val stringResourceId: Int, //문자열 리소스에 저장된 격언 텍스트의 ID
    @DrawableRes val imageResourceId: Int //드로어블 리소스에 저장된 격언 이미지의 ID
)

class ImageSource(){
    fun loadAffirmations(): List<Affirmation>{
        return listOf<Affirmation>(
            Affirmation(R.drawable.image_1),
            Affirmation(R.drawable.image_2),
            Affirmation(R.drawable.image_3),
            Affirmation(R.drawable.image_4),
            Affirmation(R.drawable.image_5)
        )
    }
}