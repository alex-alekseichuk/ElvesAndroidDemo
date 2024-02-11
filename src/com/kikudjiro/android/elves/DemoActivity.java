package com.kikudjiro.android.elves;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class DemoActivity extends Activity {

	private Bitmap _bmpBackground;
	private Bitmap _bmpTop;
	private Bitmap _bmpBottom;

	private Bitmap _bmpElven;
	private Bitmap _bmpAxe;
	//private Bitmap _bmpHelmet;
	private Bitmap _bmpCoat;
	private Bitmap _bmpGnome;

	private RelativeLayout _mainLayout;

	private FigureView _viewCharLeft;
	private FigureView _viewCharRight;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.main);

        // load images into memory from /assets/
        try
        {
        	_bmpBackground = _getBitmapFromAsset("background.png");
        	_bmpTop = _getBitmapFromAsset("top_panel.png");
        	_bmpBottom = _getBitmapFromAsset("bottom_panel.png");

        	_bmpElven = _getBitmapFromAsset("chars/Elven.png");
        	_bmpCoat = _getBitmapFromAsset("chars/Coat_01.png");
        	//_bmpHelmet = _getBitmapFromAsset("chars/Elven_Helmet1.png");
        	_bmpAxe = _getBitmapFromAsset("chars/Men_BlackLargeAxe.png");
        	
        	_bmpGnome = _getBitmapFromAsset("chars/gnome.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        _mainLayout = new RelativeLayout(this);

        RelativeLayout.LayoutParams params;

        ImageView backView = new ImageView(this);
        backView.setImageBitmap(_bmpBackground);
        _mainLayout.addView(backView);

        int h_base = 320 + 150;
        int h = 320;
        Bitmap[] bmps = {_bmpElven, _bmpCoat, /*_bmpHelmet,*/ _bmpAxe};
        _viewCharLeft = new FigureView(this, bmps, 240, h, 1);
        params = new RelativeLayout.LayoutParams(240, h);
        params.leftMargin = 0;//120 - 375/2;
        params.topMargin = h_base - h;
        _mainLayout.addView(_viewCharLeft, params);

        h = 288;
        _viewCharRight = new FigureView(this, _bmpGnome, 240, h, 0);
        params = new RelativeLayout.LayoutParams(240, h);
        params.leftMargin = 240;//360 - 375/2;
        params.topMargin = h_base - h;
        _mainLayout.addView(_viewCharRight, params);

        ImageView topView = new ImageView(this);
        topView.setImageBitmap(_bmpTop);
        _mainLayout.addView(topView);

        ImageView bottomView = new ImageView(this);
        bottomView.setImageBitmap(_bmpBottom);
        params = new RelativeLayout.LayoutParams(480, 290);
        params.topMargin = 800 - 290;
        _mainLayout.addView(bottomView, params);

        setContentView(_mainLayout);

        _mainLayout.postDelayed(_onTick, 100);

    }
    
    private Runnable _onTick = new Runnable() {
        public void run() {
        	_viewCharLeft.tick();
        	_viewCharRight.tick();
        	_mainLayout.postDelayed(_onTick, 100);
        }
    };

    /**
     * Helper Function to load bitmap from assets
     * @throws IOException 
     */
    private Bitmap _getBitmapFromAsset(String strName) throws IOException
    {
        AssetManager assetManager = getAssets();
        InputStream istr = assetManager.open(strName);
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }
    
}
