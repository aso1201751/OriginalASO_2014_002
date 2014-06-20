package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MaintenanceActivity extends Activity implements
View.OnClickListener, AdapterView.OnItemClickListener
{

	SQLiteDatabase sdb = null;
	MySQLiteOpenHelper helper = null;

	int selectedID = -1;
	int lastPosition = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maintenance_activity);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();

		Button btndlt = (Button)findViewById(R.id.btndlt);
		Button btnmdr = (Button)findViewById(R.id.btnmdr);
		ListView lsthtkt = (ListView)findViewById(R.id.lsthtkt);

		btndlt.setOnClickListener(this);
		btnmdr.setOnClickListener(this);

		lsthtkt.setOnItemClickListener(this);

		this.setDBValuetoList(lsthtkt);


	}

	public void setDBValuetoList(ListView lsthtkt){
		SQLiteCursor cursor = null;

		if(sdb == null){
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try{
			sdb = helper.getWritableDatabase();
		} catch(SQLiteException e){
			//異常終了
			Log.e("ERROR",e.toString());
		}

		cursor = this.helper.selectHitokotoList(sdb);

		int db_layout = android.R.layout.simple_list_item_1;
		String[]from = {"phrase"};
		int[] to = new int[]{android.R.id.text1};

		SimpleCursorAdapter adapter =
				new SimpleCursorAdapter(this,db_layout,cursor,from,to,0);

		lsthtkt.setAdapter(adapter);
	}

	private void deleteFromHitokoto(int id){
		if(sdb == null){
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try{
			sdb = helper.getWritableDatabase();
		}catch(SQLiteException e){
			Log.e("ERROR",e.toString());
		}finally{
			this.helper.deleteHitokoto(sdb,id);
		}
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO 自動生成されたメソッド・スタブ
		if(this.selectedID!=-1){
			parent.getChildAt(this.lastPosition).setBackgroundColor(0);
		}
		view.setBackgroundColor(android.graphics.Color.LTGRAY);

		SQLiteCursor cursor = (SQLiteCursor)parent.getItemAtPosition(position);
		this.selectedID = cursor.getInt(cursor.getColumnIndex("_id"));
		this.lastPosition = position;
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()){
		case R.id.btndlt:
			if(this.selectedID != -1){
				this.deleteFromHitokoto(this.selectedID);
				ListView lsthtkt = (ListView)findViewById(R.id.lsthtkt);
				this.setDBValuetoList(lsthtkt);
				this.selectedID = -1;
				this.lastPosition = -1;
			}
			else{
				Toast.makeText(MaintenanceActivity.this, "削除する行を選んでください", Toast.LENGTH_SHORT).show();
			}
			break;

		case R.id.btnmdr:
			 finish();
			 break;
		}

	}

}
