package com.leinaro.recipes.ui.common


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leinaro.recipes.R

@Composable
fun SearchView(
    state: MutableState<TextFieldValue>,
    onQueryChange: (String) -> Unit = {},
) {
    Row {
        OutlinedTextField(
            value = state.value,
            onValueChange = { value ->
                state.value = value
                onQueryChange(value.text)
            },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Filled.Search,
                    contentDescription = stringResource(R.string.search_items)
                )
            },
            trailingIcon = {
                if (state.value != TextFieldValue("")) {
                    IconButton(
                        onClick = {
                            state.value = TextFieldValue("")
                            onQueryChange("")
                        }
                    ) {
                        Icon(
                            Filled.Close,
                            contentDescription = stringResource(R.string.clear_search),
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    }
                }
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("Arroz")) }
    SearchView(state = textState)
}