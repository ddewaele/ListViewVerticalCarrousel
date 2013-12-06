package com.ecs.listview.vertical.carrousel;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	private CarrouselAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.list_view);

		final ListView listView = getListView();

		// Not sure why it didn't work with the xml properties. Needed to do it in code.
		// listView.setDivider(null);
		// listView.setDividerHeight(0);

		mAdapter = new CarrouselAdapter(getApplicationContext(),Constants.mStrings);

		setListAdapter(mAdapter);

		listView.setOnScrollListener(new CarrouselScrolListener(getApplicationContext()));

		listView.post(new Runnable() {
			@Override
			public void run() {
				listView.smoothScrollToPosition(1);
			}
		});

	}

}
