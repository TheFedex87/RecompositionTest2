package it.bytener.recompositiontest2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.bytener.recompositiontest2.ui.theme.RecompositionTest2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecompositionTest2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var isChecked by remember {
                        mutableStateOf(false)
                    }
                    var state by remember {
                        mutableStateOf(
                            ContactsListState(
                                isLoading = false,
                                names = listOf("John Doo")
                            )
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                    ) {
                        Checkbox(checked = isChecked, onCheckedChange = { isChecked = !isChecked })
                        Button(onClick = {
                            state = state.copy(
                                isLoading = !state.isLoading
                            )
                        }) {
                            Text(text = "Change is loading")
                        }
                        ContactsList(
                            state = state
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ContactsList(
    state: ContactsListState
) {
    Box {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Text(text = state.names.toString())
        }
    }
}

@Immutable
data class ContactsListState(
    val isLoading: Boolean,
    val names: List<String>
)

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecompositionTest2Theme {
        Greeting("Android")
    }
}