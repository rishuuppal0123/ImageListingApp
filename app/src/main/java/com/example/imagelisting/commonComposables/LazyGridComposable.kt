package com.example.imagelisting.commonComposables

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

//@Composable
//fun LazyGridComposable(
//    modifier: Modifier = Modifier,
//    numCols: Int,
//    gridCells: GridCells = GridCells.Fixed(numCols),
//    state: LazyGridState = rememberLazyGridState(),
//    context: Context = LocalContext.current,
//    itemCount: Int?,
//    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
//    contentPadding: PaddingValues = PaddingValues(0.dp),
//    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(12.dp),
//    loadState: CombinedLoadStates?,
//    retry: () -> Unit,
//    initialLoad: @Composable (() -> Unit) = { Loader() },
//    errorContent: @Composable (() -> Unit) = {
//        RetryLoad(
//            errorMessage = getErrorMessage(context = context, loadState = loadState),
//            retry = retry
//        )
//    },
//    content: LazyGridScope.() -> Unit,
//    endOfList: @Composable (() -> Unit) = {},
//    emptyContent: @Composable () -> Unit,
//) {
//    Box(modifier = modifier) {
//        if ((itemCount != null && itemCount == 0) && loadState?.append is LoadState.NotLoading && loadState.append.endOfPaginationReached) {
//            emptyContent()
//        } else {
//            LazyVerticalGrid(
//                columns = gridCells,
//                state = state,
//                contentPadding = contentPadding,
//                verticalArrangement = verticalArrangement,
//                horizontalArrangement = horizontalArrangement,
//                modifier = Modifier.fillMaxSize(),
//                content = {
//                    content()
//                    item(span = { GridItemSpan(numCols) }) {
//                        PagingLoadingComposable(
//                            loadState = loadState,
//                            initialLoad = initialLoad,
//                            errorContent = errorContent,
//                            endOfList = endOfList
//                        )
//                    }
//                })
//        }
//    }
//}