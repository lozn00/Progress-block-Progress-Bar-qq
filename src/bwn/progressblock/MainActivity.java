package bwn.progressblock;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	protected static final int MSG_PROGRESS = 1;
	private ProgressBlock progressBlock;
	private int count=1000;
	private boolean stop=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		progressBlock = (ProgressBlock) findViewById(R.id.progressblock);
		progressBlock.setMaxProgress(count);
		
		findViewById(R.id.btn_start).setOnClickListener(this);
		findViewById(R.id.btn_switch_orientation).setOnClickListener(this);
		findViewById(R.id.btn_switch_reverse).setOnClickListener(this);
		findViewById(R.id.btn_switch_text_color).setOnClickListener(this);
		findViewById(R.id.btn_switch_text_orientation).setOnClickListener(this);
		findViewById(R.id.btn_switch_block_color).setOnClickListener(this);
		findViewById(R.id.btn_switch_text_size).setOnClickListener(this);
	}
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_PROGRESS:
				progressBlock.setProgress(msg.arg1);
				break;

			default:
				break;
			}
			
		};
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_start:
				if(!stop){
					stop=true;
				}
		
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					
					try {
						Thread.sleep(1000);
						stop=false;
					} catch (InterruptedException e1) {
						
						e1.printStackTrace();
					}
					for (int i = 0; i <count; i++) {
						if(stop){
							break;
						}
						handler.obtainMessage(MSG_PROGRESS,i,0).sendToTarget();
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
					}
					stop=true;
				}
			}).start();
			
			break;
		case R.id.btn_switch_orientation:
			progressBlock.setDirection(progressBlock.getDirection()==LinearLayout.HORIZONTAL?LinearLayout.VERTICAL:LinearLayout.HORIZONTAL);
			break;
		case R.id.btn_switch_reverse:
			progressBlock.setReverse(!progressBlock.isReverse());
			break;
		case R.id.btn_switch_text_color:
			progressBlock.setProgressTextColor(v.getTag()==null?Color.GREEN:Color.RED);
			v.setTag(v.getTag()==null?"":null);
			break;
		case R.id.btn_switch_text_orientation:
		TextView tv= progressBlock.getProgressTextView();
		tv.setGravity(tv.getGravity()==Gravity.CENTER?Gravity.TOP:(tv.getGravity()==Gravity.TOP?Gravity.BOTTOM:Gravity.CENTER));
			break;
		case R.id.btn_switch_block_color:
			if(blockflag==0){
				progressBlock.setProgressBlockColor(Color.parseColor("#50ff0000"));
				blockflag=1;
			}else if(blockflag==1){
				progressBlock.setProgressBlockColor(Color.parseColor("#5000ff00"));
				blockflag=2;
			}else {
				blockflag=0;
				progressBlock.setProgressBlockColor(Color.parseColor("#500000ff"));
			}
			break;
		case R.id.btn_switch_text_size:
			 tv= progressBlock.getProgressTextView();
			if(block_text_size==0){
				tv.setTextSize(10);
				block_text_size=1;
			}else if(block_text_size==1){
				tv.setTextSize(30);
				block_text_size=2;
			}else {
				block_text_size=0;
				tv.setTextSize(50);
			}
			break;
		
		default:
	
			break;
		}
	}
	
	int blockflag=0;
	int block_text_size=0;

}
