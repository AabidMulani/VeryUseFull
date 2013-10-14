package com.example.tiltmanager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

 class SimpleCircle extends View {
	 private int x_val,y_val;
	 int MaxWT,MaxHT;
	// CONSTRUCTOR
	public SimpleCircle(Context context) {
		super(context);
		setFocusable(true);
		Display disp = ((WindowManager)this.getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		MaxWT=disp.getWidth()/2;
		x_val=MaxWT;
		MaxHT=disp.getHeight()/2;
		y_val=MaxHT;
	}

	@Override
	protected void onDraw(Canvas canvas) {

		canvas.drawColor(Color.CYAN);
		Paint p = new Paint();
		// smooths
		p.setAntiAlias(true);
		p.setColor(Color.RED);
		p.setStyle(Paint.Style.STROKE); 
		p.setStrokeWidth(4.5f);
		p.setAlpha(0x80); //
		canvas.drawCircle(getX_val(), getY_val(), 30, p);
		postInvalidateDelayed(2); 
	}
	
	public void ReDraw(){
		Log.e("Inside","ReDraw()");
		this.invalidate();
	}

	public int getX_val() {
		return x_val;
	}

	public void setX_val(int incr) {
		x_val = x_val+incr;
		if(x_val<30)
			x_val=30;
		if(x_val>MaxWT-30)
			x_val=MaxWT-30;
	}

	public int getY_val() {
		return y_val;
	}

	public void setY_val(int incr) {
		y_val = y_val+incr;
		if(y_val<30)
			y_val=30;
		if(y_val>MaxHT-30)
			y_val=MaxHT-30;
	}

}
