package com.example.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import com.example.calculator.R.id;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	// Variables in which the numbers will be placed
	String firstvar = "";
	String secondvar = "";
	String currentnum = "1";

	String Ans = "";
	int NoUse = 0;
	
	String neg = "-";
	
	//DF used to put format small and large answers correctly
	DecimalFormat df = new DecimalFormat("0.#####E0");
	DecimalFormat f = new DecimalFormat("0.#####");
	BigDecimal Output;

	//Operation is used to call the right function case
	int Operation = 0;
	// Operations-
	// 0 = nothing
	// 1 = Add
	// 2 = Subtract
	// 3 = Multiply
	// 4 = Divide
	String Add = "+";
	String Subtract = "-";
	String Multiply = "*";
	String Divide = "/";
	String Blank = "";

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Hides the top bar
		getActionBar().hide();
		
		//get's the height of the status top bar that has 
		int result = 0;
	    	int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
	    	if (resourceId > 0) {
	    		result = getResources().getDimensionPixelSize(resourceId);
	    	}  
	          
	    //get's the height of the action bar	
	    int action = 0;
	    	int resourceId1 = getResources().getIdentifier("actionBarSize", "dimen", "android");
	    	if(resourceId1 >0) {
	    		action = getResources().getDimensionPixelSize(resourceId1);
	    	}
	
    	DisplayMetrics dm = new DisplayMetrics();
    	getWindowManager().getDefaultDisplay().getMetrics(dm);
    	
    	int width=dm.widthPixels;
    	int height=dm.heightPixels;
    		
    	//variables needed ot make buttons and views the right size
		int h = (height - result - action - 10) / 8;
		int w = (width / 4);
		int q = (width / 8);
		int t = h/3;
		int p = 4*h/5;
		
		Button btnNegative = (Button) findViewById(R.id.btnNegative);
		Button btnDecimal = (Button) findViewById(R.id.btnDecimal);
		
		//declares all buttons and sets them up
		int[] buttons = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, 
				R.id.btn9, R.id.btn0, R.id.btnPlus, R.id.btnSubtract, R.id.btnDivide, R.id.btnMultiply, R.id.btnDecimal,
				R.id.btnEquals, R.id.btnClear, R.id.btnNegative};
	    for (int i = 0; i < buttons.length; i++) {
	        Button buttonNum = (Button) findViewById(buttons[i]);
	        buttonNum.setHeight(h);
	        buttonNum.setOnClickListener(this);
	        buttonNum.setWidth(w);
	        buttonNum.setTextSize(TypedValue.COMPLEX_UNIT_PX, t);
	        buttonNum.setWidth(w);
	    }
	    
	    
	    btnNegative.setWidth(q);
	    btnDecimal.setWidth(q);
	    
	    //declares all textviews and sizes them
	    int[] TextView = {R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4};
	    for (int i = 0; i < TextView.length; i++){
	    	TextView TextSize = (TextView) findViewById(TextView[i]);
	    	TextSize.setHeight(h);
	    	TextSize.setTextSize(TypedValue.COMPLEX_UNIT_PX, p);
	    }
	    
	}

	//method used to add a number to the string
	private void Appendnum(String num) {

		if (currentnum == "1") {
			if(NoUse == 0){
				firstvar = firstvar + num;
				final TextView textView1 = (TextView) findViewById(R.id.textView1);
				textView1.setText(firstvar);
				NoUse = 0;
			}
			else if(NoUse == 1){
				firstvar = num;
				final TextView textView1 = (TextView) findViewById(R.id.textView1);
				textView1.setText(firstvar);
				final TextView textView2 = (TextView) findViewById(R.id.textView2);
				textView2.setText("0");
				final TextView textView3 = (TextView) findViewById(R.id.textView3);
				textView3.setText("0");
				final TextView textView4 = (TextView) findViewById(R.id.textView4);
				textView4.setText(Blank);
				NoUse = 0;
			}
		} else if (currentnum == "2") {

			secondvar = secondvar + num;
			final TextView textView2 = (TextView) findViewById(R.id.textView2);
			textView2.setText(secondvar);

		}
	}
	
	//method used when user clicks a button
	@SuppressLint("CutPasteId")
	public void onClick(View v) {
	
		
		// The individual methods for buttons being clicked
		int id = v.getId();
		switch (id) {

		case R.id.btn1:
			Appendnum("1");
			break;

		case R.id.btn2:
			Appendnum("2");
			break;

		case R.id.btn3:

			Appendnum("3");
			break;

		case R.id.btn4:
			Appendnum("4");
			break;

		case R.id.btn5:
			Appendnum("5");
			break;

		case R.id.btn6:
			Appendnum("6");
			break;

		case R.id.btn7:
			Appendnum("7");
			break;

		case R.id.btn8:
			Appendnum("8");
			break;

		case R.id.btn9:
			Appendnum("9");
			break;

		case R.id.btn0:
			if (currentnum == "1" && firstvar != "") {
				Appendnum("0");
			} else if (currentnum == "2" && secondvar != "") {
				Appendnum("0");
			}
			break;

		// User clicks Addition Button
		case R.id.btnPlus:
			if (Operation == 0){
				if (currentnum == "1" && firstvar != "") {
				currentnum = "2";
				final TextView textView4 = (TextView) findViewById(R.id.textView4);
				textView4.setText(Add);
				Operation = 1;
				final TextView textView2 = (TextView) findViewById(R.id.textView2);
				textView2.setText("0");
				} 
				else if (NoUse == 1) {
					secondvar = "";
					final TextView textView2 = (TextView) findViewById(R.id.textView2);
					textView2.setText("0");
					firstvar = Ans;
					Operation = 1;
					currentnum = "2";
					final TextView textView1 = (TextView) findViewById(R.id.textView1);
					textView1.setText(Ans);
					final TextView textView3 = (TextView) findViewById(R.id.textView3);
					textView3.setText("0");
					final TextView textView9 = (TextView) findViewById(R.id.textView4);
					textView9.setText(Add);
				}
			}
			break;

		// User clicks Subtraction Button
		case R.id.btnSubtract:
			if (Operation == 0){
				if(currentnum == "1" && firstvar != "") {
					currentnum = "2";
					final TextView textView4 = (TextView) findViewById(R.id.textView4);
					textView4.setText(Subtract);
					Operation = 2;
					final TextView textView2 = (TextView) findViewById(R.id.textView2);
					textView2.setText("0");
				} 
				else if (NoUse == 1) {
					secondvar = "";
					final TextView textView2 = (TextView) findViewById(R.id.textView2);
					textView2.setText("0");
					firstvar = Ans;
					Operation = 2;
					currentnum = "2";
					final TextView textView1 = (TextView) findViewById(R.id.textView1);
					textView1.setText(Ans);
					final TextView textView3 = (TextView) findViewById(R.id.textView3);
					textView3.setText("0");
					final TextView textView9 = (TextView) findViewById(R.id.textView4);
					textView9.setText(Subtract);
				}
			}
			break;
			
		// User clicks Multiplication Button
		case R.id.btnMultiply:
			if (Operation == 0) {
				if(currentnum == "1" && firstvar != "") {
					currentnum = "2";
					final TextView textView4 = (TextView) findViewById(R.id.textView4);
					textView4.setText(Multiply);
					Operation = 3;
					final TextView textView2 = (TextView) findViewById(R.id.textView2);
					textView2.setText("0");
				} 
				else if (NoUse == 1) {
					secondvar = "";
					final TextView textView2 = (TextView) findViewById(R.id.textView2);
					textView2.setText("0");
					firstvar = Ans;
					Operation = 3;
					currentnum = "2";
					final TextView textView1 = (TextView) findViewById(R.id.textView1);
					textView1.setText(Ans);
					final TextView textView3 = (TextView) findViewById(R.id.textView3);
					textView3.setText("0");
					final TextView textView9 = (TextView) findViewById(R.id.textView4);
					textView9.setText(Multiply);
				}
			}	
			break;

		// User clicks Divide Button
		case R.id.btnDivide:
			if (Operation == 0) {
				if(currentnum == "1" && firstvar != "") {
					currentnum = "2";
					final TextView textView4 = (TextView) findViewById(R.id.textView4);
					textView4.setText(Divide);
					Operation = 4;
					final TextView textView2 = (TextView) findViewById(R.id.textView2);
					textView2.setText("0");
				} 
				else if (NoUse == 1) {
					secondvar = "";
					final TextView textView2 = (TextView) findViewById(R.id.textView2);
					textView2.setText("0");
					firstvar = Ans;
					Operation = 4;
					currentnum = "2";
					final TextView textView1 = (TextView) findViewById(R.id.textView1);
					textView1.setText(Ans);
					final TextView textView3 = (TextView) findViewById(R.id.textView3);
					textView3.setText("0");
					final TextView textView9 = (TextView) findViewById(R.id.textView4);
					textView9.setText(Divide);
				}
			}	
			break;

		// User clicks Decimal Button
		case R.id.btnDecimal:
			if (currentnum == "1") {
				if (firstvar.contains(".")) {
					break;
				} else {
					Appendnum(".");
				}

			} else {
				if (secondvar.contains(".")) {
					break;
				} else {
					Appendnum(".");
				}
			}
			break;
			
		// User clicks Negative Button
		case R.id.btnNegative:
			if (currentnum == "1" &&! firstvar.contains("-")) {
				if(NoUse == 0){
					firstvar = neg + firstvar;
					final TextView textView16 = (TextView) findViewById(R.id.textView1);
					textView16.setText(firstvar);
					NoUse = 0;
				}
				else if(NoUse == 1){
					firstvar = "neg";
					final TextView textView1 = (TextView) findViewById(R.id.textView1);
					textView1.setText(firstvar);
					final TextView textView2 = (TextView) findViewById(R.id.textView2);
					textView2.setText("0");
					final TextView textView3 = (TextView) findViewById(R.id.textView3);
					textView3.setText("0");
					final TextView textView4 = (TextView) findViewById(R.id.textView4);
					textView4.setText(Blank);
					NoUse = 0;
				}
			} 
			else if (currentnum == "2"  &&! secondvar.contains("-")) {

				secondvar = neg + secondvar;
				final TextView textView2 = (TextView) findViewById(R.id.textView2);
				textView2.setText(secondvar);

			}
			break;
		
		case R.id.btnClear:
			final TextView textView1 = (TextView) findViewById(R.id.textView1);
			textView1.setText("0");
			final TextView textView2 = (TextView) findViewById(R.id.textView2);
			textView2.setText("0");
			final TextView textView59 = (TextView) findViewById(R.id.textView3);
			textView59.setText("0");
			final TextView textView4 = (TextView) findViewById(R.id.textView4);
			textView4.setText(Blank);
			currentnum = "1";
			firstvar = "";
			secondvar = "";
			Operation = 0;
			Ans = "0";

			break;

		case R.id.btnEquals:
			if (currentnum == "1") {
				break;
			} 
			else if (secondvar == "0" || secondvar ==""){
			
				//A notification pops up and informs user that they need a second number
				LayoutInflater inflater = getLayoutInflater();
				View layout = inflater.inflate(R.layout.toast_layout,(ViewGroup) findViewById(R.id.custom_toast_layout_id));
				TextView text = (TextView) layout.findViewById(R.id.toasttext);
				text.setText("The second number is 0, please type in an appropriate number");
				Toast toast = new Toast(getApplicationContext());
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setDuration(Toast.LENGTH_SHORT);
				toast.setView(layout);
				toast.show();
				
			}
			else if(secondvar != "") {
				BigDecimal i = new BigDecimal(firstvar);
				BigDecimal j = new BigDecimal(secondvar);

				switch (Operation) {

				case 1:
					Output = i.add(j);
					break;

				case 2:
					Output = i.subtract(j);
					break;

				case 3:
					Output = i.multiply(j);
					break;

				case 4:
					Output = i.divide(j, 10, RoundingMode.HALF_EVEN);
					break;

				}

				String size = Output.toString();
				BigDecimal big = new BigDecimal("99999");
				BigDecimal small = new BigDecimal (".00001");

				
				if (big.compareTo(Output) ==1 || small.compareTo(Output) > 0) {
					
					double z = Output.doubleValue();
					Ans = f.format(z);
	
				} else {
					
					Ans = df.format(Output);
				}

				final TextView textView5 = (TextView) findViewById(R.id.textView3);
				textView5.setText(Ans);
				currentnum = "1";
				firstvar = "";
				secondvar = "";
				NoUse = 1;
				Operation = 0;
			}
		}
	}
}