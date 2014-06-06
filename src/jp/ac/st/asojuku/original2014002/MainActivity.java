package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements
View.OnClickListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();

		Button btnmnt = (Button)findViewById(R.id.btnmnt);
		btnmnt.setOnClickListener(this);
		Button btntrk = (Button)findViewById(R.id.btntrk);
		btntrk.setOnClickListener(this);
		Button btnchk = (Button)findViewById(R.id.btnchk);
		btnchk.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()){
		case R.id.btnmnt:
			Intent intent1 = new Intent(MainActivity.this,MaintenanceActivity.class);
			startActivity(intent1);
			break;
		case R.id.btnchk:
			Intent intent2 = new Intent(MainActivity.this,HitokotoActivity.class);
			startActivity(intent2);
			break;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
