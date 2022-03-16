package com.example.helperfinance.ui.gallery;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.helperfinance.R;
import com.example.helperfinance.databinding.FragmentGalleryBinding;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private PieChart pieChart;
    private FloatingActionButton fab_main_btn;
    private FloatingActionButton fab_expense_btn;

    private TextView fab_expense_txt;

    private boolean isOpen=false;

    private Animation FadeOpen, FadeClose;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        pieChart = (PieChart) view.findViewById(R.id.text_gallery);
        setupPieChart();
        loadPieChartData();

        fab_main_btn=view.findViewById(R.id.fb_main_plus_btn);
        fab_expense_btn=view.findViewById(R.id.expense_Ft_btn);

        fab_expense_txt=view.findViewById(R.id.expense_ft_text);

        FadeOpen= AnimationUtils.loadAnimation(getActivity(),R.anim.fade_open);
        FadeClose= AnimationUtils.loadAnimation(getActivity(),R.anim.fade_close);

        fab_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isOpen) {

                    fab_expense_btn.startAnimation(FadeClose);
                    fab_expense_btn.setClickable(false);
                    fab_expense_txt.startAnimation(FadeClose);
                    fab_expense_txt.setClickable(false);
                    isOpen=false;

                } else {
                    fab_expense_btn.startAnimation(FadeOpen);
                    fab_expense_btn.setClickable(true);

                    fab_expense_txt.startAnimation(FadeOpen);
                    fab_expense_txt.setClickable(true);
                    isOpen=true;
                }

            }
        });

        return view;
    }


    private void setupPieChart(){
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(11);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Ваши расходы");
        pieChart.setCenterTextSize(22);
        pieChart.getDescription().setEnabled(false);


        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    private void loadPieChartData(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(0.2f, "Товары для дома"));
        entries.add(new PieEntry(0.26f, "Налоги"));
        entries.add(new PieEntry(0.13f, "Развлечения"));
        entries.add(new PieEntry(0.26f, "Подарки"));
        entries.add(new PieEntry(0.15f, "Продукты"));


        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS){
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS){
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Категория расходов");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(9f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
}