package com.threebro.computerguide;

import android.content.Context;
import android.util.Log;

import com.threebro.computerguide.CSV.Laptop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class LaptopSet {
    private Context context;

    private List<Laptop> LaptopList = new ArrayList<>();
    private List<Laptop> flaptop = new ArrayList<>();
    private List<Laptop> plaptop = new ArrayList<>();

    public LaptopSet(Context context) {
        this.context = context;
    }

    public void readLaptop(){
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
                lt.setPrice(Integer.parseInt(tokens[14]));

                LaptopList.add(lt);
            }

        } catch (IOException e) {
            Log.d("MyActivity", "Error reading data file "+line,e);
            e.printStackTrace();
        }
    }

    public int Selectprice(String usage, String dusage, float dsize, int company, double wt){
        int price=0;
        int clevel=1;
        int glevel=1;
        int ram=8;

        if(usage.equals("game")){
            if(dusage.equals("Low Spec")){
                clevel=1;
                glevel=2;
                ram=8;
            }
            else if(dusage.equals("Middle spec")){
                clevel=2;
                glevel=3;
                ram=16;
            }
            else if(dusage.equals("Middle high spec")){
                clevel=3;
                glevel=3;
                ram=16;
            }
            else{
                clevel=4;
                glevel=4;
                ram=16;
            }
        }

        else if(usage.equals("professional")){
            if(dusage.equals("Video Editing")){
                clevel=3;
                glevel=3;
                ram=8;
            }
            else if(dusage.equals("Coding")){
                clevel=2;
                glevel=3;
                ram=16;
            }
            else if(dusage.equals("2D design")){
                clevel=2;
                glevel=3;
                ram=16;
            }
            else{
                clevel=4;
                glevel=4;
                ram=16;
            }
        }

        else{
            clevel=1;
            glevel=1;
            ram=4;
        }

        plaptop = new ArrayList<>();
        for(int i=0; i<LaptopList.size(); i++){
            if(LaptopList.get(i).getClv()>=clevel&&LaptopList.get(i).getGlv()>=glevel&&LaptopList.get(i).getComnum()==company
                    &&LaptopList.get(i).getWeight()<=wt&&LaptopList.get(i).getDisplay()>=dsize&&LaptopList.get(i).getMemory()>=ram){
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

    public boolean[] getAvailablePriceList(int price) {
        int length = 16;
        int budgetMin = 500000;
        int interval = 100000;
        boolean[] availablePriceList = new boolean[length];

        int priceIndex = (price - budgetMin) / interval;

        if(priceIndex >= length)
            priceIndex = length - 1;

        if(priceIndex >= 0) {
            while (priceIndex < length) {
                availablePriceList[priceIndex] = true;
                priceIndex++;
            }
        }
        return availablePriceList;
    }

    public void Selectlaptop(String usage, String dusage, float dsize, int company, double wt, int price){
        int clevel=1;
        int glevel=1;
        int ram=8;

        if(usage.equals("game")){
            if(dusage.equals("Low Spec")){
                clevel=1;
                glevel=2;
                ram=8;
            }
            else if(dusage.equals("Middle spec")){
                clevel=2;
                glevel=3;
                ram=16;
            }
            else if(dusage.equals("Middle high spec")){
                clevel=3;
                glevel=3;
                ram=16;
            }
            else{
                clevel=4;
                glevel=4;
                ram=16;
            }
        }

        else if(usage.equals("professional")){
            if(dusage.equals("Video Editing")){
                clevel=3;
                glevel=3;
                ram=8;
            }
            else if(dusage.equals("Coding")){
                clevel=2;
                glevel=3;
                ram=16;
            }
            else if(dusage.equals("2D design")){
                clevel=2;
                glevel=3;
                ram=16;
            }
            else{
                clevel=4;
                glevel=4;
                ram=16;
            }
        }

        else{
            clevel=1;
            glevel=1;
            ram=4;
        }

        flaptop = new ArrayList<>();
        for(int i=0; i<LaptopList.size(); i++){
            if(LaptopList.get(i).getClv()>=clevel&&LaptopList.get(i).getGlv()>=glevel&&LaptopList.get(i).getComnum()==company
                    &&LaptopList.get(i).getWeight()<=wt&&LaptopList.get(i).getDisplay()>=dsize&&LaptopList.get(i).getMemory()>=ram){
                if(price<2000000){
                    if(LaptopList.get(i).getPrice()<=(price+100000)&&LaptopList.get(i).getPrice()>=price){
                        Laptop lt = new Laptop();
                        lt = LaptopList.get(i);
                        flaptop.add(lt);
                    }
                }
                else{
                    if(LaptopList.get(i).getPrice()>=price){
                        Laptop lt = new Laptop();
                        lt = LaptopList.get(i);
                        flaptop.add(lt);
                    }
                }
            }
        }
    }
    public String getSimpleString(int index) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        String price = String.format(formatter.format(flaptop.get(index).getPrice()));
        String name = flaptop.get(index).getName();
        name = name.substring(0,12);

        String simple = name + "..\n" + flaptop.get(index).getCpu2()+ "\n" + flaptop.get(index).getGraphic() + "\n" + price + "원";

        return simple;
    }

    public List<Laptop> getFlaptop() {
        return flaptop;
    }
}