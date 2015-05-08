package ap.plantuiandroidapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import ap.plantuiandroidapp.CircularProgressBarRed.ProgressAnimationListenerRed;
import ap.plantuiandroidapp.CircularProgressBarGreen.ProgressAnimationListenerGreen;
import ap.plantuiandroidapp.CircularProgressBarBlue.ProgressAnimationListenerBlue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    Button Timerbtn, Bluetoothbtn, LoadValues, color;
     CircularProgressBarRed c1 = null;
     CircularProgressBarGreen c2 = null;
     CircularProgressBarBlue c3 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timerbtn = (Button) findViewById(R.id.ButtonGoToTimerMenu);
        Timerbtn.setOnClickListener(this);


        Bluetoothbtn = (Button) findViewById(R.id.BtnGoToBluetooth);
        Bluetoothbtn.setOnClickListener(this);


        LoadValues = (Button)findViewById(R.id.ButtonLoadValues);
        LoadValues.setOnClickListener(this);
        color = (Button) findViewById(R.id.ButtonGoToColorLayer);
        color.setOnClickListener(this);

        c1 = (CircularProgressBarRed) findViewById(R.id.circularprogressbarred);
        c1.setEnabled(false);
        c2 = (CircularProgressBarGreen) findViewById(R.id.circularprogressbargreen);
        c2.setEnabled(false);
        c3 = (CircularProgressBarBlue) findViewById(R.id.circularprogressbarblue);
        c3.setEnabled(false);
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
            final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setMessage("Need help at main screen?\n" +
                    "\n" +
                    "What can you see from main screen?\n" +
                    "\t- Visual values of lights\n" +
                    "\n" +
                    "What can you set up from main screen?\n" +
                    "\t- Go to bluetooth and connect to device\n" +
                    "\t- Go to clock screen and set sleep timings\n" +
                    "\t- Go to led screen to adjust lights\n" +
                    "\n" +
                    "Still having problems?\n" +
                    "Go to plantui.com/support/application/main\n" +
                    "\n" +
                    "Or send feedback or support request directly to us from\n" +
                    "plantui.com/support");
            alert.setTitle("Help");
            alert.setIcon(R.drawable.help);
            alert.create();
            alert.show();
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ButtonGoToTimerMenu:
                startActivity(new Intent("ap.plantuiandroidapp.timermenu"));
                break;
            case R.id.BtnGoToBluetooth:
                startActivity(new Intent("ap.plantuiandroidapp.bluetooth"));
                break;
            case R.id.ButtonGoToColorLayer:
                startActivity(new Intent("ap.plantuiandroidapp.Colors"));
                break;
            case R.id.ButtonLoadValues:
                LoadValues();
                break;
        }
    }
    public void LoadValues()
    {

        try {
            FileInputStream fileRedLed =  openFileInput("ValueRed.txt");
            FileInputStream fisGreenLed = openFileInput("ValueGreen.txt");
            FileInputStream fisBlueLed = openFileInput("ValueBlue.txt");
            int read;

            //a while because otherwise its only the first byte he read and then stops.
            /*
            *
            *what u see when u open a file?
            *255
            * what it actually contained!
            *32 40 40 for example that's why we use (char)
             */

            while ((read = fileRedLed.read()) != -1)
            {
               char d= (char)read;
              double Calculation = (((double)d/255)*100);


                c1.animateProgressTo(0, ((int) Calculation), new ProgressAnimationListenerRed() {


                    @Override
                    public void onAnimationStart() {
                    }

                    @Override
                    public void onAnimationProgress(int progress) {
                        c1.setTitle(progress + "%");

                    }
                    @Override
                    public void onAnimationFinish() {
                        c1.setSubTitle("RED");
                    }
                });

            }
            while((read = fisGreenLed.read())!=-1)
            {
                char d= (char)read;
                double Calculation = (((double)d/255)*100);

                c2.animateProgressTo(0, (int)Calculation, new ProgressAnimationListenerGreen() {

                    @Override
                    public void onAnimationStart() {
                    }

                    @Override
                    public void onAnimationProgress(int progress21) {
                        c2.setTitle(progress21 + "%");
                    }

                    @Override
                    public void onAnimationFinish() {
                        c2.setSubTitle("GREEN");
                    }
                });

            }
            while((read = fisBlueLed.read())!=-1)
            {
                char d= (char)read;
                double Calculation = (((double)d/255)*100);

                c3.animateProgressTo(0, (int)Calculation , new ProgressAnimationListenerBlue() {

                    @Override
                    public void onAnimationStart() {
                    }

                    @Override
                    public void onAnimationProgress(int progress31) {
                        c3.setTitle(progress31 + "%");
                    }

                    @Override
                    public void onAnimationFinish() {
                        c3.setSubTitle("BLUE");
                    }
                });
            }

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
