package com.example.algorithmsvisualizer

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.algorithmsvisualizer.data.model.Algorithm
import com.example.algorithmsvisualizer.events.AppEvents
import com.example.algorithmsvisualizer.navigation.NavigationRout
import com.example.algorithmsvisualizer.viewmodel.AlgorithmViewModel

@Composable
fun AlgorithmListScreen(
    navController: NavController,
    groupId: Int,
    viewModel: AlgorithmViewModel,
) {

    val algorithmList = viewModel.algorithmListState

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {



            Row(
                modifier = Modifier.fillMaxWidth().padding( vertical = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

//                Icon(
//                    imageVector = Icons.Default.ArrowBack,
//                    contentDescription = "Back",
//                    tint = MaterialTheme.colors.onSurface,
//                    modifier = Modifier.clickable {
//                        navController.navigateUp()
//                    }.padding(start = 5.dp)
//                )
                    SearchBar(
                        onTextChange = {}, modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(horizontal = 10.dp)
                    )
                }



        AlgorithmList(
            algorithmsItems = algorithmList.value,
            modifier = Modifier.padding(horizontal = 15.dp),
            onClick = {
                navController.navigate(NavigationRout.AlgorithmVisualizerScreen.rout + "/${it.algorithmId}")
                viewModel.onAlgorithmScreenAction(AppEvents.AlgorithmClick(it))
            })

    }

}


@Composable
fun AlgorithmList(
    modifier: Modifier = Modifier,
    algorithmsItems: List<Algorithm>,
    onClick: (Algorithm) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 10.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(algorithmsItems.size) {
            AlgorithmCard(
                image = painterResource(id = R.drawable.insertion_sort),
                algorithm = algorithmsItems[it],
                onClick = onClick,
            )
        }
    }
}

@Composable
fun AlgorithmCard(
    modifier: Modifier = Modifier,
    image: Painter,
    algorithm: Algorithm,
    titleColor: Color = MaterialTheme.colors.primary,
    descriptionTextColor: Color = MaterialTheme.colors.onSurface,
    onClick: (Algorithm) -> Unit,
) {

    Box(
        modifier = modifier
            .background(MaterialTheme.colors.surface)
            .clip(RoundedCornerShape(3.dp))
            .clickable { onClick(algorithm) }
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            Image(
                painter = image,
                contentDescription = algorithm.name,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .shadow(5.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = algorithm.name,
                color = titleColor,
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
            )


            Text(
                text = algorithm.generalInformation.trim(),
                color = descriptionTextColor,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                style = MaterialTheme.typography.h3,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

        }

    }

}