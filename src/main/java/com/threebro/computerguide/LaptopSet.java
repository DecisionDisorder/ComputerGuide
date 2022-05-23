package com.threebro.computerguide;

import android.content.Context;
import android.util.Log;

import com.threebro.computerguide.CSV.Laptop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class LaptopSet {
    private Context context;

    private List<Laptop> LaptopList = new ArrayList<>();
    private List<Laptop> flaptop = new ArrayList<>();
    private List<Laptop> plaptop = new ArrayList<>();

    private void readLaptop(){
        InputStream is = context.getResources().openRawResource(R.raw.laptop);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));

        String line ="";
        try{
            reader.readLine();
            while( (line = reader.readLine()) != null){
                Log.d("MyActivity","Line: " + line);
                String[] tokens = line.split(",");
                Laptop lt = new Laptop();

                lt.setName(tokens[0]);
                lt.setComnum(Integer.parseInt(tokens[1]));
                lt.setCompany(tokens[2]);
                lt.setDisplay(Float.parseFloat(tokens[3]));
                lt.setClv(Integer.parseInt(tokens[4]));
                lt.setCpu1(tokens[5]);
                lt.setCpu2(tokens[6]);
                lt.setCpu3(tokens[7]);
                lt.setOs(tokens[8]);
                lt.setMemory(Float.parseFloat(tokens[9]));
                lt.setSsd(Float.parseFloat(tokens[10]));
                lt.setWeight(Float.parseFloat(tokens[11]));
                lt.setGlv(Integer.parseInt(tokens[12]));
                lt.setGraphic(tokens[13]);
                lt.setPrice(Float.parseFloat(tokens[14]));

                LaptopList.add(lt);
            }

        } catch (IOException e) {
            Log.d("MyActivity", "Error reading data file "+line,e);
            e.printStackTrace();
        }
    }

    public double Selectprice(int clevel, int glevel, float dsize, int company, double wt){
        double price=0;

        for(int i=0; i<LaptopList.size(); i++){
            if(LaptopList.get(i).getClv()>=clevel&&LaptopList.get(i).getGlv()>=glevel&&LaptopList.get(i).getComnum()==company
                    &&LaptopList.get(i).getWeight()<=wt&&LaptopList.get(i).getDisplay()>=dsize){
                Laptop lt = new Laptop();
                lt = LaptopList.get(i);
                plaptop.add(lt);
            }
        }

        for(int i=0; i<plaptop.size(); i++){
            if(price==0){
                price = plaptop.get(i).getPrice();
            }
            if(plaptop.get(i).getPrice()<price){
                price = plaptop.get(i).getPrice();
            }
        }
        return price;
    }

    public void Selectlaptop(int clevel, int glevel, float dsize, int company, double wt, double price){
        for(int i=0; i<LaptopList.size(); i++){
            if(LaptopList.get(i).getClv()>=clevel&&LaptopList.get(i).getGlv()>=glevel&&LaptopList.get(i).getComnum()==company
                    &&LaptopList.get(i).getWeight()<=wt&&LaptopList.get(i).getPrice()<=price&&LaptopList.get(i).getDisplay()>=dsize){
                Laptop lt = new Laptop();
                lt = LaptopList.get(i);
                flaptop.add(lt);
            }
        }
    }
}
