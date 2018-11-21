package com.qunar.task1.fifth;

import java.util.List;
import java.util.Scanner;

/**
 * @author zgm
 * @description
 * @date 2018/11/22 1:29
 */
public class LinuxShellEntrance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {

            //当next为true时表示该输入命令不在考虑范围，跳到下一个命令
            boolean next=false;

            String input = scanner.nextLine();
            String[] orders = input.split("\\|");

            AbstractOrder rootOrder = new RootOrder();
            AbstractOrder currentOrder=rootOrder;

            for(int i=0;i<orders.length;i++) {

                if(orders[i].trim().startsWith("cat")){
                    AbstractOrder catOrder=new CatOrder(orders[i].trim());
                    currentOrder.nextOrder=catOrder;
                    currentOrder=catOrder;
                }else if(orders[i].trim().startsWith("grep")){
                    AbstractOrder grepOrder=new GrepOrder(orders[i].trim());
                    currentOrder.nextOrder=grepOrder;
                    currentOrder=grepOrder;
                }else if(orders[i].trim().startsWith("wc")){
                    AbstractOrder wcOrder=new WcOrder(orders[i].trim());
                    currentOrder.nextOrder=wcOrder;
                    currentOrder=wcOrder;
                }else {
                    System.out.println("该命令不在题目范围");
                    next=true;
                }
            }
            if(next){
                continue;
            }
            Result result = rootOrder.executeOrder();
            if(result.getIntResult()!=null){
                System.out.println(result.getIntResult());
            }else if(result.getListResult()!=null){
                List<String> listResult =result.getListResult();
                for(String str:listResult){
                    System.out.println(str);
                }
            } else if(result.getStrResult()!=null){
                System.out.println(result.getStrResult());
            }else {
                System.out.println("待扩展4");
            }
        }
    }
}
