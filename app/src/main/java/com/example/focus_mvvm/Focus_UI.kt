package com.example.focus_mvvm

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Focus_UI(viewModel: FocusViewModel = viewModel()){
    val TODOS by viewModel.todos.collectAsState()
    Box(modifier = Modifier.fillMaxSize()){
        Column() {
            Top()
            Spacer(Modifier.height(20.dp))
            Middle(TODOS, viewModel)
        }
        Bottom()
    }
}

@Composable
fun Top(){
    Row(modifier = Modifier.fillMaxWidth().padding(22.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.List, contentDescription = "",
            modifier = Modifier.size(25.dp)
        )

        Spacer(Modifier.width(16.dp))

        Text("Tasks",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End) {
            Icon(Icons.Default.AccountBox, contentDescription = "", tint = Color.White,
                modifier = Modifier.background(color = Color.Black, RoundedCornerShape(8.dp)
                ).size(30.dp),

            )
        }
    }
}

@Composable
fun Middle(todo : List<Todos>, viewModel: FocusViewModel) {

    var showList by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "WORKSPACE",
            fontSize = 18.sp,
            color = Color.Gray
        )

        Spacer(Modifier.height(5.dp))

        Text(
            "Focus on the \n Present",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                modifier = Modifier
                    .width(40.dp)
                    .height(4.dp),
                color = Color.Gray
            )

            Spacer(Modifier.width(8.dp))

            Text(
                "3 active goals remaining",
                fontSize = 18.sp,
                color = Color.Gray
            )
        }

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {

            Button(
                onClick = {
                    showList = true
                    viewModel.fetchTodos()},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0XFFD6E4FF)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    "SHOW LIST",
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }

            Spacer(Modifier.width(30.dp))

            Button(
                onClick = {
                    showList = false
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE5E7EB)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    "HIDE LIST",
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
        }

        Spacer(Modifier.height(40.dp))

        if (showList){
        LazyColumn() {
            items(todo) { todo ->
                FocusList(todos = todo)
            }
            }
        }
    }
}

@Composable
fun FocusList(todos: Todos){
    Card(modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
        shape = RoundedCornerShape(10.dp),

        elevation = CardDefaults.cardElevation(4.dp)){
        Row(Modifier.padding(16.dp)) {
            Icon(Icons.Default.CheckCircle, contentDescription = "")

            Spacer(Modifier.width(12.dp))

            Column() {
                Text("${todos.title}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(Modifier.height(4.dp))
                Box(modifier = Modifier
                    .background(Color(0xFFE5E7EB), RoundedCornerShape(10.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp)){
                    Text("${todos.completed}",
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }

    Spacer(Modifier.height(20.dp))
}

@Composable
fun Bottom() {
    Row(Modifier.fillMaxSize(),
        verticalAlignment = Alignment.Bottom) {
        Row(
            Modifier.fillMaxWidth().background(Color.White)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Column(
                Modifier.background(Color(0XFFD6E4FF), RoundedCornerShape(12.dp))
                    .padding(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Default.Notifications, contentDescription = "",
                    Modifier.size(25.dp),
                    tint = Color.Gray
                )
                Text(
                    "Notification",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Default.Home, contentDescription = "",
                    Modifier.size(25.dp),
                    tint = Color.Gray
                )
                Text(
                    "Home",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Default.CheckCircle, contentDescription = "",
                    Modifier.size(25.dp),
                    tint = Color.Gray
                )
                Text(
                    "Focus",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Default.Person, contentDescription = "",
                    Modifier.size(25.dp),
                    tint = Color.Gray
                )
                Text(
                    "Profile" ,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

