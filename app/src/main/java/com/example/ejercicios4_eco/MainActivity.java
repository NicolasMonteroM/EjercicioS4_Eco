package com.example.ejercicios4_eco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText IDInput1, IDInput2, IDInput3, IDInput4;
    private String ID1, ID2, ID3, ID4;
    private Button pingBtn, hostBtn;
    private TextView IPTextView;
    private String ipLocal = "";
    private boolean searchHostsOption = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IDInput1 = findViewById(R.id.IDInput1);
        IDInput2 = findViewById(R.id.IDInput2);
        IDInput3 = findViewById(R.id.IDInput3);
        IDInput4 = findViewById(R.id.IDInput4);
        pingBtn = findViewById(R.id.pingBtn);
        hostBtn = findViewById(R.id.hostBtn);
        IPTextView = findViewById(R.id.IPTextView);

        getHostIp();

        pingBtn.setOnClickListener(

                (v) -> {
                    loadPing();
                }
        );

        hostBtn.setOnClickListener(

                (v) -> {
                    loadHosts();
                }
        );


    }

    private void getHostIp() {

        new Thread(
                () -> {
                    try {

                        // InetAddress innetAddress = InetAddress.getLocalHost();
                        // ipLocal = innetAddress.getHostAddress();

                        Socket socket = new Socket();
                        socket.connect(new InetSocketAddress("google.com", 80));
                        ipLocal = socket.getLocalAddress().getHostAddress();

                        socket.close();

                        runOnUiThread(

                                () -> {
                                    IPTextView.setText(ipLocal);
                                }
                        );

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

        ).start();
    }

    private String getWrittenIp() {

        ID1 = IDInput1.getText().toString();
        ID2 = IDInput2.getText().toString();
        ID3 = IDInput3.getText().toString();
        ID4 = IDInput4.getText().toString();

        return ID1 + "." + ID2 + "." + ID3 + "." + ID4;

    }

    private void loadPing() {

        getWrittenIp();

        if (ID1.isEmpty() || ID2.isEmpty() || ID3.isEmpty() || ID4.isEmpty()) {
            Toast.makeText(this, "Ingresa todos los valores", Toast.LENGTH_SHORT).show();
        } else {
            searchHostsOption = false;
            Intent i = new Intent(this, MakePing.class);
            i.putExtra("ipWritten", getWrittenIp());
            i.putExtra("searchHostsOption", searchHostsOption);
            startActivity(i);
        }
    }

    private void loadHosts() {

        searchHostsOption = true;
        Intent i = new Intent(this, MakePing.class);

        String[] ipSplited = ipLocal.split("\\.");
        String ipHostIncompleted = ipSplited[0] + "." + ipSplited[1] + "." + ipSplited[2] + ".";

        i.putExtra("ipHost", ipHostIncompleted);
        i.putExtra("searchHostsOption", searchHostsOption);
        startActivity(i);

    }
}