# progressblack 安卓自定义进度条 progressbar 仿QQ下载进度条.
same to qq download,block,form top to buttom,form left to right,进度块，从左边到右边从上边到下边半透明的效果


### 主要代码
```java  

   progressBlock = (ProgressBlock) findViewById(R.id.progressblock);
		progressBlock.setMaxProgress(count);//最大值可以超过100的,进度只要传递对了进行,会进行换算的
	
			case R.id.btn_switch_orientation:
			progressBlock.setDirection(progressBlock.getDirection()==LinearLayout.HORIZONTAL?LinearLayout.VERTICAL:LinearLayout.HORIZONTAL);
			break;
		case R.id.btn_switch_reverse://反转就是 比如方向是垂直方向那么是从上到下，反正开启则是从下到上,而是水平方向反转为真则是从右到左
			progressBlock.setReverse(!progressBlock.isReverse());
			break;
		case R.id.btn_switch_text_color://设置百分比字体颜色
			progressBlock.setProgressTextColor(v.getTag()==null?Color.GREEN:Color.RED);
			v.setTag(v.getTag()==null?"":null);
			break;
		case R.id.btn_switch_text_orientation:
		TextView tv= progressBlock.getProgressTextView();
		tv.setGravity(tv.getGravity()==Gravity.CENTER?Gravity.TOP:(tv.getGravity()==Gravity.TOP?Gravity.BOTTOM:Gravity.CENTER));
			break;
		case R.id.btn_switch_block_color://设置进度块颜色,要半透明哦 所以这里是rgba的填写
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
		case R.id.btn_switch_text_size://设置进度文本百分比的文本字体大小
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
   
//具体看截图 截图在最下面
   
```
### 联系我 
1.![image](http://wpa.qq.com/pa?p=1:694886526:1)[QQ694886526](http://b.qq.com/webc.htm?new=0&sid=694886526&o=情随事迁&q=7)


2.[网站http://51bwn.com](http://www.51bwn.com)

 ![image](https://github.com/51bwn/progressblack/blob/master/assets/1.png)
  ![image](https://github.com/51bwn/progressblack/blob/master/assets/2.png)
   ![image](https://github.com/51bwn/progressblack/blob/master/assets/3.png)
 ![image](https://github.com/51bwn/progressblack/blob/master/assets/4.png)
