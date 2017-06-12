import java.io.*;
import java.util.*;
public class DataParser{
  public static void main(String[] args) throws IOException{

    String filename = "attendance.txt";
    BufferedReader bf = new BufferedReader(new FileReader(filename));
    String emailAddress;
    Map<String,ArrayList<String>> attendanceMap = new TreeMap<String,ArrayList<String>>();
    String name = "";
    String company = "";

    while((emailAddress = bf.readLine()) != null){
      //split by new line and if is name then save
      String[] companyNameSplit = emailAddress.split("\n");

      for(String el : companyNameSplit){
        // System.out.println(el);
        Email email = new Email(el);
        name = email.getName();
        company = email.getCompany();
        if(attendanceMap.containsKey(company)){
          ArrayList<String> referenceForValues = attendanceMap.get(company);
          referenceForValues.add(name);
          attendanceMap.put(company, referenceForValues);
        //the map does not contain a key so make a new arraylist for the key
        }else{
          //make a new arraylist to store the values in
          ArrayList<String> tempList = new ArrayList<String>();
          //add the name to the temp list
          tempList.add(name);
          //for the company key that exists, associate the value of name to that company
          attendanceMap.put(company,tempList);
        }
      }
    }
    //for each map
    for(Map.Entry<String,ArrayList<String>> entry : attendanceMap.entrySet()){
      String key = entry.getKey();
      ArrayList<String> value = entry.getValue();
      Collections.sort(value);
      for(int i = 0; i < value.size(); i++){
        System.out.println(key+"\t" + value.get(i));

      }
    }
    //================================optional assignment=======================
    System.out.println("");
    BufferedWriter writeToFile = null;
    try{
      File parsedCompanyAndName = new File("parsedAttendance.csv");
      writeToFile = new BufferedWriter(new FileWriter(parsedCompanyAndName));
      System.out.println("writing to csv " +parsedCompanyAndName);
      for(Map.Entry<String,ArrayList<String>> entry : attendanceMap.entrySet()){
        String key = entry.getKey();
        ArrayList<String> value = entry.getValue();
        Collections.sort(value);
        for(int i = 0; i < value.size(); i++){
          writeToFile.write(key+","+value.get(i)+"\n");
        }
      }
    }catch(IOException e){
      e.printStackTrace();
    }finally{
      if(writeToFile != null){
        writeToFile.close();
      }
    }
    //==========================histogram=======================================
    System.out.println("~~~~~HiStOgRamMiFiEd~~~~");
    String histoBar = "";
    for(Map.Entry<String, ArrayList<String>> singleComp : attendanceMap.entrySet()){
      String companyName = singleComp.getKey();
      ArrayList<String> names = singleComp.getValue();
      // for(String name1 : names){
      //   histoBar = histoBar + "*";
      // }
      int numOfNames = names.size();
      while(numOfNames != 0){
        histoBar = histoBar + "*";
        numOfNames --;
      }

      System.out.println(companyName +"\t"+"|"+ histoBar);
      histoBar = "";
    }
    //=================================WAS BILLY BOY THERE?????=================
    if(attendanceMap.containsKey("msft")){
      String ms = "msft";
//remember there is no getValue, get retrives the values specified at the given key
      ArrayList<String> msNames = attendanceMap.get(ms);
      if(msNames.contains("bill.gates")){
        System.out.println("Bill Gates was there ^_^ ");
      }else{
        System.out.println("Bill Gates was not there :<");
      }
    }
  }
}
