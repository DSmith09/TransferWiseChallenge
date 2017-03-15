package com.example.dmsmith.transferwisecodechallenge.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.dmsmith.transferwisecodechallenge.MyApp;
import com.example.dmsmith.transferwisecodechallenge.R;
import com.example.dmsmith.transferwisecodechallenge.spotify.service.SpotifyService;
import com.spotify.sdk.android.player.Spotify;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @Inject
    SpotifyService mSpotifyService;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.login_button)
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        injectDependencies();
        setOnClickListeners();
    }

    private void injectDependencies() {
        ((MyApp) getApplication()).getLoginActivityComponents().inject(this);
    }

    private void setOnClickListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSpotifyService.authenticate(LoginActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        mSpotifyService.handleCallback(this, requestCode, resultCode, intent);
        startActivity(PlaylistsListActivity.newIntent(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Spotify.destroyPlayer(mSpotifyService);
    }
}
