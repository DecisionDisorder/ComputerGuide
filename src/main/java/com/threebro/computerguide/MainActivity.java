package com.threebro.computerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;

import com.threebro.computerguide.CSV.CPU;
import com.threebro.computerguide.CSV.Case;
import com.threebro.computerguide.CSV.Cooler;
import com.threebro.computerguide.CSV.GPU;
import com.threebro.computerguide.CSV.MainBoard;
import com.threebro.computerguide.CSV.Power;
import com.threebro.computerguide.CSV.RAM;
import com.threebro.computerguide.CSV.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<CPU> CPUList = new ArrayList<>();
    private List<MainBoard> mbList = new ArrayList<>();
    private List<RAM> RAMList = new ArrayList<>();
    private List<GPU> GPUList = new ArrayList<>();
    private List<Power> PWList = new ArrayList<>();
    private List<Case> CaseList = new ArrayList<>();
    private List<Cooler> CLList = new ArrayList<>();
    private List<Storage> STList = new ArrayList<>();


    private void readStorageData(){
        InputStream is = getResources().openRawResource(R.raw.storagecsv);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));

        String line ="";
        try{
            reader.readLine();
            while( (line = reader.readLine()) != null){
                Log.d("MyActivity","Line: " + line);
                String[] tokens = line.split(",");
                Storage st = new Storage();
                st.setName(tokens[0]);
                st.setType(tokens[1]);
                st.setPc(tokens[2]);
                st.setCapacity(tokens[3]);
                st.setPrice(Integer.parseInt(tokens[4]));
                STList.add(st);
                Log.d("MyActivity", "Just created: " + st);
            }
        } catch (IOException e) {
            Log.d("MyActivity", "Error reading data file "+line,e);
            e.printStackTrace();
        }
    }

    private void readCoolData(){
        InputStream is = getResources().openRawResource(R.raw.coolcsv);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));

        String line ="";
        try{
            reader.readLine();
            while( (line = reader.readLine()) != null){
                Log.d("MyActivity","Line: " + line);
                String[] tokens = line.split(",");
                Cooler cl = new Cooler();
                cl.setName(tokens[0]);
                cl.setType(tokens[1]);
                cl.setPrice(Integer.parseInt(tokens[2]));
                cl.setRPM(Integer.parseInt(tokens[3]));
                CLList.add(cl);
                Log.d("MyActivity", "Just created: " + cl);
            }
        } catch (IOException e) {
            Log.d("MyActivity", "Error reading data file "+line,e);
            e.printStackTrace();
        }
    }

    private void readCaseData(){
        InputStream is = getResources().openRawResource(R.raw.casecsv);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));

        String line ="";
        try{
            reader.readLine();
            while( (line = reader.readLine()) != null){
                Log.d("MyActivity","Line: " + line);
                String[] tokens = line.split(",");
                Case cs = new Case();
                cs.setName(tokens[0]);
                cs.setType(tokens[1]);
                cs.setSize(tokens[2]);
                cs.setPrice(Integer.parseInt(tokens[3]));
                CaseList.add(cs);
                Log.d("MyActivity", "Just created: " + cs);
            }
        } catch (IOException e) {
            Log.d("MyActivity", "Error reading data file "+line,e);
            e.printStackTrace();
        }
    }

    private void readPWData(){
        InputStream is = getResources().openRawResource(R.raw.powercsv);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));

        String line ="";
        try{
            reader.readLine();
            while( (line = reader.readLine()) != null){
                Log.d("MyActivity","Line: " + line);

                String[] tokens = line.split(",");
                Power pw = new Power();
                pw.setName(tokens[0]);
                pw.setType(tokens[1]);
                pw.setSpecification(Integer.parseInt(tokens[2]));
                pw.setCertification(tokens[3]);
                pw.setPrice(Integer.parseInt(tokens[4]));

                PWList.add(pw);
                Log.d("MyActivity", "Just created: " + pw);
            }
        } catch (IOException e) {
            Log.d("MyActivity", "Error reading data file "+line,e);
            e.printStackTrace();
        }
    }

    private void readGPUData(){
        InputStream is = getResources().openRawResource(R.raw.gpucsv);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));

        String line ="";
        try{
            reader.readLine();
            while( (line = reader.readLine()) != null){
                Log.d("MyActivity","Line: " + line);

                String[] tokens = line.split(",");
                GPU gpu = new GPU();
                gpu.setName(tokens[0]);
                gpu.setSeries(tokens[1]);
                gpu.setPrice(Integer.parseInt(tokens[2]));
                gpu.setPower(Integer.parseInt(tokens[3]));
                gpu.setHDMI(Boolean.parseBoolean(tokens[4]));
                gpu.setDP(Boolean.parseBoolean(tokens[5]));
                gpu.setDVI(Boolean.parseBoolean(tokens[6]));
                GPUList.add(gpu);
                Log.d("MyActivity", "Just created: " + gpu);
            }
        } catch (IOException e) {
            Log.d("MyActivity", "Error reading data file "+line,e);
            e.printStackTrace();
        }
    }

    private void readRAMData(){
        InputStream is = getResources().openRawResource(R.raw.ramcsv);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));

        String line ="";
        try{
            reader.readLine();
            while( (line = reader.readLine()) != null){
                Log.d("MyActivity","Line: " + line);

                String[] tokens = line.split(",");
                RAM rm = new RAM();
                rm.setName(tokens[0]);
                rm.setRamCapacity(tokens[1]);
                rm.setMemoryStandard(tokens[2]);
                rm.setMemoryClock(tokens[3]);
                rm.setStock(tokens[4]);
                rm.setPrice(Integer.parseInt(tokens[5]));
                RAMList.add(rm);
                Log.d("MyActivity", "Just created: " + rm);
            }
        } catch (IOException e) {
            Log.d("MyActivity", "Error reading data file "+line,e);
            e.printStackTrace();
        }
    }

    private void readMBData(){
        InputStream is = getResources().openRawResource(R.raw.mbcsv);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));

        String line ="";
        try{
            reader.readLine();
            while( (line = reader.readLine()) != null){
                Log.d("MyActivity","Line: " + line);
                String[] tokens = line.split(",");
                MainBoard mb = new MainBoard();
                mb.setManufacturer(tokens[0]);
                mb.setName(tokens[1]);
                mb.setSocket(tokens[2]);
                mb.setChipset(tokens[3]);
                mb.setSize(tokens[4]);
                mb.setDDR(tokens[5]);
                mb.setMemoryMaxClock(tokens[6]);
                mb.setSlotAmount(tokens[7]);
                mb.setMemoryMaxSize(tokens[8]);
                mb.setStock(tokens[9]);
                mb.setPrice(Integer.parseInt(tokens[10]));
                mbList.add(mb);
                Log.d("MyActivity", "Just created: " + mb);
            }
        } catch (IOException e) {
            Log.d("MyActivity", "Error reading data file "+line,e);
            e.printStackTrace();
        }
    }

    private void readCPUData(){
        InputStream is = getResources().openRawResource(R.raw.cpucsv);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));

        String line ="";
        try{
            reader.readLine();
            while( (line = reader.readLine()) != null){
                Log.d("MyActivity","Line: " + line);
                String[] tokens = line.split(",");
                CPU cpu = new CPU();

                cpu.setManufacturer(tokens[0]);
                cpu.setName(tokens[1]);
                cpu.setPrice(Integer.parseInt(tokens[2]));//가격은 int타입으로 변경
                cpu.setSocket(tokens[3]);
                cpu.setProcess(tokens[4]);
                cpu.setCore(tokens[5]);
                cpu.setThread(tokens[6]);
                cpu.setBaseClock(tokens[7]);
                cpu.setMaxClock(tokens[8]);
                cpu.setL3Cache(tokens[9]);
                cpu.setTDP(tokens[10]);
                cpu.setMemoryStandard(tokens[11]);
                cpu.setMemoryMaxClock(tokens[12]);
                cpu.setInternalGraphic(tokens[13]);
                cpu.setBundleCooler(tokens[14]);
                cpu.setStock(tokens[15]);

                CPUList.add(cpu);
                Log.d("MyActivity", "Just created: " + cpu);

            }

        } catch (IOException e) {
            Log.d("MyActivity", "Error reading data file "+line,e);
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readCPUData();// CPU데이터 입력
        readMBData();// MainBoard 데이터 입력
        readRAMData();// ram 데이터 입력
        readGPUData();// gpu 데이터 입력
        readPWData();// power 데이터 입력
        readCaseData();// case 데이터 입력
        readCoolData();// cooler 데이터 입력
        readStorageData();// storage 데이터 입력

        Button desktopButton = findViewById(R.id.desktopBtn);
        desktopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent desktopIntent = new Intent(getApplicationContext(), UsageSelectionActivity.class);
                desktopIntent.putExtra("computerType", ComputerType.DESKTOP.toString());
                startActivity(desktopIntent);
            }
        });

        Button laptopButton = findViewById(R.id.laptopBtn);
        laptopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent laptopIntent = new Intent(getApplicationContext(), UsageSelectionActivity.class);
                laptopIntent.putExtra("computerType", ComputerType.LAPTOP.toString());
                startActivity(laptopIntent);
            }
        });
    }
}
