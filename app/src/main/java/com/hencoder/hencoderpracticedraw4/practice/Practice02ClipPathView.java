package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice02ClipPathView extends View {
    Paint paint = new Paint();
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);

    public Practice02ClipPathView(Context context) {
        super(context);
    }

    public Practice02ClipPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice02ClipPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.save();
        Path path = new Path();
        /**
         * 顺时针 (CW clockwise) 和逆时针 (CCW counter-clockwise) 。对于普通情况，
         * 这个参数填 CW 还是填 CCW 没有影响。它只是在需要填充图形 (Paint.Style 为 FILL
         * 或  FILL_AND_STROKE) ，并且图形出现自相交时，用于判断填充范围的。
         */
        path.addCircle(point1.x + 200, point1.y + 200, 150, Path.Direction.CW);
        canvas.clipPath(path);

        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();


        path.reset();
        path.addCircle(point2.x + 200, point2.y + 200, 150, Path.Direction.CW);
        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);//用来设置图形自相交时的填充算法
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);


    }
}
