package bwn.progressblock;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout.Directions;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by luozheng on 2016/1/8.
 * update 2016-1-30 20:36:43 增加方向
 * qq 694886526
 * 支持进度块设置从上到下 从下到上 ，从左到右
 * 最大百分比支持无上限
 * 默认进度条背景为为半透明,而进度则是透明,从不透明到透明 而文字是居中对齐,也可设置顶部对齐底部对齐
 */
public class ProgressBlock extends FrameLayout {

    private static final String TAG = "ProgressBlock";
    private TextView mtextView;
    private int mMaxProgress=100;
    private View mViewProgress;
    //本控件的宽度
    private int  mWidth;
    private int mHeight;
    /**
     * 进度条的方向 默认从水平 从左边到右边或者从右边到左边
     */
    private int mDirection=LinearLayout.HORIZONTAL;
    /**
     * 在水平方向为真则是 从右边到左边，垂直方向则是从下往上 。
     */
    private boolean  mReverse=false;
    

    public boolean isReverse() {
		return mReverse;
	}
	public void setReverse(boolean reverse) {
		this.mReverse = reverse;
	}
	public int getDirection() {
		return mDirection;
	}
    /**
     * 进度块 的方向,可以调用Linelayout.方向
     * @param mDirection
     */
	public void setDirection(int direction) {
		this.mDirection = direction;
	}

	public ProgressBlock(Context context) {
        super(context);
        init(context);

    }

    public ProgressBlock(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProgressBlock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context){
        mtextView=new TextView(context);
        mtextView.setText("0%");
        mtextView.setTextColor(Color.BLACK);
        mtextView.setGravity(Gravity.CENTER);
        mViewProgress =new View(context);
//        mViewProgress.setBackgroundColor(Color.BLUE);
        mViewProgress.setBackgroundColor(Color.parseColor("#50000000"));
        mtextView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mViewProgress.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mViewProgress);
        addView(mtextView);

        post(new Runnable() {
          

			@Override
            public void run() {
                mWidth=getWidth();
                mHeight=getHeight();
            }
        });


    }
    
    public void setProgress(int progress){
    		if(getDirection()==LinearLayout.HORIZONTAL){
    		 int margin= (int) ((mWidth/(float)mMaxProgress)*progress);
    		 Log.i(TAG,"marginLeft:"+margin+","+mWidth+"progress:"+progress+",maxProgess:"+mMaxProgress);
    		        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    		        	if(isReverse()){
    		        	 layoutParams.rightMargin=margin;//从右到左边
    		        	}else{
    		        		layoutParams.leftMargin=margin;//从左边到右边 
    		        	}
    		        mViewProgress.setLayoutParams(layoutParams);
    		        if(mtextView.getVisibility()==View.VISIBLE){
    		            int  baifenbi= (int)((progress/(float)mMaxProgress)*100);
    		            mtextView.setText((baifenbi)+"%");
    		        }
    		}else if(getDirection()==LinearLayout.VERTICAL){
    			 int margin= (int) ((mHeight/(float)mMaxProgress)*progress);
        		 Log.i(TAG,"marginLeft:"+margin+","+mHeight+"progress:"+progress+",maxProgess:"+mMaxProgress);
        		        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        		        	if(isReverse()){
        		        	 layoutParams.bottomMargin=margin;//从下到上
        		        	}else{
        		        		layoutParams.topMargin =margin;//从上到下 
        		        	}
        		        mViewProgress.setLayoutParams(layoutParams);
        		        if(mtextView.getVisibility()==View.VISIBLE){
        		            int  baifenbi= (int)((progress/(float)mMaxProgress)*100);
        		            mtextView.setText((baifenbi)+"%");
        		        }
    		}
    }
    public int getProgress(){
        return mMaxProgress;
    }
    public void setProgressText(String value){
        mtextView.setText(""+value);
    }
    public String getProgressText(String value){
       return mtextView.getText().toString();
    }
    public TextView getProgressTextView(){
        return mtextView;
    }

    public void setProgressTextsetVisibility(int  visibility){
        mtextView.setVisibility(visibility);
    }
    public ColorStateList getProgressTextColor(ColorStateList color){
       return  mtextView.getTextColors();
    }
    public void setProgressTextColor(int color){
        mtextView.setTextColor(color);
    }
    public Drawable getProgressBlockBackground(ColorStateList color){
        return  mViewProgress.getBackground();
    }

    public void setProgressBlockColor(int color){
        mViewProgress.setBackgroundColor(color);

    }
    @SuppressLint("NewApi")
	public void setProgressBlockBackground(Drawable drawable){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mViewProgress.setBackground(drawable);
        }else{
        	new RuntimeException("您的手机版本不支持设置进度块背景");
        }
    }
    public void setProgressBlockBackgroundResource(int resource){
        mViewProgress.setBackgroundResource(resource);
    }
   
    public void setMaxProgress(int progress){
        this.mMaxProgress=progress;
    }

    
    public int getMaxProgress(int progress){
        return this.mMaxProgress;
    }
 

}
