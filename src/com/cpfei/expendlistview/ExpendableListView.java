package com.cpfei.expendlistview;


import android.app.Activity;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;

public class ExpendableListView extends Activity implements OnGroupClickListener, OnChildClickListener{

	private String[] generalsTypes;
	private String[][] generals;
	ExpandableListAdapter adapter;
	ExpandableListView expandableListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				updateView();
			}
		});

		initData();
		
		expandableListView = (ExpandableListView)findViewById(R.id.list);
		expandableListView.setGroupIndicator(null);
		adapter = new ExpandableListAdapter();
		
		expandableListView.setAdapter(adapter);
		
		expandableListView.setOnChildClickListener(this);
		
		expandableListView.setOnGroupClickListener(this);

		for (int i = 0; i < adapter.getGroupCount(); i++) {
			expandableListView.expandGroup(i);
		}


	}

	private void initData() {
		// 设置组视图的显示文字
		generalsTypes = new String[] { "魏", "蜀", "吴" };
		generals = new String[][] { { "夏侯惇", "甄姬", "许褚", "郭嘉", "司马懿", "杨修" },
				{ "马超", "张飞", "刘备", "诸葛亮", "黄月英", "赵云" },
				{ "吕蒙", "陆逊", "孙权", "周瑜", "孙尚香" } };

	}

	protected void updateView() {
		int firstVisiblePosition = expandableListView.getFirstVisiblePosition();
		int lastVisiblePosition = expandableListView.getLastVisiblePosition();

		int flatListPosition = expandableListView.getFlatListPosition(5);

		long expandableListPosition = expandableListView.getExpandableListPosition(flatListPosition);


		Log.i("Tag", "firstVisiblePosition == " + firstVisiblePosition);
		Log.i("Tag", "lastVisiblePosition == " + lastVisiblePosition);
		Log.i("Tag", "flatListPosition == " + flatListPosition);
		Log.i("Tag", "expandableListPosition == " + expandableListPosition);



		View childAt = expandableListView.getChildAt(4);




		TextView viewById = (TextView) childAt.findViewById(R.id.childTxtv);

		Log.i("Tag", viewById.getText().toString());
		viewById.setText("改变过后的数据");

	}


	class ExpandableListAdapter extends BaseExpandableListAdapter {

		@Override
		public int getGroupCount() {
			return generalsTypes.length;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return generals[groupPosition].length;
		}

		@Override
		public Object getGroup(int groupPosition) {
			return generalsTypes[groupPosition];
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return generals[groupPosition][childPosition];
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {

			Log.i("Tag", "getGroupView = groupPosition == " + groupPosition);

			TextView txtv = new TextView(ExpendableListView.this);

			txtv.setPadding(30, 20, 0, 20);

			txtv.setBackgroundColor(Color.parseColor("#ababab"));

			txtv.setText(generalsTypes[groupPosition]);

			return txtv;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			Log.i("Tag", "getChildView = groupPosition == " + groupPosition + " , childPosition == " + childPosition);

			convertView = LayoutInflater.from(ExpendableListView.this).inflate(R.layout.item_child, null);

			TextView txtv = (TextView) convertView.findViewById(R.id.childTxtv);


//			TextView txtv = new TextView(ExpendableListView.this);
//
//			txtv.setPadding(40, 20, 0, 20);
//
//			txtv.setBackgroundColor(Color.parseColor("#ffffff"));

			txtv.setText(generals[groupPosition][childPosition]);

			return txtv;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}

	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		Log.i("Tag", "groupPosition = " + groupPosition + " <--> childPosition = " + childPosition);
		return false;
	}
	
	
	

	@Override
	public boolean onGroupClick(ExpandableListView parent, View v,
			int groupPosition, long id) {
		Log.i("Tag", "groupPosition = " + groupPosition);
		return true;
	}

}
