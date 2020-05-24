package Page1_schedule;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hansol.spot_200510_hs.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

import Page3_1_1_1.Page3_1_1_1_Main;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

public class Page1_full_Schedule extends AppCompatActivity {

    Page1_full_ScheduleAdapter1 adapter1;
    ArrayList<Page1_Main.RecycleItem> All_items = new ArrayList<Page1_Main.RecycleItem>();

    //여행 일차를 알기 위함
    int dayNumber = 0 ;
    String db_key;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1_full_schedule);

        //앞에서 값을 받아온다.
        Intent get = getIntent();
        All_items = (ArrayList<Page1_Main.RecycleItem>)get.getSerializableExtra("schedule_data");
        dayNumber = get.getIntExtra("dayNumber", dayNumber);
        db_key = get.getStringExtra("key");


        String startDate = All_items.get(0).date;
        String endDate = All_items.get(All_items.size()-1).date;


        //날짜를 반영
        TextView textView = (TextView)findViewById(R.id.page1_full_schedule_date);
        textView.setText(startDate.substring(0,4) + "년 "
        + startDate.substring(4, 6) + "월 "
        + startDate.substring(6) + "일 ~ "
        + endDate.substring(4, 6) + "월 "
        + endDate.substring(6) + "일");


        RecyclerView recyclerView = findViewById(R.id.page1_full_schedule_recyclerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter1 = new Page1_full_ScheduleAdapter1(All_items, dayNumber);
        recyclerView.setAdapter(adapter1);


        //*******추가 05/24*********************************************************//
        //수정하기 버튼 누르면
        FloatingActionButton editBtn = (FloatingActionButton)findViewById(R.id.page1_full_schedule_editBtn);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page1_full_Schedule.this, Page3_1_1_1_Main.class);
                intent.putExtra("key", db_key);
                intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
    }
}
