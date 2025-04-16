package br.com.listme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.listme.data.repository.ListFirebaseRepository
import br.com.listme.ui.components.BottomBar
import br.com.listme.ui.screens.AddItemScreen
import br.com.listme.ui.screens.HomeScreen
import br.com.listme.ui.screens.LoginScreen
import br.com.listme.ui.screens.ProfileScreen
import br.com.listme.ui.theme.ListMeTheme
import br.com.listme.viewmodel.ItemViewModel
import br.com.listme.viewmodel.ListViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = ListFirebaseRepository() // Cria o repositório
        val itemViewModel: ItemViewModel by viewModels {
            ListViewModelFactory(repository)
        }

        setContent {
            ListMeTheme {
                TodoApp(itemViewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoApp(itemViewModel: ItemViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        },
        contentColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        NavigationHost(
            navController = navController,
            itemViewModel = itemViewModel, // Passa o ViewModel para a navegação
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    itemViewModel: ItemViewModel, // Adiciona o ViewModel
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = "home", modifier = modifier) {
        composable("home") { HomeScreen(itemViewModel, navController) } // Passa o ViewModel
        composable("profile") { ProfileScreen(onLogoutSuccess = { }) }
        composable("login") {
            LoginScreen(onLoginSuccess = {
                navController.navigate("home")
            })
        }
        composable("addItem") {
            AddItemScreen(
                itemViewModel,
                navController
            )
        } // Passa o ViewModel
    }
}

data class BottomNavigationItemData(val route: String, val label: String, val icon: ImageVector)