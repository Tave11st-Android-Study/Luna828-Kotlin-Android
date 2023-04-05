package com.example.codelabstudy.home

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.codelabstudy.ui.theme.Green300
import com.example.codelabstudy.ui.theme.Green800
import com.example.codelabstudy.ui.theme.Purple100
import com.example.codelabstudy.ui.theme.Purple700
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

private enum class TabPage2 {
    Home, Work
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeClone(navController: NavController) {

    var tabPage by remember { mutableStateOf(TabPage2.Home) } //getValue, setValue import 해줘야함

    val backgroundColor = if (tabPage == TabPage2.Home) Purple100 else Green300

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            HomeTabBar(
                tabPage = tabPage,
                backgroundColor = backgroundColor,
                onTabSelected = { tabPage = it } // Tab을 바꿔주는 것
            )
        },
        backgroundColor = backgroundColor,
        ) {
        DemoScreen()
    }
}

@Composable
private  fun DemoScreen(){
    var sliderPosition by remember {
        mutableStateOf(20f)
    }

    val handlePositionChange = { position : Float ->
        sliderPosition = position
    }
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Hello Compose")
        Spacer(modifier = Modifier.height(150.dp))
        DemoSlider(
            sliderPosition = sliderPosition,
            onPositionChange = handlePositionChange,
        )
        Text(
            style = MaterialTheme.typography.h2,
            text = sliderPosition.toInt().toString() + "sp"
        )
    }
}


@Composable
private fun DemoSlider(
    sliderPosition: Float,
    onPositionChange: (Float) -> Unit
){
    Slider(
        modifier = Modifier.padding(10.dp),
        valueRange = 20f..40f,
        value = sliderPosition,
        onValueChange = { onPositionChange(it) }
    )
}

@Composable
private fun HomeTabBar(
    backgroundColor: Color,
    tabPage: TabPage2, //enum class
    onTabSelected: (tabPage: TabPage2) -> Unit
) {
    TabRow(
        selectedTabIndex = tabPage.ordinal,
        backgroundColor = backgroundColor,
        indicator = { tabPositions ->
            HomeTabIndicator(tabPositions, tabPage)
        }
    ) {
        HomeTab(
            icon = Icons.Default.Home,
            title = "HOME",
            onClick = { onTabSelected(TabPage2.Home) },
        )
        HomeTab(
            icon = Icons.Default.AccountBox,
            title = "WORK",
            onClick = { onTabSelected(TabPage2.Work) }
        )
    }
}

@Composable
private fun HomeTab( // Home이랑 Work의 상자 부분을 그리는 함수
    icon: ImageVector,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title)
    }
}

@Composable
private fun HomeTabIndicator(
    tabPosition: List<TabPosition>,
    tabPage: TabPage2
) {
    val indicatorLeft = tabPosition[tabPage.ordinal].left
    val indicatorRight = tabPosition[tabPage.ordinal].right
    val color = if (tabPage == TabPage2.Home) Purple700 else Green800

    Box(
        Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(x = indicatorLeft)
            .width(indicatorRight - indicatorLeft)
            .padding(4.dp)
            .fillMaxSize()
            .border(
                BorderStroke(2.dp, color),
                RoundedCornerShape(4.dp)
            )
    )
}