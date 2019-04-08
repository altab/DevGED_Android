package fr.pcohen.devged;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

//import com.crashlytics.android.Crashlytics;
import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;
import java.util.Objects;

import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;

import butterknife.BindView;
//import com.openclassrooms.firebaseoc.api.UserHelper;
//import com.openclassrooms.firebaseoc.auth.ProfileActivity;
//import com.openclassrooms.firebaseoc.base.BaseActivity;
//import com.openclassrooms.firebaseoc.mentor_chat.MentorChatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnConnexion;
    private static final int RC_SIGN_IN = 123;

    @BindView(R.id.main_activity_coordinator_layout) CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnConnexion = findViewById(R.id.btn_connexion);
        btnConnexion.setOnClickListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add new ressources", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_connexion:
                //test btnConnexion
                //Toast.makeText(this, "This is my Toast message!",Toast.LENGTH_LONG).show();
                onClickLoginButton();
        }

    }

    public void onClickLoginButton() {
        // 3 - Launch Sign-In Activity when user clicked on Login Button
        this.startSignInActivity();
    }

    private void startSignInActivity(){
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setTheme(R.style.LoginTheme)
                        .setAvailableProviders(
                                Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build()))
                        .setIsSmartLockEnabled(false, true)
                        //.setLogo(R.drawable.ic_logo_auth)
                        .build(),
                RC_SIGN_IN);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 4 - Handle SignIn Activity response on activity result
        this.handleResponseAfterSignIn(requestCode, resultCode, data);
    }

    private void showSnackBar(CoordinatorLayout coordinatorLayout, String message){
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    private void handleResponseAfterSignIn(int requestCode, int resultCode, Intent data){

        IdpResponse response = IdpResponse.fromResultIntent(data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) { // SUCCESS
                //showSnackBar(this.coordinatorLayout, getString(R.string.connection_succeed));
                Log.d("SUCCESS ************ ", getString(R.string.connection_succeed));
                Toast.makeText(this, getString(R.string.connection_succeed), Toast.LENGTH_LONG).show();
            } else { // ERRORS
                if (response == null) {
                    //showSnackBar(this.coordinatorLayout, getString(R.string.error_authentication_canceled));
                    Log.d("ERR **************", getString(R.string.error_authentication_canceled));
                    Toast.makeText(this, getString(R.string.error_authentication_canceled), Toast.LENGTH_LONG).show();
                } else if (Objects.requireNonNull(response.getError()).getErrorCode() == ErrorCodes.NO_NETWORK) {
                    //showSnackBar(this.coordinatorLayout, getString(R.string.error_no_internet));
                    Log.d("ERR **************", getString(R.string.error_no_internet));
                    Toast.makeText(this, getString(R.string.error_no_internet), Toast.LENGTH_LONG).show();
                } else if (response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    //showSnackBar(this.coordinatorLayout, getString(R.string.error_unknown_error));
                    Log.d("ERR **************", getString(R.string.error_unknown_error));
                    Toast.makeText(this, getString(R.string.error_unknown_error), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}
