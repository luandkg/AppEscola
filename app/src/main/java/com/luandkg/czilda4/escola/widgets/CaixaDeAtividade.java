package com.luandkg.czilda4.escola.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.luandkg.czilda4.utils.PaletaDeCores;

public class CaixaDeAtividade extends View {

    private int mViewWidth;
    private int mViewHeight;


    private int mTodas = 0;
    private int mZero = 0;
    private int mUm = 0;
    private int mDois = 0;
    private int mTres = 0;


    private final Paint mPaint;                 // Allocate paint outside onDraw to avoid unnecessary object creation

    public CaixaDeAtividade(Context context) {
        this(context, null);
    }

    public CaixaDeAtividade(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CaixaDeAtividade(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initMeasurments();
        desenhar(canvas);
    }

    private void initMeasurments() {
        mViewWidth = getWidth();
        mViewHeight = getHeight();
    }

    private void desenhar(Canvas canvas) {

        int height = mViewHeight;
        int width = mViewWidth;


        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        if (mTodas == 0) {
            mPaint.setColor(PaletaDeCores.CINZA);
            canvas.drawRect(new RectF(0, 0, width, height), mPaint);
        } else {

            mPaint.setColor(PaletaDeCores.VERMELHO);
            canvas.drawRect(new RectF(0, 0, width, height), mPaint);

            int tem =  mDois + mTres;

            if (tem > 0) {

                int parcela = width / mTodas;
                int tamanho = tem * parcela;

                mPaint.setColor(PaletaDeCores.VERDE);
                canvas.drawRect(new RectF(0, 0, tamanho, height), mPaint);


            } else {
                mPaint.setColor(PaletaDeCores.CINZA);
                canvas.drawRect(new RectF(0, 0, width, height), mPaint);
            }


        }


    }


    public void setMudar(int todas, int zero, int um, int dois, int tres) {

        mTodas = todas;
        mZero = zero;
        mUm = um;
        mDois = dois;
        mTres = tres;

        invalidate();
    }


}