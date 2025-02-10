package com.phycaresolutions.mymap.pushnotifirebase;

import java.io.IOException;

public class ApplicationClass {
    public static void main(String[] args) throws IOException {

        /*System.out.println("Welcome to Developine");

        OkHttpClient okHttpClient = new OkHttpClient();
        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpPost postRequest = new HttpPost("https://fcm.googleapis.com/fcm/send");

        NotificationRequestModel notificationRequestModel = new NotificationRequestModel();
        NotificationData notificationData = new NotificationData();

        notificationData.setDetail("this is firebase push notification from java client (server)");
        notificationData.setTitle("Hello Firebase Push Notification");
        notificationRequestModel.setData(notificationData);
        notificationRequestModel.setTo("eHp6V2Wtr4I:APA91bFLHRuScumQB0lg4tPNStf-vFTGNy8RzHQHX72LiuYOuj546SpmfBZlZ6o5aJXvo0YsOwHc7MGwb7Dsx1er-FOqGHb0P0dvKSmcyhcufdPowhrtnNRmeirxu5kV2lUPDZ8SbZKz");


        Gson gson = new Gson();
        Type type = new TypeToken<NotificationRequestModel>() {
        }.getType();

        String json = gson.toJson(notificationRequestModel, type);

        StringEntity input = new StringEntity(json);
        input.setContentType("application/json");
        postRequest.addHeader("Authorization", "key=AAAA7-UgB34:APA91bFz75NiS8BUX9LJDeEk9kcCahpZ1-MYyFfQ3d2gof0vUAFKNcQCmmf_10aZkmZH6fVPvcF71kgHq5Ab4ol0mvhvVwgMn-HS_0i-2DipSxd2Jf2_NUTYtJMOVwUGP318ldDgBBvP");
        postRequest.setEntity(input);


        System.out.println("reques:" + json);


        HttpResponse response = httpClient.execute(postRequest);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        } else if (response.getStatusLine().getStatusCode() == 200) {

            System.out.println("response:" + EntityUtils.toString(response.getEntity()));

        }*/

    }

}
