package lan.tmsystem.gadsleaderboard;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import lan.tmsystem.gadsleaderboard.models.Hours;
import lan.tmsystem.gadsleaderboard.models.SkillIq;

public class ConnectionFrag extends Fragment {
    public static final String TAG = "ConnectionFrag";
    private static final String URK_KEY = "UrkKey";

    private DownloadCallback<String> callback;
    private DownloadTask mDownloadTask;
    private String urlString;
    public String[] urlS;

    public ConnectionFrag(String[] s) {
        this.urlS = s;
    }

    public static ConnectionFrag getInstance(FragmentManager fragmentManager, String[] url) {
        ConnectionFrag connectionFrag = new ConnectionFrag(url);
        Bundle args = new Bundle();
        args.putString(URK_KEY + '1', url[0]);
        args.putString(URK_KEY + '2', url[1]);
        connectionFrag.setArguments(args);
        fragmentManager.beginTransaction().add(connectionFrag, TAG).commit();
        return connectionFrag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        urlString = getArguments().getString(URK_KEY);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callback = (DownloadCallback<String>) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onDestroy() {
        cancelDownload();
        super.onDestroy();
    }

    public void startDownload() {
        cancelDownload();
        mDownloadTask = new DownloadTask(callback);
        mDownloadTask.execute(urlString);
    }

    public void cancelDownload() {
        if (mDownloadTask != null) {
            mDownloadTask.cancel(true);
        }
    }

    private static class DownloadTask extends AsyncTask<String, Integer, DownloadTask.Result> {
        private static int retrn = 1;
        private static int process = 1;
        private DownloadCallback<String> callback;
        String[] urls = {"https://gadsapi.herokuapp.com/api/hours", "https://gadsapi.herokuapp.com/api/skilliq"};

        DownloadTask(DownloadCallback<String> callback) {
            setCallback(callback);
        }

        void setCallback(DownloadCallback<String> callback) {
            this.callback = callback;
        }

        static class Result {
            public String resultValue;
            public Exception exception;

            public Result(String resultValue) {
                this.resultValue = resultValue;
            }

            public Result(Exception exception) {
                this.exception = exception;
            }
        }

        @Override
        protected DownloadTask.Result doInBackground(String... urls) {
            Result result = null;
            urls = this.urls;
            if (!isCancelled() && urls != null && urls.length > 0) {
                for (String urlString : urls) {
                    try {
                        URL url = new URL(urlString);
                        String resultString = downloadUrl(url);
                        if (resultString != null) {
                            result = new Result(resultString);
                            if (result.resultValue != null) {
                                processData(retrn++, result.resultValue);
                            }
                        } else {
                            throw new IOException("No response received.");
                        }
                    } catch (Exception e) {
                        result = new Result(e);
                    }
                }
            }
            return result;
        }

        @Override
        protected void onPreExecute() {
            if (callback != null) {
                NetworkInfo networkInfo = callback.getActiveNetworkInfo();
                if (networkInfo == null || !networkInfo.isConnected() ||
                        (networkInfo.getType() != ConnectivityManager.TYPE_WIFI && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
                    callback.updateFromDownload("No network connection!");
                    cancel(true);
                }
            }
        }

        @Override
        protected void onPostExecute(Result result) {
            if (result != null && callback != null) {
                if (result.exception != null) {
                    callback.updateFromDownload(result.exception.getMessage());
                } else if (result.resultValue != null) {
                    processDataFurther();
                }
                callback.finishDownloading();
            }
        }

        private void processDataFurther() {
            String h = DataManager.getInstance().getData();
            String iq = DataManager.getInstance().getSkilliq();
            ProcessHours(h);
            ProcessSkills(iq);
        }

        private void ProcessSkills(String skilliq) {
            try {
                JSONArray hours = new JSONArray(skilliq);
                publishProgress(DownloadCallback.Progress.PROCESS_INPUT_STREAM_IN_PROGRESS);
                for (int i = 0; i < hours.length(); i++) {
                    JSONObject h = hours.getJSONObject(i);

                    SkillIq skillIq = new SkillIq(h.getString("name"), h.getString("score"), h.getString("country"), h.getString("badgeUrl"));
                    DataManager.getInstance().addSkillIq(skillIq);
                }
                publishProgress(DownloadCallback.Progress.PROCESS_INPUT_STREAM_SUCCESS);
            } catch (JSONException e) {
                publishProgress(DownloadCallback.Progress.ERROR);
                e.printStackTrace();
            }
        }

        private void ProcessHours(String data) {
            try {
                JSONArray hours = new JSONArray(data);
                publishProgress(DownloadCallback.Progress.PROCESS_INPUT_STREAM_IN_PROGRESS);
                for (int i = 0; i < hours.length(); i++) {
                    JSONObject h = hours.getJSONObject(i);

                    Hours hours1 = new Hours(h.getString("name"), h.getString("hours"), h.getString("country"), h.getString("badgeUrl"));
                    DataManager.getInstance().addHours(hours1);
                }
                publishProgress(DownloadCallback.Progress.PROCESS_INPUT_STREAM_SUCCESS);
            } catch (JSONException e) {
                publishProgress(DownloadCallback.Progress.ERROR);
                e.printStackTrace();
            }
        }

        private void processData(int i, String res) {
            if (i == 1) {
                DataManager.getInstance().setData(res);
            } else if (i == 2) {
                DataManager.getInstance().setSkilliq(res);
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d("Progress", String.valueOf(retrn));
            Log.d("Progress", String.valueOf(process));
            if (retrn <= 2)
                callback.onProgressUpdate(values[0], retrn);
            else callback.onProgressUpdate(values[0], process++);
        }

        @Override
        protected void onCancelled(Result result) {
            Log.d("Download", "Cancelled");
        }

        private String downloadUrl(URL url) throws IOException {
            InputStream stream = null;
//            HttpsURLConnection connection = null;
            HttpURLConnection connection = null;
            String result = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(3000);
                connection.setConnectTimeout(3000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();
                publishProgress(DownloadCallback.Progress.CONNECT_SUCCESS);
                int responseCode = connection.getResponseCode();
                if (responseCode != HttpsURLConnection.HTTP_OK) {
                    throw new IOException("HTTP error code: " + responseCode);
                }
                stream = connection.getInputStream();
                publishProgress(DownloadCallback.Progress.GET_INPUT_STREAM_SUCCESS, 0);
                if (stream != null) {
                    result = readStream(stream, 5000);
                }
            } finally {
                if (stream != null) {
                    stream.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return result;
        }

        public String readStream(InputStream stream, int maxReadSize) throws IOException {
            Reader reader;
            reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            char[] rawBuffer = new char[maxReadSize];
            int readSize;
            StringBuilder buffer = new StringBuilder();
            while (((readSize = reader.read(rawBuffer)) != -1) && maxReadSize > 0) {
                if (readSize > maxReadSize) {
                    readSize = maxReadSize;
                }
                buffer.append(rawBuffer, 0, readSize);
                maxReadSize -= readSize;
            }
            return buffer.toString();
        }
    }
}
