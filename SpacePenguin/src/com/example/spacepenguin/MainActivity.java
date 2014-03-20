package com.example.spacepenguin;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.spacepenguin.element.Universe;
import com.example.spacepenguin.renderer.GameRenderer;

public class MainActivity extends Activity {

	private GameGLSurfaceView surfaceView;
	private TextView gameover;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		surfaceView = (GameGLSurfaceView) findViewById(R.id.gameGLSurfaceView1);

		
		
		final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();

		final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

		Universe universe = new Universe();
		
		
		if (supportsEs2) {
			// Request an OpenGL ES 2.0 compatible context.
			surfaceView.setEGLContextClientVersion(2);

			final GameRenderer mRenderer = new GameRenderer(this, surfaceView,universe);
			
			TextView score = (TextView) findViewById(R.id.score);
			mRenderer.setScore(score);

			gameover = (TextView) findViewById(R.id.textgameover);
			mRenderer.setGameover(gameover);
			gameover.setVisibility(View.GONE);
			
			
			surfaceView.setRenderer(mRenderer);
			surfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
		} else {
			// This is where you could create an OpenGL ES 1.x compatible
			// renderer if you wanted to support both ES 1 and ES 2.
			return;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void start(View view){
		surfaceView.start();
		gameover.setVisibility(View.GONE);
	}
	

	

		
		
	}
