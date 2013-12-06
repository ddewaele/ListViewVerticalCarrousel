package com.ecs.listview.vertical.carrousel;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.TextView;

public class CarrouselScrolListener implements OnScrollListener {

	
	private Context context;

	public CarrouselScrolListener(Context context) {
		this.context = context;
	}
	
	@Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    	//Log.i(TAG, "inside onScroll with " + view.getId() + " - " + firstVisibleItem + " - " + visibleItemCount + " - " + totalItemCount);
    	
         int  count = view.getChildCount(); 

         for  (int  i=0; i<count; i++) {
             	View childAt = view.getChildAt(i);
             	final TextView title = (TextView)childAt.findViewById(R.id.item_title);
             	final ImageView circle = (ImageView)childAt.findViewById(R.id.circle);
             	
             	circle.setAlpha(0f);
             	title.setAlpha(0f);
             	
             	
             	int top = childAt.getTop(); // getTop() and getBottom() are relative to the ListView, negative values means the item is not fully visible
             	int itemHeight = childAt.getHeight(); // 300dp row item = 600px on Nexus; 
             	int minimum = 0 - (itemHeight/2);	  // we want to know when a list item comes down from the top. When we're at the middle of the list item, we're at -300px.
             	int maximum = ((View)childAt.getParent()).getHeight() - (itemHeight/2); // take the listview height minus the 300px.

             	// Start working on items that are visible.
             	if (top>minimum && top<maximum) {
             	
             		float mapped = mapNumber(top, minimum, maximum);

                 	
                 	if (!"".equals(title.getText().toString())) {
                 		circle.setAlpha(mapped);
                 		//circle.getDrawable().setBounds(50 - Float.valueOf(mapped*50).intValue(), Float.valueOf(mapped*50).intValue(), Float.valueOf(mapped*50).intValue(), Float.valueOf(mapped*50).intValue());
                 	}
                 	
                 	title.setAlpha(mapped);
                 	
                 	title.setPivotX(0);
                 	if (title.getLineCount()>1) {
                 		title.setPivotY(100);
                 	} else {
                 		title.setPivotY(title.getHeight());
                 	}
                 	title.setScaleX(mapped);
                 	title.setScaleY(mapped);
                 	
                 	
                 	//Log.i(TAG, " +++ inside onScroll with " + i + " - " +  mapped + " - " + top + " - " +  title.getText());
                 	//Log.i(Constants.TAG, " +++ inside onScroll with " + i + " - " +  title.getHeight() + " - " + title.getLineHeight() + " - " + title.getLineCount() + " - " +  title.getText());
                 	
             	} else {
             		circle.setAlpha(0.0f);
             	}
         }
    }
    
    float mapNumber(int number,int min,int max) {
		
		float offset = (min>0) ? 0 : Math.abs(0-min);
		float fNumber = Integer.valueOf(number).floatValue() + offset;
		float fMin = Integer.valueOf(min).floatValue() + offset;
		float fMax= Integer.valueOf(max).floatValue() + offset;
		
		float middle = fMax -  (fMax - fMin) / 2f;
		float result = 0f;
		
			if (fNumber>=middle) {
				result =  Math.abs((fNumber-fMax)/(middle));
			} else {
				result = Math.abs((fNumber/middle));
				
			}

			return result;        			
    }

}
