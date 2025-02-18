package com.example.chatter.feature.auth.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.chatter.R

@Composable
fun SignInScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Email") }
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Password") }
            )
            Button(onClick = {}) {
                Text(text = "Sign In")
            }
        }
    }
}

@Preview
@Composable
fun SignInScreenPreview() {
    SignInScreen(navController = rememberNavController())
}