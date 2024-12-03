package com.example.test.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.ac.MainActivity;
import com.example.test.ac.callActivity;
import com.example.test.ac.rewritePwdActivity;
import com.example.test.db.UserDbHelper;
import com.example.test.db.lotteryDbHelper;
import com.example.test.entity.UserInfo;
import com.example.test.entity.lotteryInfo;

import java.util.Date;
import java.util.Random;

public class lotteryFragment extends Fragment {

    private TextView da,shi,zui,bang,de;
    private TextView tv_lottery_username;
    private View rootView;
    private static final int[] numbers={1,2,3,4,5};
    private static final int[] weights={1,11,21,31,36};
    private static final int total=100;
    private static final Random RANDOM=new Random();

    private boolean isButtonClickable = true; // 初始化为true，表示按钮可以点击
    private Handler handler = new Handler(); // 用于设置延迟

    UserInfo userInfo = UserInfo.getsUserInfo();
    String username = userInfo.getUsername();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView=inflater.inflate(R.layout.fragment_lottery, container, false);
        tv_lottery_username=rootView.findViewById(R.id.tv_lottery_username);

        da=rootView.findViewById(R.id.da_cnt);
        shi=rootView.findViewById(R.id.shi_cnt);
        zui=rootView.findViewById(R.id.zui_cnt);
        bang=rootView.findViewById(R.id.bang_cnt);
        de=rootView.findViewById(R.id.de_cnt);
        lotteryInfo seek=lotteryDbHelper.getInstance(getContext()).seek(username);
        lotteryInfo.setSlotteryInfo(seek);

        rootView.findViewById(R.id.btn_choujiang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lotteryDbHelper.getInstance(getContext()).delete("2");

                /*int dada= seek.getDacnt();
                int shishi=seek.getShicnt();
                int zuizui=seek.getZuicnt();
                int bangbang=seek.getBangcnt();
                int dede=seek.getDecnt();*/

                if (!isButtonClickable) {
                    Toast.makeText(getContext(),"当前在冷却中，请稍后尝试",Toast.LENGTH_SHORT).show();
                    // 如果按钮不可点击，则不执行任何操作
                    return;
                }
                isButtonClickable = false;

                int dada=Integer.parseInt(da.getText().toString());
                int shishi=Integer.parseInt(shi.getText().toString());
                int zuizui=Integer.parseInt(zui.getText().toString());
                int bangbang=Integer.parseInt(bang.getText().toString());
                int dede=Integer.parseInt(de.getText().toString());
                int s=generateWeightedRandomNumber();
                if(s==1) dada++;
                else if (s==2) {
                    shishi++;
                } else if (s==3) {
                    zuizui++;
                } else if (s==4) {
                    bangbang++;
                }else if(s==5) {
                    dede++;
                }
                if(dada!=0&&shishi!=0&&zuizui!=0&&bangbang!=0&&dede!=0){
                    new AlertDialog.Builder(getContext())
                            .setTitle("恭喜！！！")
                            .setMessage("恭喜您获得与大共进早餐的机会，当前即将清空，记得截屏哦 >_< <_>")
                            .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // getActivity().finish();
                                    Intent intent=new Intent(getActivity(), callActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .setPositiveButton("迫不及待了", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //getActivity().finish();
                                    Intent intent=new Intent(getActivity(), callActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .show();
                    dada=0;shishi=0;zuizui=0;bangbang=0;dede=0;
                }
                int row = lotteryDbHelper.getInstance(getContext()).updateCnt(username,dada,shishi,zuizui,bangbang,dede);
                if(row>0){
                    Toast.makeText(getContext(),"恭喜您抽奖成功",Toast.LENGTH_SHORT).show();
                }

                String sda=Integer.toString(dada);
                String sshi=Integer.toString(shishi);
                String szui=Integer.toString(zuizui);
                String sbang=Integer.toString(bangbang);
                String sde=Integer.toString(dede);
                da.setText(sda);
                shi.setText(sshi);
                zui.setText(szui);
                bang.setText(sbang);
                de.setText(sde);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isButtonClickable = true;
                        // 可以在这里更新按钮的UI状态，比如启用或禁用按钮的视觉效果
                        // 但由于你是通过逻辑控制点击，所以这一步通常是可选的
                    }
                }, 43200000);

                /*int dada= seek.getDacnt();
                int shishi=seek.getShicnt();
                int zuizui=seek.getZuicnt();
                int bangbang=seek.getBangcnt();
                int dede=seek.getDecnt();
                 */
                /*int row = lotteryDbHelper.getInstance(getContext()).updateCnt(username,dada,shishi,zuizui,bangbang,dede);
                if(row>0){
                    Toast.makeText(getContext(),"恭喜您抽奖成功",Toast.LENGTH_SHORT).show();
                }*/


            }
        });
        /*
         *2024.11.25
         * ！！！！！！！！！！！！！！！！！！！！！！！！写到这了！！！！！！！！！！！！！！！！！！！！！！！！！
         *明天需要记得仿照register，已经创好了数据库，接下来准备进行修改，修改时候根据时间daa等+1，然后修改成功
         *如果到一个数就跳出来联系方式，进行截图，加油！！！
         *2024.11.26
         * byd被这个地方卡了整整一天了，
         * ！！！好像应该在注册时候就添加进数据库 但已经23：45了
         * 2024.11.27
         * 又是这里弄了一天，但今天我太nb了
         */
        // Inflate the layout for this fragment

        return rootView;
    }
    //生成随机来获得数字，肯定没问题
   public static int generateWeightedRandomNumber() {

        int randomIndex = RANDOM.nextInt(total);
        int cumulativeWeight = 0;

        // 通过累加权重来确定随机数应该映射到哪个数字
        for (int i = 0; i < weights.length; i++) {
            cumulativeWeight += weights[i];
            if (randomIndex < cumulativeWeight) {
                return numbers[i]; // 直接返回对应的数字
            }
        }

        throw new IllegalStateException("Random index out of expected range");
    }




    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lotteryInfo lottery_Info = lotteryInfo.getSlotteryInfo();
        UserInfo userInfo = UserInfo.getsUserInfo();
        if(lottery_Info!=null) {
            da.setText(Integer.toString(lottery_Info.getDacnt()));
            shi.setText(Integer.toString(lottery_Info.getShicnt()));
            zui.setText(Integer.toString(lottery_Info.getZuicnt()));
            bang.setText(Integer.toString(lottery_Info.getBangcnt()));
            de.setText(Integer.toString(lottery_Info.getDecnt()));
        }
        if(userInfo!=null){
            tv_lottery_username.setText(userInfo.getUsername());
        }
    }

}