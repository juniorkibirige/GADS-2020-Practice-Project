package lan.tmsystem.gadsleaderboard;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lan.tmsystem.gadsleaderboard.models.Hours;
import lan.tmsystem.gadsleaderboard.models.SkillIq;

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

        mConnectionFrag = ConnectionFrag.getInstance(getSupportFragmentManager(), new String[] {"https://gadsapi.herokuapp.com/api/hours", "https://gadsapi.herokuapp.com/api/skilliq"});
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startDownload();
            }
        }, 1500);
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!DataManager.getInstance().getData().equals("") && !DataManager.getInstance().getSkilliq().equals("")) {
//                    ProcessHours(DataManager.getInstance().getData());
//                    ProcessSkills(DataManager.getInstance().getSkilliq());
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 7000);
    }

    private void startDownload() {
        if (!downloading && mConnectionFrag != null) {
            mConnectionFrag.startDownload();
            downloading = true;
        }
    }

    @Override
    public void updateFromDownload(String result) {
        Log.d("UpdateDownload", result);
        View view = (View) findViewById(R.id.cLayout);
        Snackbar.make(view, result, Snackbar.LENGTH_LONG).show();
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
                Log.d("Download Progress", "An error occurred");
                Snackbar.make(view, "An error occurred", Snackbar.LENGTH_LONG).show();
                break;
            case Progress.CONNECT_SUCCESS:
                Log.d("Download Progress", "Connection established");
                Snackbar.make(view, "Connection established", Snackbar.LENGTH_LONG).show();
                break;
            case Progress.GET_INPUT_STREAM_SUCCESS:
                Log.d("Download Progress", "Download File " + percentComplete + "/2 found");
                Snackbar.make(view, "Download File " + percentComplete + "/2 found", Snackbar.LENGTH_LONG).show();
                break;
            case Progress.PROCESS_INPUT_STREAM_IN_PROGRESS:
                Log.d("Download Progress", "Processing downloaded data");
                Snackbar.make(view, "Processing downloaded data", Snackbar.LENGTH_LONG).show();
                break;
            case Progress.PROCESS_INPUT_STREAM_SUCCESS:
                Log.d("Download Progress", "Processing downloaded data " + percentComplete + "/4 finished");
                Snackbar.make(view, "Processing downloaded data " + percentComplete + "/4 finished", Snackbar.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void finishDownloading() {
        downloading = false;
        mConnectionFrag.cancelDownload();

    }
}
