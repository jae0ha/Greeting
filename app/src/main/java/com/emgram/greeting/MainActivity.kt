package com.emgram.greeting

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.emgram.greeting.ui.theme.GreetingTheme
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emgram.greeting.ui.adapters.LocationAdapter
import com.emgram.greeting.data.Location

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // 버튼 참조
        // activity_main 에서 정의한 지도 이동 버튼
        val buttonMap = findViewById<Button>(R.id.button_open_map)

        // 버튼클릭 리스너 설정
        buttonMap.setOnClickListener {
            // MapAcitivity로 이동하는 인텐트 생성
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        /*setContent {
            GreetingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }*/

        /*setContentView(R.layout.recylce_exam)

        // RecyclerView 인스턴스를 가져옵니다.
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView2)

        // LayoutManager를 설정합니다.
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Adapter를 설정합니다.
        val locationAdapter = LocationAdapter(sampleLocations)
        recyclerView.adapter = locationAdapter*/
    }
}

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
    GreetingTheme {
        Greeting("Android")
    }
}

// 제주도 내 실제 관광 명소 위치 데이터를 생성하는 함수
fun createRealJejuLocationData(): List<Location> {
    val jejuLocations = listOf(
        Location(1, "제주국제공항", 33.51135, 126.49277, System.currentTimeMillis()),
        Location(2, "한라산", 33.36250, 126.53369, System.currentTimeMillis()),
        Location(3, "성산일출봉", 33.45804, 126.94260, System.currentTimeMillis()),
        Location(4, "만장굴", 33.52873, 126.77163, System.currentTimeMillis()),
        Location(5, "우도", 33.50649, 126.95300, System.currentTimeMillis()),
        Location(6, "천지연폭포", 33.24605, 126.55432, System.currentTimeMillis()),
        Location(7, "중문관광단지", 33.25468, 126.41256, System.currentTimeMillis()),
        Location(8, "섭지코지", 33.43411, 126.92770, System.currentTimeMillis()),
        Location(9, "한림공원", 33.39656, 126.23961, System.currentTimeMillis()),
        Location(10, "비자림", 33.48894, 126.80959, System.currentTimeMillis()),
        Location(11, "오설록 티 뮤지엄", 33.30599, 126.28947, System.currentTimeMillis()),
        Location(12, "사려니숲길", 33.42263, 126.62675, System.currentTimeMillis()),
        Location(13, "제주올레길", 33.48902, 126.49830, System.currentTimeMillis()),
        Location(14, "에코랜드", 33.44785, 126.67157, System.currentTimeMillis()),
        Location(15, "테디베어뮤지엄", 33.25046, 126.41457, System.currentTimeMillis()),
        Location(16, "카멜리아 힐", 33.28959, 126.37053, System.currentTimeMillis()),
        Location(17, "협재해수욕장", 33.39363, 126.23986, System.currentTimeMillis()),
        Location(18, "함덕해수욕장", 33.54306, 126.66822, System.currentTimeMillis()),
        Location(19, "소인국테마파크", 33.25318, 126.40823, System.currentTimeMillis()),
        Location(20, "이호테우해변", 33.47772, 126.45677, System.currentTimeMillis()),
        Location(21, "정방폭포", 33.23700, 126.62005, System.currentTimeMillis()),
        Location(22, "마라도", 33.11881, 126.26855, System.currentTimeMillis()),
        Location(23, "산방산", 33.24681, 126.57082, System.currentTimeMillis())
    )

    return jejuLocations
}

// 예제 데이터 사용
val sampleLocations = createRealJejuLocationData()

fun test() {
    sampleLocations.forEach { println(it) }
}