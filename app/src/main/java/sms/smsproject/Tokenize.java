package sms.smsproject;

import java.util.Random;

/**
 * Created by sanderson on 02/05/2015.
 */
public class Tokenize {

    public String getToken(){
        int a = getRandonInt();
        int b = getRandonInt();
        int c = getRandonInt();

        int d = keyFunc(a,b,c);

        String token = d + "," + c + "," + b + "," + a;

        return token;
    }

    public int getRandonInt(){
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(10);
    }

    public boolean isValid(String message) {
        String[] parts =
                message.split(",");

        int d = Integer.parseInt(parts[1]);
        int c = Integer.parseInt(parts[2]);
        int b = Integer.parseInt(parts[3]);
        int a = Integer.parseInt(parts[4]);

        int x = keyFunc(a,b,c);

        if (x==d){
            return true;
        }

        return false;
    }

    private int keyFunc(int a, int b, int c){
        return (int) (Math.pow(a,2)+b-c);

    }
}
