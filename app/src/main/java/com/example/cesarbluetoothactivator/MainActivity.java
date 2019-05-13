package com.example.cesarbluetoothactivator;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
    }

    private void setupUI(){
        final Button btnEncender = findViewById(R.id.btn_encender);

        final Button btnApagar = findViewById(R.id.btn_apagar);

        final EditText showHistory = (EditText) findViewById(R.id.showHistory);

        final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        final StringBuffer historyText = new StringBuffer();

        btnEncender.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "";
                int duration = Toast.LENGTH_SHORT;
                Toast toast;

                if (mBluetoothAdapter == null) {
                    text = "Este dispositivo no dispone de Bluetooth";
                }else{
                    if(!mBluetoothAdapter.isEnabled()) {
                        mBluetoothAdapter.enable();
                        historyText.append('\n').append(Calendar.getInstance().getTime()).append(" Se activo el bluetooth");
                        showHistory.setText(historyText.toString());
                        text = "El Bluetooth se ha activado con exito";
                        btnEncender.setEnabled(false);
                        btnApagar.setEnabled(true);
                    }
                }
                toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        btnApagar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "";
                int duration = Toast.LENGTH_SHORT;
                Toast toast;

                if (mBluetoothAdapter == null) {
                    text = "Este dispositivo no dispone de Bluetooth";
                }else{
                    if(mBluetoothAdapter.isEnabled()){
                        mBluetoothAdapter.disable();
                        historyText.append('\n').append(Calendar.getInstance().getTime()).append(" Se desactivo el bluetooth");
                        showHistory.setText(historyText.toString());
                        text = "El Bluetooth fue inactivado con exito";
                        btnApagar.setEnabled(false);
                        btnEncender.setEnabled(true);
                    }
                }
                toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });
    }
}
