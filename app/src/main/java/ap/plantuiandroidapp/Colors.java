package ap.plantuiandroidapp;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Colors extends ActionBarActivity implements View.OnClickListener{

    private SeekBar SeekbarIntensityRed,SeekbarIntensityBlue, SeekbarIntensityGreen;
    private TextView IntensityRed, IntensityGreen ,IntensityBlue;
    int ValueGreen ,ValueRed ,ValueBlue;
    Button Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
        Save = (Button)findViewById(R.id.ButtonSaveValue);
        Save.setOnClickListener(this);
        SeekbarIntensityRed = (SeekBar)findViewById(R.id.SeekBarIntensityRed);
        IntensityRed = (TextView)findViewById(R.id.IntensityRedLed);
        SeekbarIntensityRed.setMax(255);

        SeekbarIntensityRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                IntensityRed.setText(progress + "");
                ValueRed = progress;





            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SeekbarIntensityGreen = (SeekBar)findViewById(R.id.SeekBarIntensityGreen);
        IntensityGreen = (TextView)findViewById(R.id.IntensityGreenLed);
        SeekbarIntensityGreen.setMax(255);

        SeekbarIntensityGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                IntensityGreen.setText(progress + "");
                ValueGreen = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SeekbarIntensityBlue = (SeekBar)findViewById(R.id.SeekBarIntensityBlue);
        IntensityBlue = (TextView)findViewById(R.id.IntensityBlueLed);
        SeekbarIntensityBlue.setMax(255);
        SeekbarIntensityBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                IntensityBlue.setText(progress + "");
                ValueBlue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Save.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_colors, menu);
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
            final AlertDialog.Builder alert = new AlertDialog.Builder(Colors.this);
            alert.setMessage("Need help at led screen?\n" +
                    "\n" +
                    "What can you see from led screen?\n" +
                    "\t- Visual values of lights\n" +
                    "\n" +
                    "What can you set up from led screen?\n" +
                    "\t- Set the led light intensity from 0% to 100% (0 shutdown, 255 at max power)\n" +
                    "\n" +
                    "Still having problems?\n" +
                    "Go to plantui.com/support/application/led\n" +
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
        Context context = getApplicationContext();
        CharSequence text = "Save Successful!!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);

        switch(v.getId())
        {
            case R.id.ButtonSaveValue:
                SaveValue();
                toast.show();
                finish();
                break;
        }
    }
    public void SaveValue()
    {
        //try catch for exception handling
        FileOutputStream fosred = null;
        FileOutputStream fosgreen = null;
        FileOutputStream fosblue= null;
        try {

            fosred = openFileOutput("ValueRed.txt", Context.MODE_PRIVATE);
            fosred.write(ValueRed);
            fosgreen = openFileOutput("ValueGreen.txt", Context.MODE_PRIVATE);
            fosgreen.write(ValueGreen);
            fosblue = openFileOutput("ValueBlue.txt", Context.MODE_PRIVATE);
            fosblue.write(ValueBlue);

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            //not caring if there is an exception or not this is running anyway.
            if(fosred != null && fosgreen != null && fosblue != null)
                try {
                       fosred.close();
                       fosgreen.close();
                       fosblue.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }}

    }


