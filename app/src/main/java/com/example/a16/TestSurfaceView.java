package com.example.a16;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

class TestSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    public TestSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }
    float x, y, r;
    Boolean started = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        System.out.println(x+" "+y+"asd");
        r = 0;
        started = true;
        return false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }
    Thread drawthread;

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawthread = new Thread(){
            Paint p = new Paint();

            @Override
            public void run() {
                p.setColor(Color.YELLOW);
                while (true) {
                    if (started) {
                        Canvas canvas = getHolder().lockCanvas();
                        canvas.drawColor(Color.BLUE);
                        canvas.drawCircle(x, y, r, p);
                        getHolder().unlockCanvasAndPost(canvas);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        r += 5;
                    }
                }
            }
        };
        drawthread.start();
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }
}