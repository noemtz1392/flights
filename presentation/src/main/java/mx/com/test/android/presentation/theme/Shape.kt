/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mx.com.test.android.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val shapes = Shapes(
    small = RoundedCornerShape(percent = 50),
    medium = RoundedCornerShape(size = 0f),
    large = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 0.dp,
        bottomEnd = 0.dp,
        bottomStart = 16.dp
    )
)

val shapeFlightStatus = RoundedCornerShape(
    topStart = 8.dp,
    topEnd = 8.dp,
    bottomStart = 20.dp,
    bottomEnd = 20.dp
)

val shapeFlightDetail = RoundedCornerShape(
    topStart = 16.dp,
    topEnd = 16.dp,
    bottomStart = 0.dp,
    bottomEnd = 0.dp
)

val shapeDeparture = RoundedCornerShape(
    topStart = 8.dp,
    topEnd = 8.dp,
    bottomStart = 8.dp,
    bottomEnd = 8.dp
)