package com.example.edu.newportpoliofeeder;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,ListView.OnItemClickListener {
    private BluetoothAdapter bluetoothAdapter=null;
    Set<BluetoothDevice> pairedDeviceList;
    ListView listViewPairedDeviceList;
    Button btnOn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        btnOn = findViewById(R.id.btnOn);
        btnOn.setOnClickListener(this);

        listViewPairedDeviceList = findViewById(R.id.listViewPairedDeviceList);
    }

    @Override
    public void onClick(View v) {
        pairedDeviceList = bluetoothAdapter.getBondedDevices();
        ArrayList pairedList = new ArrayList();
        for (BluetoothDevice bt : pairedDeviceList){
            pairedList.add(bt.getName() + "\n" + bt.getAddress());
        }
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, pairedList);
        listViewPairedDeviceList.setAdapter(adapter);
        listViewPairedDeviceList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String info = ((TextView)view).getText().toString();
        String address = info.substring(info.length() - 17);
        Intent intent = new Intent(view.getContext(), BlutoothActivity.class);
        intent.putExtra("device_address", address);
        startActivity(intent);

    }
}
