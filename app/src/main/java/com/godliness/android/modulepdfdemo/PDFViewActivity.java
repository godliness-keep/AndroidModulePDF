package com.godliness.android.modulepdfdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.godliness.android.modulepdfdemo.controller.PDFController;

import java.io.IOException;

public class PDFViewActivity extends AppCompatActivity {

    private PDFView mPdfView;
    private PDFController mController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);
        mPdfView = findViewById(R.id.pdfview);


        // 初始化控制器
        initController();

        // 加载pdf资源 getAssets().open("test-pdf.pdf")
//        try {
//            mController.fromStream(getAssets().open("test-pdf.pdf")).swipeHorizontal(true).load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        mController.onError(null);

    }

    private void initController() {
        mController = new PDFController(mPdfView);
    }

}
