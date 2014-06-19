package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements
View.OnClickListener
{

	SQLiteDatabase sdb = null;
	MySQLiteOpenHelper helper = null;

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

		if(sdb == null){
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try{
			sdb = helper.getWritableDatabase();
		} catch(SQLiteException e){
			//異常終了
			return;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		Intent intent = null;
		switch(v.getId()){
		case R.id.btntrk://登録ボタンが押された
			//エディットテキストからの入力内容を取り出す
			EditText etv = (EditText)findViewById(R.id.edttxt);
			String inputMsg = etv.getText().toString();

			//inputMsgがnullじゃない、かつ、空でない場合のみif文内を実行
			if(inputMsg!=null && !inputMsg.isEmpty()){
				//MySQLiteOpenHelperのインサートメソッドを呼び出し
				helper.insertHitokoto(sdb, inputMsg);
			}

			//入力欄をクリア
			etv.setText("");
			break;
		case R.id.btnmnt://メンテボタンが押された
			intent = new Intent(MainActivity.this,MaintenanceActivity.class);
			startActivity(intent);
			break;
		case R.id.btnchk://一言チェックボタンが押された

			//MySQLiteOpenHelperのセレクト一言メソッドを呼び出して一言をランダムに取得
			String strHitokoto = helper.selectRandomHitokoto(sdb);

			intent = new Intent(MainActivity.this,HitokotoActivity.class);
			intent.putExtra("hitokoto", strHitokoto);

			startActivity(intent);
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
