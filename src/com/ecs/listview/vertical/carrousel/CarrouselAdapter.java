package com.ecs.listview.vertical.carrousel;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CarrouselAdapter extends ArrayAdapter<String> {

    private static final int VERTICAL_SEPERATOR_MARGIN = 25;
	private static final int VERTICAL_SEPERATOR_WIDTH = 2;
	private static final int ROW_SPACING = 100;

	public CarrouselAdapter(final Context context, final String[] items) {
        super(context, 0, items);
    }

	@Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
    	View view = convertView;
        if (view == null) {
        	
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
            
            RelativeLayout layout = (RelativeLayout)view;
            View child = new View(getContext());
            child.setBackgroundColor(Color.DKGRAY);
			layout.addView(child, 0);

			ViewHolder viewHolder = new ViewHolder();
            viewHolder.title = (TextView)view.findViewById(R.id.item_title);
            viewHolder.image = (ImageView) view.findViewById(R.id.circle);
            
            view.setTag(viewHolder);
            
            int height = 0;
            if (position == getCount()-1 || position==0) {
            	height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, ROW_SPACING * 2, getContext().getResources().getDisplayMetrics());	
        	} else {
        		height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, ROW_SPACING, getContext().getResources().getDisplayMetrics());
        	}
        	
//            RelativeLayout layout = (RelativeLayout)view;
//            View child = layout.getChildAt(0);
            int width= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, VERTICAL_SEPERATOR_WIDTH, getContext().getResources().getDisplayMetrics());
            int marginLeft = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, VERTICAL_SEPERATOR_MARGIN, getContext().getResources().getDisplayMetrics());
            RelativeLayout.LayoutParams layoutParams= new RelativeLayout.LayoutParams(width, height);
            layoutParams.setMargins(marginLeft,0,0,0);
    		child.setLayoutParams(layoutParams);
            
            
        }
        
        ViewHolder holder = (ViewHolder) view.getTag();
        
        if (position == getCount()-1 || position==0) {
          RelativeLayout layout = (RelativeLayout)view;
          View child = layout.getChildAt(0);
        	int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, ROW_SPACING * 2, getContext().getResources().getDisplayMetrics());
            int width= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, VERTICAL_SEPERATOR_WIDTH, getContext().getResources().getDisplayMetrics());
            int marginLeft = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, VERTICAL_SEPERATOR_MARGIN, getContext().getResources().getDisplayMetrics());
            RelativeLayout.LayoutParams layoutParams= new RelativeLayout.LayoutParams(width, height);
            layoutParams.setMargins(marginLeft,0,0,0);
    		child.setLayoutParams(layoutParams);
    	}
        

		            
        holder.title.setTextColor(Color.BLACK);
        holder.title.setText(getItem(position));
        Log.i("App", " +++ getView called " + position + " - " + getCount() + " - " + holder.title.getText() + " - " + view.getX() + ":" + view.getY() + ":" + view.getTop() + ":" + view.getBottom());
        return view;
    }
    
	static class ViewHolder {
	    public TextView title;
	    public TextView description;
	    public ImageView image;
	}
}

