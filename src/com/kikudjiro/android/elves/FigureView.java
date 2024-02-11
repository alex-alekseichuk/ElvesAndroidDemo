package com.kikudjiro.android.elves;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class FigureView extends View {

	public FigureView(Context context, Bitmap bitmap, int width, int height, int initMode) {
		super(context);
		_bitmaps = new Bitmap[1];
		_bitmaps[0] = bitmap;
		_width = width;
		_height = height;
		_mode = initMode;
	}
	public FigureView(Context context, Bitmap[] bmps, int width, int height, int initMode) {
		super(context);
		_bitmaps = bmps;
		_width = width;
		_height = height;
		_mode = initMode;
	}

		
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
	    Paint paint = new Paint();
	    //paint.setAntiAlias(true);

	    Rect rectSrc = new Rect(_width * _frame, _height * _mode, _width * (_frame + 1), _height * (_mode + 1));
	    Rect rectDest = new Rect(0, 0, _width, _height);
	    for (Bitmap bitmap : _bitmaps)
	    	canvas.drawBitmap(bitmap, rectSrc, rectDest, paint);
	}

    public boolean onTouchEvent(MotionEvent event) {
    	if (event.getPointerCount() == 0) 
            return true;
    	
    	int action = event.getAction() & MotionEvent.ACTION_MASK;

   		switch (action)
   		{
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
   			case MotionEvent.ACTION_DOWN:
   			case MotionEvent.ACTION_POINTER_DOWN:
   				_nRepeats++;
   				if (_mode == 0) {
   					_mode = 1;
   				}
   				break;
   		}
   		return false;
    }
	
	public void tick() {
		_frame ++;
		if (_frame >= 10) {
			_frame = 0;
			if (_mode == 1) {
				if (_nRepeats <= 0)
					_mode = 0;
				else
					_nRepeats--;
			}
		}
		invalidate();
	}

	private Bitmap[] _bitmaps;
	
	private int _width;
	private int _height;
	
	private int _frame = 0;
	private int _mode = 0;
	private int _nRepeats = 0;
	
}
