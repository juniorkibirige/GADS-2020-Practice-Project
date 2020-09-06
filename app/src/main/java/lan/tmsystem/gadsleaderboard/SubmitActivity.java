package lan.tmsystem.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class SubmitActivity extends AppCompatActivity {
    EditText txt_first, txt_last, txt_email, txt_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        ImageButton backBtn = findViewById(R.id.backSubmit);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmitActivity.super.onBackPressed();
            }
        });
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

    public void onSubmit(View view) {
        txt_first = view.findViewById(R.id.txtFirstName);
        txt_last = view.findViewById(R.id.txtLastName);
        txt_email = view.findViewById(R.id.txtEmail);
        txt_link = view.findViewById(R.id.txtGithub);

        if(txt_first.getText().toString().equals("")) {

        }
    }
}