package com.example.assignment1;

import android.media.session.MediaSession;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment1.data.model.post;
import com.example.assignment1.data.remote.APIService;
import com.example.assignment1.data.remote.ApiUtils;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.text.TextUtils.isEmpty;

public class MainActivity extends AppCompatActivity {

    EditText username;
    Button login;
    APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        login = findViewById(R.id.login);

        mAPIService = ApiUtils.getAPIService();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkDataEntered();
                String name = username.getText().toString().trim();

                if(!TextUtils.isEmpty(name) ) {

                    sendPost(name);

                }

            }
        });
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return (TextUtils.isEmpty(str));
    }

        void checkDataEntered() {
            //blank
            if (isEmpty(username)) {
            Toast t = Toast.makeText(this, "You must enter user name to register!", Toast.LENGTH_SHORT);
            t.show();
            }
            //space
            if (username.getText().toString().contains(" ")) {
                username.setError("No Spaces Allowed");
                }
        }
    private static final String TAG = "MainActivity";

    public void sendPost(String name) {

        Call<post> call =  mAPIService.savePost(name);

        call.enqueue(new Callback<post>() {

            @Override
            public void onResponse(Call<post> call, Response<post> response) {

               if(response.isSuccessful()) {
                  showResponse(response.body().toString());
                   Log.i(TAG, "post submitted to API." + response.toString());
            }
            }

            @Override
            public void onFailure(Call<post> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });

    }

    public interface OnGetUserCallback {
        void onGetUser(post user);

        void onError(Throwable t);
    }

    public void showResponse(String response) {
        if(username.getVisibility() == View.GONE) {
            username.setVisibility(View.VISIBLE);
        }
        username.setText(response);
    }

}