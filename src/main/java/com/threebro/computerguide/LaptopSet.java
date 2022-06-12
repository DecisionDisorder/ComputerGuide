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

    // read data from laptop.csv
    public void readLaptop(){
        InputStream is = context.getResources().openRawResource(R.raw.laptop);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));
        int i=0;
        String line ="";
        try{
            reader.readLine();
            while( (line = reader.readLine()) != null){
                //Log.d("MyActivity","Line: " + line);
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
                lt.setIndex(i);
                i++;

                LaptopList.add(lt);
            }

        } catch (IOException e) {
            Log.d("MyActivity", "Error reading data file "+line,e);
            e.printStackTrace();
        }
    }

    // Laptop set selection algorithm to get available price range
    // Use dynamic activity
    public int SelectPrice(String usage, String dusage, float dsize, int company, double wt){
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

    // Only the price range on the estimate list is returned with a boolean array converted to true.
    public boolean[] getAvailablePriceList() {
        int length = 16;        // Number of budget ranges (500,000~2,000,000)
        int budgetMin = 500000; // Minimum budget range
        int interval = 100000;  // Interval of budget ranges
        boolean[] availablePriceList = new boolean[length];

        for(int i = 0; i < plaptop.size(); i++) {
            int price = plaptop.get(i).getPrice();           // Get total price
            int priceIndex = (price - budgetMin) / interval; // Set price index according to price

            // Set price index upper bound
            if(priceIndex >= length)
                priceIndex = length - 1;

            // Set available price to be true
            availablePriceList[priceIndex] = true;
        }

        return availablePriceList;
    }

    //Laptop select algorithm by user selection ( usage, display size, company, weight, price )
    public void SelectLaptop(String usage, String dusage, float dsize, int company, double wt, int price){
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
            if(LaptopList.get(i).getClv() >= clevel && LaptopList.get(i).getGlv() >= glevel&&LaptopList.get(i).getComnum() == company
                    && LaptopList.get(i).getWeight() <= wt && LaptopList.get(i).getDisplay() >= dsize&&LaptopList.get(i).getMemory() >= ram){
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

    // Returns a simple specification to be marked on the Product List
    public static String getSimpleString(Laptop laptop) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        String price = String.format(formatter.format(laptop.getPrice()));
        String name = laptop.getName();
        name = name.substring(0,12);

        String simple = name + "..\n" + laptop.getCpu2()+ "\n" + laptop.getGraphic() + "\n" + price + "원";

        return simple;
    }

    public List<Laptop> getFlaptop() {
        return flaptop;
    }

    public List<Laptop> getLaptopList() {
        return LaptopList;
    }

}