package com.kikudjiro.android.elves;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class MainView extends View {

	private Bitmap _bitmap;
	public MainView(Context context, Bitmap bitmap) {
		super(context);
		_bitmap = bitmap;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
	    Paint paint = new Paint();
	    //paint.setAntiAlias(true);

	    Rect rectDest = new Rect(0, 0, 480, 800);
	    canvas.drawBitmap(_bitmap, rectDest, rectDest, paint);
	}
	
}
