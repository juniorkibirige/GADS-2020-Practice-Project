package lan.tmsystem.gadsleaderboard;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.snackbar.Snackbar;

public class SplashScreen extends FragmentActivity implements DownloadCallback<String> {
    Handler mHandler;
    DataManager mDataManager;
    private ConnectionFrag mConnectionFrag;
    private boolean downloading = false;
    private static String[] urls = {"https://gadsapi.herokuapp.com/api/hours", "https://gadsapi.herokuapp.com/api/skilliq"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        mDataManager = DataManager.getInstance();

        mConnectionFrag = ConnectionFrag.getInstance(getSupportFragmentManager(), new String[]{"https://gadsapi.herokuapp.com/api/hours", "https://gadsapi.herokuapp.com/api/skilliq"});
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startDownload();
            }
        }, 1500);
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (!DataManager.getInstance().getData().equals("") && !DataManager.getInstance().getSkilliq().equals("")) {
//                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    private void startDownload() {
        if (!downloading && mConnectionFrag != null) {
            mConnectionFrag.startDownload();
            downloading = true;
        }
    }

    @Override
    public void updateFromDownload(String result) {
        if (result.contains("No address associated with hostname") || result.contains("Failed to connect")) {
            View view = (View) findViewById(R.id.cLayout);
            Snackbar.make(view, "Check your Internet Connection!", 5000).show();
            mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 6000);
        } else if (result.contains("timed out")) {
            View view = (View) findViewById(R.id.cLayout);
            Snackbar.make(view, "Connection Timed Out!", 5000).show();
            mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 6000);
        }
    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo();
    }

    @Override
    public void onProgressUpdate(int progressCode, int percentComplete) {
        View view = (View) findViewById(R.id.cLayout);
        switch (progressCode) {
            case Progress.ERROR:
//                Log.d("Download Progress", "An error occurred");
                Snackbar.make(view, "An error occurred", Snackbar.LENGTH_LONG).show();
                break;
            case Progress.CONNECT_SUCCESS:
//                Log.d("Download Progress", "Connection established");
//                Snackbar.make(view, "Connection established", Snackbar.LENGTH_LONG).show();
                break;
            case Progress.GET_INPUT_STREAM_SUCCESS:
//                Log.d("Download Progress", "Download File " + percentComplete + "/2 found");
//                Snackbar.make(view, "Download File " + percentComplete + "/2 found", Snackbar.LENGTH_LONG).show();
                break;
            case Progress.PROCESS_INPUT_STREAM_IN_PROGRESS:
//                Log.d("Download Progress", "Processing downloaded data");
//                Snackbar.make(view, "Processing downloaded data", Snackbar.LENGTH_LONG).show();
                break;
            case Progress.PROCESS_INPUT_STREAM_SUCCESS:
//                Log.d("Download Progress", "Processing downloaded data " + percentComplete + "/4 finished");
//                Snackbar.make(view, "Processing downloaded data " + percentComplete + "/4 finished", Snackbar.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void finishDownloading() {
        downloading = false;
        mConnectionFrag.cancelDownload();
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
