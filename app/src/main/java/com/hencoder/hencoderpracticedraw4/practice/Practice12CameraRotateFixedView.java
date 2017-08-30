package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice12CameraRotateFixedView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);
    Camera camera = new Camera();
    Matrix matrix = new Matrix();

    public Practice12CameraRotateFixedView(Context context) {
        super(context);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 方式1: 使用canvas.translate
         * Canvas 的几何变换顺序是反的，所以要把移动到中心的代码写在下面，把从中心移动回来的代码写在上面。
         * 用matrix的post不需要如此.
         *
         * 如果你需要图形左右对称，需要配合上 Canvas.translate()，
         * 在三维旋转之前把绘制内容的中心点移动到原点，即旋转的轴心，然后在三维旋转后再把投影移动回来.
         */
        canvas.save();
        camera.save();
        canvas.translate(point1.x + bitmap.getWidth() / 2, point1.y + bitmap.getHeight() / 2);
        camera.rotateX(30f);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.translate(-(point1.x + bitmap.getWidth() / 2), -(point1.y + bitmap.getHeight() / 2));
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        /**
         * 方式2: 使用matrix(不要求理解, 能用方式1即可)
         * Canvas 的几何变换顺序是反的，所以要把移动到中心的代码写在下面，把从中心移动回来的代码写在上面。
         * 用matrix的post不需要如此.
         *
         * 如果你需要图形左右对称，需要配合上 Canvas.translate()，
         * 在三维旋转之前把绘制内容的中心点移动到原点，即旋转的轴心，然后在三维旋转后再把投影移动回来.
         */
        matrix.reset();
        canvas.save();
        camera.save();
        camera.rotateY(30);
        camera.getMatrix(matrix);//获取转换效果后的Matrix对象
        //camera.applyToCanvas(canvas);去掉
        camera.restore();
        matrix.preTranslate(-(point2.x + bitmap.getWidth() / 2), -(point2.y + bitmap.getHeight() / 2));
        matrix.postTranslate(point2.x + bitmap.getWidth() / 2, point2.y + bitmap.getHeight() / 2);
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
