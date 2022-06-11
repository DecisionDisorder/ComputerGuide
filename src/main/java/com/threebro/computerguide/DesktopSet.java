package com.threebro.computerguide;

import android.content.Context;
import android.util.Log;

import com.threebro.computerguide.CSV.CPU;
import com.threebro.computerguide.CSV.Case;
import com.threebro.computerguide.CSV.Cooler;
import com.threebro.computerguide.CSV.GPU;
import com.threebro.computerguide.CSV.MainBoard;
import com.threebro.computerguide.CSV.Power;
import com.threebro.computerguide.CSV.RAM;
import com.threebro.computerguide.CSV.Storage;
import com.threebro.computerguide.CSV.Usage;
import com.threebro.computerguide.Combi.CPUMB;
import com.threebro.computerguide.Combi.FinalRes;
import com.threebro.computerguide.Combi.FinalTwo;
import com.threebro.computerguide.Combi.GPUPW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesktopSet {
    private Context context;

    private List<CPU> CPUList = new ArrayList<>();
    private List<MainBoard> mbList = new ArrayList<>();
    private List<RAM> RAMList = new ArrayList<>();
    private List<GPU> GPUList = new ArrayList<>();
    private List<Power> PWList = new ArrayList<>();
    private List<Case> CaseList = new ArrayList<>();
    private List<Cooler> CLList = new ArrayList<>();
    private List<Storage> STList = new ArrayList<>();
    private List<CPUMB> CMList = new ArrayList<>();
    private List<GPUPW> GPWList = new ArrayList<>();
    private List<FinalRes> FinalList = new ArrayList<>();
    private List<FinalTwo> Final2 = new ArrayList<>();

    public List<FinalTwo> getFinal2() {
        return Final2;
    }

    public List<CPU> getCPUList() {
        return CPUList;
    }

    public List<MainBoard> getMbList() {
        return mbList;
    }

    public List<RAM> getRAMList() {
        return RAMList;
    }

    public List<GPU> getGPUList() {
        return GPUList;
    }

    public List<Power> getPWList() {
        return PWList;
    }

    public List<Case> getCaseList() {
        return CaseList;
    }

    public List<Cooler> getCLList() {
        return CLList;
    }

    public List<Storage> getSTList() {
        return STList;
    }

    public DesktopSet(Context context) {
        this.context = context;
    }

    // READ From Csv File about strage data
    private void readStorageData(){
        InputStream is = context.getResources().openRawResource(R.raw.storagecsv);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));
        int i = 0;
        String line ="";
        try{
            reader.readLine();
            while( (line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                Storage st = new Storage();
                st.setName(tokens[0]);
                st.setType(tokens[1]);
                st.setPc(tokens[2]);
                st.setCapacity(tokens[3]);
                st.setPrice(Integer.parseInt(tokens[4]));
                st.setIndex(i);
                i++;
                STList.add(st);
            }
        } catch (IOException e) {
            Log.d("MyActivity", "Error reading data file "+line,e);
            e.printStackTrace();
        }
    }

    // READ From Csv File about cooler data
    private void readCoolData(){
        InputStream is = context.getResources().openRawResource(R.raw.coolcsv);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));
        int i = 0;
        String line ="";
        try{
            reader.readLine();
            while( (line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                Cooler cl = new Cooler();
                cl.setName(tokens[0]);
                cl.setType(tokens[1]);
                cl.setPrice(Integer.parseInt(tokens[2]));
                cl.setRPM(Integer.parseInt(tokens[3]));
                cl.setIndex(i);
                i++;
                CLList.add(cl);
            }
        } catch (IOException e) {
            Log.d("MyActivity", "Error reading data file "+line,e);
            e.printStackTrace();
        }
    }

    // READ From Csv File about Case data
    private void readCaseData(){
        InputStream is = context.getResources().openRawResource(R.raw.casecsv);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));
        int i=0;
        String line ="";
        try{
            reader.readLine();
            while( (line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                Case cs = new Case();
                cs.setName(tokens[0]);
                cs.setType(tokens[1]);
                cs.setSize(tokens[2]);
                cs.setPrice(Integer.parseInt(tokens[3]));
                cs.setIndex(i);
                i++;
                CaseList.add(cs);
            }
        } catch (IOException e) {
            Log.d("MyActivity", "Error reading data file "+line,e);
            e.printStackTrace();
        }
    }

    // READ From Csv File about Power data
    private void readPWData(){
        InputStream is = context.getResources().openRawResource(R.raw.powercsv);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));
        int i =0;
        String line ="";
        try{
            reader.readLine();
            while( (line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                Power pw = new Power();
                pw.setName(tokens[0]);
                pw.setType(tokens[1]);
                pw.setSpecification(Integer.parseInt(tokens[2]));
                pw.setCertification(tokens[3]);
                pw.setPrice(Integer.parseInt(tokens[4]));
                pw.setIndex(i);
                i++;

                PWList.add(pw);
            }
        } catch (IOException e) {
            Log.d("MyActivity", "Error reading data file "+line,e);
            e.printStackTrace();
        }
    }

    // READ From Csv File about Gpu data
    private void readGPUData(){
        InputStream is = context.getResources().openRawResource(R.raw.gpucsv);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));
        int i = 0;
        String line ="";
        try{
            reader.readLine();
            while( (line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                GPU gpu = new GPU();
                gpu.setName(tokens[0]);
                gpu.setSeries(tokens[1]);
                gpu.setPrice(Integer.parseInt(tokens[2]));
                gpu.setPower(Integer.parseInt(tokens[3]));
                gpu.setHDMI(Boolean.parseBoolean(tokens[4]));
                gpu.setDP(Boolean.parseBoolean(tokens[5]));
                gpu.setDVI(Boolean.parseBoolean(tokens[6]));
                gpu.setPriority(Integer.parseInt(tokens[7]));// 그래픽 우선순위 때문에
                gpu.setIndex(i);
                i++;
                GPUList.add(gpu);
            }
        } catch (IOException e) {
            Log.d("MyActivity", "Error reading data file "+line,e);
            e.printStackTrace();
        }
    }

    // READ From Csv File about Memory data
    private void readRAMData(){
        InputStream is = context.getResources().openRawResource(R.raw.ramcsv);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));
        int i=0;
        String line ="";
        try{
            reader.readLine();
            while( (line = reader.readLine()) != null){ ;

                String[] tokens = line.split(",");
                RAM rm = new RAM();
                rm.setName(tokens[0]);
                rm.setRamCapacity(tokens[1]);
                rm.setMemoryStandard(tokens[2]);
                rm.setMemoryClock(tokens[3]);
                rm.setStock(tokens[4]);
                rm.setPrice(Integer.parseInt(tokens[5]));
                rm.setIndex(i);
                i++;
                RAMList.add(rm);
            }
        } catch (IOException e) {
            Log.d("MyActivity", "Error reading data file "+line,e);
            e.printStackTrace();
        }
    }

    // // READ From Csv File about Mainboard data
    private void readMBData(){
        InputStream is = context.getResources().openRawResource(R.raw.mbcsv);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));
        int i = 0;
        String line ="";
        try{
            reader.readLine();
            while( (line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                MainBoard mb = new MainBoard();
                mb.setManufacturer(tokens[0]);
                mb.setName(tokens[1]);
                mb.setSocket(tokens[2]);
                mb.setChipset(tokens[3]);
                mb.setSize(tokens[4]);
                mb.setDDR(tokens[5]);
                mb.setMemoryMaxClock(tokens[6]);
                mb.setSlotAmount(Integer.parseInt(tokens[7]));
                mb.setMemoryMaxSize(tokens[8]);
                mb.setStock(tokens[9]);
                mb.setPrice(Integer.parseInt(tokens[10]));
                mb.setIndex(i);
                i++;
                mbList.add(mb);
            }
        } catch (IOException e) {
            Log.d("MyActivity", "Error reading data file "+line,e);
            e.printStackTrace();
        }
    }

    // // READ From Csv File about CPU data
    private void readCPUData(){
        InputStream is = context.getResources().openRawResource(R.raw.cpucsv);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));
        int i =0;
        String line ="";
        try{
            reader.readLine();
            while( (line = reader.readLine()) != null){
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
                cpu.setPriorityGaming(Integer.parseInt(tokens[16]));
                cpu.setPriorityTask(Integer.parseInt(tokens[17]));
                cpu.setIndex(i);
                i++;

                CPUList.add(cpu);
            }

        } catch (IOException e) {
            Log.d("MyActivity", "Error reading data file "+line,e);
            e.printStackTrace();
        }
    }

    public void SetContext(Context context) {
        this.context = context;
    }

    // Combination CPU + MAINBOARD by Socket ( if socket is same combine with one combination )
    public void MakeCPUMB(){
        for(int i=0;i<CPUList.size();i++){
            CPUMB CPU = new CPUMB();
            CPU.setCPU(CPUList.get(i));
            CMList.add(CPU);

            for(int j=0;j<mbList.size();j++){
                if(CMList.get(i).getCPU().getSocket().equals(mbList.get(j).getSocket())){
                    MainBoard mb = new MainBoard();
                    mb = mbList.get(j);
                    CMList.get(i).getMbList().add(mb);
                }
            }
        }
    }

    // Combination GPU + Power by Capacity ( if socket is same combine with one combination )
    public void MakeGPUPW(){
        for(int i=0; i<GPUList.size();i++){
            GPUPW GPU = new GPUPW();
            GPU.setGPU(GPUList.get(i));
            GPWList.add(GPU);

            for(int j=0;j<PWList.size();j++){
                if(GPWList.get(i).getGPU().getPower() <= PWList.get(j).getSpecification()){
                    Power pw = new Power();
                    pw = PWList.get(j);
                    GPWList.get(i).getPower().add(pw);
                }
            }

        }
    }

    // Returns a simple specification to be marked on the Product List
    public static String getSimpleString(FinalTwo estimate) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        String price = String.format(formatter.format(estimate.getPrice()));

        String simple = estimate.getCpu().getName() + "\n" + estimate.getRm().getRamCapacity() + " x" + estimate.getRm().getAmount()
                + "\n" + estimate.getGpu().getSeries() + "\n" + estimate.getSt().getCapacity() + "\n" + price + "원";

        return simple;
    }

    public void FinalCombinationGaming(int cpuPriority, int GpuPriority){// price는 원하는 가격대 cpuPriority,gpuPriority는 해당사양cpu 순서도
        FinalList = new ArrayList<>();
        //CPU부터 권장사양의 최저가 부터 가격을 설정후 gpu의 가격 설정
        for(int i=0;i<CMList.size();i++){
            if((CMList.get(i).getCPU().getPriorityGaming()) <= cpuPriority){//기존 만들었던 cpu조합에서 원하는 우선순위와 같을경우(cpu가 권장cpu 일경우
                for(int k=0;k<GPWList.size();k++){//그래픽 정보 입력
                    if(GpuPriority==0){//내장그래픽 사용시
                        FinalRes FR = new FinalRes(CaseList.get(1),STList.get(1),RAMList.get(4),CLList.get(17));// 기본삽입정보
                        FR.setCpu(CMList.get(i));// CPU를 만족하는거니깐 그대로 배열 입력
                        FR.setGp(GPWList.get(k));//FinalList에 GPU 입력
                        setInternalGraphic(FR, i, k);
                        //FR.setTotalPrice(FR.getCa().getPrice()+FR.getSt().getPrice()+FR.getRm().getPrice()+FR.getCl().getPrice()+FR.getCpu().getCPU().getPrice()+FR.getCpu().getMbList().get(0).getPrice()+FR.getGpu().getPower().get(0).getPrice());//둘다 메인보드와 파워는 0으로 설정 최저가 이기 때문에
                        FinalList.add(FR);
                    }
                    else if((GPWList.get(k).getGPU().getPriority()) <= GpuPriority ){//그래픽 필요가 안들어올시
                        FinalRes FR = new FinalRes(CaseList.get(1),STList.get(1),RAMList.get(4),CLList.get(17));// 기본삽입정보
                        FR.setCpu(CMList.get(i));// CPU를 만족하는거니깐 그대로 배열 입력
                        FR.setGp(GPWList.get(k));//FinalList에 GPU 입력
                        //FR.setTotalPrice(FR.getCa().getPrice()+FR.getSt().getPrice()+FR.getRm().getPrice()+FR.getCl().getPrice()+FR.getCpu().getCPU().getPrice()+FR.getCpu().getMbList().get(0).getPrice()+FR.getGpu().getGPU().getPrice()+FR.getGpu().getPower().get(0).getPrice());//둘다 메인보드와 파워는 0으로 설정 최저가 이기 때문에
                        FinalList.add(FR);
                    }
                }
            }
        }
        Collections.sort(FinalList,new PriceCompare());
    }

    // If they dont need gpu set graphic information to 내장긍래픽
    public void setInternalGraphic(FinalRes FR,int i,int k){
        FR.setCpu(CMList.get(i));// CPU를 만족하는거니깐 그대로 배열 입력
        FR.setGp(GPWList.get(k));//FinalList에 GPU 입력
        FR.getGpu().getGPU().setName("내장그래픽");
        FR.getGpu().getGPU().setPrice(0);
        FR.getGpu().getGPU().setSeries("내장그래픽");
        FR.getGpu().getGPU().setPower(0);
        FR.getGpu().getGPU().setPriority(0);
        //FR.setTotalPrice(FR.getCa().getPrice()+FR.getSt().getPrice()+FR.getRm().getPrice()+FR.getCl().getPrice()+FR.getCpu().getCPU().getPrice()+FR.getCpu().getMbList().get(0).getPrice()+FR.getGpu().getPower().get(0).getPrice());//둘다 메인보드와 파워는 0으로 설정 최저가 이기 때문에

    }

    public void inPutTemp(FinalTwo temp,int i){
        temp.setCa(FinalList.get(i).getCa());
        temp.setCl(FinalList.get(i).getCl());
        temp.setSt(FinalList.get(i).getSt());
        temp.setRm(FinalList.get(i).getRm());
        temp.setCpu(FinalList.get(i).getCpu().getCPU());
        temp.setGpu(FinalList.get(i).getGpu().getGPU());
        temp.setMb(FinalList.get(i).getCpu().getMbList().get(0));
        temp.setPw(FinalList.get(i).getGpu().getPower().get(0));
        temp.setPrice(temp.getCa().getPrice()+temp.getCl().getPrice()+temp.getSt().getPrice()+temp.getRm().getPrice()+temp.getCpu().getPrice()+temp.getMb().getPrice()+temp.getGpu().getPrice()+temp.getPw().getPrice());

    }

    public void FinalCombinationPrice(int priceLow, int priceHigh) {
        int brand=0;//amd랑 인텔 구분 주기위해서
        int j=0;
        int flag=0;
        Final2 = new ArrayList<>();
        for(int i=0; i<FinalList.size(); i++){//인텔에서 추출
            if(FinalList.get(i).getTotalPrice()>priceLow&& FinalList.get(i).getCpu().getCPU().getManufacturer().equals("Intel")&&FinalList.get(i).getTotalPrice()<=priceHigh && brand ==0){//가격보다 작을경우 리턴
                FinalTwo temp = new FinalTwo();
                inPutTemp(temp,i);
                Final2.add(temp);
                j++;
                brand++;
                flag=i;
            }
        }
        for(int i=0; i<FinalList.size(); i++){//AMD에서 추출
            if(FinalList.get(i).getTotalPrice()>=priceLow &&FinalList.get(i).getTotalPrice()<priceHigh && FinalList.get(i).getCpu().getCPU().getManufacturer().equals("AMD") && brand==1){//가격보다 작을경우 리턴
                FinalTwo temp = new FinalTwo();
                inPutTemp(temp,i);
                Final2.add(temp);
                brand++;
            }
        }
        if(brand==1){
            for(int i=flag+1; i<FinalList.size(); i++){//If tehy cant extract two compnay because component is small
                if(FinalList.get(i).getTotalPrice()>=priceLow&& FinalList.get(i).getCpu().getCPU().getManufacturer().equals("Intel")&&FinalList.get(i).getTotalPrice()<=priceHigh && brand ==1){//가격보다 작을경우 리턴
                    FinalTwo temp = new FinalTwo();
                    inPutTemp(temp,i);
                    Final2.add(temp);
                    j++;
                    brand++;
                }
            }
        }

    }

    public void readCsvData(){
        readCPUData();// CPU데이터 입력
        readMBData();// MainBoard 데이터 입력
        readRAMData();// ram 데이터 입력
        readGPUData();// gpu 데이터 입력
        readPWData();// power 데이터 입력
        readCaseData();// case 데이터 입력
        readCoolData();// cooler 데이터 입력
        readStorageData();// storage 데이터 입력
    }

    public void MakeComBi(){
        MakeCPUMB();
        MakeGPUPW();
    }

    // Only the price range on the estimate list is returned with a boolean array converted to true.
    public boolean[] getAvailablePriceList() {
        int length = 16;        // Number of budget ranges (500,000~2,000,000)
        int budgetMin = 500000; // Minimum budget range
        int interval = 100000;  // Interval of budget ranges
        boolean[] availablePriceList = new boolean[length];

        for(int i = 0; i < FinalList.size(); i++) {
            int price = FinalList.get(i).getTotalPrice();   // Get total price
            int priceIndex = (price - budgetMin) / interval;// Set price index according to price

            // Set price index upper bound
            if(priceIndex >= length)
                priceIndex = length - 1;

            // Set available price to be true
            availablePriceList[priceIndex] = true;
        }

        return availablePriceList;
    }

}