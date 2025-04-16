package br.com.listme.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import br.com.listme.R
import br.com.listme.data.model.List
import br.com.listme.viewmodel.ItemViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(itemViewModel: ItemViewModel, navController: NavHostController) {
    val tasks by itemViewModel.allTasks.collectAsState(initial = emptyList())
    var showDialog by remember { mutableStateOf(false) }
    var listToDelete by remember { mutableStateOf<List?>(null) }

    if (showDialog && listToDelete != null) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    itemViewModel.removeTask(listToDelete!!)
                    showDialog = false
                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            },
            text = { Text("Are you sure you want to delete this item?") }
        )
    }

    Scaffold(
        topBar = {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box {
                    TopAppBar(
                        title = { Text("List Me! App") },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .zIndex(0f)
                    )

                    Image(
                        painter = painterResource(R.drawable.logolions),
                        contentDescription = "pic",
                        modifier = Modifier
                            .align(Alignment.CenterEnd) // Or Alignment.TopCenter / TopStart etc.
                            .zIndex(10f)
                            .padding(30.dp, 0.dp)
                            .width(80.dp)
                            .height(80.dp)
                    )
                }


            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("addItem")
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Item")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { innerPadding ->
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(130.dp))
                Image(painter = painterResource(id = R.drawable.logohospital), contentDescription = "pic")
                if (tasks.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No items. All done! Congratulations.")
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        items(tasks) { task ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = task.name,
                                    modifier = Modifier.weight(1f),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Text(
                                    text = task.createdAt,
                                    modifier = Modifier.weight(1f),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                IconButton(onClick = {
                                    listToDelete = task
                                    showDialog = true
                                }) {
                                    Icon(
                                        Icons.Default.Delete,
                                        contentDescription = "Delete"
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    )
}
