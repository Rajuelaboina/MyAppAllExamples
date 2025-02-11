package com.phycaresolutions.mymap.checknetworkstate;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

public class CheckNetWork {

    public static Boolean getNetWorkState(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network network = connectivityManager.getActiveNetwork();
        NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (capabilities!=null){
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) return true;
                else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))return true;
                else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) return true;
                else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) return true;
                else return false;
            }
        }
       else {
           try{
               NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
               return networkInfo !=null && networkInfo.isConnected();
           }catch (Exception e){
               return false;
           }
        }
        return false;
    }

}
