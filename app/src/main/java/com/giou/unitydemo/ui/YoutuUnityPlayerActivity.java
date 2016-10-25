package com.giou.unitydemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.giou.unitydemo.MainActivity;
import com.giou.unitydemo.R;
import com.giou.unitydemo.utils.UIUtils;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;


public class YoutuUnityPlayerActivity extends UnityPlayerActivity {

	private static final String TAG = YoutuUnityPlayerActivity.class.getSimpleName();

	public static YoutuUnityPlayerActivity Instance = null;
	private Activity mBackActivity = null;
	private LinearLayout mSplashLayout = null;
	private RelativeLayout mUnityLoading;
	private ImageView mUnityLoadingImage;

	// Setup activity layout
	@Override protected void onCreate (Bundle savedInstanceState) {

		Instance = this;
		super.onCreate(savedInstanceState);

		mSplashLayout = (LinearLayout)this.getLayoutInflater().inflate(R.layout.splash_layout, null);

		mUnityPlayer.addView(mSplashLayout);
		gotoNextActivity();
	}

	private void gotoNextActivity() {
		Log.d(TAG, "loadLauncherActivity");

		UIUtils.postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(YoutuUnityPlayerActivity.this, MainActivity.class);
				startActivity(intent);
				overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			}
		}, 2000);
		UIUtils.postDelayed(new Runnable() {
			@Override
			public void run() {
				mUnityPlayer.removeView(mSplashLayout);
			}
		}, 5000);

	}

	// Quit Unity
	@Override
	protected void onDestroy () {
		super.onDestroy();
		Instance = null;
	}

	//Unity Interface begin
	public void loadLauncherActivity() {

	}

	public void loadBackActivity() {

	}

//	public void selectScene(String scene){
//		UnityPlayer.UnitySendMessage("MessageCenter", "postSceneFromAndroid", scene);
//	}

//	public void initPlayer(String name){
//		UnityPlayer.UnitySendMessage("MessageCenter", "initPlayer", name);
//	}

	public void loadUnityScene(Activity backActivity) {

//		mSplashLayout = (LinearLayout)this.getLayoutInflater().inflate(R.layout.splash_layout, null);
//		mUnityPlayer.addView(mSplashLayout);

//		mUnityLoading = (RelativeLayout) this.getLayoutInflater().inflate(R.layout.unity_layout, null);
//		mUnityLoadingImage = (ImageView) mUnityLoading.findViewById(R.id.iv_unity_loading);
//		Glide.with(UIUtils.getContext()).load(R.drawable.loading_bg2).into(mUnityLoadingImage);
//
//		mUnityPlayer.addView(mUnityLoading);

		mBackActivity = backActivity;

		Log.d(TAG, "loadUnityScene is : " + backActivity);
		Intent intent = new Intent(getApplicationContext(), YoutuUnityPlayerActivity.class);
		backActivity.startActivity(intent);
	}

	private void removeUnityPlayerPic() {
//		UIUtils.postDelayed(new Runnable() {
//			@Override
//			public void run() {
//				Log.d(TAG,"removeUnityPlayerPic");
//				mUnityPlayer.removeView(mSplashLayout);
//			}
//		},2000);
	}

	public void unityGameStart(){
//		mUnityPlayer.removeView(mSplashLayout);
		UIUtils.postDelayed(new Runnable() {
			@Override
			public void run() {
				Log.d(TAG,"unityGameStart");
				mUnityPlayer.removeView(mUnityLoading);
			}
		},2000);
	}


	public void postMessageToUnity(String paramMessage) {
		UnityPlayer.UnitySendMessage("MessageCenter", "postMessageFromAndroid", paramMessage);

	}


}
