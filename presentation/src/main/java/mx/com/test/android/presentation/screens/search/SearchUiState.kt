package mx.com.test.android.presentation.screens.search

data class SearchUiState(
    val foundResults: Boolean = false,
    val isSearching: Boolean = false,
    val message: String = String()
)