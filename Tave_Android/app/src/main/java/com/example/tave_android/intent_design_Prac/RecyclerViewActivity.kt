package com.example.tave_android.intent_design_Prac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tave_android.databinding.ActivityMainBinding
import com.example.tave_android.databinding.ActivityRecyclerViewBinding
import com.example.tave_android.databinding.ItemRecyclerBinding
import com.example.tave_android.model.Memo
import java.text.SimpleDateFormat

class RecyclerViewActivity : AppCompatActivity() {

    private val binding by lazy { ActivityRecyclerViewBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 1. 데이터를 불러온다
        val data = loadData()
        // 2. 아답터를 생성
        val customAdapter = CustomAdapter(data)
        // 3. 화면의 RecyclerView와 연결
        binding.recyclerView.adapter = customAdapter
        // 4. 레이아웃 매니저 설정
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun loadData() : MutableList<Memo> {
        val memoList = mutableListOf<Memo>()
        for(no in 1..50){
            val title = "이것이 안드로이드다 $no"
            val date = System.currentTimeMillis()
            val memo = Memo(no, title, date)
            memoList.add(memo)
        }
        return memoList
    }
}

class CustomAdapter(val listData: MutableList<Memo>) : RecyclerView.Adapter<CustomAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }
    override fun getItemCount(): Int =  listData.size

    override fun onBindViewHolder(holder: Holder, position: Int) { //스크롤 할 시, 1~30보여주고 스크롤 후 31번 보여줌 (데이터를 돌려서 씀)
        //1. 사용할 데이터를 꺼내고
        val memo = listData.get(position)
        //2. 홀더에 데이터를 전달
        holder.setMemo(memo)
    }

    class Holder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {
        lateinit var currentMemo: Memo

        //클릭처리는 init 에서만 한다
        init {
            binding.root.setOnClickListener{
                Toast.makeText(binding.root.context,"클릭된 아이템 : ${currentMemo.title}", Toast.LENGTH_SHORT).show()
            }
        }

        //3. 받은 데이터를 화면에 출력한다.
        fun setMemo(memo: Memo) {
            currentMemo = memo
            // 이곳에 binding.ro에t.setOnClickListener를 하면 자원이 낭비되면서 계속 데이터를 불러올 수 있음
            with(binding){
                textNo.text = "${memo.no}"
                textTitle.text = memo.title

                val sdf = SimpleDateFormat("yyyy-MM-dd")
                val formattedDate = sdf.format(memo.timestamp)
                textDate.text = formattedDate
            }

        }
    }
}

/*
Adapter란?
- 하나의 Object(객체)로서, 보여지는 View와 그 View에 올릴 Data를 연결하는 일종의 Bridge이다.
- 즉, 데이터의 원본을 받아 관리하고, 어댑터뷰가 출력할 수 있는 형태로 데이터를 제공하는 중간 객체 역할을 함
- ArrayAdapter, CursorAdapter, SimpleAdapter 등 있다.
- 어댑터 뷰가 출력할 수 있는 데이터로 만들어 놓는 공간 의미 , 어댑터 뷰는 데이터를 출력하는 역할
- 어댑터와 연결된 원본 데이터가 변경되면 notifyDataSetChanged 메서드를 호출하여 어댑터 뷰에 원본이 변경되었다고 알려주어 어댑터 뷰가 다시 그림 그리도록 해야함함

Adapter View란?
- 많은 정보를 효과적으로 처리하기 위해, View에 직접 정보를 주입하지 않고, Adapter라는 중간 매개체 이용
- ViewGruop 을 상속받으므로, 내부적으로 많은 뷰들을 담을 수 있음
- ListView, GridView, Spinner, Gallery 등이 있음

*/