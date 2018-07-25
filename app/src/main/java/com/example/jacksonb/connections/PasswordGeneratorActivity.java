package com.example.jacksonb.connections;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PasswordGeneratorActivity extends AppCompatActivity
{
    Button calcButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_generator_layout);

        calcButton = (Button) findViewById(R.id.btnCalc);
        calcButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CalcPassword();
            }
        });
    }

    public void CalcPassword()
    {
        try
        {
            Time dt = new Time(Time.getCurrentTimezone());
            dt.setToNow();
            int iyear = dt.year - 1980;
            int iday = (dt.yearDay - 4) + (3);
            EditText txtserial = (EditText) findViewById(R.id.txtserial);

            try
            {

                if (txtserial.getText().toString() != BuildConfig.FLAVOR)
                {
                    ((TextView) findViewById(R.id.txtpassword)).setText(Integer.toString(((Integer.parseInt(ConvertToNumericSerial(txtserial.getText().toString())) + iday) * ((iday * 64) | iyear)) % 1000000));

                }

            }
            catch (NumberFormatException e) {Log.d("test2", "Exception 1");e.printStackTrace();}

        }
        catch (Exception e2) {Log.d("test2", "Exception 2");}

    }



    private String ConvertToNumericSerial(String input_string)
    {

        String serialno = BuildConfig.FLAVOR;

        input_string = input_string.trim();

        for (int i = 0; i < input_string.length(); i++)
        {

            switch (input_string.charAt(i))
            {

                case 'A':

                case 'K':

                case 'U':

                    serialno = serialno + "0";

                    break;

                case 'B':

                case 'L':

                case 'V':

                    serialno = serialno + "1";

                    break;

                case 'C':

                case 'M':

                case 'W':

                    serialno = serialno + "2";

                    break;

                case 'D':

                case 'N':

                case 'X':

                    serialno = serialno + "3";

                    break;

                case 'E':

                case 'O':

                case 'Y':

                    serialno = serialno + "4";

                    break;

                case 'F':

                case 'P':

                case 'Z':

                    serialno = serialno + "5";

                    break;

                case 'G':

                case 'Q':

                    serialno = serialno + "6";

                    break;

                case 'H':

                case 'R':

                    serialno = serialno + "7";

                    break;

                case 'I':

                case 'S':

                    serialno = serialno + "8";

                    break;

                case 'J':

                case 'T':

                    serialno = serialno + "9";

                    break;

                case 'a':

                case 'k':

                case 'u':

                    serialno = serialno + "0";

                    break;

                case 'b':

                case 'l':

                case 'v':

                    serialno = serialno + "1";

                    break;

                case 'c':

                case 'm':

                case 'w':

                    serialno = serialno + "2";

                    break;

                case 'd':

                case 'n':

                case 'x':

                    serialno = serialno + "3";

                    break;

                case 'e':

                case 'o':

                case 'y':

                    serialno = serialno + "4";

                    break;

                case 'f':

                case 'p':

                case 'z':

                    serialno = serialno + "5";

                    break;

                case 'g':

                case 'q':

                    serialno = serialno + "6";

                    break;

                case 'h':

                case 'r':

                    serialno = serialno + "7";

                    break;

                case 'i':

                case 's':

                    serialno = serialno + "8";

                    break;

                case 'j':

                case 't':

                    serialno = serialno + "9";

                    break;

                default:

                    serialno = serialno + input_string.charAt(i);

                    break;

            }

        }

        return serialno;

    }
}
