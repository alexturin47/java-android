package com.example.game_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    public static int WIDTH_DEVICE;
    public static int HEIGTH_DEVICE;
    private Bug bug;
    private List<Bug> bugs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DrawView drawView = new DrawView(this);
        setContentView(drawView);

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metricsB = new DisplayMetrics();
        display.getMetrics(metricsB);
        WIDTH_DEVICE = metricsB.widthPixels;
        HEIGTH_DEVICE = metricsB.heightPixels;

        bugs = new ArrayList<Bug>();
        BugThread bugThread;
        for(int i=0; i< 5; i++){
            bug = new Bug(0,0, 0, 0,true);
            bugs.add(bug);
            bugThread = BugThread.createAndStart(bug);
        }

    }




    class DrawView extends SurfaceView implements SurfaceHolder.Callback {

        private DrawThread drawThread;
        Paint paint;
        Bitmap bitmap;
        Matrix matrix;

        public DrawView(Context context) {
            super(context);


            paint = new Paint(Paint.ANTI_ALIAS_FLAG);

            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bee2);

            matrix = new Matrix();

            getHolder().addCallback(this);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            drawThread = new DrawThread(getHolder());
            drawThread.setRunning(true);
            drawThread.start();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            drawThread.setRunning(false);
            while (retry) {
                try {
                    drawThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                }
            }
        }

        class DrawThread extends Thread {

            private boolean running = false;
            private SurfaceHolder surfaceHolder;

            public DrawThread(SurfaceHolder surfaceHolder) {
                this.surfaceHolder = surfaceHolder;
            }

            public void setRunning(boolean running) {
                this.running = running;
            }

            @Override
            public void run() {
                Canvas canvas;
                while (running) {
                    canvas = null;
                    try {
                        canvas = surfaceHolder.lockCanvas(null);
                        if (canvas == null)
                            continue;

                        canvas.drawColor(Color.GREEN);

                        for (Bug bug: bugs ) {
                            bug.setX(bug.getX()+bug.getdX());
                            bug.setY(bug.getY()+ bug.getdY());

                            matrix.postRotate(bug.getAngle());
                            bug.setAngle(0);
                            onScreenOut(bug);

                           // matrix.postTranslate(bug.getdX(),bug.getdY());
                            canvas.drawBitmap(bitmap, matrix, paint);
                            bitmap.;
                            canvas.drawBitmap(bitmap, bug.getX(),bug.getY(), paint);
                        }

                    } finally {
                        if (canvas != null) {
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }

            private void onScreenOut(Bug bug) {
                int X = bug.getX();
                int Y = bug.getY();
                if (X + 48 < 0 ) X = WIDTH_DEVICE -70 ;
                if (X > WIDTH_DEVICE) X = 0;
                if (Y + 70 < 0) Y = HEIGTH_DEVICE - 48;
                if (Y > HEIGTH_DEVICE) Y = 0;
                bug.setX(X);
                bug.setY(Y);
            }
        }

    }

}







