    package com.example.lab6_ph35654;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.bai1);
            ArrayList<item_coso> location1 = new ArrayList<>();
            Spinner spinner = findViewById(R.id.sp_Location);

            location1.add(new item_coso("Hà nội",R.drawable.hanoi));
            location1.add(new item_coso("Hồ chí minh",R.drawable.hcm));
            location1.add(new item_coso("Tây nguyên",R.drawable.taynguyen));
            location1.add(new item_coso("Cần thơ",R.drawable.download));
            location1.add(new item_coso("Đà nẵng",R.drawable.danang));

            Appdeter_lociton adapter = new Appdeter_lociton(this,location1);
            spinner.setAdapter(adapter);

            Button button = findViewById(R.id.btn_login);
            EditText ten = findViewById(R.id.txt_ten);
            EditText diachi = findViewById(R.id.txt_diachia);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = spinner.getSelectedItemPosition();
                    String sTen = ten.getText().toString();
                    String sDiachi = diachi.getText().toString();
                    String sCoSo = location1.get(index).getName();
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("ten",sTen);
                    bundle.putString("location",sDiachi);
                    bundle.putString("coSo",sCoSo);
                    intent.putExtras(bundle);
                    setResult(2,intent);
                    if(!sTen.equals("")&&!sDiachi.equals("")&&!sCoSo.equals("")){
                        finish();
                    }else {
                        Toast.makeText(MainActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }
    }