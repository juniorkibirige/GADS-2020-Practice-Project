package lan.tmsystem.gadsleaderboard;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import lan.tmsystem.gadsleaderboard.dialogs.CustomDialog;

public class SubmitActivity extends AppCompatActivity {
    EditText txt_first, txt_last, txt_email, txt_link;
    ImageView errorFirst, errorLast, errorEmail, errorLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        txt_first = findViewById(R.id.txtFirstName);
        txt_last = findViewById(R.id.txtLastName);
        txt_email = findViewById(R.id.txtEmail);
        txt_link = findViewById(R.id.txtGithub);
        errorFirst = findViewById(R.id.errorFirst);
        errorLast = findViewById(R.id.errorLast);
        errorEmail = findViewById(R.id.errorEmail);
        errorLink = findViewById(R.id.errorLink);

        errorLink.setVisibility(View.INVISIBLE);
        errorLast.setVisibility(View.INVISIBLE);
        errorFirst.setVisibility(View.INVISIBLE);
        errorEmail.setVisibility(View.INVISIBLE);
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
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    public void onSubmit(View view) {
        errorLink.setVisibility(View.INVISIBLE);
        errorLast.setVisibility(View.INVISIBLE);
        errorFirst.setVisibility(View.INVISIBLE);
        errorEmail.setVisibility(View.INVISIBLE);

        String txtFirstName = txt_first.getText().toString();
        String txtLastName = txt_last.getText().toString();
        String txtEmail = txt_email.getText().toString();
        String txtProjectLink = txt_link.getText().toString();

        if (txtFirstName.equals("") && txtLastName.equals("") && txtEmail.equals("") && txtProjectLink.equals("")) {
            errorFirst.setVisibility(View.VISIBLE);
            errorLast.setVisibility(View.VISIBLE);
            errorEmail.setVisibility(View.VISIBLE);
            errorLink.setVisibility(View.VISIBLE);
        } else if (txtFirstName.equals("")) {
            errorFirst.setVisibility(View.VISIBLE);
        } else if (txtLastName.equals("")) {
            errorLast.setVisibility(View.VISIBLE);
        } else if (txtEmail.equals("")) {
            errorEmail.setVisibility(View.VISIBLE);
        } else if (txtProjectLink.equals("")) {
            errorLink.setVisibility(View.VISIBLE);
        } else {
            CustomDialog customDialog = new CustomDialog(SubmitActivity.this, txt_first, txt_last, txt_email, txt_link);
            Objects.requireNonNull(customDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            customDialog.show();
        }
    }
}
