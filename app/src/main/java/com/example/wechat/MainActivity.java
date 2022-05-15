package com.example.wechat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wechat.Adapters.FragmentAdapter;
import com.example.wechat.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth mAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
       binding.viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
       binding.tablayout.setupWithViewPager(binding.viewPager);
}
    public void onBackPressed()
    {

        finishAffinity();
        System.exit(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.groupChat:
               // Toast.makeText(this, "Group Chat is started", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,GroupChat.class);
                startActivity(intent);
                break;
            case R.id.settings:
                Intent intent2=new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent2);
             //   Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.logout:
               mAuth.signOut();
                Intent intent1 =new Intent(MainActivity.this,SignInActivity.class);
                startActivity(intent1);
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}