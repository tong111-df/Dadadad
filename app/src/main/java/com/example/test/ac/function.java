package com.example.test.ac;

import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.test.R;
import com.example.test.fragment.friendsFragment;
import com.example.test.fragment.homeFragment;
import com.example.test.fragment.lotteryFragment;
import com.example.test.fragment.personFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class function extends AppCompatActivity {
    private homeFragment mhomeFragment;
    private friendsFragment mfriendsFragment;
    private lotteryFragment mlotteryFragment;
    private personFragment mpersonFragment;
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_function);


        mBottomNavigationView=findViewById(R.id.bottomna);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.home){
                    selectedFragment(0);
                }
                else if(item.getItemId()==R.id.friends){
                    selectedFragment(1);
                }
                else if(item.getItemId()==R.id.lottery){
                    selectedFragment(2);
                }
                else{
                    selectedFragment(3);
                }
                return true;
            }
        });
        selectedFragment(1);
    }

    private void selectedFragment(int position){
        androidx.fragment.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(fragmentTransaction);
        if(position==0){
            if(mhomeFragment==null){
                mhomeFragment=new homeFragment();
                fragmentTransaction.add(R.id.content,mhomeFragment);
            }
            else{
                fragmentTransaction.show(mhomeFragment);
            }
        }
        else if(position==1){
            if(mfriendsFragment==null){
                mfriendsFragment=new friendsFragment();
                fragmentTransaction.add(R.id.content,mfriendsFragment);
            }
            else{
                fragmentTransaction.show(mfriendsFragment);
            }
        }
        else if(position==2){
            if(mlotteryFragment==null){
                mlotteryFragment=new lotteryFragment();
                fragmentTransaction.add(R.id.content,mlotteryFragment);
            }
            else{
                fragmentTransaction.show(mlotteryFragment);
            }
        }
        else{
            if(mpersonFragment==null){
                mpersonFragment=new personFragment();
                fragmentTransaction.add(R.id.content,mpersonFragment);
            }
            else{
                fragmentTransaction.show(mpersonFragment);
            }
        }
        fragmentTransaction.commit();
    }
    private void hideFragment(FragmentTransaction fragmentTransaction){
        if(mhomeFragment!=null){
            fragmentTransaction.hide(mhomeFragment);
        }
        if(mfriendsFragment!=null){
            fragmentTransaction.hide(mfriendsFragment);
        }
        if(mlotteryFragment!=null){
            fragmentTransaction.hide(mlotteryFragment);
        }
        if(mpersonFragment!=null){
            fragmentTransaction.hide(mpersonFragment);
        }
    }
}