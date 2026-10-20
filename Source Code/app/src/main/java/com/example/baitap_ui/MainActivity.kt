package com.example.baitap_ui


import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                UIDemoApp()
            }
        }
    }
}




@Composable
fun UIDemoApp() {
    var currentPage by remember {
        mutableStateOf("Welcome")
    }

    var selectedComponent by remember {
        mutableStateOf("Text")
    }
    BackHandler (enabled = currentPage != "Welcome") {
        currentPage = when (currentPage) {
            "Chi tiết" -> "Danh sách UI"
            else -> "Welcome"
        }
    }
    when (currentPage) {
        "Welcome" -> WelcomeScreen {
            currentPage = "Danh sách UI"
        }

        "Danh sách UI" -> HomeScreen(
            onBack = {
                currentPage = "Welcome"
            },
            onOpen = { name ->
                selectedComponent = name
                currentPage = "Chi tiết"
            }
        )

        "Chi tiết" -> ComponentDetailScreen(
            name = selectedComponent,
            onBack = {
                currentPage = "Danh sách UI"
            }
        )
    }
}





@Composable
fun HomeScreen(
    onBack: () -> Unit,
    onOpen: (String) -> Unit
) {
    val groups = listOf(
        "Display" to listOf("Text", "Image"),
        "Input" to listOf(
            "TextField", "PasswordField",
            "Toggle", "Slider", "DatePicker"
        ),
        "Selection" to listOf(
            "Button", "Checkbox", "RadioButton", "Picker / Menu"
        ),
        "Navigation" to listOf(
            "TabBar", "NavigationBar"
        ),
        "Layout" to listOf("Column", "Row"),
        "Content" to listOf("List", "Card", "Divider")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FB))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = onBack) {
                Text("← Quay lại")
            }

            Text(
                text = "UI Components",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3)
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(groups) { group ->
                Text(
                    text = group.first,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                group.second.forEach { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp)
                            .clickable {
                                onOpen(item)
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFD9EDFF)
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = item,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Color(0xFF17466D)
                                )

                                Text(
                                    text = when (item) {
                                        "Text" -> "Hiển thị văn bản"
                                        "Image" -> "Hiển thị hình ảnh"
                                        "TextField" -> "Nhập nội dung"
                                        "PasswordField" -> "Nhập mật khẩu"
                                        "Button" -> "Nút thực hiện hành động"
                                        "Checkbox" -> "Chọn nhiều tùy chọn"
                                        "RadioButton" -> "Chọn một tùy chọn"
                                        "Toggle" -> "Bật hoặc tắt"
                                        "Slider" -> "Chọn giá trị theo thanh trượt"
                                        "DatePicker" -> "Chọn ngày"
                                        "Picker / Menu" -> "Chọn mục trong menu"
                                        "Column" -> "Sắp xếp theo chiều dọc"
                                        "Row" -> "Sắp xếp theo chiều ngang"
                                        "TabBar" -> "Chuyển đổi các tab"
                                        "NavigationBar" -> "Thanh điều hướng"
                                        "List" -> "Danh sách dữ liệu"
                                        "Card" -> "Thẻ nội dung"
                                        else -> "Phân chia nội dung"
                                    },
                                    fontSize = 13.sp,
                                    color = Color.DarkGray
                                )
                            }

                            Text(
                                text = "›",
                                fontSize = 28.sp,
                                color = Color(0xFF2196F3)
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun TextDetail() {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Text Detail", fontSize = 24.sp)

        Text("Đây là văn bản bình thường")

        Text(
            "Đây là chữ in đậm",
            fontWeight = FontWeight.Bold
        )

        Text(
            "Đây là chữ màu xanh",
            color = Color(0xFF2196F3)
        )

        Text(
            "Đây là chữ lớn",
            fontSize = 30.sp
        )
    }
}

@Composable
fun ImageDetail() {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Image Detail", fontSize = 24.sp)

        Image(
            painter = painterResource(
                id = android.R.drawable.ic_menu_gallery
            ),
            contentDescription = "Ảnh minh họa",
            modifier = Modifier.size(150.dp)
        )

        Text("Image dùng để hiển thị hình ảnh")
    }
}

@Composable
fun InputDetail(isPassword: Boolean) {
    var value by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            if (isPassword) "PasswordField" else "TextField",
            fontSize = 24.sp
        )

        OutlinedTextField(
            value = value,
            onValueChange = { value = it },
            label = {
                Text(
                    if (isPassword) "Mật khẩu"
                    else "Nhập nội dung"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation =
                if (isPassword) PasswordVisualTransformation()
                else androidx.compose.ui.text.input.VisualTransformation.None
        )

        Text("Bạn đã nhập: " + if (isPassword) "••••••" else value)
    }
}
@Composable
fun ButtonDetail() {
    var message by remember { mutableStateOf("Chưa bấm nút") }

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Button Detail", fontSize = 24.sp)

        Button(onClick = {
            message = "Bạn đã bấm Button"
        }) {
            Text("Button")
        }

        OutlinedButton(onClick = {
            message = "Bạn đã bấm OutlinedButton"
        }) {
            Text("Outlined Button")
        }

        TextButton(onClick = {
            message = "Bạn đã bấm TextButton"
        }) {
            Text("Text Button")
        }

        Text(message)
    }
}

    @Composable
    fun CheckboxDetail() {
        var checked by remember { mutableStateOf(false) }

        Column(modifier = Modifier.padding(20.dp)) {
            Text("Checkbox Detail", fontSize = 24.sp)

            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it }
                )

                Text("Tôi đồng ý")
            }

            Text(if (checked) "Đã chọn" else "Chưa chọn")
        }
    }

    @Composable
    fun RadioDetail() {
        var selected by remember { mutableStateOf("Kotlin") }

        Column(modifier = Modifier.padding(20.dp)) {
            Text("RadioButton", fontSize = 24.sp)

            listOf("Kotlin", "Java", "Python").forEach { option ->
                Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selected == option,
                        onClick = { selected = option }
                    )

                    Text(option)
                }
            }

            Text("Bạn chọn: $selected")
        }
    }

    @Composable
    fun SwitchDetail() {
        var enabled by remember { mutableStateOf(false) }

        Column(modifier = Modifier.padding(20.dp)) {
            Text("Switch / Toggle", fontSize = 24.sp)

            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Text("Bật thông báo")

                Spacer(modifier = Modifier.width(12.dp))

                Switch(
                    checked = enabled,
                    onCheckedChange = { enabled = it }
                )
            }

            Text(if (enabled) "Đang bật" else "Đang tắt")
        }
    }

    @Composable
    fun SliderDetail() {
        var value by remember { mutableStateOf(50f) }

        Column(modifier = Modifier.padding(20.dp)) {
            Text("Slider Detail", fontSize = 24.sp)

            Slider(
                value = value,
                onValueChange = { value = it },
                valueRange = 0f..100f
            )

            Text("Giá trị: ${value.toInt()}")
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DatePickerDetail() {
        var showPicker by remember { mutableStateOf(false) }

        val dateState = rememberDatePickerState()

        Column(modifier = Modifier.padding(20.dp)) {
            Text("DatePicker Detail", fontSize = 24.sp)

            Button(onClick = { showPicker = true }) {
                Text("Chọn ngày")
            }

            Text(
                if (dateState.selectedDateMillis == null)
                    "Chưa chọn ngày"
                else
                    "Đã chọn ngày"
            )

            if (showPicker) {
                DatePickerDialog(
                    onDismissRequest = { showPicker = false },
                    confirmButton = {
                        TextButton(onClick = {
                            showPicker = false
                        }) {
                            Text("Xong")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            showPicker = false
                        }) {
                            Text("Hủy")
                        }
                    }
                ) {
                    DatePicker(state = dateState)
                }
            }
        }
    }

    @Composable
    fun MenuDetail() {
        var expanded by remember { mutableStateOf(false) }
        var selected by remember { mutableStateOf("Chưa chọn") }

        Column(modifier = Modifier.padding(20.dp)) {
            Text("Picker / DropdownMenu", fontSize = 24.sp)

            Box {
                Button(onClick = { expanded = true }) {
                    Text("Chọn tùy chọn")
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    listOf("Mục 1", "Mục 2", "Mục 3").forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item) },
                            onClick = {
                                selected = item
                                expanded = false
                            }
                        )
                    }
                }
            }

            Text("Đang chọn: $selected")
        }
    }


    @Composable
    fun ColumnDetail() {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Column: xếp dọc", fontSize = 24.sp)

            listOf("Mục 1", "Mục 2", "Mục 3").forEach { item ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        item,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }

    @Composable
    fun RowDetail() {
        Column(modifier = Modifier.padding(20.dp)) {
            Text("Row: xếp ngang", fontSize = 24.sp)

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("A", "B", "C").forEach { item ->
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFD5EAF9)
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp),
                            contentAlignment = androidx.compose.ui.Alignment.Center
                        ) {
                            Text(item)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun CardDetail() {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Card Detail", fontSize = 24.sp)

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Thẻ thông tin",
                        fontWeight = FontWeight.Bold
                    )
                    Text("Đây là phần nội dung bên trong Card.")
                }
            }
        }
    }



            @Composable
            fun ListDetail() {
                val data = listOf(
                    "Bài học 1",
                    "Bài học 2",
                    "Bài học 3",
                    "Bài học 4",
                    "Bài học 5"
                )

                Column(modifier = Modifier.padding(20.dp)) {
                    Text("List Detail", fontSize = 24.sp)

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    ) {
                        items(data) { item ->
                            ListItem(
                                headlineContent = { Text(item) },
                                supportingContent = { Text("Mô tả nội dung") }
                            )

                            HorizontalDivider()
                        }
                    }
                }
            }

    @Composable
    fun DividerDetail() {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Divider Detail", fontSize = 24.sp)

            Text("Phần thứ nhất")

            HorizontalDivider()

            Text("Phần thứ hai")
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TabsDetail() {
        val tabs = listOf("Tổng quan", "Bài học", "Thông tin")
        var selected by remember { mutableStateOf(0) }

        Column(modifier = Modifier.padding(16.dp)) {
            Text("TabBar Detail", fontSize = 24.sp)

            TabRow(selectedTabIndex = selected) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selected == index,
                        onClick = { selected = index },
                        text = { Text(title) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("Nội dung tab: ${tabs[selected]}")
        }
    }

    @Composable
    fun NavigationBarDetail() {
        val tabs = listOf("Home", "Search", "Profile")
        val symbols = listOf("⌂", "⌕", "☺")
        var selected by remember { mutableStateOf(0) }

        Column(modifier = Modifier.padding(16.dp)) {
            Text("NavigationBar Detail", fontSize = 24.sp)

            Spacer(modifier = Modifier.height(12.dp))

            NavigationBar {
                tabs.forEachIndexed { index, title ->
                    NavigationBarItem(
                        selected = selected == index,
                        onClick = { selected = index },
                        icon = { Text(symbols[index]) },
                        label = { Text(title) }
                    )
                }
            }

            Text("Bạn đang chọn: ${tabs[selected]}")
        }
    }


@Composable
fun WelcomeScreen(onReady: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "UI",
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Jetpack Compose",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Khám phá các thành phần trong giao diện Android",
                fontSize = 16.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Các kiểu Text, Button, Image, TextField và nhiều thành phần UI khác.",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }

        Button(
            onClick = onReady,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text(
                text = "Bắt đầu",

                fontSize = 18.sp
            )
        }
    }
}


@Composable
fun ComponentDetailScreen(
    name: String,
    onBack: () -> Unit
) {
    var text by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }
    var enabled by remember { mutableStateOf(true) }
    var sliderValue by remember { mutableFloatStateOf(50f) }
    var selected by remember { mutableIntStateOf(0) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FB))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = {
                    onBack()
                }
            ){
                Text("← Quay lại")
            }

            Text(
                text = name,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Ví dụ $name",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            when (name) {
                "Text" -> {
                    Text("Văn bản bình thường")
                    Text(
                        "Chữ lớn và đậm",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Chữ màu xanh",
                        color = Color(0xFF2196F3)
                    )
                    Text(
                        "Chữ gạch chân",
                        textDecoration = TextDecoration.Underline
                    )
                }

                "Image" -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .background(
                                Color(0xFFD9EDFF),
                                shape=RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "🖼️",
                            fontSize = 64.sp
                        )
                    }
                    Text("Vùng minh họa hình ảnh")
                }

                "TextField" -> {
                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Nhập họ tên") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text("Nội dung: $text")
                }

                "PasswordField" -> {
                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Mật khẩu") },
                        visualTransformation =
                            PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                "Button" -> {
                    Button(onClick = { checked = !checked }) {
                        Text("Bấm vào đây")
                    }
                    Text(
                        if (checked) "Bạn đã bấm nút!"
                        else "Hãy thử bấm nút."
                    )
                    OutlinedButton(onClick = { text = "Đã chọn" }) {
                        Text("Outlined Button")
                    }
                    TextButton(onClick = { text = "Text Button" }) {
                        Text("Text Button")
                    }
                    Text(text)
                }

                "Checkbox" -> {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = checked,
                            onCheckedChange = { checked = it }
                        )
                        Text("Tôi đồng ý")
                    }
                    Text(if (checked) "Đã chọn" else "Chưa chọn")
                }

                "RadioButton" -> {
                    listOf("Nam", "Nữ", "Khác").forEachIndexed { i, label ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = selected == i,
                                onClick = { selected = i }
                            )
                            Text(label)
                        }
                    }
                }

                "Toggle" -> {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Thông báo", modifier = Modifier.weight(1f))
                        Switch(
                            checked = enabled,
                            onCheckedChange = { enabled = it }
                        )
                    }
                    Text(if (enabled) "Đang bật" else "Đang tắt")
                }

                "Slider" -> {
                    Text("Giá trị: ${sliderValue.toInt()}")
                    Slider(
                        value = sliderValue,
                        onValueChange = { sliderValue = it },
                        valueRange = 0f..100f
                    )
                }

                "DatePicker" -> {
                    Text("Chọn ngày")
                    Button(onClick = { expanded = !expanded }) {
                        Text(if (expanded) "Đã chọn ngày mẫu" else "Chọn ngày")
                    }
                    if (expanded) {
                        Text("20/10/2026 (minh họa)")
                    }
                }

                "Picker / Menu" -> {
                    Box {
                        Button(onClick = { expanded = true }) {
                            Text("Mở menu")
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            listOf("Android", "Kotlin", "Compose").forEach {
                                DropdownMenuItem(
                                    text = { Text(it) },
                                    onClick = {
                                        text = it
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                    Text("Đã chọn: $text")
                }

                "Column" -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFD9EDFF))
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Phần tử 1")
                        Text("Phần tử 2")
                        Text("Phần tử 3")
                    }
                }

                "Row" -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFD9EDFF))
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text("A")
                        Text("B")
                        Text("C")
                    }
                }

                "TabBar" -> {
                    TabRow(selectedTabIndex = selected) {
                        listOf("Tổng quan", "Bài học", "Cá nhân")
                            .forEachIndexed { i, title ->
                                Tab(
                                    selected = selected == i,
                                    onClick = { selected = i },
                                    text = { Text(title) }
                                )
                            }
                    }
                    Text("Nội dung tab: ${selected + 1}")
                }

                "NavigationBar" -> {
                    NavigationBar {
                        listOf("Home", "Search", "Person")
                            .forEachIndexed { i, label ->
                                NavigationBarItem(
                                    selected = selected == i,
                                    onClick = { selected = i },
                                    icon = {
                                        Text(
                                            when (i) {
                                                0 -> "⌂"
                                                1 -> "⌕"
                                                else -> "●"
                                            },
                                            fontSize = 22.sp
                                        )
                                    },
                                    label = { Text(label) }
                                )
                            }
                    }
                }

                "List" -> {
                    listOf("Lập trình Kotlin", "Giao diện Android", "Jetpack Compose")
                        .forEach {
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                )
                            ) {
                                Text(
                                    text = "📘  $it",
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                }

                "Card" -> {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFD9EDFF)
                        )
                    ) {
                        Column(Modifier.padding(20.dp)) {
                            Text(
                                "Thẻ học tập",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            Text("Đây là nội dung bên trong Card.")
                            Button(onClick = { checked = !checked }) {
                                Text(if (checked) "Đã lưu" else "Lưu thẻ")
                            }
                        }
                    }
                }

                "Divider" -> {
                    Text("Nội dung phía trên")
                    HorizontalDivider(
                        thickness = 2.dp,
                        color = Color(0xFF2196F3)
                    )
                    Text("Nội dung phía dưới")
                }
            }
        }
    }
}
