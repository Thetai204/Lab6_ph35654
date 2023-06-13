package com.example.lab6_ph35654;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class Appdeter_sinhvien extends AppCompatActivity {
    ArrayList<item_DSsinhvien> ds = new ArrayList<>();
    ListView lv_sv;
    ADTNN adtnn;
    String ten = "1";
    String diaChi = "1";
    String coSo = "1";

    ActivityResultLauncher<Intent> updateSV = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==2){
                        Intent intent = result.getData();
                        Bundle bundle = intent.getExtras();

                        String ten = bundle.getString(Appdeter_lociton.KEY_TEN);
                        String diaDiem = bundle.getString(Appdeter_lociton.KEY_DIACHI);
                        String coSo = bundle.getString(Appdeter_lociton.KEY_COSO);

                        svmo.setName( ten);
                        svmo.setLocation(diaDiem);
                        svmo.setCoSo(coSo);

                        adtnn.notifyDataSetChanged();
                    }
                }
            }
    );
    ActivityResultLauncher<Intent> getData = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 2) {
                        Intent intent = result.getData();
                        Bundle bundle = intent.getExtras();
                        ten = bundle.getString("ten");
                        diaChi = bundle.getString("location");
                        coSo = bundle.getString("coSo");

                        ds.add(new item_DSsinhvien("Họ Tên: " + ten, "Địa chỉ: " + diaChi, "Fpoly: " + coSo));
                        adtnn = new ADTNN(Appdeter_sinhvien.this, ds);
                        lv_sv.setAdapter(adtnn);


                    }
                }
            }
    );
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add_persion) {
            Intent intent = new Intent(Appdeter_sinhvien.this,MainActivity.class);
            Intent intent1 = new Intent(Appdeter_sinhvien.this,MainActivity.class);
            intent.putExtra("update","Thêm sinh viên");
            setResult(2,intent1);
            getData.launch(intent);
        }else  if(item.getItemId()==R.id.bangDiem){
            Toast.makeText(this, "Đang trong quá trình phát triển", Toast.LENGTH_SHORT).show();

        }else if(item.getItemId()== R.id.dangXuat){

            Toast.makeText(this, "Bạn đã đăng xuất", Toast.LENGTH_SHORT).show();
            finish();
        }else if (item.getItemId()==R.id.dieDanh){
            Toast.makeText(this, "Đang trong quá trình phát triển", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId()==R.id.seach) {

        } else if (item.getItemId()==android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai2);
       Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv_sv= findViewById(R.id.lv_list);
            }



    private item_DSsinhvien svmo;
    private class ADTNN extends BaseAdapter implements com.example.lab6_ph35654.ADTNN {
        Activity activity;
        ArrayList<item_DSsinhvien> sv;
        ArrayList<item_DSsinhvien> sv1 ;
        public ADTNN(Activity activity, ArrayList<item_DSsinhvien> sv, ArrayList<item_DSsinhvien> sv1) {
            this.activity = activity;
            this.sv = sv;
            this.sv1 = sv1;
        }
        public ADTNN(Activity activity, ArrayList<item_DSsinhvien> sv) {
            this.activity = activity;
            this.sv = sv;
        }


        @Override
        public int getCount() {
            return sv.size();
        }

        @Override
        public Object getItem(int position) {
            return sv.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_danhsach, parent, false);

            item_DSsinhvien svModle = sv.get(position);
            TextView tvName = convertView.findViewById(R.id.tv_hoTen);
            TextView tvdiachi = convertView.findViewById(R.id.tv_diaChi);
            TextView tvCoSo = convertView.findViewById(R.id.tv_coSo);
            Button xoa = convertView.findViewById(R.id.btn_delete);
            Button update = convertView.findViewById(R.id.btn_update);

            tvName.setText(svModle.getName());
            tvdiachi.setText(svModle.getLocation());
            tvCoSo.setText(svModle.getCoSo());

            xoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sv.remove(position);
                    adtnn.notifyDataSetChanged();
                }
            });
            update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){

                Intent intent = new Intent(Appdeter_sinhvien.this, MainActivity.class);

                intent.putExtra(Appdeter_lociton.KEY_SV, "Sửa sinh viên");
                setResult(2, intent);
                svmo = sv.get(position);


                updateSV.launch(intent);


            }

        });



            return convertView;
    }

    @Override
    public Filter getFilter() {


        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String s = constraint.toString();
                if (s.isEmpty()) {
                    sv = sv1;
                } else {
                    ArrayList<item_DSsinhvien> listS = new ArrayList<>();
                    for (item_DSsinhvien a : sv1) {
                        if (a.getName().toLowerCase().contains(s.toLowerCase())) {
                            listS.add(a);
                        }
                    }
                    sv = listS;
                }
                FilterResults FilterResults = new FilterResults();
                FilterResults.values = sv;

                return FilterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                sv = (ArrayList<item_DSsinhvien>) results.values;
                notifyDataSetChanged();
            }
        };
    }
 }
}