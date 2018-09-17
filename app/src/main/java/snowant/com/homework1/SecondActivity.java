package snowant.com.homework1;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {


    private static final String EMAIL_TEXT = "EMAIL_TEXT";
    private TextView textView;
    private Button button;

    public static void start(Activity activity, String emailText) {
        Intent intent = new Intent(activity, SecondActivity.class);
        intent.putExtra(EMAIL_TEXT, emailText);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.textView_second_activity);
        button = findViewById(R.id.button_second_activity);
        textView.setText(getIntent().getStringExtra(EMAIL_TEXT));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uriText =
                        "mailto:pepelanton@gmail.com" +
                                "?subject=" + Uri.encode(getString(R.string.hello)) +
                                "&body=" + Uri.encode(textView.getText().toString());

                Uri uri = Uri.parse(uriText);
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(uri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException ex) {
                        Toast.makeText(SecondActivity.this, getString(R.string.no_email_app), Toast.LENGTH_LONG).show();

                    }
                }

            }
        });
    }


}
