package com.creativeshare.registerme.activities_fragments.activities.home_activity.fragments.fragment_home.fragment_create_cv;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.creativeshare.registerme.R;
import com.creativeshare.registerme.activities_fragments.activities.home_activity.activity.Home_Activity;
import com.creativeshare.registerme.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;


public class Fragment_Create_Edit_Cv extends Fragment {
    private Home_Activity homeActivity;
    private String cuurent_language;
    private TabLayout tab;
    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private int image[];
    private ImageView im_back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_edit_cv, container, false);
        initView(view);
        return view;

    }

    private void initView(View view) {
        homeActivity = (Home_Activity) getActivity();
        Paper.init(homeActivity);
        cuurent_language = Paper.book().read("lang", Locale.getDefault().getLanguage());
        tab = view.findViewById(R.id.tab);
        pager = view.findViewById(R.id.pager);
        tab.setupWithViewPager(pager);
        im_back=view.findViewById(R.id.arrow);
        if(cuurent_language.equals("en")){
            im_back.setRotation(180.0f);
        }
        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeActivity.Back();
            }
        });
        pager.setOffscreenPageLimit(3);
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        image = new int[]{R.drawable.ic_about, R.drawable.ic_edt_profile};
        fragmentList.add(Fragment_Create_Cv.newInstance());
        fragmentList.add(Fragment_Edit_Cv.newInstance());

        titleList.add(getString(R.string.Create_Cv));
        titleList.add(getString(R.string.Edit_cv));
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.AddFragments(fragmentList);
adapter.AddTitles(titleList);
        pager.setAdapter(adapter);




    }
    public static Fragment_Create_Edit_Cv newInstance() {
        return new Fragment_Create_Edit_Cv();
    }

    private void createTabIcons() {
        LinearLayout[] tabs = new LinearLayout[titleList.size()];
        for (int i = 0; i < titleList.size(); i++) {
            tabs[i] = (LinearLayout) LayoutInflater.from(homeActivity).inflate(R.layout.tab_item, null);
            TextView tv_tab = tabs[i].findViewById(R.id.tv_tab);
            ImageView im_tab = tabs[i].findViewById(R.id.im_tab);
            if (i == 0) {
                tv_tab.setTextColor(getResources().getColor(R.color.colorAccent));
                im_tab.setColorFilter(getResources().getColor(R.color.colorAccent));
            } else {
                tv_tab.setTextColor(getResources().getColor(R.color.colorPrimary));
                im_tab.setColorFilter(getResources().getColor(R.color.colorPrimary));
            }
            tv_tab.setText(titleList.get(i));
            im_tab.setImageResource(image[i]);

            tab.getTabAt(i).setCustomView(tabs[i]);
            if(i==0){
                tab.getTabAt(i).getCustomView().setBackgroundColor(homeActivity.getResources().getColor(R.color.colorPrimary));
            }
            else {
                tab.getTabAt(i).getCustomView().setBackgroundColor(homeActivity.getResources().getColor(R.color.colorAccent));

            }
        }

    }



}
